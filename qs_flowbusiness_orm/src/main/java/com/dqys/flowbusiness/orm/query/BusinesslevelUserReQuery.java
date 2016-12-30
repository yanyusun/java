package com.dqys.flowbusiness.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-12-27.
 */
public class BusinesslevelUserReQuery extends BaseQuery {
    private Integer businesslevelreId;

    private Integer roleType;

    public Integer getBusinesslevelreId() {
        return businesslevelreId;
    }

    public void setBusinesslevelreId(Integer businesslevelreId) {
        this.businesslevelreId = businesslevelreId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}
