package projetarchitecture.projetarchitecture.kafka.consumers;

import projetarchitecture.projetarchitecture.kafka.events.AccountCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountEventListener {

    private final ObjectMapper objectMapper = new ObjectMapper(); // For deserializing JSON

    @KafkaListener(topics = "account-created", groupId = "account-service-group")
    public void handleAccountCreatedEvent(String message) {
        try {
            // Deserialize the message into an AccountCreatedEvent object
            AccountCreatedEvent event = objectMapper.readValue(message, AccountCreatedEvent.class);

            // Process the event (Example: Log, trigger downstream processing, etc.)
            System.out.println("Processing Account Created Event: " + event);

            // Example: Add custom logic here
            // 1. Log the event to an audit trail
            logAccountCreated(event);

            // 2. Update another system or database (e.g., account status)
            updateAccountStatus(event);

        } catch (Exception e) {
            System.err.println("Failed to process Account Created Event: " + e.getMessage());
            // Optionally, send the message to a dead-letter queue or retry mechanism
        }
    }

    private void logAccountCreated(AccountCreatedEvent event) {
        // Example: Logging the event
        System.out.println("Audit Log: Account created with ID: " + event.getAccountId());
    }

    private void updateAccountStatus(AccountCreatedEvent event) {
        // Example: Logic to update account status in another database/system
        System.out.println("Updating account status for account ID: " + event.getAccountId());
        // Add database interaction or HTTP request logic here
    }
}
