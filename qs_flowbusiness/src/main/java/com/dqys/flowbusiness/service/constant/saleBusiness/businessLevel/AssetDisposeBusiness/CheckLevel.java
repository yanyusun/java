package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class CheckLevel implements BusinessLevel{
    @Override
    public Integer getLevel() {
        return 1110;
    }

    @Override
    public String getName() {
        return "待审核";
    }

    /**
     * 审核通过
     */
    public static final int dispose_check_OK= BusinessOperTypeEnum.dispose_check_OK.getValue();
    /**
     * 驳回
     */
    public static final int dispose_reject= BusinessOperTypeEnum.dispose_reject.getValue();
    /**
     * 无效
     */
    public static final int dispose_unable=BusinessOperTypeEnum.dispose_unable.getValue();

}
