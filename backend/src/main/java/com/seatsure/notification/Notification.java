package com.seatsure.notification;

import com.seatsure.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "notifications", indexes = {@Index(name = "idx_notifications_user_read", columnList = "user_id, is_read")})
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;
    @Column(nullable = false, length = 50)
    private String type;
    @Column(name = "is_read", nullable = false)
    private boolean isRead;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    private static boolean $default$isRead() {
        return false;
    }


    public static class NotificationBuilder {
        private Long id;
        private User user;
        private String title;
        private String message;
        private String type;
        private boolean isRead$set;
        private boolean isRead$value;
        private Instant createdAt;

        NotificationBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder message(final String message) {
            this.message = message;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder type(final String type) {
            this.type = type;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder isRead(final boolean isRead) {
            this.isRead$value = isRead;
            isRead$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Notification.NotificationBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Notification build() {
            boolean isRead$value = this.isRead$value;
            if (!this.isRead$set) isRead$value = Notification.$default$isRead();
            return new Notification(this.id, this.user, this.title, this.message, this.type, isRead$value, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Notification.NotificationBuilder(id=" + this.id + ", user=" + this.user + ", title=" + this.title + ", message=" + this.message + ", type=" + this.type + ", isRead$value=" + this.isRead$value + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static Notification.NotificationBuilder builder() {
        return new Notification.NotificationBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.type;
    }

    public boolean isRead() {
        return this.isRead;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setRead(final boolean isRead) {
        this.isRead = isRead;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Notification() {
        this.isRead = Notification.$default$isRead();
    }

    public Notification(final Long id, final User user, final String title, final String message, final String type, final boolean isRead, final Instant createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }
}
