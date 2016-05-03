package com.dqys.core.base;

import java.util.Date;
import java.util.List;

/**
 * @author by pan on 16-5-3.
 */
public abstract class BaseQuery {

    private Integer id;     //唯一ID
    private Integer version;        //数据版本号
    private Integer stateflag;      //数据状态
    private Date createAt;         //创建时间

    private Boolean isPaging;       //是否分页
    private Integer curPage;        //当前页
    private Integer pageSize = 20;       //页大小
    private Integer startRecode = 0;      //起始记录

    private Boolean isOrder;        //是否排序
    private List orderBy;       //排序列表
    private String orderDesc;       //排序方向

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getStateflag() {
        return stateflag;
    }

    public void setStateflag(Integer stateflag) {
        this.stateflag = stateflag;
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

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartRecode() {
        return startRecode;
    }

    public void setStartRecode(Integer startRecode) {
        this.startRecode = startRecode;
    }

    public Boolean getOrder() {
        return isOrder;
    }

    public void setOrder(Boolean order) {
        isOrder = order;
    }

    public List getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(List orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
