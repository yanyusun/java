package com.dqys.business.orm.pojo.partner;

import com.dqys.core.base.BaseQuery;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/15.
 */
public class PartnerQuery implements Serializable {
    private Integer relationStatus = 1;//合作状态（0待合作，1建立合作，2拒绝合作，3终止合作）
    private Integer page = 0;
    private Integer pageCount = 20;
    private Integer startPage = 0;
    private String nameLike;//公司名称或营业执照号
    private Integer userId;//当前用户id
    private Integer isPage;//是否需要分页

    public Integer getIsPage() {
        return isPage;
    }

    public void setIsPage(Integer isPage) {
        this.isPage = isPage;
    }

    public Integer getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(Integer relationStatus) {
        this.relationStatus = relationStatus;
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

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
