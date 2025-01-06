package projetarchitecture.projetarchitecture.service;

import projetarchitecture.projetarchitecture.dao.TransactionDAO;
import projetarchitecture.projetarchitecture.dto.TransactionRequestDTO;
import projetarchitecture.projetarchitecture.dto.TransactionResponseDTO;
import projetarchitecture.projetarchitecture.kafka.producers.KafkaProducerService;
import projetarchitecture.projetarchitecture.kafka.events.TransactionCompletedEvent;
import projetarchitecture.projetarchitecture.mapper.TransactionMapper;
import projetarchitecture.projetarchitecture.model.Transaction;
import projetarchitecture.projetarchitecture.strategy.transaction.DepositStrategy;
import projetarchitecture.projetarchitecture.strategy.transaction.TransactionStrategy;
import projetarchitecture.projetarchitecture.strategy.transaction.TransferStrategy;
import projetarchitecture.projetarchitecture.strategy.transaction.WithdrawalStrategy;
import projetarchitecture.projetarchitecture.util.TransactionValidationChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionValidationChain validationChain;

    @Autowired
    private KafkaProducerService kafkaProducerService; // Inject KafkaProducerService

    private final Map<String, TransactionStrategy> strategyMap = new HashMap<>();

    @Autowired
    public TransactionService(DepositStrategy depositStrategy, WithdrawalStrategy withdrawalStrategy, TransferStrategy transferStrategy) {
        strategyMap.put("Deposit", depositStrategy);
        strategyMap.put("Withdrawal", withdrawalStrategy);
        strategyMap.put("Transfer", transferStrategy);
    }

    public TransactionResponseDTO createTransaction(TransactionRequestDTO requestDTO) {
        // Convert DTO to Transaction entity
        Transaction transaction = transactionMapper.toEntity(requestDTO);

        // Validate transaction
        validationChain.validate(transaction);

        // Fetch the appropriate strategy and process the transaction
        TransactionStrategy strategy = strategyMap.get(transaction.getType());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported transaction type: " + transaction.getType());
        }
        strategy.process(transaction);

        // Save transaction
        transaction = transactionDAO.save(transaction);

        // Publish TransactionCompletedEvent
        publishTransactionCompletedEvent(transaction);

        // Return response DTO
        return transactionMapper.toDTO(transaction);
    }

    private void publishTransactionCompletedEvent(Transaction transaction) {
        TransactionCompletedEvent event = new TransactionCompletedEvent();
        event.setTransactionId(transaction.getId());
        event.setType(transaction.getType());
        event.setAmount(transaction.getAmount());
        event.setAccountId(transaction.getAccountId());
        event.setDestinationAccountId(transaction.getDestinationAccountId()); // For transfers
        kafkaProducerService.publish("transaction-completed", event);
    }
}
