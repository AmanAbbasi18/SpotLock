package com.seatsure.cinema;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(name = "total_seats", nullable = false)
    private int totalSeats;
    @Column(nullable = false, length = 20)
    private String status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    private static String $default$status() {
        return "ACTIVE";
    }


    public static class ScreenBuilder {
        private Long id;
        private Cinema cinema;
        private String name;
        private int totalSeats;
        private boolean status$set;
        private String status$value;
        private Instant createdAt;

        ScreenBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Screen.ScreenBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Screen.ScreenBuilder cinema(final Cinema cinema) {
            this.cinema = cinema;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Screen.ScreenBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Screen.ScreenBuilder totalSeats(final int totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Screen.ScreenBuilder status(final String status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Screen.ScreenBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Screen build() {
            String status$value = this.status$value;
            if (!this.status$set) status$value = Screen.$default$status();
            return new Screen(this.id, this.cinema, this.name, this.totalSeats, status$value, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Screen.ScreenBuilder(id=" + this.id + ", cinema=" + this.cinema + ", name=" + this.name + ", totalSeats=" + this.totalSeats + ", status$value=" + this.status$value + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static Screen.ScreenBuilder builder() {
        return new Screen.ScreenBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Cinema getCinema() {
        return this.cinema;
    }

    public String getName() {
        return this.name;
    }

    public int getTotalSeats() {
        return this.totalSeats;
    }

    public String getStatus() {
        return this.status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCinema(final Cinema cinema) {
        this.cinema = cinema;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTotalSeats(final int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Screen() {
        this.status = Screen.$default$status();
    }

    public Screen(final Long id, final Cinema cinema, final String name, final int totalSeats, final String status, final Instant createdAt) {
        this.id = id;
        this.cinema = cinema;
        this.name = name;
        this.totalSeats = totalSeats;
        this.status = status;
        this.createdAt = createdAt;
    }
}
