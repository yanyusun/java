package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class HasRejectLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1112;
    }

    @Override
    public String getName() {
        return "已驳回";
    }

    /**
     * 重新申请
     */
    public static final int dispose_re_announce= BusinessOperTypeEnum.dispose_re_announce.getValue();
}
