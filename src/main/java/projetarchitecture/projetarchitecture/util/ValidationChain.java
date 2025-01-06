package projetarchitecture.projetarchitecture.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic validation chain for managing multiple validators of a specific type.
 * @param <T> The type of object to validate.
 */
@Component
public class ValidationChain<T> {
    private final List<Validator<T>> validators = new ArrayList<>();

    /**
     * Adds a validator to the chain.
     * @param validator The validator to add.
     */
    public void addValidator(Validator<T> validator) {
        validators.add(validator);
    }

    /**
     * Validates the given object using all validators in the chain.
     * @param object The object to validate.
     */
    public void validate(T object) {
        for (Validator<T> validator : validators) {
            validator.validate(object);
        }
    }
}

