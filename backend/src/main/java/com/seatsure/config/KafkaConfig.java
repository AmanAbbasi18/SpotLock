package com.seatsure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(name = "seatsure.kafka.enabled", havingValue = "true")
public class KafkaConfig {

    @Bean
    public NewTopic bookingEventsTopic() {
        return TopicBuilder.name("seatsure.booking.events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paymentEventsTopic() {
        return TopicBuilder.name("seatsure.payment.events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic seatEventsTopic() {
        return TopicBuilder.name("seatsure.seat.events")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic bookingEventsDlt() {
        return TopicBuilder.name("seatsure.booking.events.DLT")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
