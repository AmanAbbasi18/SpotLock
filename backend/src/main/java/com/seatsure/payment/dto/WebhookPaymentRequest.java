package com.seatsure.payment.dto;

import jakarta.validation.constraints.NotBlank;

public class WebhookPaymentRequest {
    @NotBlank(message = "Event ID is required")
    private String eventId;
    @NotBlank(message = "Payment reference is required")
    private String paymentReference;
    @NotBlank(message = "Provider status is required")
    private String providerStatus; // SUCCESS, FAILED
    private String providerReference;
    private String signature;


    public static class WebhookPaymentRequestBuilder {
        private String eventId;
        private String paymentReference;
        private String providerStatus;
        private String providerReference;
        private String signature;

        WebhookPaymentRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public WebhookPaymentRequest.WebhookPaymentRequestBuilder eventId(final String eventId) {
            this.eventId = eventId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public WebhookPaymentRequest.WebhookPaymentRequestBuilder paymentReference(final String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public WebhookPaymentRequest.WebhookPaymentRequestBuilder providerStatus(final String providerStatus) {
            this.providerStatus = providerStatus;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public WebhookPaymentRequest.WebhookPaymentRequestBuilder providerReference(final String providerReference) {
            this.providerReference = providerReference;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public WebhookPaymentRequest.WebhookPaymentRequestBuilder signature(final String signature) {
            this.signature = signature;
            return this;
        }

        public WebhookPaymentRequest build() {
            return new WebhookPaymentRequest(this.eventId, this.paymentReference, this.providerStatus, this.providerReference, this.signature);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "WebhookPaymentRequest.WebhookPaymentRequestBuilder(eventId=" + this.eventId + ", paymentReference=" + this.paymentReference + ", providerStatus=" + this.providerStatus + ", providerReference=" + this.providerReference + ", signature=" + this.signature + ")";
        }
    }

    public static WebhookPaymentRequest.WebhookPaymentRequestBuilder builder() {
        return new WebhookPaymentRequest.WebhookPaymentRequestBuilder();
    }

    public String getEventId() {
        return this.eventId;
    }

    public String getPaymentReference() {
        return this.paymentReference;
    }

    public String getProviderStatus() {
        return this.providerStatus;
    }

    public String getProviderReference() {
        return this.providerReference;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setEventId(final String eventId) {
        this.eventId = eventId;
    }

    public void setPaymentReference(final String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public void setProviderStatus(final String providerStatus) {
        this.providerStatus = providerStatus;
    }

    public void setProviderReference(final String providerReference) {
        this.providerReference = providerReference;
    }

    public void setSignature(final String signature) {
        this.signature = signature;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof WebhookPaymentRequest)) return false;
        final WebhookPaymentRequest other = (WebhookPaymentRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$eventId = this.getEventId();
        final java.lang.Object other$eventId = other.getEventId();
        if (this$eventId == null ? other$eventId != null : !this$eventId.equals(other$eventId)) return false;
        final java.lang.Object this$paymentReference = this.getPaymentReference();
        final java.lang.Object other$paymentReference = other.getPaymentReference();
        if (this$paymentReference == null ? other$paymentReference != null : !this$paymentReference.equals(other$paymentReference)) return false;
        final java.lang.Object this$providerStatus = this.getProviderStatus();
        final java.lang.Object other$providerStatus = other.getProviderStatus();
        if (this$providerStatus == null ? other$providerStatus != null : !this$providerStatus.equals(other$providerStatus)) return false;
        final java.lang.Object this$providerReference = this.getProviderReference();
        final java.lang.Object other$providerReference = other.getProviderReference();
        if (this$providerReference == null ? other$providerReference != null : !this$providerReference.equals(other$providerReference)) return false;
        final java.lang.Object this$signature = this.getSignature();
        final java.lang.Object other$signature = other.getSignature();
        if (this$signature == null ? other$signature != null : !this$signature.equals(other$signature)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof WebhookPaymentRequest;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $eventId = this.getEventId();
        result = result * PRIME + ($eventId == null ? 43 : $eventId.hashCode());
        final java.lang.Object $paymentReference = this.getPaymentReference();
        result = result * PRIME + ($paymentReference == null ? 43 : $paymentReference.hashCode());
        final java.lang.Object $providerStatus = this.getProviderStatus();
        result = result * PRIME + ($providerStatus == null ? 43 : $providerStatus.hashCode());
        final java.lang.Object $providerReference = this.getProviderReference();
        result = result * PRIME + ($providerReference == null ? 43 : $providerReference.hashCode());
        final java.lang.Object $signature = this.getSignature();
        result = result * PRIME + ($signature == null ? 43 : $signature.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "WebhookPaymentRequest(eventId=" + this.getEventId() + ", paymentReference=" + this.getPaymentReference() + ", providerStatus=" + this.getProviderStatus() + ", providerReference=" + this.getProviderReference() + ", signature=" + this.getSignature() + ")";
    }

    public WebhookPaymentRequest() {
    }

    public WebhookPaymentRequest(final String eventId, final String paymentReference, final String providerStatus, final String providerReference, final String signature) {
        this.eventId = eventId;
        this.paymentReference = paymentReference;
        this.providerStatus = providerStatus;
        this.providerReference = providerReference;
        this.signature = signature;
    }
}
