package com.dqys.business.orm.query.coordinator;

import com.dqys.core.base.BaseQuery;

import java.util.Date;

/**
 * Created by Yvan on 16/7/22.
 */
public class UserTeamQuery extends BaseQuery {

    private Integer companyId;
    private Integer mangerId;
    private Integer status;
    private Integer ctreaterId;
    private Integer objectId;
    private Integer objectType;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getMangerId() {
        return mangerId;
    }

    public void setMangerId(Integer mangerId) {
        this.mangerId = mangerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCtreaterId() {
        return ctreaterId;
    }

    public void setCtreaterId(Integer ctreaterId) {
        this.ctreaterId = ctreaterId;
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
}
