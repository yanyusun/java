package com.dqys.flowbusiness.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-12-27.
 */
public class BusinessLevelReQuery extends BaseQuery {
    private Integer atBusinessType;

    private Integer operType;

    public Integer getAtBusinessType() {
        return atBusinessType;
    }

    public void setAtBusinessType(Integer atBusinessType) {
        this.atBusinessType = atBusinessType;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }
}
