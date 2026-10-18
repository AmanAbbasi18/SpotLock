package com.seatsure.showtime;

import com.seatsure.cinema.Screen;
import com.seatsure.movie.Movie;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;
    @Column(name = "start_time", nullable = false)
    private Instant startTime;
    @Column(name = "end_time", nullable = false)
    private Instant endTime;
    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ShowStatus status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    private static ShowStatus $default$status() {
        return ShowStatus.SCHEDULED;
    }


    public static class ShowBuilder {
        private Long id;
        private Movie movie;
        private Screen screen;
        private Instant startTime;
        private Instant endTime;
        private BigDecimal basePrice;
        private boolean status$set;
        private ShowStatus status$value;
        private Instant createdAt;

        ShowBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Show.ShowBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Show.ShowBuilder movie(final Movie movie) {
            this.movie = movie;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Show.ShowBuilder screen(final Screen screen) {
            this.screen = screen;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Show.ShowBuilder startTime(final Instant startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Show.ShowBuilder endTime(final Instant endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Show.ShowBuilder basePrice(final BigDecimal basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Show.ShowBuilder status(final ShowStatus status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Show.ShowBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Show build() {
            ShowStatus status$value = this.status$value;
            if (!this.status$set) status$value = Show.$default$status();
            return new Show(this.id, this.movie, this.screen, this.startTime, this.endTime, this.basePrice, status$value, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Show.ShowBuilder(id=" + this.id + ", movie=" + this.movie + ", screen=" + this.screen + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", basePrice=" + this.basePrice + ", status$value=" + this.status$value + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static Show.ShowBuilder builder() {
        return new Show.ShowBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public Instant getStartTime() {
        return this.startTime;
    }

    public Instant getEndTime() {
        return this.endTime;
    }

    public BigDecimal getBasePrice() {
        return this.basePrice;
    }

    public ShowStatus getStatus() {
        return this.status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }

    public void setScreen(final Screen screen) {
        this.screen = screen;
    }

    public void setStartTime(final Instant startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(final Instant endTime) {
        this.endTime = endTime;
    }

    public void setBasePrice(final BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public void setStatus(final ShowStatus status) {
        this.status = status;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Show() {
        this.status = Show.$default$status();
    }

    public Show(final Long id, final Movie movie, final Screen screen, final Instant startTime, final Instant endTime, final BigDecimal basePrice, final ShowStatus status, final Instant createdAt) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.basePrice = basePrice;
        this.status = status;
        this.createdAt = createdAt;
    }
}
