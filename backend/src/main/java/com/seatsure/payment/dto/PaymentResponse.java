package com.seatsure.payment.dto;

import com.seatsure.booking.BookingStatus;
import com.seatsure.payment.Payment;
import com.seatsure.payment.PaymentStatus;
import java.math.BigDecimal;
import java.time.Instant;

public class PaymentResponse {
    private Long id;
    private Long bookingId;
    private String bookingReference;
    private String paymentReference;
    private String idempotencyKey;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private String providerReference;
    private String failureReason;
    private BookingStatus bookingStatus;
    private Instant createdAt;

    public static PaymentResponse fromEntity(Payment payment) {
        return PaymentResponse.builder().id(payment.getId()).bookingId(payment.getBooking().getId()).bookingReference(payment.getBooking().getBookingReference()).paymentReference(payment.getPaymentReference()).idempotencyKey(payment.getIdempotencyKey()).amount(payment.getAmount()).currency(payment.getCurrency()).status(payment.getStatus()).providerReference(payment.getProviderReference()).failureReason(payment.getFailureReason()).bookingStatus(payment.getBooking().getStatus()).createdAt(payment.getCreatedAt()).build();
    }


    public static class PaymentResponseBuilder {
        private Long id;
        private Long bookingId;
        private String bookingReference;
        private String paymentReference;
        private String idempotencyKey;
        private BigDecimal amount;
        private String currency;
        private PaymentStatus status;
        private String providerReference;
        private String failureReason;
        private BookingStatus bookingStatus;
        private Instant createdAt;

        PaymentResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder bookingId(final Long bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder bookingReference(final String bookingReference) {
            this.bookingReference = bookingReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder paymentReference(final String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder idempotencyKey(final String idempotencyKey) {
            this.idempotencyKey = idempotencyKey;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder currency(final String currency) {
            this.currency = currency;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder status(final PaymentStatus status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder providerReference(final String providerReference) {
            this.providerReference = providerReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder failureReason(final String failureReason) {
            this.failureReason = failureReason;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder bookingStatus(final BookingStatus bookingStatus) {
            this.bookingStatus = bookingStatus;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentResponse.PaymentResponseBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PaymentResponse build() {
            return new PaymentResponse(this.id, this.bookingId, this.bookingReference, this.paymentReference, this.idempotencyKey, this.amount, this.currency, this.status, this.providerReference, this.failureReason, this.bookingStatus, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "PaymentResponse.PaymentResponseBuilder(id=" + this.id + ", bookingId=" + this.bookingId + ", bookingReference=" + this.bookingReference + ", paymentReference=" + this.paymentReference + ", idempotencyKey=" + this.idempotencyKey + ", amount=" + this.amount + ", currency=" + this.currency + ", status=" + this.status + ", providerReference=" + this.providerReference + ", failureReason=" + this.failureReason + ", bookingStatus=" + this.bookingStatus + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static PaymentResponse.PaymentResponseBuilder builder() {
        return new PaymentResponse.PaymentResponseBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Long getBookingId() {
        return this.bookingId;
    }

    public String getBookingReference() {
        return this.bookingReference;
    }

    public String getPaymentReference() {
        return this.paymentReference;
    }

    public String getIdempotencyKey() {
        return this.idempotencyKey;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public String getProviderReference() {
        return this.providerReference;
    }

    public String getFailureReason() {
        return this.failureReason;
    }

    public BookingStatus getBookingStatus() {
        return this.bookingStatus;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setBookingId(final Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setBookingReference(final String bookingReference) {
        this.bookingReference = bookingReference;
    }

    public void setPaymentReference(final String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public void setIdempotencyKey(final String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public void setStatus(final PaymentStatus status) {
        this.status = status;
    }

    public void setProviderReference(final String providerReference) {
        this.providerReference = providerReference;
    }

    public void setFailureReason(final String failureReason) {
        this.failureReason = failureReason;
    }

    public void setBookingStatus(final BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof PaymentResponse)) return false;
        final PaymentResponse other = (PaymentResponse) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$bookingId = this.getBookingId();
        final java.lang.Object other$bookingId = other.getBookingId();
        if (this$bookingId == null ? other$bookingId != null : !this$bookingId.equals(other$bookingId)) return false;
        final java.lang.Object this$bookingReference = this.getBookingReference();
        final java.lang.Object other$bookingReference = other.getBookingReference();
        if (this$bookingReference == null ? other$bookingReference != null : !this$bookingReference.equals(other$bookingReference)) return false;
        final java.lang.Object this$paymentReference = this.getPaymentReference();
        final java.lang.Object other$paymentReference = other.getPaymentReference();
        if (this$paymentReference == null ? other$paymentReference != null : !this$paymentReference.equals(other$paymentReference)) return false;
        final java.lang.Object this$idempotencyKey = this.getIdempotencyKey();
        final java.lang.Object other$idempotencyKey = other.getIdempotencyKey();
        if (this$idempotencyKey == null ? other$idempotencyKey != null : !this$idempotencyKey.equals(other$idempotencyKey)) return false;
        final java.lang.Object this$amount = this.getAmount();
        final java.lang.Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final java.lang.Object this$currency = this.getCurrency();
        final java.lang.Object other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$providerReference = this.getProviderReference();
        final java.lang.Object other$providerReference = other.getProviderReference();
        if (this$providerReference == null ? other$providerReference != null : !this$providerReference.equals(other$providerReference)) return false;
        final java.lang.Object this$failureReason = this.getFailureReason();
        final java.lang.Object other$failureReason = other.getFailureReason();
        if (this$failureReason == null ? other$failureReason != null : !this$failureReason.equals(other$failureReason)) return false;
        final java.lang.Object this$bookingStatus = this.getBookingStatus();
        final java.lang.Object other$bookingStatus = other.getBookingStatus();
        if (this$bookingStatus == null ? other$bookingStatus != null : !this$bookingStatus.equals(other$bookingStatus)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof PaymentResponse;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $bookingId = this.getBookingId();
        result = result * PRIME + ($bookingId == null ? 43 : $bookingId.hashCode());
        final java.lang.Object $bookingReference = this.getBookingReference();
        result = result * PRIME + ($bookingReference == null ? 43 : $bookingReference.hashCode());
        final java.lang.Object $paymentReference = this.getPaymentReference();
        result = result * PRIME + ($paymentReference == null ? 43 : $paymentReference.hashCode());
        final java.lang.Object $idempotencyKey = this.getIdempotencyKey();
        result = result * PRIME + ($idempotencyKey == null ? 43 : $idempotencyKey.hashCode());
        final java.lang.Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final java.lang.Object $currency = this.getCurrency();
        result = result * PRIME + ($currency == null ? 43 : $currency.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $providerReference = this.getProviderReference();
        result = result * PRIME + ($providerReference == null ? 43 : $providerReference.hashCode());
        final java.lang.Object $failureReason = this.getFailureReason();
        result = result * PRIME + ($failureReason == null ? 43 : $failureReason.hashCode());
        final java.lang.Object $bookingStatus = this.getBookingStatus();
        result = result * PRIME + ($bookingStatus == null ? 43 : $bookingStatus.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PaymentResponse(id=" + this.getId() + ", bookingId=" + this.getBookingId() + ", bookingReference=" + this.getBookingReference() + ", paymentReference=" + this.getPaymentReference() + ", idempotencyKey=" + this.getIdempotencyKey() + ", amount=" + this.getAmount() + ", currency=" + this.getCurrency() + ", status=" + this.getStatus() + ", providerReference=" + this.getProviderReference() + ", failureReason=" + this.getFailureReason() + ", bookingStatus=" + this.getBookingStatus() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    public PaymentResponse() {
    }

    public PaymentResponse(final Long id, final Long bookingId, final String bookingReference, final String paymentReference, final String idempotencyKey, final BigDecimal amount, final String currency, final PaymentStatus status, final String providerReference, final String failureReason, final BookingStatus bookingStatus, final Instant createdAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.bookingReference = bookingReference;
        this.paymentReference = paymentReference;
        this.idempotencyKey = idempotencyKey;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.providerReference = providerReference;
        this.failureReason = failureReason;
        this.bookingStatus = bookingStatus;
        this.createdAt = createdAt;
    }
}
