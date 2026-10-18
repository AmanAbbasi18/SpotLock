package com.seatsure.cinema.dto;

import com.seatsure.cinema.Screen;

public class ScreenDto {
    private Long id;
    private Long cinemaId;
    private String name;
    private int totalSeats;
    private String status;

    public static ScreenDto fromEntity(Screen screen) {
        return ScreenDto.builder().id(screen.getId()).cinemaId(screen.getCinema().getId()).name(screen.getName()).totalSeats(screen.getTotalSeats()).status(screen.getStatus()).build();
    }


    public static class ScreenDtoBuilder {
        private Long id;
        private Long cinemaId;
        private String name;
        private int totalSeats;
        private String status;

        ScreenDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public ScreenDto.ScreenDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ScreenDto.ScreenDtoBuilder cinemaId(final Long cinemaId) {
            this.cinemaId = cinemaId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ScreenDto.ScreenDtoBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ScreenDto.ScreenDtoBuilder totalSeats(final int totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ScreenDto.ScreenDtoBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public ScreenDto build() {
            return new ScreenDto(this.id, this.cinemaId, this.name, this.totalSeats, this.status);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ScreenDto.ScreenDtoBuilder(id=" + this.id + ", cinemaId=" + this.cinemaId + ", name=" + this.name + ", totalSeats=" + this.totalSeats + ", status=" + this.status + ")";
        }
    }

    public static ScreenDto.ScreenDtoBuilder builder() {
        return new ScreenDto.ScreenDtoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getCinemaId() {
        return this.cinemaId;
    }

    public String getName() {
        return this.name;
    }

    public int getTotalSeats() {
        return this.totalSeats;
    }

    public String getStatus() {
        return this.status;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCinemaId(final Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTotalSeats(final int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ScreenDto)) return false;
        final ScreenDto other = (ScreenDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getTotalSeats() != other.getTotalSeats()) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$cinemaId = this.getCinemaId();
        final java.lang.Object other$cinemaId = other.getCinemaId();
        if (this$cinemaId == null ? other$cinemaId != null : !this$cinemaId.equals(other$cinemaId)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ScreenDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getTotalSeats();
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $cinemaId = this.getCinemaId();
        result = result * PRIME + ($cinemaId == null ? 43 : $cinemaId.hashCode());
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ScreenDto(id=" + this.getId() + ", cinemaId=" + this.getCinemaId() + ", name=" + this.getName() + ", totalSeats=" + this.getTotalSeats() + ", status=" + this.getStatus() + ")";
    }

    public ScreenDto() {
    }

    public ScreenDto(final Long id, final Long cinemaId, final String name, final int totalSeats, final String status) {
        this.id = id;
        this.cinemaId = cinemaId;
        this.name = name;
        this.totalSeats = totalSeats;
        this.status = status;
    }
}
