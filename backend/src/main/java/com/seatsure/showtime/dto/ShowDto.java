package com.seatsure.showtime.dto;

import com.seatsure.showtime.Show;
import com.seatsure.showtime.ShowStatus;
import java.math.BigDecimal;
import java.time.Instant;

public class ShowDto {
    private Long id;
    private Long movieId;
    private String movieTitle;
    private String moviePosterUrl;
    private String movieLanguage;
    private String movieGenre;
    private Long cinemaId;
    private String cinemaName;
    private String cinemaCity;
    private Long screenId;
    private String screenName;
    private Instant startTime;
    private Instant endTime;
    private BigDecimal basePrice;
    private ShowStatus status;

    public static ShowDto fromEntity(Show show) {
        return ShowDto.builder().id(show.getId()).movieId(show.getMovie().getId()).movieTitle(show.getMovie().getTitle()).moviePosterUrl(show.getMovie().getPosterUrl()).movieLanguage(show.getMovie().getLanguage()).movieGenre(show.getMovie().getGenre()).cinemaId(show.getScreen().getCinema().getId()).cinemaName(show.getScreen().getCinema().getName()).cinemaCity(show.getScreen().getCinema().getCity()).screenId(show.getScreen().getId()).screenName(show.getScreen().getName()).startTime(show.getStartTime()).endTime(show.getEndTime()).basePrice(show.getBasePrice()).status(show.getStatus()).build();
    }


    public static class ShowDtoBuilder {
        private Long id;
        private Long movieId;
        private String movieTitle;
        private String moviePosterUrl;
        private String movieLanguage;
        private String movieGenre;
        private Long cinemaId;
        private String cinemaName;
        private String cinemaCity;
        private Long screenId;
        private String screenName;
        private Instant startTime;
        private Instant endTime;
        private BigDecimal basePrice;
        private ShowStatus status;

        ShowDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder movieId(final Long movieId) {
            this.movieId = movieId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder movieTitle(final String movieTitle) {
            this.movieTitle = movieTitle;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder moviePosterUrl(final String moviePosterUrl) {
            this.moviePosterUrl = moviePosterUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder movieLanguage(final String movieLanguage) {
            this.movieLanguage = movieLanguage;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder movieGenre(final String movieGenre) {
            this.movieGenre = movieGenre;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder cinemaId(final Long cinemaId) {
            this.cinemaId = cinemaId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder cinemaName(final String cinemaName) {
            this.cinemaName = cinemaName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder cinemaCity(final String cinemaCity) {
            this.cinemaCity = cinemaCity;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder screenId(final Long screenId) {
            this.screenId = screenId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder screenName(final String screenName) {
            this.screenName = screenName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder startTime(final Instant startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder endTime(final Instant endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder basePrice(final BigDecimal basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowDto.ShowDtoBuilder status(final ShowStatus status) {
            this.status = status;
            return this;
        }

        public ShowDto build() {
            return new ShowDto(this.id, this.movieId, this.movieTitle, this.moviePosterUrl, this.movieLanguage, this.movieGenre, this.cinemaId, this.cinemaName, this.cinemaCity, this.screenId, this.screenName, this.startTime, this.endTime, this.basePrice, this.status);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ShowDto.ShowDtoBuilder(id=" + this.id + ", movieId=" + this.movieId + ", movieTitle=" + this.movieTitle + ", moviePosterUrl=" + this.moviePosterUrl + ", movieLanguage=" + this.movieLanguage + ", movieGenre=" + this.movieGenre + ", cinemaId=" + this.cinemaId + ", cinemaName=" + this.cinemaName + ", cinemaCity=" + this.cinemaCity + ", screenId=" + this.screenId + ", screenName=" + this.screenName + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", basePrice=" + this.basePrice + ", status=" + this.status + ")";
        }
    }

    public static ShowDto.ShowDtoBuilder builder() {
        return new ShowDto.ShowDtoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getMovieId() {
        return this.movieId;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public String getMoviePosterUrl() {
        return this.moviePosterUrl;
    }

    public String getMovieLanguage() {
        return this.movieLanguage;
    }

    public String getMovieGenre() {
        return this.movieGenre;
    }

    public Long getCinemaId() {
        return this.cinemaId;
    }

    public String getCinemaName() {
        return this.cinemaName;
    }

    public String getCinemaCity() {
        return this.cinemaCity;
    }

    public Long getScreenId() {
        return this.screenId;
    }

    public String getScreenName() {
        return this.screenName;
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

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMovieId(final Long movieId) {
        this.movieId = movieId;
    }

    public void setMovieTitle(final String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMoviePosterUrl(final String moviePosterUrl) {
        this.moviePosterUrl = moviePosterUrl;
    }

    public void setMovieLanguage(final String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public void setMovieGenre(final String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public void setCinemaId(final Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setCinemaName(final String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setCinemaCity(final String cinemaCity) {
        this.cinemaCity = cinemaCity;
    }

    public void setScreenId(final Long screenId) {
        this.screenId = screenId;
    }

    public void setScreenName(final String screenName) {
        this.screenName = screenName;
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

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ShowDto)) return false;
        final ShowDto other = (ShowDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$movieId = this.getMovieId();
        final java.lang.Object other$movieId = other.getMovieId();
        if (this$movieId == null ? other$movieId != null : !this$movieId.equals(other$movieId)) return false;
        final java.lang.Object this$cinemaId = this.getCinemaId();
        final java.lang.Object other$cinemaId = other.getCinemaId();
        if (this$cinemaId == null ? other$cinemaId != null : !this$cinemaId.equals(other$cinemaId)) return false;
        final java.lang.Object this$screenId = this.getScreenId();
        final java.lang.Object other$screenId = other.getScreenId();
        if (this$screenId == null ? other$screenId != null : !this$screenId.equals(other$screenId)) return false;
        final java.lang.Object this$movieTitle = this.getMovieTitle();
        final java.lang.Object other$movieTitle = other.getMovieTitle();
        if (this$movieTitle == null ? other$movieTitle != null : !this$movieTitle.equals(other$movieTitle)) return false;
        final java.lang.Object this$moviePosterUrl = this.getMoviePosterUrl();
        final java.lang.Object other$moviePosterUrl = other.getMoviePosterUrl();
        if (this$moviePosterUrl == null ? other$moviePosterUrl != null : !this$moviePosterUrl.equals(other$moviePosterUrl)) return false;
        final java.lang.Object this$movieLanguage = this.getMovieLanguage();
        final java.lang.Object other$movieLanguage = other.getMovieLanguage();
        if (this$movieLanguage == null ? other$movieLanguage != null : !this$movieLanguage.equals(other$movieLanguage)) return false;
        final java.lang.Object this$movieGenre = this.getMovieGenre();
        final java.lang.Object other$movieGenre = other.getMovieGenre();
        if (this$movieGenre == null ? other$movieGenre != null : !this$movieGenre.equals(other$movieGenre)) return false;
        final java.lang.Object this$cinemaName = this.getCinemaName();
        final java.lang.Object other$cinemaName = other.getCinemaName();
        if (this$cinemaName == null ? other$cinemaName != null : !this$cinemaName.equals(other$cinemaName)) return false;
        final java.lang.Object this$cinemaCity = this.getCinemaCity();
        final java.lang.Object other$cinemaCity = other.getCinemaCity();
        if (this$cinemaCity == null ? other$cinemaCity != null : !this$cinemaCity.equals(other$cinemaCity)) return false;
        final java.lang.Object this$screenName = this.getScreenName();
        final java.lang.Object other$screenName = other.getScreenName();
        if (this$screenName == null ? other$screenName != null : !this$screenName.equals(other$screenName)) return false;
        final java.lang.Object this$startTime = this.getStartTime();
        final java.lang.Object other$startTime = other.getStartTime();
        if (this$startTime == null ? other$startTime != null : !this$startTime.equals(other$startTime)) return false;
        final java.lang.Object this$endTime = this.getEndTime();
        final java.lang.Object other$endTime = other.getEndTime();
        if (this$endTime == null ? other$endTime != null : !this$endTime.equals(other$endTime)) return false;
        final java.lang.Object this$basePrice = this.getBasePrice();
        final java.lang.Object other$basePrice = other.getBasePrice();
        if (this$basePrice == null ? other$basePrice != null : !this$basePrice.equals(other$basePrice)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ShowDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $movieId = this.getMovieId();
        result = result * PRIME + ($movieId == null ? 43 : $movieId.hashCode());
        final java.lang.Object $cinemaId = this.getCinemaId();
        result = result * PRIME + ($cinemaId == null ? 43 : $cinemaId.hashCode());
        final java.lang.Object $screenId = this.getScreenId();
        result = result * PRIME + ($screenId == null ? 43 : $screenId.hashCode());
        final java.lang.Object $movieTitle = this.getMovieTitle();
        result = result * PRIME + ($movieTitle == null ? 43 : $movieTitle.hashCode());
        final java.lang.Object $moviePosterUrl = this.getMoviePosterUrl();
        result = result * PRIME + ($moviePosterUrl == null ? 43 : $moviePosterUrl.hashCode());
        final java.lang.Object $movieLanguage = this.getMovieLanguage();
        result = result * PRIME + ($movieLanguage == null ? 43 : $movieLanguage.hashCode());
        final java.lang.Object $movieGenre = this.getMovieGenre();
        result = result * PRIME + ($movieGenre == null ? 43 : $movieGenre.hashCode());
        final java.lang.Object $cinemaName = this.getCinemaName();
        result = result * PRIME + ($cinemaName == null ? 43 : $cinemaName.hashCode());
        final java.lang.Object $cinemaCity = this.getCinemaCity();
        result = result * PRIME + ($cinemaCity == null ? 43 : $cinemaCity.hashCode());
        final java.lang.Object $screenName = this.getScreenName();
        result = result * PRIME + ($screenName == null ? 43 : $screenName.hashCode());
        final java.lang.Object $startTime = this.getStartTime();
        result = result * PRIME + ($startTime == null ? 43 : $startTime.hashCode());
        final java.lang.Object $endTime = this.getEndTime();
        result = result * PRIME + ($endTime == null ? 43 : $endTime.hashCode());
        final java.lang.Object $basePrice = this.getBasePrice();
        result = result * PRIME + ($basePrice == null ? 43 : $basePrice.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ShowDto(id=" + this.getId() + ", movieId=" + this.getMovieId() + ", movieTitle=" + this.getMovieTitle() + ", moviePosterUrl=" + this.getMoviePosterUrl() + ", movieLanguage=" + this.getMovieLanguage() + ", movieGenre=" + this.getMovieGenre() + ", cinemaId=" + this.getCinemaId() + ", cinemaName=" + this.getCinemaName() + ", cinemaCity=" + this.getCinemaCity() + ", screenId=" + this.getScreenId() + ", screenName=" + this.getScreenName() + ", startTime=" + this.getStartTime() + ", endTime=" + this.getEndTime() + ", basePrice=" + this.getBasePrice() + ", status=" + this.getStatus() + ")";
    }

    public ShowDto() {
    }

    public ShowDto(final Long id, final Long movieId, final String movieTitle, final String moviePosterUrl, final String movieLanguage, final String movieGenre, final Long cinemaId, final String cinemaName, final String cinemaCity, final Long screenId, final String screenName, final Instant startTime, final Instant endTime, final BigDecimal basePrice, final ShowStatus status) {
        this.id = id;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.moviePosterUrl = moviePosterUrl;
        this.movieLanguage = movieLanguage;
        this.movieGenre = movieGenre;
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
        this.cinemaCity = cinemaCity;
        this.screenId = screenId;
        this.screenName = screenName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.basePrice = basePrice;
        this.status = status;
    }
}
