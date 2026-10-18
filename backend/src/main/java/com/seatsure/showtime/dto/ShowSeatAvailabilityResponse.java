package com.seatsure.showtime.dto;

import java.util.List;

public class ShowSeatAvailabilityResponse {
    private Long showId;
    private int totalSeats;
    private int availableSeats;
    private int heldSeats;
    private int bookedSeats;
    private List<ShowSeatDto> seats;


    public static class ShowSeatAvailabilityResponseBuilder {
        private Long showId;
        private int totalSeats;
        private int availableSeats;
        private int heldSeats;
        private int bookedSeats;
        private List<ShowSeatDto> seats;

        ShowSeatAvailabilityResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder showId(final Long showId) {
            this.showId = showId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder totalSeats(final int totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder availableSeats(final int availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder heldSeats(final int heldSeats) {
            this.heldSeats = heldSeats;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder bookedSeats(final int bookedSeats) {
            this.bookedSeats = bookedSeats;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder seats(final List<ShowSeatDto> seats) {
            this.seats = seats;
            return this;
        }

        public ShowSeatAvailabilityResponse build() {
            return new ShowSeatAvailabilityResponse(this.showId, this.totalSeats, this.availableSeats, this.heldSeats, this.bookedSeats, this.seats);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder(showId=" + this.showId + ", totalSeats=" + this.totalSeats + ", availableSeats=" + this.availableSeats + ", heldSeats=" + this.heldSeats + ", bookedSeats=" + this.bookedSeats + ", seats=" + this.seats + ")";
        }
    }

    public static ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder builder() {
        return new ShowSeatAvailabilityResponse.ShowSeatAvailabilityResponseBuilder();
    }

    public Long getShowId() {
        return this.showId;
    }

    public int getTotalSeats() {
        return this.totalSeats;
    }

    public int getAvailableSeats() {
        return this.availableSeats;
    }

    public int getHeldSeats() {
        return this.heldSeats;
    }

    public int getBookedSeats() {
        return this.bookedSeats;
    }

    public List<ShowSeatDto> getSeats() {
        return this.seats;
    }

    public void setShowId(final Long showId) {
        this.showId = showId;
    }

    public void setTotalSeats(final int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setAvailableSeats(final int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setHeldSeats(final int heldSeats) {
        this.heldSeats = heldSeats;
    }

    public void setBookedSeats(final int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public void setSeats(final List<ShowSeatDto> seats) {
        this.seats = seats;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ShowSeatAvailabilityResponse)) return false;
        final ShowSeatAvailabilityResponse other = (ShowSeatAvailabilityResponse) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getTotalSeats() != other.getTotalSeats()) return false;
        if (this.getAvailableSeats() != other.getAvailableSeats()) return false;
        if (this.getHeldSeats() != other.getHeldSeats()) return false;
        if (this.getBookedSeats() != other.getBookedSeats()) return false;
        final java.lang.Object this$showId = this.getShowId();
        final java.lang.Object other$showId = other.getShowId();
        if (this$showId == null ? other$showId != null : !this$showId.equals(other$showId)) return false;
        final java.lang.Object this$seats = this.getSeats();
        final java.lang.Object other$seats = other.getSeats();
        if (this$seats == null ? other$seats != null : !this$seats.equals(other$seats)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ShowSeatAvailabilityResponse;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getTotalSeats();
        result = result * PRIME + this.getAvailableSeats();
        result = result * PRIME + this.getHeldSeats();
        result = result * PRIME + this.getBookedSeats();
        final java.lang.Object $showId = this.getShowId();
        result = result * PRIME + ($showId == null ? 43 : $showId.hashCode());
        final java.lang.Object $seats = this.getSeats();
        result = result * PRIME + ($seats == null ? 43 : $seats.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ShowSeatAvailabilityResponse(showId=" + this.getShowId() + ", totalSeats=" + this.getTotalSeats() + ", availableSeats=" + this.getAvailableSeats() + ", heldSeats=" + this.getHeldSeats() + ", bookedSeats=" + this.getBookedSeats() + ", seats=" + this.getSeats() + ")";
    }

    public ShowSeatAvailabilityResponse() {
    }

    public ShowSeatAvailabilityResponse(final Long showId, final int totalSeats, final int availableSeats, final int heldSeats, final int bookedSeats, final List<ShowSeatDto> seats) {
        this.showId = showId;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.heldSeats = heldSeats;
        this.bookedSeats = bookedSeats;
        this.seats = seats;
    }
}
