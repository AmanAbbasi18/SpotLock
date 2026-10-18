package com.seatsure.payment;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.UUID;

@Component
public class SimulatedPaymentProvider {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SimulatedPaymentProvider.class);


    public static class ProviderResult {
        private boolean success;
        private boolean timeout;
        private String providerReference;
        private String errorMessage;


        public static class ProviderResultBuilder {
            private boolean success;
            private boolean timeout;
            private String providerReference;
            private String errorMessage;

            ProviderResultBuilder() {
            }

            /**
             * @return {@code this}.
             */
            public SimulatedPaymentProvider.ProviderResult.ProviderResultBuilder success(final boolean success) {
                this.success = success;
                return this;
            }

            /**
             * @return {@code this}.
             */
            public SimulatedPaymentProvider.ProviderResult.ProviderResultBuilder timeout(final boolean timeout) {
                this.timeout = timeout;
                return this;
            }

            /**
             * @return {@code this}.
             */
            public SimulatedPaymentProvider.ProviderResult.ProviderResultBuilder providerReference(final String providerReference) {
                this.providerReference = providerReference;
                return this;
            }

            /**
             * @return {@code this}.
             */
            public SimulatedPaymentProvider.ProviderResult.ProviderResultBuilder errorMessage(final String errorMessage) {
                this.errorMessage = errorMessage;
                return this;
            }

            public SimulatedPaymentProvider.ProviderResult build() {
                return new SimulatedPaymentProvider.ProviderResult(this.success, this.timeout, this.providerReference, this.errorMessage);
            }

            @java.lang.Override
            public java.lang.String toString() {
                return "SimulatedPaymentProvider.ProviderResult.ProviderResultBuilder(success=" + this.success + ", timeout=" + this.timeout + ", providerReference=" + this.providerReference + ", errorMessage=" + this.errorMessage + ")";
            }
        }

        public static SimulatedPaymentProvider.ProviderResult.ProviderResultBuilder builder() {
            return new SimulatedPaymentProvider.ProviderResult.ProviderResultBuilder();
        }

        public boolean isSuccess() {
            return this.success;
        }

        public boolean isTimeout() {
            return this.timeout;
        }

        public String getProviderReference() {
            return this.providerReference;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public void setSuccess(final boolean success) {
            this.success = success;
        }

        public void setTimeout(final boolean timeout) {
            this.timeout = timeout;
        }

        public void setProviderReference(final String providerReference) {
            this.providerReference = providerReference;
        }

        public void setErrorMessage(final String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof SimulatedPaymentProvider.ProviderResult)) return false;
            final SimulatedPaymentProvider.ProviderResult other = (SimulatedPaymentProvider.ProviderResult) o;
            if (!other.canEqual((java.lang.Object) this)) return false;
            if (this.isSuccess() != other.isSuccess()) return false;
            if (this.isTimeout() != other.isTimeout()) return false;
            final java.lang.Object this$providerReference = this.getProviderReference();
            final java.lang.Object other$providerReference = other.getProviderReference();
            if (this$providerReference == null ? other$providerReference != null : !this$providerReference.equals(other$providerReference)) return false;
            final java.lang.Object this$errorMessage = this.getErrorMessage();
            final java.lang.Object other$errorMessage = other.getErrorMessage();
            if (this$errorMessage == null ? other$errorMessage != null : !this$errorMessage.equals(other$errorMessage)) return false;
            return true;
        }

        protected boolean canEqual(final java.lang.Object other) {
            return other instanceof SimulatedPaymentProvider.ProviderResult;
        }

        @java.lang.Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            result = result * PRIME + (this.isSuccess() ? 79 : 97);
            result = result * PRIME + (this.isTimeout() ? 79 : 97);
            final java.lang.Object $providerReference = this.getProviderReference();
            result = result * PRIME + ($providerReference == null ? 43 : $providerReference.hashCode());
            final java.lang.Object $errorMessage = this.getErrorMessage();
            result = result * PRIME + ($errorMessage == null ? 43 : $errorMessage.hashCode());
            return result;
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "SimulatedPaymentProvider.ProviderResult(success=" + this.isSuccess() + ", timeout=" + this.isTimeout() + ", providerReference=" + this.getProviderReference() + ", errorMessage=" + this.getErrorMessage() + ")";
        }

        public ProviderResult() {
        }

        public ProviderResult(final boolean success, final boolean timeout, final String providerReference, final String errorMessage) {
            this.success = success;
            this.timeout = timeout;
            this.providerReference = providerReference;
            this.errorMessage = errorMessage;
        }
    }

    public ProviderResult processPayment(String paymentReference, BigDecimal amount, String outcomeOverride) {
        log.info("Simulating payment request for reference: {} amount: {} outcome: {}", paymentReference, amount, outcomeOverride);
        String outcome = (outcomeOverride != null && !outcomeOverride.isBlank()) ? outcomeOverride.toUpperCase().trim() : "SUCCESS";
        return switch (outcome) {
            case "FAILURE" -> ProviderResult.builder().success(false).timeout(false).providerReference("PRV_FAIL_" + UUID.randomUUID().toString().substring(0, 8)).errorMessage("Payment declined: Insufficient funds or card declined by issuing bank").build();
            case "TIMEOUT" -> ProviderResult.builder().success(false).timeout(true).providerReference("PRV_TIME_" + UUID.randomUUID().toString().substring(0, 8)).errorMessage("Payment gateway request timed out. Status pending reconciliation").build();
            default -> ProviderResult.builder().success(true).timeout(false).providerReference("PRV_SUCC_" + UUID.randomUUID().toString().substring(0, 8)).errorMessage(null).build();
        };
    }
}
