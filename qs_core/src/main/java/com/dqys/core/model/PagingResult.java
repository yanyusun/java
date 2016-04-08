package com.dqys.core.model;

import java.util.List;

/**
 * @author by pan on 11/24/15.
 */
public class PagingResult<T> {

    private Integer pageNum;
    private Integer curPage;
    private List<T> data;

    public PagingResult() {

    }

    public PagingResult(Integer pageNum, Integer curPage, List<T> data) {
        this.pageNum = pageNum;
        this.curPage = curPage;
        this.data = data;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPageNum() {

        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
