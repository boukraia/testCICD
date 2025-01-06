package projetarchitecture.projetarchitecture.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * MongoDB Entity for transactions.
 */
@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    private String type; // e.g., "Deposit", "Withdrawal", "Transfer"
    private Double amount;

    private String accountId; // For deposits/withdrawals
    private String destinationAccountId; // For transfers

    private LocalDateTime timestamp;
}
