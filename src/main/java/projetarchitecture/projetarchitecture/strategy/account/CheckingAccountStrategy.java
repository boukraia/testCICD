package projetarchitecture.projetarchitecture.strategy.account;

import projetarchitecture.projetarchitecture.model.Account;
import org.springframework.stereotype.Component;

/**
 * Implements behavior specific to Checking accounts.
 */
@Component
public class CheckingAccountStrategy implements AccountTypeStrategy {
    @Override
    public void processAccountBehavior(Account account, double amount) {
        // Checking accounts may allow overdrafts or other specific rules
        // No restrictions for this example
    }
}
