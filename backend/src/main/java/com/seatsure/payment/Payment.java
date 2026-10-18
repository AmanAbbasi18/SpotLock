package com.seatsure.payment;

import com.seatsure.booking.Booking;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
    @Column(name = "payment_reference", nullable = false, unique = true, length = 64)
    private String paymentReference;
    @Column(name = "idempotency_key", nullable = false, unique = true, length = 128)
    private String idempotencyKey;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false, length = 3)
    private String currency;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;
    @Column(name = "provider_reference", length = 128)
    private String providerReference;
    @Column(name = "failure_reason")
    private String failureReason;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    private static String $default$currency() {
        return "INR";
    }

    private static PaymentStatus $default$status() {
        return PaymentStatus.INITIATED;
    }


    public static class PaymentBuilder {
        private Long id;
        private Booking booking;
        private String paymentReference;
        private String idempotencyKey;
        private BigDecimal amount;
        private boolean currency$set;
        private String currency$value;
        private boolean status$set;
        private PaymentStatus status$value;
        private String providerReference;
        private String failureReason;
        private Instant createdAt;
        private Instant updatedAt;

        PaymentBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder booking(final Booking booking) {
            this.booking = booking;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder paymentReference(final String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder idempotencyKey(final String idempotencyKey) {
            this.idempotencyKey = idempotencyKey;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder currency(final String currency) {
            this.currency$value = currency;
            currency$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder status(final PaymentStatus status) {
            this.status$value = status;
            status$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder providerReference(final String providerReference) {
            this.providerReference = providerReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder failureReason(final String failureReason) {
            this.failureReason = failureReason;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public Payment.PaymentBuilder updatedAt(final Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Payment build() {
            String currency$value = this.currency$value;
            if (!this.currency$set) currency$value = Payment.$default$currency();
            PaymentStatus status$value = this.status$value;
            if (!this.status$set) status$value = Payment.$default$status();
            return new Payment(this.id, this.booking, this.paymentReference, this.idempotencyKey, this.amount, currency$value, status$value, this.providerReference, this.failureReason, this.createdAt, this.updatedAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "Payment.PaymentBuilder(id=" + this.id + ", booking=" + this.booking + ", paymentReference=" + this.paymentReference + ", idempotencyKey=" + this.idempotencyKey + ", amount=" + this.amount + ", currency$value=" + this.currency$value + ", status$value=" + this.status$value + ", providerReference=" + this.providerReference + ", failureReason=" + this.failureReason + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }

    public static Payment.PaymentBuilder builder() {
        return new Payment.PaymentBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public Booking getBooking() {
        return this.booking;
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

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setBooking(final Booking booking) {
        this.booking = booking;
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

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(final Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Payment() {
        this.currency = Payment.$default$currency();
        this.status = Payment.$default$status();
    }

    public Payment(final Long id, final Booking booking, final String paymentReference, final String idempotencyKey, final BigDecimal amount, final String currency, final PaymentStatus status, final String providerReference, final String failureReason, final Instant createdAt, final Instant updatedAt) {
        this.id = id;
        this.booking = booking;
        this.paymentReference = paymentReference;
        this.idempotencyKey = idempotencyKey;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.providerReference = providerReference;
        this.failureReason = failureReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
