package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by yan on 16-12-26.
 */
public class UnableLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1160;
    }

    @Override
    public String getName() {
        return "无效";
    }
}
