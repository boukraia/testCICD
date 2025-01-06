package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.dao.AccountDAO;
import projetarchitecture.projetarchitecture.model.Account;
import projetarchitecture.projetarchitecture.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator to ensure sufficient balance for withdrawals or transfers.
 */
@Component
public class SufficientBalanceValidator implements Validator<Transaction> {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void validate(Transaction transaction) {
        if ("Withdrawal".equals(transaction.getType()) || "Transfer".equals(transaction.getType())) {
            Account account = accountDAO.findById(transaction.getAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Account not found: " + transaction.getAccountId()));
            if (account.getBalance() < transaction.getAmount()) {
                throw new IllegalArgumentException("Insufficient balance in account: " + transaction.getAccountId());
            }
        }
    }
}
