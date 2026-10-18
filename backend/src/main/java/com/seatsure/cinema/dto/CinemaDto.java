package com.seatsure.cinema.dto;

import com.seatsure.cinema.Cinema;
import java.time.Instant;

public class CinemaDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String status;
    private Instant createdAt;

    public static CinemaDto fromEntity(Cinema cinema) {
        return CinemaDto.builder().id(cinema.getId()).name(cinema.getName()).address(cinema.getAddress()).city(cinema.getCity()).status(cinema.getStatus()).createdAt(cinema.getCreatedAt()).build();
    }


    public static class CinemaDtoBuilder {
        private Long id;
        private String name;
        private String address;
        private String city;
        private String status;
        private Instant createdAt;

        CinemaDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public CinemaDto.CinemaDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CinemaDto.CinemaDtoBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CinemaDto.CinemaDtoBuilder address(final String address) {
            this.address = address;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CinemaDto.CinemaDtoBuilder city(final String city) {
            this.city = city;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CinemaDto.CinemaDtoBuilder status(final String status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public CinemaDto.CinemaDtoBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CinemaDto build() {
            return new CinemaDto(this.id, this.name, this.address, this.city, this.status, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "CinemaDto.CinemaDtoBuilder(id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", city=" + this.city + ", status=" + this.status + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static CinemaDto.CinemaDtoBuilder builder() {
        return new CinemaDto.CinemaDtoBuilder();
    }

    public Long getId() {
        return this.id;
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

    public String getStatus() {
        return this.status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof CinemaDto)) return false;
        final CinemaDto other = (CinemaDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final java.lang.Object this$address = this.getAddress();
        final java.lang.Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final java.lang.Object this$city = this.getCity();
        final java.lang.Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof CinemaDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final java.lang.Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final java.lang.Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "CinemaDto(id=" + this.getId() + ", name=" + this.getName() + ", address=" + this.getAddress() + ", city=" + this.getCity() + ", status=" + this.getStatus() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    public CinemaDto() {
    }

    public CinemaDto(final Long id, final String name, final String address, final String city, final String status, final Instant createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.status = status;
        this.createdAt = createdAt;
    }
}
