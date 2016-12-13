package com.dqys.core.base;

/**
 * Created by Yvan on 16/6/8.
 * <p/>
 * 分页查询继承类,
 */
public abstract class BasePagination {

    private Integer page = 0;
    private Integer pageCount = 20;//单页显示数
    private Integer startPage;//起始数

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
