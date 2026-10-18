package com.seatsure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seatsure.outbox.DomainEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "seatsure.kafka.enabled", havingValue = "true")
public class KafkaEventPublisher implements EventPublisher {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(KafkaEventPublisher.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaEventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(DomainEvent event) {
        try {
            String topic = resolveTopicForEvent(event.getEventType());
            String payloadJson = objectMapper.writeValueAsString(event);
            log.info("[KafkaEventPublisher] Publishing event {} to topic {}", event.getEventId(), topic);
            kafkaTemplate.send(topic, event.getAggregateId(), payloadJson).whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Failed to publish event {} to topic {}: {}", event.getEventId(), topic, ex.getMessage());
                } else {
                    log.debug("Event {} delivered to topic {} partition {} offset {}", event.getEventId(), topic, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
                }
            });
        } catch (Exception e) {
            log.error("Error serializing Kafka domain event: {}", e.getMessage());
            throw new RuntimeException("Kafka publication failed", e);
        }
    }

    private String resolveTopicForEvent(String eventType) {
        if (eventType.startsWith("Booking")) {
            return "seatsure.booking.events";
        } else if (eventType.startsWith("Payment")) {
            return "seatsure.payment.events";
        } else {
            return "seatsure.seat.events";
        }
    }
}
