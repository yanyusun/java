package com.dqys.sale.orm.query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
public class FixedAssetQuery extends PageEntity implements Serializable {
    private List<Integer> fixedAssetIds;

    public List<Integer> getFixedAssetIds() {
        return fixedAssetIds;
    }

    public void setFixedAssetIds(List<Integer> fixedAssetIds) {
        this.fixedAssetIds = fixedAssetIds;
    }
}
