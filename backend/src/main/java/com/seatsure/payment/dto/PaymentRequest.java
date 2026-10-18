package com.seatsure.payment.dto;

import jakarta.validation.constraints.NotBlank;

public class PaymentRequest {
    @NotBlank(message = "Idempotency key is required to guarantee exactly-once payment processing")
    private String idempotencyKey;
    private String simulationOutcome; // SUCCESS, FAILURE, TIMEOUT
    private String paymentMethod;

    private static String $default$simulationOutcome() {
        return "SUCCESS";
    }

    private static String $default$paymentMethod() {
        return "UPI";
    }


    public static class PaymentRequestBuilder {
        private String idempotencyKey;
        private boolean simulationOutcome$set;
        private String simulationOutcome$value;
        private boolean paymentMethod$set;
        private String paymentMethod$value;

        PaymentRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public PaymentRequest.PaymentRequestBuilder idempotencyKey(final String idempotencyKey) {
            this.idempotencyKey = idempotencyKey;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentRequest.PaymentRequestBuilder simulationOutcome(final String simulationOutcome) {
            this.simulationOutcome$value = simulationOutcome;
            simulationOutcome$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PaymentRequest.PaymentRequestBuilder paymentMethod(final String paymentMethod) {
            this.paymentMethod$value = paymentMethod;
            paymentMethod$set = true;
            return this;
        }

        public PaymentRequest build() {
            String simulationOutcome$value = this.simulationOutcome$value;
            if (!this.simulationOutcome$set) simulationOutcome$value = PaymentRequest.$default$simulationOutcome();
            String paymentMethod$value = this.paymentMethod$value;
            if (!this.paymentMethod$set) paymentMethod$value = PaymentRequest.$default$paymentMethod();
            return new PaymentRequest(this.idempotencyKey, simulationOutcome$value, paymentMethod$value);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "PaymentRequest.PaymentRequestBuilder(idempotencyKey=" + this.idempotencyKey + ", simulationOutcome$value=" + this.simulationOutcome$value + ", paymentMethod$value=" + this.paymentMethod$value + ")";
        }
    }

    public static PaymentRequest.PaymentRequestBuilder builder() {
        return new PaymentRequest.PaymentRequestBuilder();
    }

    public String getIdempotencyKey() {
        return this.idempotencyKey;
    }

    public String getSimulationOutcome() {
        return this.simulationOutcome;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setIdempotencyKey(final String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public void setSimulationOutcome(final String simulationOutcome) {
        this.simulationOutcome = simulationOutcome;
    }

    public void setPaymentMethod(final String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof PaymentRequest)) return false;
        final PaymentRequest other = (PaymentRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$idempotencyKey = this.getIdempotencyKey();
        final java.lang.Object other$idempotencyKey = other.getIdempotencyKey();
        if (this$idempotencyKey == null ? other$idempotencyKey != null : !this$idempotencyKey.equals(other$idempotencyKey)) return false;
        final java.lang.Object this$simulationOutcome = this.getSimulationOutcome();
        final java.lang.Object other$simulationOutcome = other.getSimulationOutcome();
        if (this$simulationOutcome == null ? other$simulationOutcome != null : !this$simulationOutcome.equals(other$simulationOutcome)) return false;
        final java.lang.Object this$paymentMethod = this.getPaymentMethod();
        final java.lang.Object other$paymentMethod = other.getPaymentMethod();
        if (this$paymentMethod == null ? other$paymentMethod != null : !this$paymentMethod.equals(other$paymentMethod)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof PaymentRequest;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $idempotencyKey = this.getIdempotencyKey();
        result = result * PRIME + ($idempotencyKey == null ? 43 : $idempotencyKey.hashCode());
        final java.lang.Object $simulationOutcome = this.getSimulationOutcome();
        result = result * PRIME + ($simulationOutcome == null ? 43 : $simulationOutcome.hashCode());
        final java.lang.Object $paymentMethod = this.getPaymentMethod();
        result = result * PRIME + ($paymentMethod == null ? 43 : $paymentMethod.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PaymentRequest(idempotencyKey=" + this.getIdempotencyKey() + ", simulationOutcome=" + this.getSimulationOutcome() + ", paymentMethod=" + this.getPaymentMethod() + ")";
    }

    public PaymentRequest() {
        this.simulationOutcome = PaymentRequest.$default$simulationOutcome();
        this.paymentMethod = PaymentRequest.$default$paymentMethod();
    }

    public PaymentRequest(final String idempotencyKey, final String simulationOutcome, final String paymentMethod) {
        this.idempotencyKey = idempotencyKey;
        this.simulationOutcome = simulationOutcome;
        this.paymentMethod = paymentMethod;
    }
}
