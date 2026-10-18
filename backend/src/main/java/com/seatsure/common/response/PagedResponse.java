package com.seatsure.common.response;

import java.util.List;

public class PagedResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;


    public static class PagedResponseBuilder<T> {
        private List<T> content;
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean isLast;

        PagedResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        public PagedResponse.PagedResponseBuilder<T> content(final List<T> content) {
            this.content = content;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PagedResponse.PagedResponseBuilder<T> pageNumber(final int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PagedResponse.PagedResponseBuilder<T> pageSize(final int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PagedResponse.PagedResponseBuilder<T> totalElements(final long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PagedResponse.PagedResponseBuilder<T> totalPages(final int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        /**
         * @return {@code this}.
         */
        public PagedResponse.PagedResponseBuilder<T> isLast(final boolean isLast) {
            this.isLast = isLast;
            return this;
        }

        public PagedResponse<T> build() {
            return new PagedResponse<T>(this.content, this.pageNumber, this.pageSize, this.totalElements, this.totalPages, this.isLast);
        }

        @java.lang.Override
        public java.lang.String toString() {
            return "PagedResponse.PagedResponseBuilder(content=" + this.content + ", pageNumber=" + this.pageNumber + ", pageSize=" + this.pageSize + ", totalElements=" + this.totalElements + ", totalPages=" + this.totalPages + ", isLast=" + this.isLast + ")";
        }
    }

    public static <T> PagedResponse.PagedResponseBuilder<T> builder() {
        return new PagedResponse.PagedResponseBuilder<T>();
    }

    public List<T> getContent() {
        return this.content;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public boolean isLast() {
        return this.isLast;
    }

    public void setContent(final List<T> content) {
        this.content = content;
    }

    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    public void setLast(final boolean isLast) {
        this.isLast = isLast;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof PagedResponse)) return false;
        final PagedResponse<?> other = (PagedResponse<?>) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.getPageNumber() != other.getPageNumber()) return false;
        if (this.getPageSize() != other.getPageSize()) return false;
        if (this.getTotalElements() != other.getTotalElements()) return false;
        if (this.getTotalPages() != other.getTotalPages()) return false;
        if (this.isLast() != other.isLast()) return false;
        final java.lang.Object this$content = this.getContent();
        final java.lang.Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        return true;
    }

    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof PagedResponse;
    }

    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getPageNumber();
        result = result * PRIME + this.getPageSize();
        final long $totalElements = this.getTotalElements();
        result = result * PRIME + (int) ($totalElements >>> 32 ^ $totalElements);
        result = result * PRIME + this.getTotalPages();
        result = result * PRIME + (this.isLast() ? 79 : 97);
        final java.lang.Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PagedResponse(content=" + this.getContent() + ", pageNumber=" + this.getPageNumber() + ", pageSize=" + this.getPageSize() + ", totalElements=" + this.getTotalElements() + ", totalPages=" + this.getTotalPages() + ", isLast=" + this.isLast() + ")";
    }

    public PagedResponse() {
    }

    public PagedResponse(final List<T> content, final int pageNumber, final int pageSize, final long totalElements, final int totalPages, final boolean isLast) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }
}
