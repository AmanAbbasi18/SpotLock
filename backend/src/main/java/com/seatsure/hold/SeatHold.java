package com.seatsure.hold;

import com.seatsure.showtime.Show;
import com.seatsure.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "seat_holds")
public class SeatHold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private HoldStatus status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;
    @Column(name = "released_at")
    private Instant releasedAt;

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    private static HoldStatus $default$status() {
        return HoldStatus.ACTIVE;
    }


    public static class SeatHoldBuilder {
        private Long id;
        private User user;
        private Show show;
        private boolean status$set;
        private HoldStatus status$value;
        private Instant createdAt;
        private Instant expiresAt;
        private Instant releasedAt;

        SeatHoldBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public SeatHold.SeatHoldBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHold.SeatHoldBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHold.SeatHoldBuilder show(final Show show) {
            this.show = show;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHold.SeatHoldBuilder status(final HoldStatus status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHold.SeatHoldBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHold.SeatHoldBuilder expiresAt(final Instant expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHold.SeatHoldBuilder releasedAt(final Instant releasedAt) {
            this.releasedAt = releasedAt;
            return this;
        }

        public SeatHold build() {
            HoldStatus status$value = this.status$value;
            if (!this.status$set) status$value = SeatHold.$default$status();
            return new SeatHold(this.id, this.user, this.show, status$value, this.createdAt, this.expiresAt, this.releasedAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "SeatHold.SeatHoldBuilder(id=" + this.id + ", user=" + this.user + ", show=" + this.show + ", status$value=" + this.status$value + ", createdAt=" + this.createdAt + ", expiresAt=" + this.expiresAt + ", releasedAt=" + this.releasedAt + ")";
        }
    }

    public static SeatHold.SeatHoldBuilder builder() {
        return new SeatHold.SeatHoldBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Show getShow() {
        return this.show;
    }

    public HoldStatus getStatus() {
        return this.status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getExpiresAt() {
        return this.expiresAt;
    }

    public Instant getReleasedAt() {
        return this.releasedAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setShow(final Show show) {
        this.show = show;
    }

    public void setStatus(final HoldStatus status) {
        this.status = status;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(final Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setReleasedAt(final Instant releasedAt) {
        this.releasedAt = releasedAt;
    }

    public SeatHold() {
        this.status = SeatHold.$default$status();
    }

    public SeatHold(final Long id, final User user, final Show show, final HoldStatus status, final Instant createdAt, final Instant expiresAt, final Instant releasedAt) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.status = status;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.releasedAt = releasedAt;
    }
}
