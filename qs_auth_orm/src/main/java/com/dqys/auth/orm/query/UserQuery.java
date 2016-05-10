package com.dqys.auth.orm.query;

import com.dqys.core.base.BaseQuery;

/**
 * @author by pan on 16-5-6.
 */
public class UserQuery extends BaseQuery {

    private Integer userId;
    private byte userType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserType(byte userType) {
        this.userType = userType;
    }

    public byte getUserType() {
        return userType;
    }
}
