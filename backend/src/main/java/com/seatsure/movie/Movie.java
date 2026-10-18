package com.seatsure.movie;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes;
    @Column(nullable = false, length = 50)
    private String language;
    @Column(nullable = false, length = 50)
    private String genre;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
    @Column(name = "poster_url", length = 500)
    private String posterUrl;
    @Column(name = "content_rating", nullable = false, length = 10)
    private String contentRating;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MovieStatus status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    private static String $default$contentRating() {
        return "UA";
    }

    private static MovieStatus $default$status() {
        return MovieStatus.ACTIVE;
    }


    public static class MovieBuilder {
        private Long id;
        private String title;
        private String description;
        private int durationMinutes;
        private String language;
        private String genre;
        private LocalDate releaseDate;
        private String posterUrl;
        private boolean contentRating$set;
        private String contentRating$value;
        private boolean status$set;
        private MovieStatus status$value;
        private Instant createdAt;
        private Instant updatedAt;

        MovieBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder durationMinutes(final int durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder language(final String language) {
            this.language = language;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder genre(final String genre) {
            this.genre = genre;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder releaseDate(final LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder posterUrl(final String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder contentRating(final String contentRating) {
            this.contentRating$value = contentRating;
            contentRating$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder status(final MovieStatus status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Movie.MovieBuilder updatedAt(final Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Movie build() {
            String contentRating$value = this.contentRating$value;
            if (!this.contentRating$set) contentRating$value = Movie.$default$contentRating();
            MovieStatus status$value = this.status$value;
            if (!this.status$set) status$value = Movie.$default$status();
            return new Movie(this.id, this.title, this.description, this.durationMinutes, this.language, this.genre, this.releaseDate, this.posterUrl, contentRating$value, status$value, this.createdAt, this.updatedAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Movie.MovieBuilder(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", durationMinutes=" + this.durationMinutes + ", language=" + this.language + ", genre=" + this.genre + ", releaseDate=" + this.releaseDate + ", posterUrl=" + this.posterUrl + ", contentRating$value=" + this.contentRating$value + ", status$value=" + this.status$value + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }

    public static Movie.MovieBuilder builder() {
        return new Movie.MovieBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDurationMinutes() {
        return this.durationMinutes;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getGenre() {
        return this.genre;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public String getPosterUrl() {
        return this.posterUrl;
    }

    public String getContentRating() {
        return this.contentRating;
    }

    public MovieStatus getStatus() {
        return this.status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDurationMinutes(final int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public void setReleaseDate(final LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPosterUrl(final String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setContentRating(final String contentRating) {
        this.contentRating = contentRating;
    }

    public void setStatus(final MovieStatus status) {
        this.status = status;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Movie() {
        this.contentRating = Movie.$default$contentRating();
        this.status = Movie.$default$status();
    }

    public Movie(final Long id, final String title, final String description, final int durationMinutes, final String language, final String genre, final LocalDate releaseDate, final String posterUrl, final String contentRating, final MovieStatus status, final Instant createdAt, final Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.language = language;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.contentRating = contentRating;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
