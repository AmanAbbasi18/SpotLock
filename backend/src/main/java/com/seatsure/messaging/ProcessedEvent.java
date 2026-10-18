package com.seatsure.messaging;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "processed_events")
@IdClass(ProcessedEvent.ProcessedEventId.class)
public class ProcessedEvent {
    @Id
    @Column(name = "event_id", nullable = false, length = 64)
    private String eventId;
    @Id
    @Column(name = "consumer_group", nullable = false, length = 64)
    private String consumerGroup;
    @CreationTimestamp
    @Column(name = "processed_at", nullable = false, updatable = false)
    private Instant processedAt;


    public static class ProcessedEventId implements Serializable {
        private String eventId;
        private String consumerGroup;

        public String getEventId() {
            return this.eventId;
        }

        public String getConsumerGroup() {
            return this.consumerGroup;
        }

        public void setEventId(final String eventId) {
            this.eventId = eventId;
        }

        public void setConsumerGroup(final String consumerGroup) {
            this.consumerGroup = consumerGroup;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof ProcessedEvent.ProcessedEventId)) return false;
            final ProcessedEvent.ProcessedEventId other = (ProcessedEvent.ProcessedEventId) o;
            if (!other.canEqual((java.lang.Object) this)) return false;
            final java.lang.Object this$eventId = this.getEventId();
            final java.lang.Object other$eventId = other.getEventId();
            if (this$eventId == null ? other$eventId != null : !this$eventId.equals(other$eventId)) return false;
            final java.lang.Object this$consumerGroup = this.getConsumerGroup();
            final java.lang.Object other$consumerGroup = other.getConsumerGroup();
            if (this$consumerGroup == null ? other$consumerGroup != null : !this$consumerGroup.equals(other$consumerGroup)) return false;
            return true;
        }

        protected boolean canEqual(final java.lang.Object other) {
            return other instanceof ProcessedEvent.ProcessedEventId;
        }

        @java.lang.Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final java.lang.Object $eventId = this.getEventId();
            result = result * PRIME + ($eventId == null ? 43 : $eventId.hashCode());
            final java.lang.Object $consumerGroup = this.getConsumerGroup();
            result = result * PRIME + ($consumerGroup == null ? 43 : $consumerGroup.hashCode());
            return result;
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ProcessedEvent.ProcessedEventId(eventId=" + this.getEventId() + ", consumerGroup=" + this.getConsumerGroup() + ")";
        }

        public ProcessedEventId() {
        }

        public ProcessedEventId(final String eventId, final String consumerGroup) {
            this.eventId = eventId;
            this.consumerGroup = consumerGroup;
        }
    }


    public static class ProcessedEventBuilder {
        private String eventId;
        private String consumerGroup;
        private Instant processedAt;

        ProcessedEventBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public ProcessedEvent.ProcessedEventBuilder eventId(final String eventId) {
            this.eventId = eventId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ProcessedEvent.ProcessedEventBuilder consumerGroup(final String consumerGroup) {
            this.consumerGroup = consumerGroup;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ProcessedEvent.ProcessedEventBuilder processedAt(final Instant processedAt) {
            this.processedAt = processedAt;
            return this;
        }

        public ProcessedEvent build() {
            return new ProcessedEvent(this.eventId, this.consumerGroup, this.processedAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ProcessedEvent.ProcessedEventBuilder(eventId=" + this.eventId + ", consumerGroup=" + this.consumerGroup + ", processedAt=" + this.processedAt + ")";
        }
    }

    public static ProcessedEvent.ProcessedEventBuilder builder() {
        return new ProcessedEvent.ProcessedEventBuilder();
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getConsumerGroup() {
        return this.consumerGroup;
    }

    public Instant getProcessedAt() {
        return this.processedAt;
    }

    public void setEventId(final String eventId) {
        this.eventId = eventId;
    }

    public void setConsumerGroup(final String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public void setProcessedAt(final Instant processedAt) {
        this.processedAt = processedAt;
    }

    public ProcessedEvent() {
    }

    public ProcessedEvent(final String eventId, final String consumerGroup, final Instant processedAt) {
        this.eventId = eventId;
        this.consumerGroup = consumerGroup;
        this.processedAt = processedAt;
    }
}
