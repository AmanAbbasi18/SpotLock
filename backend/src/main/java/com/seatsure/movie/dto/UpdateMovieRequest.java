package com.seatsure.movie.dto;

import com.seatsure.movie.MovieStatus;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;

public class UpdateMovieRequest {
    private String title;
    private String description;
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer durationMinutes;
    private String language;
    private String genre;
    private LocalDate releaseDate;
    private String posterUrl;
    private String contentRating;
    private MovieStatus status;


    public static class UpdateMovieRequestBuilder {
        private String title;
        private String description;
        private Integer durationMinutes;
        private String language;
        private String genre;
        private LocalDate releaseDate;
        private String posterUrl;
        private String contentRating;
        private MovieStatus status;

        UpdateMovieRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder durationMinutes(final Integer durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder language(final String language) {
            this.language = language;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder genre(final String genre) {
            this.genre = genre;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder releaseDate(final LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder posterUrl(final String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder contentRating(final String contentRating) {
            this.contentRating = contentRating;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UpdateMovieRequest.UpdateMovieRequestBuilder status(final MovieStatus status) {
            this.status = status;
            return this;
        }

        public UpdateMovieRequest build() {
            return new UpdateMovieRequest(this.title, this.description, this.durationMinutes, this.language, this.genre, this.releaseDate, this.posterUrl, this.contentRating, this.status);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "UpdateMovieRequest.UpdateMovieRequestBuilder(title=" + this.title + ", description=" + this.description + ", durationMinutes=" + this.durationMinutes + ", language=" + this.language + ", genre=" + this.genre + ", releaseDate=" + this.releaseDate + ", posterUrl=" + this.posterUrl + ", contentRating=" + this.contentRating + ", status=" + this.status + ")";
        }
    }

    public static UpdateMovieRequest.UpdateMovieRequestBuilder builder() {
        return new UpdateMovieRequest.UpdateMovieRequestBuilder();
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getDurationMinutes() {
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

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDurationMinutes(final Integer durationMinutes) {
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

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateMovieRequest)) return false;
        final UpdateMovieRequest other = (UpdateMovieRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$durationMinutes = this.getDurationMinutes();
        final java.lang.Object other$durationMinutes = other.getDurationMinutes();
        if (this$durationMinutes == null ? other$durationMinutes != null : !this$durationMinutes.equals(other$durationMinutes)) return false;
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
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof UpdateMovieRequest;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $durationMinutes = this.getDurationMinutes();
        result = result * PRIME + ($durationMinutes == null ? 43 : $durationMinutes.hashCode());
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
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "UpdateMovieRequest(title=" + this.getTitle() + ", description=" + this.getDescription() + ", durationMinutes=" + this.getDurationMinutes() + ", language=" + this.getLanguage() + ", genre=" + this.getGenre() + ", releaseDate=" + this.getReleaseDate() + ", posterUrl=" + this.getPosterUrl() + ", contentRating=" + this.getContentRating() + ", status=" + this.getStatus() + ")";
    }

    public UpdateMovieRequest() {
    }

    public UpdateMovieRequest(final String title, final String description, final Integer durationMinutes, final String language, final String genre, final LocalDate releaseDate, final String posterUrl, final String contentRating, final MovieStatus status) {
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.language = language;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.contentRating = contentRating;
        this.status = status;
    }
}
