package com.seatsure.user.dto;

import com.seatsure.user.Role;
import com.seatsure.user.User;
import java.time.Instant;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private boolean enabled;
    private Instant createdAt;

    public static UserDto fromEntity(User user) {
        return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).role(user.getRole()).enabled(user.isEnabled()).createdAt(user.getCreatedAt()).build();
    }


    public static class UserDtoBuilder {
        private Long id;
        private String name;
        private String email;
        private Role role;
        private boolean enabled;
        private Instant createdAt;

        UserDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder email(final String email) {
            this.email = email;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder role(final Role role) {
            this.role = role;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder enabled(final boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public UserDto.UserDtoBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserDto build() {
            return new UserDto(this.id, this.name, this.email, this.role, this.enabled, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "UserDto.UserDtoBuilder(id=" + this.id + ", name=" + this.name + ", email=" + this.email + ", role=" + this.role + ", enabled=" + this.enabled + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static UserDto.UserDtoBuilder builder() {
        return new UserDto.UserDtoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Role getRole() {
        return this.role;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDto)) return false;
        final UserDto other = (UserDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.isEnabled() != other.isEnabled()) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final java.lang.Object this$email = this.getEmail();
        final java.lang.Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final java.lang.Object this$role = this.getRole();
        final java.lang.Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof UserDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.isEnabled() ? 79 : 97);
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final java.lang.Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final java.lang.Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "UserDto(id=" + this.getId() + ", name=" + this.getName() + ", email=" + this.getEmail() + ", role=" + this.getRole() + ", enabled=" + this.isEnabled() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    public UserDto() {
    }

    public UserDto(final Long id, final String name, final String email, final Role role, final boolean enabled, final Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }
}
