package com.seatsure.messaging;

import com.seatsure.outbox.DomainEvent;

public interface EventPublisher {
    void publish(DomainEvent event);
}
