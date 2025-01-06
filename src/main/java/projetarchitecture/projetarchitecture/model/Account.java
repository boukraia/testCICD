package projetarchitecture.projetarchitecture.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "accounts")
public class Account {
    @Id
    private String id;

    @NotEmpty(message = "Account number is required")
    private String accountNumber;

    @NotNull(message = "Balance cannot be null")
    @Min(value = 0, message = "Balance cannot be negative")
    private Double balance;

    @NotEmpty(message = "Account type is required")
    private String type; // e.g., "Savings", "Checking"

    @NotEmpty(message = "Client ID is required")
    private String clientId; // Foreign key to Client
}
