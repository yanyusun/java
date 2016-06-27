package com.dqys.business.orm.query.asset;

import com.dqys.core.base.BasePagination;

/**
 * Created by Yvan on 16/6/8.
 */
public class AssetQuery extends BasePagination{

    private Integer id; // 主键


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
