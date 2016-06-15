package com.dqys.core.base;

/**
 * Created by Yvan on 16/6/8.
 */
public class BasePagination {

    private Integer page;
    private Integer pageCount;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page != null && page > -1) {
            this.page = page;
        } else {
            this.page = 0;
        }
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        if (pageCount != null && pageCount > 0) {
            this.pageCount = pageCount;
        } else {
            this.pageCount = 20;
        }
    }
}
