package com.dqys.sale.orm.query;

import com.dqys.sale.orm.pojo.AssetUserRe;

import java.io.Serializable;

/**
 * Created by mkfeng on 2017/1/5.
 */
public class AssetUserReQuery extends PageEntity implements Serializable {
    private AssetUserRe userRe;

    public AssetUserRe getUserRe() {
        return userRe;
    }

    public void setUserRe(AssetUserRe userRe) {
        this.userRe = userRe;
    }
}
