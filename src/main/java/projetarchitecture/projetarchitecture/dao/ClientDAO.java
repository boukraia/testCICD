package projetarchitecture.projetarchitecture.dao;

import projetarchitecture.projetarchitecture.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDAO extends MongoRepository<Client, String> {
}
