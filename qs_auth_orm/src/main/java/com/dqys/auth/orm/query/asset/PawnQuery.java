package com.dqys.auth.orm.query.asset;

import com.dqys.core.base.BasePagination;

/**
 * Created by Yvan on 16/6/8.
 */
public class PawnQuery extends BasePagination {

    private Integer lenderId;

    public Integer getLenderId() {
        return lenderId;
    }

    public void setLenderId(Integer lenderId) {
        this.lenderId = lenderId;
    }
}
