package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.model.Account;
import org.springframework.stereotype.Component;

/**
 * Validator to ensure an account balance is not negative.
 */
@Component
public class AccountBalanceValidator implements Validator<Account> {
    @Override
    public void validate(Account account) {
        if (account.getBalance() < 0) {
            throw new IllegalArgumentException("Account balance cannot be negative");
        }
    }
}
