package com.seatsure.cinema;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, length = 500)
    private String address;
    @Column(nullable = false, length = 100)
    private String city;
    @Column(nullable = false, length = 20)
    private String status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    private static String $default$status() {
        return "ACTIVE";
    }


    public static class CinemaBuilder {
        private Long id;
        private String name;
        private String address;
        private String city;
        private boolean status$set;
        private String status$value;
        private Instant createdAt;

        CinemaBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Cinema.CinemaBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Cinema.CinemaBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Cinema.CinemaBuilder address(final String address) {
            this.address = address;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Cinema.CinemaBuilder city(final String city) {
            this.city = city;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Cinema.CinemaBuilder status(final String status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Cinema.CinemaBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Cinema build() {
            String status$value = this.status$value;
            if (!this.status$set) status$value = Cinema.$default$status();
            return new Cinema(this.id, this.name, this.address, this.city, status$value, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Cinema.CinemaBuilder(id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", city=" + this.city + ", status$value=" + this.status$value + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static Cinema.CinemaBuilder builder() {
        return new Cinema.CinemaBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
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

    public void setName(final String name) {
        this.name = name;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Cinema() {
        this.status = Cinema.$default$status();
    }

    public Cinema(final Long id, final String name, final String address, final String city, final String status, final Instant createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.status = status;
        this.createdAt = createdAt;
    }
}
