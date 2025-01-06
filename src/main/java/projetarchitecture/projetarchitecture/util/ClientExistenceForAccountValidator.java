package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.dao.ClientDAO;
import projetarchitecture.projetarchitecture.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator to ensure the client associated with an account exists.
 */
@Component
public class ClientExistenceForAccountValidator implements Validator<Account> {
    @Autowired
    private ClientDAO clientDAO;

    @Override
    public void validate(Account account) {
        if (!clientDAO.existsById(account.getClientId())) {
            throw new IllegalArgumentException("Client with ID: " + account.getClientId() + " does not exist");
        }
    }
}
