package com.dqys.sale.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * Created by yan on 16-12-19.
 */
public class UserBusTotalQuery extends BaseQuery {
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
