package projetarchitecture.projetarchitecture.kafka.events;

import lombok.Data;

@Data
public class TransactionCompletedEvent {
    private String transactionId;
    private String type; // Deposit, Withdrawal, Transfer
    private double amount;
    private String accountId;
    private String destinationAccountId; // For transfers
}
