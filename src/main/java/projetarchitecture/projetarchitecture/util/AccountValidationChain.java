package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.model.Account;
import org.springframework.stereotype.Component;

/**
 * Validation chain for account-specific validations.
 */
@Component
public class AccountValidationChain extends ValidationChain<Account> {
    // Specific to Account validations
}
