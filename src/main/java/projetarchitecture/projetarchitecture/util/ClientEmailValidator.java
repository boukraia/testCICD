package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientEmailValidator implements ClientValidator {
    @Override
    public void validate(Client client) {
        if (!client.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
