package com.seatsure.booking;

import com.seatsure.showtime.ShowSeat;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "booking_seats", uniqueConstraints = {@UniqueConstraint(name = "uk_booking_show_seat", columnNames = {"booking_id", "show_seat_id"})})
public class BookingSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_seat_id", nullable = false)
    private ShowSeat showSeat;
    @Column(name = "price_at_booking", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtBooking;


    public static class BookingSeatBuilder {
        private Long id;
        private Booking booking;
        private ShowSeat showSeat;
        private BigDecimal priceAtBooking;

        BookingSeatBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public BookingSeat.BookingSeatBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingSeat.BookingSeatBuilder booking(final Booking booking) {
            this.booking = booking;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingSeat.BookingSeatBuilder showSeat(final ShowSeat showSeat) {
            this.showSeat = showSeat;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingSeat.BookingSeatBuilder priceAtBooking(final BigDecimal priceAtBooking) {
            this.priceAtBooking = priceAtBooking;
            return this;
        }

        public BookingSeat build() {
            return new BookingSeat(this.id, this.booking, this.showSeat, this.priceAtBooking);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "BookingSeat.BookingSeatBuilder(id=" + this.id + ", booking=" + this.booking + ", showSeat=" + this.showSeat + ", priceAtBooking=" + this.priceAtBooking + ")";
        }
    }

    public static BookingSeat.BookingSeatBuilder builder() {
        return new BookingSeat.BookingSeatBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Booking getBooking() {
        return this.booking;
    }

    public ShowSeat getShowSeat() {
        return this.showSeat;
    }

    public BigDecimal getPriceAtBooking() {
        return this.priceAtBooking;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setBooking(final Booking booking) {
        this.booking = booking;
    }

    public void setShowSeat(final ShowSeat showSeat) {
        this.showSeat = showSeat;
    }

    public void setPriceAtBooking(final BigDecimal priceAtBooking) {
        this.priceAtBooking = priceAtBooking;
    }

    public BookingSeat() {
    }

    public BookingSeat(final Long id, final Booking booking, final ShowSeat showSeat, final BigDecimal priceAtBooking) {
        this.id = id;
        this.booking = booking;
        this.showSeat = showSeat;
        this.priceAtBooking = priceAtBooking;
    }
}
