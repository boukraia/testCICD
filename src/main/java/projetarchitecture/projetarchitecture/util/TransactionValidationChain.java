package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Validation chain for transactions.
 */
@Component
public class TransactionValidationChain {
    private final List<Validator<Transaction>> validators = new ArrayList<>();

    public void addValidator(Validator<Transaction> validator) {
        validators.add(validator);
    }

    public void validate(Transaction transaction) {
        for (Validator<Transaction> validator : validators) {
            validator.validate(transaction);
        }
    }
}
