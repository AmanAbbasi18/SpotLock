package com.seatsure.outbox;

import java.time.Instant;
import java.util.Map;

public class DomainEvent {
    private String eventId;
    private String aggregateType;
    private String aggregateId;
    private String eventType;
    private Map<String, Object> payload;
    private Instant timestamp;


    public static class DomainEventBuilder {
        private String eventId;
        private String aggregateType;
        private String aggregateId;
        private String eventType;
        private Map<String, Object> payload;
        private Instant timestamp;

        DomainEventBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public DomainEvent.DomainEventBuilder eventId(final String eventId) {
            this.eventId = eventId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DomainEvent.DomainEventBuilder aggregateType(final String aggregateType) {
            this.aggregateType = aggregateType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DomainEvent.DomainEventBuilder aggregateId(final String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DomainEvent.DomainEventBuilder eventType(final String eventType) {
            this.eventType = eventType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DomainEvent.DomainEventBuilder payload(final Map<String, Object> payload) {
            this.payload = payload;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public DomainEvent.DomainEventBuilder timestamp(final Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public DomainEvent build() {
            return new DomainEvent(this.eventId, this.aggregateType, this.aggregateId, this.eventType, this.payload, this.timestamp);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "DomainEvent.DomainEventBuilder(eventId=" + this.eventId + ", aggregateType=" + this.aggregateType + ", aggregateId=" + this.aggregateId + ", eventType=" + this.eventType + ", payload=" + this.payload + ", timestamp=" + this.timestamp + ")";
        }
    }

    public static DomainEvent.DomainEventBuilder builder() {
        return new DomainEvent.DomainEventBuilder();
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getAggregateType() {
        return this.aggregateType;
    }

    public String getAggregateId() {
        return this.aggregateId;
    }

    public String getEventType() {
        return this.eventType;
    }

    public Map<String, Object> getPayload() {
        return this.payload;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public void setEventId(final String eventId) {
        this.eventId = eventId;
    }

    public void setAggregateType(final String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public void setAggregateId(final String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    public void setPayload(final Map<String, Object> payload) {
        this.payload = payload;
    }

    public void setTimestamp(final Instant timestamp) {
        this.timestamp = timestamp;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof DomainEvent)) return false;
        final DomainEvent other = (DomainEvent) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$eventId = this.getEventId();
        final java.lang.Object other$eventId = other.getEventId();
        if (this$eventId == null ? other$eventId != null : !this$eventId.equals(other$eventId)) return false;
        final java.lang.Object this$aggregateType = this.getAggregateType();
        final java.lang.Object other$aggregateType = other.getAggregateType();
        if (this$aggregateType == null ? other$aggregateType != null : !this$aggregateType.equals(other$aggregateType)) return false;
        final java.lang.Object this$aggregateId = this.getAggregateId();
        final java.lang.Object other$aggregateId = other.getAggregateId();
        if (this$aggregateId == null ? other$aggregateId != null : !this$aggregateId.equals(other$aggregateId)) return false;
        final java.lang.Object this$eventType = this.getEventType();
        final java.lang.Object other$eventType = other.getEventType();
        if (this$eventType == null ? other$eventType != null : !this$eventType.equals(other$eventType)) return false;
        final java.lang.Object this$payload = this.getPayload();
        final java.lang.Object other$payload = other.getPayload();
        if (this$payload == null ? other$payload != null : !this$payload.equals(other$payload)) return false;
        final java.lang.Object this$timestamp = this.getTimestamp();
        final java.lang.Object other$timestamp = other.getTimestamp();
        if (this$timestamp == null ? other$timestamp != null : !this$timestamp.equals(other$timestamp)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof DomainEvent;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $eventId = this.getEventId();
        result = result * PRIME + ($eventId == null ? 43 : $eventId.hashCode());
        final java.lang.Object $aggregateType = this.getAggregateType();
        result = result * PRIME + ($aggregateType == null ? 43 : $aggregateType.hashCode());
        final java.lang.Object $aggregateId = this.getAggregateId();
        result = result * PRIME + ($aggregateId == null ? 43 : $aggregateId.hashCode());
        final java.lang.Object $eventType = this.getEventType();
        result = result * PRIME + ($eventType == null ? 43 : $eventType.hashCode());
        final java.lang.Object $payload = this.getPayload();
        result = result * PRIME + ($payload == null ? 43 : $payload.hashCode());
        final java.lang.Object $timestamp = this.getTimestamp();
        result = result * PRIME + ($timestamp == null ? 43 : $timestamp.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "DomainEvent(eventId=" + this.getEventId() + ", aggregateType=" + this.getAggregateType() + ", aggregateId=" + this.getAggregateId() + ", eventType=" + this.getEventType() + ", payload=" + this.getPayload() + ", timestamp=" + this.getTimestamp() + ")";
    }

    public DomainEvent() {
    }

    public DomainEvent(final String eventId, final String aggregateType, final String aggregateId, final String eventType, final Map<String, Object> payload, final Instant timestamp) {
        this.eventId = eventId;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
        this.timestamp = timestamp;
    }
}
