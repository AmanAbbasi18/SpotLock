package com.seatsure.showtime;

import com.seatsure.cinema.Seat;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "show_seats", uniqueConstraints = {@UniqueConstraint(name = "uk_show_seat", columnNames = {"show_id", "seat_id"})})
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ShowSeatStatus status;
    @Column(name = "current_hold_id")
    private Long currentHoldId;
    @Version
    @Column(nullable = false)
    private Long version;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    private static ShowSeatStatus $default$status() {
        return ShowSeatStatus.AVAILABLE;
    }

    private static Long $default$version() {
        return 0L;
    }


    public static class ShowSeatBuilder {
        private Long id;
        private Show show;
        private Seat seat;
        private BigDecimal price;
        private boolean status$set;
        private ShowSeatStatus status$value;
        private Long currentHoldId;
        private boolean version$set;
        private Long version$value;
        private Instant updatedAt;

        ShowSeatBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public ShowSeat.ShowSeatBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeat.ShowSeatBuilder show(final Show show) {
            this.show = show;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeat.ShowSeatBuilder seat(final Seat seat) {
            this.seat = seat;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeat.ShowSeatBuilder price(final BigDecimal price) {
            this.price = price;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeat.ShowSeatBuilder status(final ShowSeatStatus status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeat.ShowSeatBuilder currentHoldId(final Long currentHoldId) {
            this.currentHoldId = currentHoldId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeat.ShowSeatBuilder version(final Long version) {
            this.version$value = version;
            version$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeat.ShowSeatBuilder updatedAt(final Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ShowSeat build() {
            ShowSeatStatus status$value = this.status$value;
            if (!this.status$set) status$value = ShowSeat.$default$status();
            Long version$value = this.version$value;
            if (!this.version$set) version$value = ShowSeat.$default$version();
            return new ShowSeat(this.id, this.show, this.seat, this.price, status$value, this.currentHoldId, version$value, this.updatedAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ShowSeat.ShowSeatBuilder(id=" + this.id + ", show=" + this.show + ", seat=" + this.seat + ", price=" + this.price + ", status$value=" + this.status$value + ", currentHoldId=" + this.currentHoldId + ", version$value=" + this.version$value + ", updatedAt=" + this.updatedAt + ")";
        }
    }

    public static ShowSeat.ShowSeatBuilder builder() {
        return new ShowSeat.ShowSeatBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Show getShow() {
        return this.show;
    }

    public Seat getSeat() {
        return this.seat;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public ShowSeatStatus getStatus() {
        return this.status;
    }

    public Long getCurrentHoldId() {
        return this.currentHoldId;
    }

    public Long getVersion() {
        return this.version;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setShow(final Show show) {
        this.show = show;
    }

    public void setSeat(final Seat seat) {
        this.seat = seat;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public void setStatus(final ShowSeatStatus status) {
        this.status = status;
    }

    public void setCurrentHoldId(final Long currentHoldId) {
        this.currentHoldId = currentHoldId;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    public void setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ShowSeat() {
        this.status = ShowSeat.$default$status();
        this.version = ShowSeat.$default$version();
    }

    public ShowSeat(final Long id, final Show show, final Seat seat, final BigDecimal price, final ShowSeatStatus status, final Long currentHoldId, final Long version, final Instant updatedAt) {
        this.id = id;
        this.show = show;
        this.seat = seat;
        this.price = price;
        this.status = status;
        this.currentHoldId = currentHoldId;
        this.version = version;
        this.updatedAt = updatedAt;
    }
}
