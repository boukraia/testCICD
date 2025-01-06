package projetarchitecture.projetarchitecture.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic accountCreatedTopic() {
        return new NewTopic("account-created", 3, (short) 1); // 3 partitions, 1 replication factor
    }

    @Bean
    public NewTopic transactionCompletedTopic() {
        return new NewTopic("transaction-completed", 3, (short) 1);
    }

    @Bean
    public NewTopic lowBalanceTopic() {
        return new NewTopic("low-balance", 3, (short) 1);
    }
}
