package com.seatsure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seatsure.audit.AuditService;
import com.seatsure.outbox.DomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AuditConsumer {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuditConsumer.class);
    private final ProcessedEventRepository processedEventRepository;
    private final AuditService auditService;
    private final ObjectMapper objectMapper;

    public AuditConsumer(ProcessedEventRepository processedEventRepository, AuditService auditService, ObjectMapper objectMapper) {
        this.processedEventRepository = processedEventRepository;
        this.auditService = auditService;
        this.objectMapper = objectMapper;
    }

    private static final String CONSUMER_GROUP = "audit-group";

    @EventListener
    @Transactional
    public void onLocalDomainEvent(DomainEvent event) {
        processEvent(event);
    }

    @KafkaListener(topics = {"seatsure.booking.events", "seatsure.payment.events", "seatsure.seat.events"}, groupId = "seatsure-audit-group", autoStartup = "${seatsure.kafka.enabled:false}")
    @Transactional
    public void onKafkaMessage(String message) {
        try {
            DomainEvent event = objectMapper.readValue(message, DomainEvent.class);
            processEvent(event);
        } catch (Exception e) {
            log.error("Failed to parse Kafka message in AuditConsumer: {}", e.getMessage());
        }
    }

    private void processEvent(DomainEvent event) {
        if (processedEventRepository.existsByEventIdAndConsumerGroup(event.getEventId(), CONSUMER_GROUP)) {
            return;
        }
        try {
            Long userId = null;
            if (event.getPayload() != null && event.getPayload().get("userId") instanceof Number num) {
                userId = num.longValue();
            }
            auditService.recordAsync(userId, "EVENT_" + event.getEventType().toUpperCase(), event.getAggregateType(), event.getAggregateId(), objectMapper.writeValueAsString(event.getPayload()), "SYSTEM_EVENT_BUS");
            ProcessedEvent processed = ProcessedEvent.builder().eventId(event.getEventId()).consumerGroup(CONSUMER_GROUP).build();
            processedEventRepository.save(processed);
        } catch (Exception e) {
            log.error("Error processing event {} in AuditConsumer: {}", event.getEventId(), e.getMessage());
        }
    }
}
