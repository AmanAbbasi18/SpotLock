package com.seatsure.booking;

import com.seatsure.showtime.Show;
import com.seatsure.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "booking_reference", nullable = false, unique = true, length = 64)
    private String bookingReference;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookingStatus status;
    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;
    @Column(nullable = false, length = 3)
    private String currency;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    private static BookingStatus $default$status() {
        return BookingStatus.PENDING;
    }

    private static String $default$currency() {
        return "INR";
    }


    public static class BookingBuilder {
        private Long id;
        private String bookingReference;
        private User user;
        private Show show;
        private boolean status$set;
        private BookingStatus status$value;
        private BigDecimal totalAmount;
        private boolean currency$set;
        private String currency$value;
        private Instant createdAt;
        private Instant updatedAt;
        private Instant expiresAt;

        BookingBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder bookingReference(final String bookingReference) {
            this.bookingReference = bookingReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder user(final User user) {
            this.user = user;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder show(final Show show) {
            this.show = show;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder status(final BookingStatus status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder totalAmount(final BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder currency(final String currency) {
            this.currency$value = currency;
            currency$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder updatedAt(final Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Booking.BookingBuilder expiresAt(final Instant expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Booking build() {
            BookingStatus status$value = this.status$value;
            if (!this.status$set) status$value = Booking.$default$status();
            String currency$value = this.currency$value;
            if (!this.currency$set) currency$value = Booking.$default$currency();
            return new Booking(this.id, this.bookingReference, this.user, this.show, status$value, this.totalAmount, currency$value, this.createdAt, this.updatedAt, this.expiresAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Booking.BookingBuilder(id=" + this.id + ", bookingReference=" + this.bookingReference + ", user=" + this.user + ", show=" + this.show + ", status$value=" + this.status$value + ", totalAmount=" + this.totalAmount + ", currency$value=" + this.currency$value + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", expiresAt=" + this.expiresAt + ")";
        }
    }

    public static Booking.BookingBuilder builder() {
        return new Booking.BookingBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getBookingReference() {
        return this.bookingReference;
    }

    public User getUser() {
        return this.user;
    }

    public Show getShow() {
        return this.show;
    }

    public BookingStatus getStatus() {
        return this.status;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public Instant getExpiresAt() {
        return this.expiresAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setBookingReference(final String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setShow(final Show show) {
        this.show = show;
    }

    public void setStatus(final BookingStatus status) {
        this.status = status;
    }

    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setExpiresAt(final Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Booking() {
        this.status = Booking.$default$status();
        this.currency = Booking.$default$currency();
    }

    public Booking(final Long id, final String bookingReference, final User user, final Show show, final BookingStatus status, final BigDecimal totalAmount, final String currency, final Instant createdAt, final Instant updatedAt, final Instant expiresAt) {
        this.id = id;
        this.bookingReference = bookingReference;
        this.user = user;
        this.show = show;
        this.status = status;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
    }
}
