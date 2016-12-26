package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class HasAnnouncedLevel  implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1050;
    }

    @Override
    public String getName() {
        return "已发布";
    }
    /**
     * 下架
     */
    public static final int under_line = BusinessOperTypeEnum.under_line.getValue();
}
