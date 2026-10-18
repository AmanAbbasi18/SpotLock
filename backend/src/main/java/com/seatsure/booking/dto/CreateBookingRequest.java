package com.seatsure.booking.dto;

import jakarta.validation.constraints.NotNull;

public class CreateBookingRequest {
    @NotNull(message = "Seat hold ID is required")
    private Long holdId;


    public static class CreateBookingRequestBuilder {
        private Long holdId;

        CreateBookingRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public CreateBookingRequest.CreateBookingRequestBuilder holdId(final Long holdId) {
            this.holdId = holdId;
            return this;
        }

        public CreateBookingRequest build() {
            return new CreateBookingRequest(this.holdId);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "CreateBookingRequest.CreateBookingRequestBuilder(holdId=" + this.holdId + ")";
        }
    }

    public static CreateBookingRequest.CreateBookingRequestBuilder builder() {
        return new CreateBookingRequest.CreateBookingRequestBuilder();
    }

    public Long getHoldId() {
        return this.holdId;
    }

    public void setHoldId(final Long holdId) {
        this.holdId = holdId;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateBookingRequest)) return false;
        final CreateBookingRequest other = (CreateBookingRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$holdId = this.getHoldId();
        final java.lang.Object other$holdId = other.getHoldId();
        if (this$holdId == null ? other$holdId != null : !this$holdId.equals(other$holdId)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof CreateBookingRequest;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $holdId = this.getHoldId();
        result = result * PRIME + ($holdId == null ? 43 : $holdId.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CreateBookingRequest(holdId=" + this.getHoldId() + ")";
    }

    public CreateBookingRequest() {
    }

    public CreateBookingRequest(final Long holdId) {
        this.holdId = holdId;
    }
}
