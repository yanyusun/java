package com.dqys.flowbusiness.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-12-27.
 */
public class BusinesslevelUserReQuery extends BaseQuery {
    private Integer businesslevelReId;

    private Integer roleType;

    public Integer getBusinesslevelReId() {
        return businesslevelReId;
    }

    public void setBusinesslevelReId(Integer businesslevelReId) {
        this.businesslevelReId = businesslevelReId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}
