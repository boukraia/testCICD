package projetarchitecture.projetarchitecture.strategy.transaction;

import projetarchitecture.projetarchitecture.dao.AccountDAO;
import projetarchitecture.projetarchitecture.model.Account;
import projetarchitecture.projetarchitecture.model.Transaction;
import projetarchitecture.projetarchitecture.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles withdrawal transactions.
 */
@Component
public class WithdrawalStrategy implements TransactionStrategy {
    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountTypeService accountTypeService;

    @Override
    public void process(Transaction transaction) {
        Account account = accountDAO.findById(transaction.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + transaction.getAccountId()));

        // Apply account-specific rules before processing
        accountTypeService.applyAccountTypeBehavior(account, transaction.getAmount());

        if (account.getBalance() < transaction.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in account: " + transaction.getAccountId());
        }

        account.setBalance(account.getBalance() - transaction.getAmount());
        accountDAO.save(account);
    }
}
