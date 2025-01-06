package projetarchitecture.projetarchitecture.strategy.transaction;

import projetarchitecture.projetarchitecture.dao.AccountDAO;
import projetarchitecture.projetarchitecture.model.Account;
import projetarchitecture.projetarchitecture.model.Transaction;
import projetarchitecture.projetarchitecture.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles transfer transactions.
 */
@Component
public class TransferStrategy implements TransactionStrategy {
    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountTypeService accountTypeService;

    @Override
    public void process(Transaction transaction) {
        Account sourceAccount = accountDAO.findById(transaction.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found: " + transaction.getAccountId()));

        Account destinationAccount = accountDAO.findById(transaction.getDestinationAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found: " + transaction.getDestinationAccountId()));

        // Apply source account-specific rules before processing
        accountTypeService.applyAccountTypeBehavior(sourceAccount, transaction.getAmount());

        if (sourceAccount.getBalance() < transaction.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in source account: " + transaction.getAccountId());
        }

        // Deduct from source account
        sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
        accountDAO.save(sourceAccount);

        // Add to destination account
        destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());
        accountDAO.save(destinationAccount);
    }
}
