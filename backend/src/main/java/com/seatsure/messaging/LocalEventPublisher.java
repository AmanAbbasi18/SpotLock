package com.seatsure.messaging;

import com.seatsure.outbox.DomainEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "seatsure.kafka.enabled", havingValue = "false", matchIfMissing = true)
public class LocalEventPublisher implements EventPublisher {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LocalEventPublisher.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    public LocalEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        log.info("[LocalEventPublisher] Publishing domain event {} ({}) locally", event.getEventId(), event.getEventType());
        applicationEventPublisher.publishEvent(event);
    }
}
