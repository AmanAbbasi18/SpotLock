package com.seatsure.booking.dto;

import com.seatsure.cinema.SeatType;
import java.math.BigDecimal;

public class BookingSeatDto {
    private Long showSeatId;
    private String rowLabel;
    private int seatNumber;
    private SeatType seatType;
    private BigDecimal price;


    public static class BookingSeatDtoBuilder {
        private Long showSeatId;
        private String rowLabel;
        private int seatNumber;
        private SeatType seatType;
        private BigDecimal price;

        BookingSeatDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public BookingSeatDto.BookingSeatDtoBuilder showSeatId(final Long showSeatId) {
            this.showSeatId = showSeatId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingSeatDto.BookingSeatDtoBuilder rowLabel(final String rowLabel) {
            this.rowLabel = rowLabel;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingSeatDto.BookingSeatDtoBuilder seatNumber(final int seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingSeatDto.BookingSeatDtoBuilder seatType(final SeatType seatType) {
            this.seatType = seatType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public BookingSeatDto.BookingSeatDtoBuilder price(final BigDecimal price) {
            this.price = price;
            return this;
        }

        public BookingSeatDto build() {
            return new BookingSeatDto(this.showSeatId, this.rowLabel, this.seatNumber, this.seatType, this.price);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "BookingSeatDto.BookingSeatDtoBuilder(showSeatId=" + this.showSeatId + ", rowLabel=" + this.rowLabel + ", seatNumber=" + this.seatNumber + ", seatType=" + this.seatType + ", price=" + this.price + ")";
        }
    }

    public static BookingSeatDto.BookingSeatDtoBuilder builder() {
        return new BookingSeatDto.BookingSeatDtoBuilder();
    }

    public Long getShowSeatId() {
        return this.showSeatId;
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

    public void setShowSeatId(final Long showSeatId) {
        this.showSeatId = showSeatId;
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

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof BookingSeatDto)) return false;
        final BookingSeatDto other = (BookingSeatDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getSeatNumber() != other.getSeatNumber()) return false;
        final java.lang.Object this$showSeatId = this.getShowSeatId();
        final java.lang.Object other$showSeatId = other.getShowSeatId();
        if (this$showSeatId == null ? other$showSeatId != null : !this$showSeatId.equals(other$showSeatId)) return false;
        final java.lang.Object this$rowLabel = this.getRowLabel();
        final java.lang.Object other$rowLabel = other.getRowLabel();
        if (this$rowLabel == null ? other$rowLabel != null : !this$rowLabel.equals(other$rowLabel)) return false;
        final java.lang.Object this$seatType = this.getSeatType();
        final java.lang.Object other$seatType = other.getSeatType();
        if (this$seatType == null ? other$seatType != null : !this$seatType.equals(other$seatType)) return false;
        final java.lang.Object this$price = this.getPrice();
        final java.lang.Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof BookingSeatDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getSeatNumber();
        final java.lang.Object $showSeatId = this.getShowSeatId();
        result = result * PRIME + ($showSeatId == null ? 43 : $showSeatId.hashCode());
        final java.lang.Object $rowLabel = this.getRowLabel();
        result = result * PRIME + ($rowLabel == null ? 43 : $rowLabel.hashCode());
        final java.lang.Object $seatType = this.getSeatType();
        result = result * PRIME + ($seatType == null ? 43 : $seatType.hashCode());
        final java.lang.Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "BookingSeatDto(showSeatId=" + this.getShowSeatId() + ", rowLabel=" + this.getRowLabel() + ", seatNumber=" + this.getSeatNumber() + ", seatType=" + this.getSeatType() + ", price=" + this.getPrice() + ")";
    }

    public BookingSeatDto() {
    }

    public BookingSeatDto(final Long showSeatId, final String rowLabel, final int seatNumber, final SeatType seatType, final BigDecimal price) {
        this.showSeatId = showSeatId;
        this.rowLabel = rowLabel;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
    }
}
