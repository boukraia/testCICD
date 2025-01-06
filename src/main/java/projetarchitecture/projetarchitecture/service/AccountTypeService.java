package projetarchitecture.projetarchitecture.service;

import projetarchitecture.projetarchitecture.strategy.account.AccountTypeStrategy;
import projetarchitecture.projetarchitecture.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service to handle account-specific behaviors using the Strategy Pattern.
 */
@Service
public class AccountTypeService {

    private final Map<String, AccountTypeStrategy> strategyMap;

    /**
     * Injects strategies for different account types.
     * The strategies are automatically injected as beans based on their @Component annotation.
     * @param strategyMap Map of account type strategies, keyed by account type (e.g., "Savings", "Checking").
     */
    @Autowired
    public AccountTypeService(Map<String, AccountTypeStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    /**
     * Applies the appropriate behavior for the given account type.
     * @param account The account to process.
     * @param amount The amount involved in the transaction.
     */
    public void applyAccountTypeBehavior(Account account, double amount) {
        AccountTypeStrategy strategy = strategyMap.get(account.getType());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported account type: " + account.getType());
        }
        strategy.processAccountBehavior(account, amount);
    }
}

