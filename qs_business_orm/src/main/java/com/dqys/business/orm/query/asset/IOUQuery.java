package com.dqys.business.orm.query.asset;

import com.dqys.core.base.BaseQuery;

/**
 * Created by Yvan on 16/6/8.
 */
public class IOUQuery extends BaseQuery {

    private Integer lenderId; // 借款人ID

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }
}
