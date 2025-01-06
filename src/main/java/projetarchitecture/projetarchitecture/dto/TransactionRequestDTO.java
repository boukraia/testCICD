package projetarchitecture.projetarchitecture.dto;

import lombok.Data;

/**
 * DTO for transaction requests.
 */
@Data
public class TransactionRequestDTO {
    private String type; // "Deposit", "Withdrawal", "Transfer"
    private Double amount;

    private String accountId; // For deposits/withdrawals
    private String destinationAccountId; // For transfers
}
