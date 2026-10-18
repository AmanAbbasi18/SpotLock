package com.seatsure.movie.dto;

import com.seatsure.movie.Movie;
import com.seatsure.movie.MovieStatus;
import java.time.Instant;
import java.time.LocalDate;

public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private int durationMinutes;
    private String language;
    private String genre;
    private LocalDate releaseDate;
    private String posterUrl;
    private String contentRating;
    private MovieStatus status;
    private Instant createdAt;

    public static MovieDto fromEntity(Movie movie) {
        return MovieDto.builder().id(movie.getId()).title(movie.getTitle()).description(movie.getDescription()).durationMinutes(movie.getDurationMinutes()).language(movie.getLanguage()).genre(movie.getGenre()).releaseDate(movie.getReleaseDate()).posterUrl(movie.getPosterUrl()).contentRating(movie.getContentRating()).status(movie.getStatus()).createdAt(movie.getCreatedAt()).build();
    }


    public static class MovieDtoBuilder {
        private Long id;
        private String title;
        private String description;
        private int durationMinutes;
        private String language;
        private String genre;
        private LocalDate releaseDate;
        private String posterUrl;
        private String contentRating;
        private MovieStatus status;
        private Instant createdAt;

        MovieDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder durationMinutes(final int durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder language(final String language) {
            this.language = language;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder genre(final String genre) {
            this.genre = genre;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder releaseDate(final LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder posterUrl(final String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder contentRating(final String contentRating) {
            this.contentRating = contentRating;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder status(final MovieStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public MovieDto.MovieDtoBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public MovieDto build() {
            return new MovieDto(this.id, this.title, this.description, this.durationMinutes, this.language, this.genre, this.releaseDate, this.posterUrl, this.contentRating, this.status, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "MovieDto.MovieDtoBuilder(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", durationMinutes=" + this.durationMinutes + ", language=" + this.language + ", genre=" + this.genre + ", releaseDate=" + this.releaseDate + ", posterUrl=" + this.posterUrl + ", contentRating=" + this.contentRating + ", status=" + this.status + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static MovieDto.MovieDtoBuilder builder() {
        return new MovieDto.MovieDtoBuilder();
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

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof MovieDto)) return false;
        final MovieDto other = (MovieDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getDurationMinutes() != other.getDurationMinutes()) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$title = this.getTitle();
        final java.lang.Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final java.lang.Object this$description = this.getDescription();
        final java.lang.Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
        final java.lang.Object this$language = this.getLanguage();
        final java.lang.Object other$language = other.getLanguage();
        if (this$language == null ? other$language != null : !this$language.equals(other$language)) return false;
        final java.lang.Object this$genre = this.getGenre();
        final java.lang.Object other$genre = other.getGenre();
        if (this$genre == null ? other$genre != null : !this$genre.equals(other$genre)) return false;
        final java.lang.Object this$releaseDate = this.getReleaseDate();
        final java.lang.Object other$releaseDate = other.getReleaseDate();
        if (this$releaseDate == null ? other$releaseDate != null : !this$releaseDate.equals(other$releaseDate)) return false;
        final java.lang.Object this$posterUrl = this.getPosterUrl();
        final java.lang.Object other$posterUrl = other.getPosterUrl();
        if (this$posterUrl == null ? other$posterUrl != null : !this$posterUrl.equals(other$posterUrl)) return false;
        final java.lang.Object this$contentRating = this.getContentRating();
        final java.lang.Object other$contentRating = other.getContentRating();
        if (this$contentRating == null ? other$contentRating != null : !this$contentRating.equals(other$contentRating)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof MovieDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getDurationMinutes();
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final java.lang.Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final java.lang.Object $language = this.getLanguage();
        result = result * PRIME + ($language == null ? 43 : $language.hashCode());
        final java.lang.Object $genre = this.getGenre();
        result = result * PRIME + ($genre == null ? 43 : $genre.hashCode());
        final java.lang.Object $releaseDate = this.getReleaseDate();
        result = result * PRIME + ($releaseDate == null ? 43 : $releaseDate.hashCode());
        final java.lang.Object $posterUrl = this.getPosterUrl();
        result = result * PRIME + ($posterUrl == null ? 43 : $posterUrl.hashCode());
        final java.lang.Object $contentRating = this.getContentRating();
        result = result * PRIME + ($contentRating == null ? 43 : $contentRating.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "MovieDto(id=" + this.getId() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", durationMinutes=" + this.getDurationMinutes() + ", language=" + this.getLanguage() + ", genre=" + this.getGenre() + ", releaseDate=" + this.getReleaseDate() + ", posterUrl=" + this.getPosterUrl() + ", contentRating=" + this.getContentRating() + ", status=" + this.getStatus() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    public MovieDto() {
    }

    public MovieDto(final Long id, final String title, final String description, final int durationMinutes, final String language, final String genre, final LocalDate releaseDate, final String posterUrl, final String contentRating, final MovieStatus status, final Instant createdAt) {
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
    }
}
