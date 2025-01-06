package projetarchitecture.projetarchitecture.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for transaction responses.
 */
@Data
public class TransactionResponseDTO {
    private String id;
    private String type;
    private Double amount;

    private String accountId;
    private String destinationAccountId;

    private LocalDateTime timestamp;
}
