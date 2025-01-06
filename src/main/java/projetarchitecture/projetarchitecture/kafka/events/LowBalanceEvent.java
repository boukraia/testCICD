package projetarchitecture.projetarchitecture.kafka.events;

import lombok.Data;

@Data
public class LowBalanceEvent {
    private String accountId;
    private double currentBalance;
    private double threshold;
}
