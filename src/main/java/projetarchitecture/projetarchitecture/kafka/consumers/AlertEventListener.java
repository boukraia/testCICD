package projetarchitecture.projetarchitecture.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AlertEventListener {

    @KafkaListener(topics = "low-balance", groupId = "alert-service-group")
    public void handleLowBalanceEvent(String message) {
        System.out.println("Low Balance Event Received: " + message);
        // Add logic to handle low balance alert
    }
}
