package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetCollectionBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 17-1-3.
 */
public class CollectionLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1310;
    }

    @Override
    public String getName() {
        return "已收藏";//已收藏
    }


}
