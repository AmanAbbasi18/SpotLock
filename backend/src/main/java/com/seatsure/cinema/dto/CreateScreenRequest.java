package com.seatsure.cinema.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateScreenRequest {
    @NotBlank(message = "Screen name is required")
    private String name;
    @NotNull(message = "Total seats is required")
    @Min(value = 1, message = "Screen must have at least 1 seat")
    private Integer totalSeats;


    public static class CreateScreenRequestBuilder {
        private String name;
        private Integer totalSeats;

        CreateScreenRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public CreateScreenRequest.CreateScreenRequestBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CreateScreenRequest.CreateScreenRequestBuilder totalSeats(final Integer totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public CreateScreenRequest build() {
            return new CreateScreenRequest(this.name, this.totalSeats);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "CreateScreenRequest.CreateScreenRequestBuilder(name=" + this.name + ", totalSeats=" + this.totalSeats + ")";
        }
    }

    public static CreateScreenRequest.CreateScreenRequestBuilder builder() {
        return new CreateScreenRequest.CreateScreenRequestBuilder();
    }

    public String getName() {
        return this.name;
    }

    public Integer getTotalSeats() {
        return this.totalSeats;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTotalSeats(final Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateScreenRequest)) return false;
        final CreateScreenRequest other = (CreateScreenRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$totalSeats = this.getTotalSeats();
        final java.lang.Object other$totalSeats = other.getTotalSeats();
        if (this$totalSeats == null ? other$totalSeats != null : !this$totalSeats.equals(other$totalSeats)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof CreateScreenRequest;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $totalSeats = this.getTotalSeats();
        result = result * PRIME + ($totalSeats == null ? 43 : $totalSeats.hashCode());
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CreateScreenRequest(name=" + this.getName() + ", totalSeats=" + this.getTotalSeats() + ")";
    }

    public CreateScreenRequest() {
    }

    public CreateScreenRequest(final String name, final Integer totalSeats) {
        this.name = name;
        this.totalSeats = totalSeats;
    }
}
