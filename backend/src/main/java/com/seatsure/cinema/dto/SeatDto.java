package com.seatsure.cinema.dto;

import com.seatsure.cinema.Seat;
import com.seatsure.cinema.SeatType;

public class SeatDto {
    private Long id;
    private Long screenId;
    private String rowLabel;
    private int seatNumber;
    private SeatType seatType;
    private String status;

    public static SeatDto fromEntity(Seat seat) {
        return SeatDto.builder().id(seat.getId()).screenId(seat.getScreen().getId()).rowLabel(seat.getRowLabel()).seatNumber(seat.getSeatNumber()).seatType(seat.getSeatType()).status(seat.getStatus()).build();
    }


    public static class SeatDtoBuilder {
        private Long id;
        private Long screenId;
        private String rowLabel;
        private int seatNumber;
        private SeatType seatType;
        private String status;

        SeatDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public SeatDto.SeatDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatDto.SeatDtoBuilder screenId(final Long screenId) {
            this.screenId = screenId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatDto.SeatDtoBuilder rowLabel(final String rowLabel) {
            this.rowLabel = rowLabel;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatDto.SeatDtoBuilder seatNumber(final int seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatDto.SeatDtoBuilder seatType(final SeatType seatType) {
            this.seatType = seatType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatDto.SeatDtoBuilder status(final String status) {
            this.status = status;
            return this;
        }

        public SeatDto build() {
            return new SeatDto(this.id, this.screenId, this.rowLabel, this.seatNumber, this.seatType, this.status);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "SeatDto.SeatDtoBuilder(id=" + this.id + ", screenId=" + this.screenId + ", rowLabel=" + this.rowLabel + ", seatNumber=" + this.seatNumber + ", seatType=" + this.seatType + ", status=" + this.status + ")";
        }
    }

    public static SeatDto.SeatDtoBuilder builder() {
        return new SeatDto.SeatDtoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getScreenId() {
        return this.screenId;
    }

    public String getRowLabel() {
        return this.rowLabel;
    }

    public int getSeatNumber() {
        return this.seatNumber;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public String getStatus() {
        return this.status;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setScreenId(final Long screenId) {
        this.screenId = screenId;
    }

    public void setRowLabel(final String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public void setSeatNumber(final int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setSeatType(final SeatType seatType) {
        this.seatType = seatType;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof SeatDto)) return false;
        final SeatDto other = (SeatDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getSeatNumber() != other.getSeatNumber()) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$screenId = this.getScreenId();
        final java.lang.Object other$screenId = other.getScreenId();
        if (this$screenId == null ? other$screenId != null : !this$screenId.equals(other$screenId)) return false;
        final java.lang.Object this$rowLabel = this.getRowLabel();
        final java.lang.Object other$rowLabel = other.getRowLabel();
        if (this$rowLabel == null ? other$rowLabel != null : !this$rowLabel.equals(other$rowLabel)) return false;
        final java.lang.Object this$seatType = this.getSeatType();
        final java.lang.Object other$seatType = other.getSeatType();
        if (this$seatType == null ? other$seatType != null : !this$seatType.equals(other$seatType)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof SeatDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getSeatNumber();
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $screenId = this.getScreenId();
        result = result * PRIME + ($screenId == null ? 43 : $screenId.hashCode());
        final java.lang.Object $rowLabel = this.getRowLabel();
        result = result * PRIME + ($rowLabel == null ? 43 : $rowLabel.hashCode());
        final java.lang.Object $seatType = this.getSeatType();
        result = result * PRIME + ($seatType == null ? 43 : $seatType.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SeatDto(id=" + this.getId() + ", screenId=" + this.getScreenId() + ", rowLabel=" + this.getRowLabel() + ", seatNumber=" + this.getSeatNumber() + ", seatType=" + this.getSeatType() + ", status=" + this.getStatus() + ")";
    }

    public SeatDto() {
    }

    public SeatDto(final Long id, final Long screenId, final String rowLabel, final int seatNumber, final SeatType seatType, final String status) {
        this.id = id;
        this.screenId = screenId;
        this.rowLabel = rowLabel;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = status;
    }
}
