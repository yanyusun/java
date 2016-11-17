package com.dqys.core.base;

import java.io.Serializable;

/**
 * Created by Yvan on 16/6/22.
 */
public class BasePageDTO<T> implements Serializable {

    private Integer page = 0; // 当前页
    private Integer total = 0; // 总条数
    private Integer pageCount = 20; // 总数量
    private T data; // 具体数据

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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
