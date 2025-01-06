package projetarchitecture.projetarchitecture.strategy.transaction;

import projetarchitecture.projetarchitecture.model.Transaction;

/**
 * Strategy interface for transaction-specific behavior.
 */
public interface TransactionStrategy {
    void process(Transaction transaction);
}
