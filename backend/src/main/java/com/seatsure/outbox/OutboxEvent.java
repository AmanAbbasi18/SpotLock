package com.seatsure.outbox;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "outbox_events", indexes = {@Index(name = "idx_outbox_published", columnList = "published, created_at")})
public class OutboxEvent {
    @Id
    @Column(length = 64)
    private String id;
    @Column(name = "aggregate_type", nullable = false, length = 50)
    private String aggregateType;
    @Column(name = "aggregate_id", nullable = false, length = 64)
    private String aggregateId;
    @Column(name = "event_type", nullable = false, length = 50)
    private String eventType;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false)
    private boolean published;
    @Column(name = "published_at")
    private Instant publishedAt;
    @Column(name = "retry_count", nullable = false)
    private int retryCount;

    private static boolean $default$published() {
        return false;
    }

    private static int $default$retryCount() {
        return 0;
    }


    public static class OutboxEventBuilder {
        private String id;
        private String aggregateType;
        private String aggregateId;
        private String eventType;
        private String payload;
        private Instant createdAt;
        private boolean published$set;
        private boolean published$value;
        private Instant publishedAt;
        private boolean retryCount$set;
        private int retryCount$value;

        OutboxEventBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder id(final String id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder aggregateType(final String aggregateType) {
            this.aggregateType = aggregateType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder aggregateId(final String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder eventType(final String eventType) {
            this.eventType = eventType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder payload(final String payload) {
            this.payload = payload;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder published(final boolean published) {
            this.published$value = published;
            published$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder publishedAt(final Instant publishedAt) {
            this.publishedAt = publishedAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public OutboxEvent.OutboxEventBuilder retryCount(final int retryCount) {
            this.retryCount$value = retryCount;
            retryCount$set = true;
            return this;
        }

        public OutboxEvent build() {
            boolean published$value = this.published$value;
            if (!this.published$set) published$value = OutboxEvent.$default$published();
            int retryCount$value = this.retryCount$value;
            if (!this.retryCount$set) retryCount$value = OutboxEvent.$default$retryCount();
            return new OutboxEvent(this.id, this.aggregateType, this.aggregateId, this.eventType, this.payload, this.createdAt, published$value, this.publishedAt, retryCount$value);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "OutboxEvent.OutboxEventBuilder(id=" + this.id + ", aggregateType=" + this.aggregateType + ", aggregateId=" + this.aggregateId + ", eventType=" + this.eventType + ", payload=" + this.payload + ", createdAt=" + this.createdAt + ", published$value=" + this.published$value + ", publishedAt=" + this.publishedAt + ", retryCount$value=" + this.retryCount$value + ")";
        }
    }

    public static OutboxEvent.OutboxEventBuilder builder() {
        return new OutboxEvent.OutboxEventBuilder();
    }

    public String getId() {
        return this.id;
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

    public String getPayload() {
        return this.payload;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public boolean isPublished() {
        return this.published;
    }

    public Instant getPublishedAt() {
        return this.publishedAt;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void setId(final String id) {
        this.id = id;
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

    public void setPayload(final String payload) {
        this.payload = payload;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublished(final boolean published) {
        this.published = published;
    }

    public void setPublishedAt(final Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setRetryCount(final int retryCount) {
        this.retryCount = retryCount;
    }

    public OutboxEvent() {
        this.published = OutboxEvent.$default$published();
        this.retryCount = OutboxEvent.$default$retryCount();
    }

    public OutboxEvent(final String id, final String aggregateType, final String aggregateId, final String eventType, final String payload, final Instant createdAt, final boolean published, final Instant publishedAt, final int retryCount) {
        this.id = id;
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
        this.createdAt = createdAt;
        this.published = published;
        this.publishedAt = publishedAt;
        this.retryCount = retryCount;
    }
}
