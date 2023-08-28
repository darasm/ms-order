package org.btg.dto;

import jakarta.validation.constraints.Min;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

import java.util.Objects;

public class PaginationInfo {

    @QueryParam("page")
    @DefaultValue("1")
    @Min(0)
    private int page = 1;

    @QueryParam("pageSize")
    @DefaultValue("10")
    @Min(0)
    private int pageSize = 10;

    @QueryParam("orderProperty")
    private String orderProperty;

    @QueryParam("orderType")
    @DefaultValue("ASC")
    private String orderType = "ASC";

    @DefaultValue("0")
    @Min(0L)
    private long count = 0;

    public PaginationInfo() {
        // Default constructor
    }

    public int getPage() {
        return page;
    }

    public PaginationInfo setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PaginationInfo setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getOrderProperty() {
        return orderProperty;
    }

    public PaginationInfo setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
        return this;
    }

    public String getOrderType() {
        return orderType;
    }

    public PaginationInfo setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public long getCount() {
        return count;
    }

    public PaginationInfo setCount(long count) {
        this.count = count;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationInfo that = (PaginationInfo) o;
        return page == that.page && pageSize == that.pageSize && count == that.count && Objects.equals(orderProperty, that.orderProperty) && Objects.equals(orderType, that.orderType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, pageSize, orderProperty, orderType, count);
    }

    @Override
    public String toString() {
        return "PaginationInfo{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", orderProperty='" + orderProperty + '\'' +
                ", orderType='" + orderType + '\'' +
                ", count=" + count +
                '}';
    }
}
