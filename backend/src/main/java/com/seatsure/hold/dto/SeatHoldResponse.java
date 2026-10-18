package com.seatsure.hold.dto;

import com.seatsure.hold.HoldStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class SeatHoldResponse {
    private Long holdId;
    private Long showId;
    private Long userId;
    private List<Long> seatIds;
    private BigDecimal totalAmount;
    private HoldStatus status;
    private Instant createdAt;
    private Instant expiresAt;
    private long expiresInSeconds;


    public static class SeatHoldResponseBuilder {
        private Long holdId;
        private Long showId;
        private Long userId;
        private List<Long> seatIds;
        private BigDecimal totalAmount;
        private HoldStatus status;
        private Instant createdAt;
        private Instant expiresAt;
        private long expiresInSeconds;

        SeatHoldResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder holdId(final Long holdId) {
            this.holdId = holdId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder showId(final Long showId) {
            this.showId = showId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder userId(final Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder seatIds(final List<Long> seatIds) {
            this.seatIds = seatIds;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder totalAmount(final BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder status(final HoldStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder expiresAt(final Instant expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public SeatHoldResponse.SeatHoldResponseBuilder expiresInSeconds(final long expiresInSeconds) {
            this.expiresInSeconds = expiresInSeconds;
            return this;
        }

        public SeatHoldResponse build() {
            return new SeatHoldResponse(this.holdId, this.showId, this.userId, this.seatIds, this.totalAmount, this.status, this.createdAt, this.expiresAt, this.expiresInSeconds);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "SeatHoldResponse.SeatHoldResponseBuilder(holdId=" + this.holdId + ", showId=" + this.showId + ", userId=" + this.userId + ", seatIds=" + this.seatIds + ", totalAmount=" + this.totalAmount + ", status=" + this.status + ", createdAt=" + this.createdAt + ", expiresAt=" + this.expiresAt + ", expiresInSeconds=" + this.expiresInSeconds + ")";
        }
    }

    public static SeatHoldResponse.SeatHoldResponseBuilder builder() {
        return new SeatHoldResponse.SeatHoldResponseBuilder();
    }

    public Long getHoldId() {
        return this.holdId;
    }

    public Long getShowId() {
        return this.showId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public List<Long> getSeatIds() {
        return this.seatIds;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public HoldStatus getStatus() {
        return this.status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getExpiresAt() {
        return this.expiresAt;
    }

    public long getExpiresInSeconds() {
        return this.expiresInSeconds;
    }

    public void setHoldId(final Long holdId) {
        this.holdId = holdId;
    }

    public void setShowId(final Long showId) {
        this.showId = showId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setSeatIds(final List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(final HoldStatus status) {
        this.status = status;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(final Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setExpiresInSeconds(final long expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof SeatHoldResponse)) return false;
        final SeatHoldResponse other = (SeatHoldResponse) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getExpiresInSeconds() != other.getExpiresInSeconds()) return false;
        final java.lang.Object this$holdId = this.getHoldId();
        final java.lang.Object other$holdId = other.getHoldId();
        if (this$holdId == null ? other$holdId != null : !this$holdId.equals(other$holdId)) return false;
        final java.lang.Object this$showId = this.getShowId();
        final java.lang.Object other$showId = other.getShowId();
        if (this$showId == null ? other$showId != null : !this$showId.equals(other$showId)) return false;
        final java.lang.Object this$userId = this.getUserId();
        final java.lang.Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final java.lang.Object this$seatIds = this.getSeatIds();
        final java.lang.Object other$seatIds = other.getSeatIds();
        if (this$seatIds == null ? other$seatIds != null : !this$seatIds.equals(other$seatIds)) return false;
        final java.lang.Object this$totalAmount = this.getTotalAmount();
        final java.lang.Object other$totalAmount = other.getTotalAmount();
        if (this$totalAmount == null ? other$totalAmount != null : !this$totalAmount.equals(other$totalAmount)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final java.lang.Object this$expiresAt = this.getExpiresAt();
        final java.lang.Object other$expiresAt = other.getExpiresAt();
        if (this$expiresAt == null ? other$expiresAt != null : !this$expiresAt.equals(other$expiresAt)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof SeatHoldResponse;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $expiresInSeconds = this.getExpiresInSeconds();
        result = result * PRIME + (int) ($expiresInSeconds >>> 32 ^ $expiresInSeconds);
        final java.lang.Object $holdId = this.getHoldId();
        result = result * PRIME + ($holdId == null ? 43 : $holdId.hashCode());
        final java.lang.Object $showId = this.getShowId();
        result = result * PRIME + ($showId == null ? 43 : $showId.hashCode());
        final java.lang.Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final java.lang.Object $seatIds = this.getSeatIds();
        result = result * PRIME + ($seatIds == null ? 43 : $seatIds.hashCode());
        final java.lang.Object $totalAmount = this.getTotalAmount();
        result = result * PRIME + ($totalAmount == null ? 43 : $totalAmount.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final java.lang.Object $expiresAt = this.getExpiresAt();
        result = result * PRIME + ($expiresAt == null ? 43 : $expiresAt.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SeatHoldResponse(holdId=" + this.getHoldId() + ", showId=" + this.getShowId() + ", userId=" + this.getUserId() + ", seatIds=" + this.getSeatIds() + ", totalAmount=" + this.getTotalAmount() + ", status=" + this.getStatus() + ", createdAt=" + this.getCreatedAt() + ", expiresAt=" + this.getExpiresAt() + ", expiresInSeconds=" + this.getExpiresInSeconds() + ")";
    }

    public SeatHoldResponse() {
    }

    public SeatHoldResponse(final Long holdId, final Long showId, final Long userId, final List<Long> seatIds, final BigDecimal totalAmount, final HoldStatus status, final Instant createdAt, final Instant expiresAt, final long expiresInSeconds) {
        this.holdId = holdId;
        this.showId = showId;
        this.userId = userId;
        this.seatIds = seatIds;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.expiresInSeconds = expiresInSeconds;
    }
}
