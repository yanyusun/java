package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class HasRejectLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1030;
    }

    @Override
    public String getName() {
        return "已驳回";
    }
    /**
     * 审核通过
     */
    public static final int check_OK = BusinessOperTypeEnum.check_OK.getValue();

    /**
     * 重新发布
     */
    public static final int re_announce = BusinessOperTypeEnum.re_announce.getValue();
}
