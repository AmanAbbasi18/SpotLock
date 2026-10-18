package com.seatsure.showtime.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

public class CreateShowRequest {
    @NotNull(message = "Movie ID is required")
    private Long movieId;
    @NotNull(message = "Screen ID is required")
    private Long screenId;
    @NotNull(message = "Start time is required")
    private Instant startTime;
    @NotNull(message = "End time is required")
    private Instant endTime;
    @NotNull(message = "Base price is required")
    @DecimalMin(value = "1.00", message = "Base price must be at least 1.00")
    private BigDecimal basePrice;


    public static class CreateShowRequestBuilder {
        private Long movieId;
        private Long screenId;
        private Instant startTime;
        private Instant endTime;
        private BigDecimal basePrice;

        CreateShowRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public CreateShowRequest.CreateShowRequestBuilder movieId(final Long movieId) {
            this.movieId = movieId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CreateShowRequest.CreateShowRequestBuilder screenId(final Long screenId) {
            this.screenId = screenId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CreateShowRequest.CreateShowRequestBuilder startTime(final Instant startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CreateShowRequest.CreateShowRequestBuilder endTime(final Instant endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CreateShowRequest.CreateShowRequestBuilder basePrice(final BigDecimal basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public CreateShowRequest build() {
            return new CreateShowRequest(this.movieId, this.screenId, this.startTime, this.endTime, this.basePrice);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "CreateShowRequest.CreateShowRequestBuilder(movieId=" + this.movieId + ", screenId=" + this.screenId + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", basePrice=" + this.basePrice + ")";
        }
    }

    public static CreateShowRequest.CreateShowRequestBuilder builder() {
        return new CreateShowRequest.CreateShowRequestBuilder();
    }

    public Long getMovieId() {
        return this.movieId;
    }

    public Long getScreenId() {
        return this.screenId;
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

    public void setMovieId(final Long movieId) {
        this.movieId = movieId;
    }

    public void setScreenId(final Long screenId) {
        this.screenId = screenId;
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

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateShowRequest)) return false;
        final CreateShowRequest other = (CreateShowRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$movieId = this.getMovieId();
        final java.lang.Object other$movieId = other.getMovieId();
        if (this$movieId == null ? other$movieId != null : !this$movieId.equals(other$movieId)) return false;
        final java.lang.Object this$screenId = this.getScreenId();
        final java.lang.Object other$screenId = other.getScreenId();
        if (this$screenId == null ? other$screenId != null : !this$screenId.equals(other$screenId)) return false;
        final java.lang.Object this$startTime = this.getStartTime();
        final java.lang.Object other$startTime = other.getStartTime();
        if (this$startTime == null ? other$startTime != null : !this$startTime.equals(other$startTime)) return false;
        final java.lang.Object this$endTime = this.getEndTime();
        final java.lang.Object other$endTime = other.getEndTime();
        if (this$endTime == null ? other$endTime != null : !this$endTime.equals(other$endTime)) return false;
        final java.lang.Object this$basePrice = this.getBasePrice();
        final java.lang.Object other$basePrice = other.getBasePrice();
        if (this$basePrice == null ? other$basePrice != null : !this$basePrice.equals(other$basePrice)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof CreateShowRequest;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $movieId = this.getMovieId();
        result = result * PRIME + ($movieId == null ? 43 : $movieId.hashCode());
        final java.lang.Object $screenId = this.getScreenId();
        result = result * PRIME + ($screenId == null ? 43 : $screenId.hashCode());
        final java.lang.Object $startTime = this.getStartTime();
        result = result * PRIME + ($startTime == null ? 43 : $startTime.hashCode());
        final java.lang.Object $endTime = this.getEndTime();
        result = result * PRIME + ($endTime == null ? 43 : $endTime.hashCode());
        final java.lang.Object $basePrice = this.getBasePrice();
        result = result * PRIME + ($basePrice == null ? 43 : $basePrice.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CreateShowRequest(movieId=" + this.getMovieId() + ", screenId=" + this.getScreenId() + ", startTime=" + this.getStartTime() + ", endTime=" + this.getEndTime() + ", basePrice=" + this.getBasePrice() + ")";
    }

    public CreateShowRequest() {
    }

    public CreateShowRequest(final Long movieId, final Long screenId, final Instant startTime, final Instant endTime, final BigDecimal basePrice) {
        this.movieId = movieId;
        this.screenId = screenId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.basePrice = basePrice;
    }
}
