package com.seatsure.cinema;

import jakarta.persistence.*;

@Entity
@Table(name = "seats", uniqueConstraints = {@UniqueConstraint(name = "uk_screen_row_seat", columnNames = {"screen_id", "row_label", "seat_number"})})
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;
    @Column(name = "row_label", nullable = false, length = 5)
    private String rowLabel;
    @Column(name = "seat_number", nullable = false)
    private int seatNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type", nullable = false, length = 20)
    private SeatType seatType;
    @Column(nullable = false, length = 20)
    private String status;

    private static String $default$status() {
        return "ACTIVE";
    }


    public static class SeatBuilder {
        private Long id;
        private Screen screen;
        private String rowLabel;
        private int seatNumber;
        private SeatType seatType;
        private boolean status$set;
        private String status$value;

        SeatBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Seat.SeatBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Seat.SeatBuilder screen(final Screen screen) {
            this.screen = screen;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Seat.SeatBuilder rowLabel(final String rowLabel) {
            this.rowLabel = rowLabel;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Seat.SeatBuilder seatNumber(final int seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Seat.SeatBuilder seatType(final SeatType seatType) {
            this.seatType = seatType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Seat.SeatBuilder status(final String status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        public Seat build() {
            String status$value = this.status$value;
            if (!this.status$set) status$value = Seat.$default$status();
            return new Seat(this.id, this.screen, this.rowLabel, this.seatNumber, this.seatType, status$value);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Seat.SeatBuilder(id=" + this.id + ", screen=" + this.screen + ", rowLabel=" + this.rowLabel + ", seatNumber=" + this.seatNumber + ", seatType=" + this.seatType + ", status$value=" + this.status$value + ")";
        }
    }

    public static Seat.SeatBuilder builder() {
        return new Seat.SeatBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public String getRowLabel() {
        return this.rowLabel;
    }

    public int getSeatNumber() {
        return this.seatNumber;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setScreen(final Screen screen) {
        this.screen = screen;
    }

    public void setRowLabel(final String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public void setSeatNumber(final int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setSeatType(final SeatType seatType) {
        this.seatType = seatType;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Seat() {
        this.status = Seat.$default$status();
    }

    public Seat(final Long id, final Screen screen, final String rowLabel, final int seatNumber, final SeatType seatType, final String status) {
        this.id = id;
        this.screen = screen;
        this.rowLabel = rowLabel;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = status;
    }
}
