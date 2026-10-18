package com.seatsure.booking.dto;

import com.seatsure.booking.Booking;
import com.seatsure.booking.BookingSeat;
import com.seatsure.booking.BookingStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class BookingResponse {
    private Long id;
    private String bookingReference;
    private Long userId;
    private Long showId;
    private String movieTitle;
    private String moviePosterUrl;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaCity;
    private String screenName;
    private Instant startTime;
    private Instant endTime;
    private BookingStatus status;
    private BigDecimal totalAmount;
    private String currency;
    private List<BookingSeatDto> seats;
    private Instant createdAt;
    private Instant expiresAt;

    public static BookingResponse fromEntity(Booking booking, List<BookingSeat> bookingSeats) {
        List<BookingSeatDto> seatDtos = bookingSeats.stream().map(bs -> BookingSeatDto.builder().showSeatId(bs.getShowSeat().getId()).rowLabel(bs.getShowSeat().getSeat().getRowLabel()).seatNumber(bs.getShowSeat().getSeat().getSeatNumber()).seatType(bs.getShowSeat().getSeat().getSeatType()).price(bs.getPriceAtBooking()).build()).toList();
        return BookingResponse.builder().id(booking.getId()).bookingReference(booking.getBookingReference()).userId(booking.getUser().getId()).showId(booking.getShow().getId()).movieTitle(booking.getShow().getMovie().getTitle()).moviePosterUrl(booking.getShow().getMovie().getPosterUrl()).cinemaName(booking.getShow().getScreen().getCinema().getName()).cinemaAddress(booking.getShow().getScreen().getCinema().getAddress()).cinemaCity(booking.getShow().getScreen().getCinema().getCity()).screenName(booking.getShow().getScreen().getName()).startTime(booking.getShow().getStartTime()).endTime(booking.getShow().getEndTime()).status(booking.getStatus()).totalAmount(booking.getTotalAmount()).currency(booking.getCurrency()).seats(seatDtos).createdAt(booking.getCreatedAt()).expiresAt(booking.getExpiresAt()).build();
    }


    public static class BookingResponseBuilder {
        private Long id;
        private String bookingReference;
        private Long userId;
        private Long showId;
        private String movieTitle;
        private String moviePosterUrl;
        private String cinemaName;
        private String cinemaAddress;
        private String cinemaCity;
        private String screenName;
        private Instant startTime;
        private Instant endTime;
        private BookingStatus status;
        private BigDecimal totalAmount;
        private String currency;
        private List<BookingSeatDto> seats;
        private Instant createdAt;
        private Instant expiresAt;

        BookingResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder bookingReference(final String bookingReference) {
            this.bookingReference = bookingReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder userId(final Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder showId(final Long showId) {
            this.showId = showId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder movieTitle(final String movieTitle) {
            this.movieTitle = movieTitle;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder moviePosterUrl(final String moviePosterUrl) {
            this.moviePosterUrl = moviePosterUrl;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder cinemaName(final String cinemaName) {
            this.cinemaName = cinemaName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder cinemaAddress(final String cinemaAddress) {
            this.cinemaAddress = cinemaAddress;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder cinemaCity(final String cinemaCity) {
            this.cinemaCity = cinemaCity;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder screenName(final String screenName) {
            this.screenName = screenName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder startTime(final Instant startTime) {
            this.startTime = startTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder endTime(final Instant endTime) {
            this.endTime = endTime;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder status(final BookingStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder totalAmount(final BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder currency(final String currency) {
            this.currency = currency;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder seats(final List<BookingSeatDto> seats) {
            this.seats = seats;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingResponse.BookingResponseBuilder expiresAt(final Instant expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public BookingResponse build() {
            return new BookingResponse(this.id, this.bookingReference, this.userId, this.showId, this.movieTitle, this.moviePosterUrl, this.cinemaName, this.cinemaAddress, this.cinemaCity, this.screenName, this.startTime, this.endTime, this.status, this.totalAmount, this.currency, this.seats, this.createdAt, this.expiresAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "BookingResponse.BookingResponseBuilder(id=" + this.id + ", bookingReference=" + this.bookingReference + ", userId=" + this.userId + ", showId=" + this.showId + ", movieTitle=" + this.movieTitle + ", moviePosterUrl=" + this.moviePosterUrl + ", cinemaName=" + this.cinemaName + ", cinemaAddress=" + this.cinemaAddress + ", cinemaCity=" + this.cinemaCity + ", screenName=" + this.screenName + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", status=" + this.status + ", totalAmount=" + this.totalAmount + ", currency=" + this.currency + ", seats=" + this.seats + ", createdAt=" + this.createdAt + ", expiresAt=" + this.expiresAt + ")";
        }
    }

    public static BookingResponse.BookingResponseBuilder builder() {
        return new BookingResponse.BookingResponseBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getBookingReference() {
        return this.bookingReference;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getShowId() {
        return this.showId;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public String getMoviePosterUrl() {
        return this.moviePosterUrl;
    }

    public String getCinemaName() {
        return this.cinemaName;
    }

    public String getCinemaAddress() {
        return this.cinemaAddress;
    }

    public String getCinemaCity() {
        return this.cinemaCity;
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

    public BookingStatus getStatus() {
        return this.status;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public List<BookingSeatDto> getSeats() {
        return this.seats;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getExpiresAt() {
        return this.expiresAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setBookingReference(final String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setShowId(final Long showId) {
        this.showId = showId;
    }

    public void setMovieTitle(final String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMoviePosterUrl(final String moviePosterUrl) {
        this.moviePosterUrl = moviePosterUrl;
    }

    public void setCinemaName(final String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setCinemaAddress(final String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public void setCinemaCity(final String cinemaCity) {
        this.cinemaCity = cinemaCity;
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

    public void setStatus(final BookingStatus status) {
        this.status = status;
    }

    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public void setSeats(final List<BookingSeatDto> seats) {
        this.seats = seats;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(final Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof BookingResponse)) return false;
        final BookingResponse other = (BookingResponse) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$userId = this.getUserId();
        final java.lang.Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final java.lang.Object this$showId = this.getShowId();
        final java.lang.Object other$showId = other.getShowId();
        if (this$showId == null ? other$showId != null : !this$showId.equals(other$showId)) return false;
        final java.lang.Object this$bookingReference = this.getBookingReference();
        final java.lang.Object other$bookingReference = other.getBookingReference();
        if (this$bookingReference == null ? other$bookingReference != null : !this$bookingReference.equals(other$bookingReference)) return false;
        final java.lang.Object this$movieTitle = this.getMovieTitle();
        final java.lang.Object other$movieTitle = other.getMovieTitle();
        if (this$movieTitle == null ? other$movieTitle != null : !this$movieTitle.equals(other$movieTitle)) return false;
        final java.lang.Object this$moviePosterUrl = this.getMoviePosterUrl();
        final java.lang.Object other$moviePosterUrl = other.getMoviePosterUrl();
        if (this$moviePosterUrl == null ? other$moviePosterUrl != null : !this$moviePosterUrl.equals(other$moviePosterUrl)) return false;
        final java.lang.Object this$cinemaName = this.getCinemaName();
        final java.lang.Object other$cinemaName = other.getCinemaName();
        if (this$cinemaName == null ? other$cinemaName != null : !this$cinemaName.equals(other$cinemaName)) return false;
        final java.lang.Object this$cinemaAddress = this.getCinemaAddress();
        final java.lang.Object other$cinemaAddress = other.getCinemaAddress();
        if (this$cinemaAddress == null ? other$cinemaAddress != null : !this$cinemaAddress.equals(other$cinemaAddress)) return false;
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
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$totalAmount = this.getTotalAmount();
        final java.lang.Object other$totalAmount = other.getTotalAmount();
        if (this$totalAmount == null ? other$totalAmount != null : !this$totalAmount.equals(other$totalAmount)) return false;
        final java.lang.Object this$currency = this.getCurrency();
        final java.lang.Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final java.lang.Object this$seats = this.getSeats();
        final java.lang.Object other$seats = other.getSeats();
        if (this$seats == null ? other$seats != null : !this$seats.equals(other$seats)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final java.lang.Object this$expiresAt = this.getExpiresAt();
        final java.lang.Object other$expiresAt = other.getExpiresAt();
        if (this$expiresAt == null ? other$expiresAt != null : !this$expiresAt.equals(other$expiresAt)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof BookingResponse;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final java.lang.Object $showId = this.getShowId();
        result = result * PRIME + ($showId == null ? 43 : $showId.hashCode());
        final java.lang.Object $bookingReference = this.getBookingReference();
        result = result * PRIME + ($bookingReference == null ? 43 : $bookingReference.hashCode());
        final java.lang.Object $movieTitle = this.getMovieTitle();
        result = result * PRIME + ($movieTitle == null ? 43 : $movieTitle.hashCode());
        final java.lang.Object $moviePosterUrl = this.getMoviePosterUrl();
        result = result * PRIME + ($moviePosterUrl == null ? 43 : $moviePosterUrl.hashCode());
        final java.lang.Object $cinemaName = this.getCinemaName();
        result = result * PRIME + ($cinemaName == null ? 43 : $cinemaName.hashCode());
        final java.lang.Object $cinemaAddress = this.getCinemaAddress();
        result = result * PRIME + ($cinemaAddress == null ? 43 : $cinemaAddress.hashCode());
        final java.lang.Object $cinemaCity = this.getCinemaCity();
        result = result * PRIME + ($cinemaCity == null ? 43 : $cinemaCity.hashCode());
        final java.lang.Object $screenName = this.getScreenName();
        result = result * PRIME + ($screenName == null ? 43 : $screenName.hashCode());
        final java.lang.Object $startTime = this.getStartTime();
        result = result * PRIME + ($startTime == null ? 43 : $startTime.hashCode());
        final java.lang.Object $endTime = this.getEndTime();
        result = result * PRIME + ($endTime == null ? 43 : $endTime.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $totalAmount = this.getTotalAmount();
        result = result * PRIME + ($totalAmount == null ? 43 : $totalAmount.hashCode());
        final java.lang.Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final java.lang.Object $seats = this.getSeats();
        result = result * PRIME + ($seats == null ? 43 : $seats.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final java.lang.Object $expiresAt = this.getExpiresAt();
        result = result * PRIME + ($expiresAt == null ? 43 : $expiresAt.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "BookingResponse(id=" + this.getId() + ", bookingReference=" + this.getBookingReference() + ", userId=" + this.getUserId() + ", showId=" + this.getShowId() + ", movieTitle=" + this.getMovieTitle() + ", moviePosterUrl=" + this.getMoviePosterUrl() + ", cinemaName=" + this.getCinemaName() + ", cinemaAddress=" + this.getCinemaAddress() + ", cinemaCity=" + this.getCinemaCity() + ", screenName=" + this.getScreenName() + ", startTime=" + this.getStartTime() + ", endTime=" + this.getEndTime() + ", status=" + this.getStatus() + ", totalAmount=" + this.getTotalAmount() + ", currency=" + this.getCurrency() + ", seats=" + this.getSeats() + ", createdAt=" + this.getCreatedAt() + ", expiresAt=" + this.getExpiresAt() + ")";
    }

    public BookingResponse() {
    }

    public BookingResponse(final Long id, final String bookingReference, final Long userId, final Long showId, final String movieTitle, final String moviePosterUrl, final String cinemaName, final String cinemaAddress, final String cinemaCity, final String screenName, final Instant startTime, final Instant endTime, final BookingStatus status, final BigDecimal totalAmount, final String currency, final List<BookingSeatDto> seats, final Instant createdAt, final Instant expiresAt) {
        this.id = id;
        this.bookingReference = bookingReference;
        this.userId = userId;
        this.showId = showId;
        this.movieTitle = movieTitle;
        this.moviePosterUrl = moviePosterUrl;
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
        this.cinemaCity = cinemaCity;
        this.screenName = screenName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.seats = seats;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
