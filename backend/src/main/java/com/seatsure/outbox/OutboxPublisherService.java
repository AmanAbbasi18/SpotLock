package com.seatsure.outbox;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seatsure.messaging.EventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class OutboxPublisherService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OutboxPublisherService.class);
    private final OutboxEventRepository outboxEventRepository;
    private final EventPublisher eventPublisher;
    private final ObjectMapper objectMapper;

    public OutboxPublisherService(OutboxEventRepository outboxEventRepository, EventPublisher eventPublisher, ObjectMapper objectMapper) {
        this.outboxEventRepository = outboxEventRepository;
        this.eventPublisher = eventPublisher;
        this.objectMapper = objectMapper;
    }

    /**
     * Polls the outbox_events table for unpublished domain events and dispatches them.
     * Guarantees at-least-once delivery semantics.
     */
    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishPendingOutboxEvents() {
        List<OutboxEvent> pendingEvents = outboxEventRepository.findByPublishedFalseOrderByCreatedAtAsc(PageRequest.of(0, 50));
        if (pendingEvents.isEmpty()) {
            return;
        }
        log.debug("Found {} unpublished outbox events to process", pendingEvents.size());
        for (OutboxEvent outboxEvent : pendingEvents) {
            try {
                Map<String, Object> payloadMap = objectMapper.readValue(outboxEvent.getPayload(), new TypeReference<Map<String, Object>>() {
                });
                DomainEvent domainEvent = DomainEvent.builder().eventId(outboxEvent.getId()).aggregateType(outboxEvent.getAggregateType()).aggregateId(outboxEvent.getAggregateId()).eventType(outboxEvent.getEventType()).payload(payloadMap).timestamp(outboxEvent.getCreatedAt()).build();
                eventPublisher.publish(domainEvent);
                outboxEvent.setPublished(true);
                outboxEvent.setPublishedAt(Instant.now());
                outboxEventRepository.save(outboxEvent);
            } catch (Exception e) {
                outboxEvent.setRetryCount(outboxEvent.getRetryCount() + 1);
                outboxEventRepository.save(outboxEvent);
                log.error("Failed to publish outbox event #{} (retry #{}): {}", outboxEvent.getId(), outboxEvent.getRetryCount(), e.getMessage());
            }
        }
    }
}
