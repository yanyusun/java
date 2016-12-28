package com.dqys.flowbusiness.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-12-27.
 */
public class BusinessLevelReQuery extends BaseQuery {
    private Integer atBusinessLevel;

    private Integer operType;

    public Integer getAtBusinessLevel() {
        return atBusinessLevel;
    }

    public void setAtBusinessLevel(Integer atBusinessLevel) {
        this.atBusinessLevel = atBusinessLevel;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }
}
