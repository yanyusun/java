package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class TenderLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1100;
    }

    @Override
    public String getName() {
        return "招标中";
    }

}
