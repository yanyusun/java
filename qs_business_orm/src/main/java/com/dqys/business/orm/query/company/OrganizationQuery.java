package com.dqys.business.orm.query.company;

import com.dqys.core.base.BaseQuery;

/**
 * Created by Yvan on 16/6/27.
 */
public class OrganizationQuery extends BaseQuery {

    private Integer companyId; // 公司ID
    private String type; // 类型
    private String pid; // 上级id

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
