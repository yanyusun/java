package com.dqys.business.orm.pojo.common;

import com.dqys.core.base.BaseModel;

public class NavUnviewCompany extends BaseModel {

    private Integer navId;

    private Integer companyId;


    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}