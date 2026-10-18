package com.seatsure.user;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;
    @Column(nullable = false)
    private boolean revoked;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    private static boolean $default$revoked() {
        return false;
    }


    public static class RefreshTokenBuilder {
        private Long id;
        private User user;
        private String token;
        private Instant expiresAt;
        private boolean revoked$set;
        private boolean revoked$value;
        private Instant createdAt;

        RefreshTokenBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder token(final String token) {
            this.token = token;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder expiresAt(final Instant expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder revoked(final boolean revoked) {
            this.revoked$value = revoked;
            revoked$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public RefreshToken.RefreshTokenBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RefreshToken build() {
            boolean revoked$value = this.revoked$value;
            if (!this.revoked$set) revoked$value = RefreshToken.$default$revoked();
            return new RefreshToken(this.id, this.user, this.token, this.expiresAt, revoked$value, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "RefreshToken.RefreshTokenBuilder(id=" + this.id + ", user=" + this.user + ", token=" + this.token + ", expiresAt=" + this.expiresAt + ", revoked$value=" + this.revoked$value + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static RefreshToken.RefreshTokenBuilder builder() {
        return new RefreshToken.RefreshTokenBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public String getToken() {
        return this.token;
    }

    public Instant getExpiresAt() {
        return this.expiresAt;
    }

    public boolean isRevoked() {
        return this.revoked;
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

    public void setToken(final String token) {
        this.token = token;
    }

    public void setExpiresAt(final Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setRevoked(final boolean revoked) {
        this.revoked = revoked;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public RefreshToken() {
        this.revoked = RefreshToken.$default$revoked();
    }

    public RefreshToken(final Long id, final User user, final String token, final Instant expiresAt, final boolean revoked, final Instant createdAt) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expiresAt = expiresAt;
        this.revoked = revoked;
        this.createdAt = createdAt;
    }
}
