package projetarchitecture.projetarchitecture.dao;

import projetarchitecture.projetarchitecture.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO extends MongoRepository<Account, String> {
    List<Account> findByClientId(String clientId); // Find all accounts for a specific client
}
