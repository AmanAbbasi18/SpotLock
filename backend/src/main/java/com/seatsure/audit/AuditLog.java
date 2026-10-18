package com.seatsure.audit;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(nullable = false, length = 100)
    private String action;
    @Column(name = "entity_type", nullable = false, length = 50)
    private String entityType;
    @Column(name = "entity_id", nullable = false, length = 64)
    private String entityId;
    @Column(columnDefinition = "TEXT")
    private String details;
    @Column(name = "ip_address", length = 45)
    private String ipAddress;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;


    public static class AuditLogBuilder {
        private Long id;
        private Long userId;
        private String action;
        private String entityType;
        private String entityId;
        private String details;
        private String ipAddress;
        private Instant createdAt;

        AuditLogBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder userId(final Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder action(final String action) {
            this.action = action;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder entityType(final String entityType) {
            this.entityType = entityType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder entityId(final String entityId) {
            this.entityId = entityId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder details(final String details) {
            this.details = details;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder ipAddress(final String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuditLog.AuditLogBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AuditLog build() {
            return new AuditLog(this.id, this.userId, this.action, this.entityType, this.entityId, this.details, this.ipAddress, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "AuditLog.AuditLogBuilder(id=" + this.id + ", userId=" + this.userId + ", action=" + this.action + ", entityType=" + this.entityType + ", entityId=" + this.entityId + ", details=" + this.details + ", ipAddress=" + this.ipAddress + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static AuditLog.AuditLogBuilder builder() {
        return new AuditLog.AuditLogBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getAction() {
        return this.action;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public String getDetails() {
        return this.details;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public void setEntityType(final String entityType) {
        this.entityType = entityType;
    }

    public void setEntityId(final String entityId) {
        this.entityId = entityId;
    }

    public void setDetails(final String details) {
        this.details = details;
    }

    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public AuditLog() {
    }

    public AuditLog(final Long id, final Long userId, final String action, final String entityType, final String entityId, final String details, final String ipAddress, final Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.details = details;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
    }
}
