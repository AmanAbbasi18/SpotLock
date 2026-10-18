package com.seatsure.showtime.dto;

import com.seatsure.cinema.SeatType;
import com.seatsure.showtime.ShowSeat;
import com.seatsure.showtime.ShowSeatStatus;
import java.math.BigDecimal;

public class ShowSeatDto {
    private Long showSeatId;
    private Long seatId;
    private String rowLabel;
    private int seatNumber;
    private SeatType seatType;
    private BigDecimal price;
    private ShowSeatStatus status;
    private boolean isHeldByCurrentUser;

    public static ShowSeatDto fromEntity(ShowSeat showSeat, Long currentUserId, Long currentUserHoldId) {
        boolean heldByMe = false;
        if (showSeat.getStatus() == ShowSeatStatus.HELD && currentUserHoldId != null) {
            heldByMe = currentUserHoldId.equals(showSeat.getCurrentHoldId());
        }
        return ShowSeatDto.builder().showSeatId(showSeat.getId()).seatId(showSeat.getSeat().getId()).rowLabel(showSeat.getSeat().getRowLabel()).seatNumber(showSeat.getSeat().getSeatNumber()).seatType(showSeat.getSeat().getSeatType()).price(showSeat.getPrice()).status(showSeat.getStatus()).isHeldByCurrentUser(heldByMe).build();
    }


    public static class ShowSeatDtoBuilder {
        private Long showSeatId;
        private Long seatId;
        private String rowLabel;
        private int seatNumber;
        private SeatType seatType;
        private BigDecimal price;
        private ShowSeatStatus status;
        private boolean isHeldByCurrentUser;

        ShowSeatDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatDto.ShowSeatDtoBuilder showSeatId(final Long showSeatId) {
            this.showSeatId = showSeatId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatDto.ShowSeatDtoBuilder seatId(final Long seatId) {
            this.seatId = seatId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatDto.ShowSeatDtoBuilder rowLabel(final String rowLabel) {
            this.rowLabel = rowLabel;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatDto.ShowSeatDtoBuilder seatNumber(final int seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatDto.ShowSeatDtoBuilder seatType(final SeatType seatType) {
            this.seatType = seatType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatDto.ShowSeatDtoBuilder price(final BigDecimal price) {
            this.price = price;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatDto.ShowSeatDtoBuilder status(final ShowSeatStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ShowSeatDto.ShowSeatDtoBuilder isHeldByCurrentUser(final boolean isHeldByCurrentUser) {
            this.isHeldByCurrentUser = isHeldByCurrentUser;
            return this;
        }

        public ShowSeatDto build() {
            return new ShowSeatDto(this.showSeatId, this.seatId, this.rowLabel, this.seatNumber, this.seatType, this.price, this.status, this.isHeldByCurrentUser);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ShowSeatDto.ShowSeatDtoBuilder(showSeatId=" + this.showSeatId + ", seatId=" + this.seatId + ", rowLabel=" + this.rowLabel + ", seatNumber=" + this.seatNumber + ", seatType=" + this.seatType + ", price=" + this.price + ", status=" + this.status + ", isHeldByCurrentUser=" + this.isHeldByCurrentUser + ")";
        }
    }

    public static ShowSeatDto.ShowSeatDtoBuilder builder() {
        return new ShowSeatDto.ShowSeatDtoBuilder();
    }

    public Long getShowSeatId() {
        return this.showSeatId;
    }

    public Long getSeatId() {
        return this.seatId;
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

    public BigDecimal getPrice() {
        return this.price;
    }

    public ShowSeatStatus getStatus() {
        return this.status;
    }

    public boolean isHeldByCurrentUser() {
        return this.isHeldByCurrentUser;
    }

    public void setShowSeatId(final Long showSeatId) {
        this.showSeatId = showSeatId;
    }

    public void setSeatId(final Long seatId) {
        this.seatId = seatId;
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

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public void setStatus(final ShowSeatStatus status) {
        this.status = status;
    }

    public void setHeldByCurrentUser(final boolean isHeldByCurrentUser) {
        this.isHeldByCurrentUser = isHeldByCurrentUser;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ShowSeatDto)) return false;
        final ShowSeatDto other = (ShowSeatDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getSeatNumber() != other.getSeatNumber()) return false;
        if (this.isHeldByCurrentUser() != other.isHeldByCurrentUser()) return false;
        final java.lang.Object this$showSeatId = this.getShowSeatId();
        final java.lang.Object other$showSeatId = other.getShowSeatId();
        if (this$showSeatId == null ? other$showSeatId != null : !this$showSeatId.equals(other$showSeatId)) return false;
        final java.lang.Object this$seatId = this.getSeatId();
        final java.lang.Object other$seatId = other.getSeatId();
        if (this$seatId == null ? other$seatId != null : !this$seatId.equals(other$seatId)) return false;
        final java.lang.Object this$rowLabel = this.getRowLabel();
        final java.lang.Object other$rowLabel = other.getRowLabel();
        if (this$rowLabel == null ? other$rowLabel != null : !this$rowLabel.equals(other$rowLabel)) return false;
        final java.lang.Object this$seatType = this.getSeatType();
        final java.lang.Object other$seatType = other.getSeatType();
        if (this$seatType == null ? other$seatType != null : !this$seatType.equals(other$seatType)) return false;
        final java.lang.Object this$price = this.getPrice();
        final java.lang.Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ShowSeatDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getSeatNumber();
        result = result * PRIME + (this.isHeldByCurrentUser() ? 79 : 97);
        final java.lang.Object $showSeatId = this.getShowSeatId();
        result = result * PRIME + ($showSeatId == null ? 43 : $showSeatId.hashCode());
        final java.lang.Object $seatId = this.getSeatId();
        result = result * PRIME + ($seatId == null ? 43 : $seatId.hashCode());
        final java.lang.Object $rowLabel = this.getRowLabel();
        result = result * PRIME + ($rowLabel == null ? 43 : $rowLabel.hashCode());
        final java.lang.Object $seatType = this.getSeatType();
        result = result * PRIME + ($seatType == null ? 43 : $seatType.hashCode());
        final java.lang.Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ShowSeatDto(showSeatId=" + this.getShowSeatId() + ", seatId=" + this.getSeatId() + ", rowLabel=" + this.getRowLabel() + ", seatNumber=" + this.getSeatNumber() + ", seatType=" + this.getSeatType() + ", price=" + this.getPrice() + ", status=" + this.getStatus() + ", isHeldByCurrentUser=" + this.isHeldByCurrentUser() + ")";
    }

    public ShowSeatDto() {
    }

    public ShowSeatDto(final Long showSeatId, final Long seatId, final String rowLabel, final int seatNumber, final SeatType seatType, final BigDecimal price, final ShowSeatStatus status, final boolean isHeldByCurrentUser) {
        this.showSeatId = showSeatId;
        this.seatId = seatId;
        this.rowLabel = rowLabel;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.status = status;
        this.isHeldByCurrentUser = isHeldByCurrentUser;
    }
}
