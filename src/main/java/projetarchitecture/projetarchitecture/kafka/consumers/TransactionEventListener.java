package projetarchitecture.projetarchitecture.kafka.consumers;

import projetarchitecture.projetarchitecture.kafka.events.TransactionCompletedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventListener {

    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON deserialization

    @KafkaListener(topics = "transaction-completed", groupId = "transaction-service-group")
    public void handleTransactionCompletedEvent(String message) {
        try {
            // Deserialize the JSON message into a TransactionCompletedEvent object
            TransactionCompletedEvent event = objectMapper.readValue(message, TransactionCompletedEvent.class);

            // Log the event for traceability
            System.out.println("Transaction Completed Event Received: " + event);

            // Perform additional processing
            logTransaction(event);
            updateDatabase(event);
            notifyUser(event);

        } catch (Exception e) {
            System.err.println("Failed to process Transaction Completed Event: " + e.getMessage());
            // Optionally, handle the error with a dead-letter queue or retry logic
        }
    }

    private void logTransaction(TransactionCompletedEvent event) {
        // Example: Log transaction details
        System.out.println("Logging transaction ID: " + event.getTransactionId());
    }

    private void updateDatabase(TransactionCompletedEvent event) {
        // Example: Update transaction details in another system or database
        System.out.println("Updating database for transaction ID: " + event.getTransactionId());
        // Add database interaction or integration logic here
    }

    private void notifyUser(TransactionCompletedEvent event) {
        // Example: Notify the user about the completed transaction
        System.out.println("Notifying user for transaction ID: " + event.getTransactionId());
        // Use an email service, SMS service, or push notification logic here
    }
}
