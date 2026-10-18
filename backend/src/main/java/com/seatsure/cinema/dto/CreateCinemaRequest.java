package com.seatsure.cinema.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCinemaRequest {
    @NotBlank(message = "Cinema name is required")
    private String name;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "City is required")
    private String city;


    public static class CreateCinemaRequestBuilder {
        private String name;
        private String address;
        private String city;

        CreateCinemaRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public CreateCinemaRequest.CreateCinemaRequestBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CreateCinemaRequest.CreateCinemaRequestBuilder address(final String address) {
            this.address = address;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CreateCinemaRequest.CreateCinemaRequestBuilder city(final String city) {
            this.city = city;
            return this;
        }

        public CreateCinemaRequest build() {
            return new CreateCinemaRequest(this.name, this.address, this.city);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "CreateCinemaRequest.CreateCinemaRequestBuilder(name=" + this.name + ", address=" + this.address + ", city=" + this.city + ")";
        }
    }

    public static CreateCinemaRequest.CreateCinemaRequestBuilder builder() {
        return new CreateCinemaRequest.CreateCinemaRequestBuilder();
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateCinemaRequest)) return false;
        final CreateCinemaRequest other = (CreateCinemaRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final java.lang.Object this$address = this.getAddress();
        final java.lang.Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final java.lang.Object this$city = this.getCity();
        final java.lang.Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof CreateCinemaRequest;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final java.lang.Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final java.lang.Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CreateCinemaRequest(name=" + this.getName() + ", address=" + this.getAddress() + ", city=" + this.getCity() + ")";
    }

    public CreateCinemaRequest() {
    }

    public CreateCinemaRequest(final String name, final String address, final String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }
}
