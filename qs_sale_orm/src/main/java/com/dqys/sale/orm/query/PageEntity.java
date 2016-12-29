package com.dqys.sale.orm.query;

/**
 * Created by Administrator on 2016/12/21.
 */
public class PageEntity {
    private Integer page = 0;//当前页
    private Integer pageCount = 20;//每页展示数量
    private Integer startPage = 1;//起始位置
    private Integer totalCount = 0;//总数量

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

    public Integer getStartPage() {
        if (page > 0) {
            startPage = (page - 1) * pageCount;
        }
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
