package projetarchitecture.projetarchitecture.service;

import projetarchitecture.projetarchitecture.dao.ClientDAO;
import projetarchitecture.projetarchitecture.model.Client;
import projetarchitecture.projetarchitecture.util.ClientValidationChain;
import projetarchitecture.projetarchitecture.util.ValidationChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientDAO clientDAO;




    @Autowired
    private ClientValidationChain clientValidationChain;

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public Client getClientById(String id) {
        return clientDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    /**
     * Creates a new client after validating it.
     * @param client The client to create.
     * @return The created client.
     */
    public Client createClient(Client client) {
        clientValidationChain.validate(client); // Apply all client-specific validations
        return clientDAO.save(client);
    }
    public void deleteClient(String id) {
        clientDAO.deleteById(id);
    }
}
