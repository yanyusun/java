package com.dqys.core.base;

import java.util.Date;
import java.util.List;

/**
 * @author by pan on 16-5-3.
 */
public abstract class BaseQuery {

    private Integer id;     //唯一ID
    private Date createAt;         //创建时间

    private Boolean isPaging = false;       //是否分页
    private Integer startPageNum = 0;        //当前页
    private Integer pageSize = 20;       //页大小

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Boolean getPaging() {
        return isPaging;
    }

    public void setPaging(Boolean paging) {
        isPaging = paging;
    }

    public Integer getStartPageNum() {
        return startPageNum;
    }

    public void setStartPageNum(Integer startPageNum) {
        this.startPageNum = startPageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
