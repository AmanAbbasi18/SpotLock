package com.seatsure.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private List<String> errors;
    private Instant timestamp;

    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder().success(true).message("Operation successful").data(data).timestamp(Instant.now()).build();
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return ApiResponse.<T>builder().success(true).message(message).data(data).timestamp(Instant.now()).build();
    }

    public static <T> ApiResponse<T> error(String errorCode, String message) {
        return ApiResponse.<T>builder().success(false).errorCode(errorCode).message(message).timestamp(Instant.now()).build();
    }

    public static <T> ApiResponse<T> error(String errorCode, String message, List<String> errors) {
        return ApiResponse.<T>builder().success(false).errorCode(errorCode).message(message).errors(errors).timestamp(Instant.now()).build();
    }

    private static <T> Instant $default$timestamp() {
        return Instant.now();
    }


    public static class ApiResponseBuilder<T> {
        private boolean success;
        private String message;
        private T data;
        private String errorCode;
        private List<String> errors;
        private boolean timestamp$set;
        private Instant timestamp$value;

        ApiResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> success(final boolean success) {
            this.success = success;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> message(final String message) {
            this.message = message;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> errorCode(final String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> errors(final List<String> errors) {
            this.errors = errors;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public ApiResponse.ApiResponseBuilder<T> timestamp(final Instant timestamp) {
            this.timestamp$value = timestamp;
            timestamp$set = true;
            return this;
        }

        public ApiResponse<T> build() {
            Instant timestamp$value = this.timestamp$value;
            if (!this.timestamp$set) timestamp$value = ApiResponse.<T>$default$timestamp();
            return new ApiResponse<T>(this.success, this.message, this.data, this.errorCode, this.errors, timestamp$value);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "ApiResponse.ApiResponseBuilder(success=" + this.success + ", message=" + this.message + ", data=" + this.data + ", errorCode=" + this.errorCode + ", errors=" + this.errors + ", timestamp$value=" + this.timestamp$value + ")";
        }
    }

    public static <T> ApiResponse.ApiResponseBuilder<T> builder() {
        return new ApiResponse.ApiResponseBuilder<T>();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setTimestamp(final Instant timestamp) {
        this.timestamp = timestamp;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ApiResponse)) return false;
        final ApiResponse<?> other = (ApiResponse<?>) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.isSuccess() != other.isSuccess()) return false;
        final java.lang.Object this$message = this.getMessage();
        final java.lang.Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final java.lang.Object this$data = this.getData();
        final java.lang.Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
        final java.lang.Object this$errorCode = this.getErrorCode();
        final java.lang.Object other$errorCode = other.getErrorCode();
        if (this$errorCode == null ? other$errorCode != null : !this$errorCode.equals(other$errorCode)) return false;
        final java.lang.Object this$errors = this.getErrors();
        final java.lang.Object other$errors = other.getErrors();
        if (this$errors == null ? other$errors != null : !this$errors.equals(other$errors)) return false;
        final java.lang.Object this$timestamp = this.getTimestamp();
        final java.lang.Object other$timestamp = other.getTimestamp();
        if (this$timestamp == null ? other$timestamp != null : !this$timestamp.equals(other$timestamp)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ApiResponse;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.isSuccess() ? 79 : 97);
        final java.lang.Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final java.lang.Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        final java.lang.Object $errorCode = this.getErrorCode();
        result = result * PRIME + ($errorCode == null ? 43 : $errorCode.hashCode());
        final java.lang.Object $errors = this.getErrors();
        result = result * PRIME + ($errors == null ? 43 : $errors.hashCode());
        final java.lang.Object $timestamp = this.getTimestamp();
        result = result * PRIME + ($timestamp == null ? 43 : $timestamp.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ApiResponse(success=" + this.isSuccess() + ", message=" + this.getMessage() + ", data=" + this.getData() + ", errorCode=" + this.getErrorCode() + ", errors=" + this.getErrors() + ", timestamp=" + this.getTimestamp() + ")";
    }

    public ApiResponse() {
        this.timestamp = ApiResponse.$default$timestamp();
    }

    public ApiResponse(final boolean success, final String message, final T data, final String errorCode, final List<String> errors, final Instant timestamp) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
        this.errors = errors;
        this.timestamp = timestamp;
    }
}
