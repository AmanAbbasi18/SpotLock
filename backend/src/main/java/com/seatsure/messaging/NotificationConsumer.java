package com.seatsure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seatsure.notification.NotificationService;
import com.seatsure.outbox.DomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NotificationConsumer {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NotificationConsumer.class);
    private final ProcessedEventRepository processedEventRepository;
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    public NotificationConsumer(ProcessedEventRepository processedEventRepository, NotificationService notificationService, ObjectMapper objectMapper) {
        this.processedEventRepository = processedEventRepository;
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }

    private static final String CONSUMER_GROUP = "notification-group";

    @EventListener
    @Transactional
    public void onLocalDomainEvent(DomainEvent event) {
        processEvent(event);
    }

    @KafkaListener(topics = {"seatsure.booking.events", "seatsure.payment.events"}, groupId = "seatsure-notification-group", autoStartup = "${seatsure.kafka.enabled:false}")
    @Transactional
    public void onKafkaMessage(String message) {
        try {
            DomainEvent event = objectMapper.readValue(message, DomainEvent.class);
            processEvent(event);
        } catch (Exception e) {
            log.error("Failed to parse Kafka message in NotificationConsumer: {}", e.getMessage());
        }
    }

    private void processEvent(DomainEvent event) {
        // Consumer Deduplication Check
        if (processedEventRepository.existsByEventIdAndConsumerGroup(event.getEventId(), CONSUMER_GROUP)) {
            log.debug("Event {} already processed by {}, skipping", event.getEventId(), CONSUMER_GROUP);
            return;
        }
        try {
            switch (event.getEventType()) {
                case "BookingConfirmed" -> {
                    Number userIdNum = (Number) event.getPayload().get("userId");
                    if (userIdNum != null) {
                        String bookingRef = (String) event.getPayload().get("bookingReference");
                        notificationService.createNotification(userIdNum.longValue(), "Booking Confirmed: " + bookingRef, "Your tickets have been confirmed! Show your booking reference at the cinema entrance.", "BOOKING_CONFIRMED");
                    }
                }
                case "BookingCancelled" -> {
                    Number userIdNum = (Number) event.getPayload().get("userId");
                    if (userIdNum != null) {
                        String bookingRef = (String) event.getPayload().get("bookingReference");
                        notificationService.createNotification(userIdNum.longValue(), "Booking Cancelled: " + bookingRef, "Your booking has been cancelled and a refund has been initiated to your original payment method.", "BOOKING_CANCELLED");
                    }
                }
                case "PaymentFailed" -> {
                    String reason = (String) event.getPayload().get("reason");
                    Number bookingId = (Number) event.getPayload().get("bookingId");
                    log.info("Notification: Payment failed for booking #{}: {}", bookingId, reason);
                }
            }
            // Mark event processed for this consumer group
            ProcessedEvent processed = ProcessedEvent.builder().eventId(event.getEventId()).consumerGroup(CONSUMER_GROUP).build();
            processedEventRepository.save(processed);
        } catch (Exception e) {
            log.error("Error processing event {} in NotificationConsumer: {}", event.getEventId(), e.getMessage());
        }
    }
}
