package org.btg.dto;

import jakarta.validation.constraints.Min;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

public class PageInfo {
    @QueryParam("page")
    @DefaultValue("1")
    @Min(0)
    private int page = 1;

    @QueryParam("pageSize")
    @DefaultValue("10")
    @Min(0)
    private int pageSize = 10;

    public PageInfo() {
        // Default constructor
    }

    public int getPage() {
        return page;
    }

    public PageInfo setPage(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageInfo setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
