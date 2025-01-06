package projetarchitecture.projetarchitecture.service;

import projetarchitecture.projetarchitecture.dao.AccountDAO;
import projetarchitecture.projetarchitecture.kafka.producers.KafkaProducerService;
import projetarchitecture.projetarchitecture.kafka.events.AccountCreatedEvent;
import projetarchitecture.projetarchitecture.model.Account;
import projetarchitecture.projetarchitecture.util.AccountValidationChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountValidationChain accountValidationChain;

    @Autowired
    private KafkaProducerService kafkaProducerService; // Inject KafkaProducerService for event publishing

    public List<Account> getAllAccounts() {
        return accountDAO.findAll();
    }

    public Account getAccountById(String id) {
        return accountDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    /**
     * Creates a new account after validating it.
     * Publishes an AccountCreatedEvent to Kafka upon successful creation.
     *
     * @param account The account to create.
     * @return The created account.
     */
    public Account createAccount(Account account) {
        // Apply all account-specific validations
        accountValidationChain.validate(account);

        // Save the account to the database
        Account savedAccount = accountDAO.save(account);

        // Publish the AccountCreatedEvent to Kafka
        AccountCreatedEvent event = new AccountCreatedEvent();
        event.setAccountId(savedAccount.getId());
        event.setAccountType(savedAccount.getType());
        event.setInitialBalance(savedAccount.getBalance());
        kafkaProducerService.publish("account-created", event);

        return savedAccount;
    }

    public List<Account> getAccountsByClientId(String clientId) {
        return accountDAO.findByClientId(clientId);
    }

    public void deleteAccount(String id) {
        accountDAO.deleteById(id);
    }
}
