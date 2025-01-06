package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.dao.ClientDAO;
import projetarchitecture.projetarchitecture.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator to check if a client already exists.
 */
@Component
public class ClientExistenceValidator implements Validator<Client> {
    @Autowired
    private ClientDAO clientDAO;

    @Override
    public void validate(Client client) {
        if (clientDAO.existsById(client.getId())) {
            throw new IllegalArgumentException("Client already exists with ID: " + client.getId());
        }
    }
}
