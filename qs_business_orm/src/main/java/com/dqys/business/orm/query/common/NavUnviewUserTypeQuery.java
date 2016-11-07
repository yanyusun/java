package com.dqys.business.orm.query.common;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-11-7.
 */
public class NavUnviewUserTypeQuery extends BaseQuery{
    private Integer navId;

    private Integer userType;

    private Integer object;

    private Integer objectId;

    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getObject() {
        return object;
    }

    public void setObject(Integer object) {
        this.object = object;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }
}
