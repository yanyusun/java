package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class HasDisposeLevel implements BusinessLevel{
    @Override
    public Integer getLevel() {
        return 1140;
    }

    @Override
    public String getName() {
        return "已处置";
    }
    /**
     * 重启
     */
    public static final int dispose_reset= BusinessOperTypeEnum.dispose_reset.getValue();
}
