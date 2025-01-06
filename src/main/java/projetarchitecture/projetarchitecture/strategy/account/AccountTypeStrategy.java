package projetarchitecture.projetarchitecture.strategy.account;

import projetarchitecture.projetarchitecture.model.Account;

/**
 * Strategy interface for account-specific behavior.
 */
public interface AccountTypeStrategy {
    void processAccountBehavior(Account account, double amount);
}
