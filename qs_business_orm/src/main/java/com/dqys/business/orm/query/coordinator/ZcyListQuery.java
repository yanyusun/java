package com.dqys.business.orm.query.coordinator;

import com.dqys.core.base.BasePagination;

import java.util.List;

/**
 * Created by mkfeng on 2016/8/19.
 *
 * @apiDefine ZcyListQuery
 * @apiParam {int} status  状态（0待接收1带分配）
 */
public class ZcyListQuery extends BasePagination {
    private List<Integer> objectIdList;
    private Integer status;//状态
    private Integer objectType;
    private Integer objectId;
    private Integer startPage;

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getObjectIdList() {
        return objectIdList;
    }

    public void setObjectIdList(List<Integer> objectIdList) {
        this.objectIdList = objectIdList;
    }
}
