package com.dqys.business.orm.query.asset;

import com.dqys.core.base.BaseQuery;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
public class AssetQuery extends BaseQuery {

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
