package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class CheckLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1020;
    }

    @Override
    public String getName() {
        return "待审核";
    }

    /**
     * 审核通过
     */
    public static final int check_OK = BusinessOperTypeEnum.check_OK.getValue();
    /**
     * 驳回
     */
    public static final int reject = BusinessOperTypeEnum.reject.getValue();
    /**
     * 无效
     */
    public static final int unable = BusinessOperTypeEnum.unable.getValue();
}
