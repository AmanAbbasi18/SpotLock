package com.seatsure.notification.dto;

import com.seatsure.notification.Notification;
import java.time.Instant;

public class NotificationDto {
    private Long id;
    private String title;
    private String message;
    private String type;
    private boolean isRead;
    private Instant createdAt;

    public static NotificationDto fromEntity(Notification notification) {
        return NotificationDto.builder().id(notification.getId()).title(notification.getTitle()).message(notification.getMessage()).type(notification.getType()).isRead(notification.isRead()).createdAt(notification.getCreatedAt()).build();
    }


    public static class NotificationDtoBuilder {
        private Long id;
        private String title;
        private String message;
        private String type;
        private boolean isRead;
        private Instant createdAt;

        NotificationDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder message(final String message) {
            this.message = message;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder type(final String type) {
            this.type = type;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder isRead(final boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public NotificationDto.NotificationDtoBuilder createdAt(final Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public NotificationDto build() {
            return new NotificationDto(this.id, this.title, this.message, this.type, this.isRead, this.createdAt);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "NotificationDto.NotificationDtoBuilder(id=" + this.id + ", title=" + this.title + ", message=" + this.message + ", type=" + this.type + ", isRead=" + this.isRead + ", createdAt=" + this.createdAt + ")";
        }
    }

    public static NotificationDto.NotificationDtoBuilder builder() {
        return new NotificationDto.NotificationDtoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.type;
    }

    public boolean isRead() {
        return this.isRead;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setRead(final boolean isRead) {
        this.isRead = isRead;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof NotificationDto)) return false;
        final NotificationDto other = (NotificationDto) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.isRead() != other.isRead()) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$title = this.getTitle();
        final java.lang.Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final java.lang.Object this$message = this.getMessage();
        final java.lang.Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final java.lang.Object this$type = this.getType();
        final java.lang.Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof NotificationDto;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.isRead() ? 79 : 97);
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final java.lang.Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final java.lang.Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "NotificationDto(id=" + this.getId() + ", title=" + this.getTitle() + ", message=" + this.getMessage() + ", type=" + this.getType() + ", isRead=" + this.isRead() + ", createdAt=" + this.getCreatedAt() + ")";
    }

    public NotificationDto() {
    }

    public NotificationDto(final Long id, final String title, final String message, final String type, final boolean isRead, final Instant createdAt) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }
}
