package projetarchitecture.projetarchitecture.kafka.events;

import lombok.Data;

@Data
public class AccountCreatedEvent {
    private String accountId;
    private String accountType;
    private double initialBalance;
}
