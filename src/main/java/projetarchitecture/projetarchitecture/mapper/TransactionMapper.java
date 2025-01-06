package projetarchitecture.projetarchitecture.mapper;

import projetarchitecture.projetarchitecture.dto.TransactionRequestDTO;
import projetarchitecture.projetarchitecture.dto.TransactionResponseDTO;
import projetarchitecture.projetarchitecture.model.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionRequestDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setAccountId(dto.getAccountId());
        transaction.setDestinationAccountId(dto.getDestinationAccountId());
        transaction.setTimestamp(LocalDateTime.now());
        return transaction;
    }

    public TransactionResponseDTO toDTO(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(transaction.getId());
        dto.setType(transaction.getType());
        dto.setAmount(transaction.getAmount());
        dto.setAccountId(transaction.getAccountId());
        dto.setDestinationAccountId(transaction.getDestinationAccountId());
        dto.setTimestamp(transaction.getTimestamp());
        return dto;
    }
}
