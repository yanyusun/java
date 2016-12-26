package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class UnableLevel implements BusinessLevel{
    @Override
    public Integer getLevel() {
        return 1080;
    }

    @Override
    public String getName() {
        return "无效";
    }
    /**
     * 重新发布
     */
    public static final int re_announce = BusinessOperTypeEnum.re_announce.getValue();
}
