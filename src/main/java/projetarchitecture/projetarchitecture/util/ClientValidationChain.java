package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.model.Client;
import org.springframework.stereotype.Component;

/**
 * Validation chain for client-specific validations.
 */
@Component
public class ClientValidationChain extends ValidationChain<Client> {
    // Specific to Client validations
}
