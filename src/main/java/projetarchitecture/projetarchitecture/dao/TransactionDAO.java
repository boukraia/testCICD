package projetarchitecture.projetarchitecture.dao;

import projetarchitecture.projetarchitecture.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends MongoRepository<Transaction, String> {
    List<Transaction> findByAccountId(String accountId);
}
