package com.seatsure.user.dto;

public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresInMs;
    private UserDto user;

    private static String $default$tokenType() {
        return "Bearer";
    }


    public static class AuthResponseBuilder {
        private String accessToken;
        private String refreshToken;
        private boolean tokenType$set;
        private String tokenType$value;
        private long expiresInMs;
        private UserDto user;

        AuthResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder accessToken(final String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder refreshToken(final String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder tokenType(final String tokenType) {
            this.tokenType$value = tokenType;
            tokenType$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder expiresInMs(final long expiresInMs) {
            this.expiresInMs = expiresInMs;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public AuthResponse.AuthResponseBuilder user(final UserDto user) {
            this.user = user;
            return this;
        }

        public AuthResponse build() {
            String tokenType$value = this.tokenType$value;
            if (!this.tokenType$set) tokenType$value = AuthResponse.$default$tokenType();
            return new AuthResponse(this.accessToken, this.refreshToken, tokenType$value, this.expiresInMs, this.user);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "AuthResponse.AuthResponseBuilder(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", tokenType$value=" + this.tokenType$value + ", expiresInMs=" + this.expiresInMs + ", user=" + this.user + ")";
        }
    }

    public static AuthResponse.AuthResponseBuilder builder() {
        return new AuthResponse.AuthResponseBuilder();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public long getExpiresInMs() {
        return this.expiresInMs;
    }

    public UserDto getUser() {
        return this.user;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setTokenType(final String tokenType) {
        this.tokenType = tokenType;
    }

    public void setExpiresInMs(final long expiresInMs) {
        this.expiresInMs = expiresInMs;
    }

    public void setUser(final UserDto user) {
        this.user = user;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof AuthResponse)) return false;
        final AuthResponse other = (AuthResponse) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getExpiresInMs() != other.getExpiresInMs()) return false;
        final java.lang.Object this$accessToken = this.getAccessToken();
        final java.lang.Object other$accessToken = other.getAccessToken();
        if (this$accessToken == null ? other$accessToken != null : !this$accessToken.equals(other$accessToken)) return false;
        final java.lang.Object this$refreshToken = this.getRefreshToken();
        final java.lang.Object other$refreshToken = other.getRefreshToken();
        if (this$refreshToken == null ? other$refreshToken != null : !this$refreshToken.equals(other$refreshToken)) return false;
        final java.lang.Object this$tokenType = this.getTokenType();
        final java.lang.Object other$tokenType = other.getTokenType();
        if (this$tokenType == null ? other$tokenType != null : !this$tokenType.equals(other$tokenType)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof AuthResponse;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $expiresInMs = this.getExpiresInMs();
        result = result * PRIME + (int) ($expiresInMs >>> 32 ^ $expiresInMs);
        final java.lang.Object $accessToken = this.getAccessToken();
        result = result * PRIME + ($accessToken == null ? 43 : $accessToken.hashCode());
        final java.lang.Object $refreshToken = this.getRefreshToken();
        result = result * PRIME + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        final java.lang.Object $tokenType = this.getTokenType();
        result = result * PRIME + ($tokenType == null ? 43 : $tokenType.hashCode());
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "AuthResponse(accessToken=" + this.getAccessToken() + ", refreshToken=" + this.getRefreshToken() + ", tokenType=" + this.getTokenType() + ", expiresInMs=" + this.getExpiresInMs() + ", user=" + this.getUser() + ")";
    }

    public AuthResponse() {
        this.tokenType = AuthResponse.$default$tokenType();
    }

    public AuthResponse(final String accessToken, final String refreshToken, final String tokenType, final long expiresInMs, final UserDto user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.expiresInMs = expiresInMs;
        this.user = user;
    }
}
