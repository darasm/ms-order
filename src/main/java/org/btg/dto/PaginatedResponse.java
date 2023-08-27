package org.btg.dto;

public class PaginatedResponse<T> {

    private T content;
    private PaginationInfo pagination;

    public PaginatedResponse(T content, PaginationInfo pagination) {
        this.content = content;
        this.pagination = pagination;
    }

    public PaginatedResponse() {
        //Default constructor
    }

    public T getContent() {
        return content;
    }

    public PaginatedResponse<T> setContent(T content) {
        this.content = content;
        return this;
    }

    public PaginationInfo getPagination() {
        return pagination;
    }

    public PaginatedResponse<T> setPagination(PaginationInfo pagination) {
        this.pagination = pagination;
        return this;
    }

}
