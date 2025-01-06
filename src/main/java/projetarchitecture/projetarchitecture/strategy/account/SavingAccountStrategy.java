package projetarchitecture.projetarchitecture.strategy.account;

import projetarchitecture.projetarchitecture.model.Account;
import org.springframework.stereotype.Component;

/**
 * Implements behavior specific to Savings accounts.
 */
@Component
public class SavingAccountStrategy implements AccountTypeStrategy {
    private static final double MIN_BALANCE = 100.0;

    @Override
    public void processAccountBehavior(Account account, double amount) {
        if (account.getBalance() - amount < MIN_BALANCE) {
            throw new IllegalArgumentException("Savings account must maintain a minimum balance of " + MIN_BALANCE);
        }
    }
}
