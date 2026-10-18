package com.seatsure.hold.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class CreateHoldRequest {
    @NotEmpty(message = "At least one seat must be selected")
    private List<Long> seatIds;


    public static class CreateHoldRequestBuilder {
        private List<Long> seatIds;

        CreateHoldRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public CreateHoldRequest.CreateHoldRequestBuilder seatIds(final List<Long> seatIds) {
            this.seatIds = seatIds;
            return this;
        }

        public CreateHoldRequest build() {
            return new CreateHoldRequest(this.seatIds);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "CreateHoldRequest.CreateHoldRequestBuilder(seatIds=" + this.seatIds + ")";
        }
    }

    public static CreateHoldRequest.CreateHoldRequestBuilder builder() {
        return new CreateHoldRequest.CreateHoldRequestBuilder();
    }

    public List<Long> getSeatIds() {
        return this.seatIds;
    }

    public void setSeatIds(final List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateHoldRequest)) return false;
        final CreateHoldRequest other = (CreateHoldRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$seatIds = this.getSeatIds();
        final java.lang.Object other$seatIds = other.getSeatIds();
        if (this$seatIds == null ? other$seatIds != null : !this$seatIds.equals(other$seatIds)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof CreateHoldRequest;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $seatIds = this.getSeatIds();
        result = result * PRIME + ($seatIds == null ? 43 : $seatIds.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CreateHoldRequest(seatIds=" + this.getSeatIds() + ")";
    }

    public CreateHoldRequest() {
    }

    public CreateHoldRequest(final List<Long> seatIds) {
        this.seatIds = seatIds;
    }
}
