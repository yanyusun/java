package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class OnDisposeLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1113;
    }

    @Override
    public String getName() {
        return "正在处置";
    }
    /**
     * 取消处置
     */
    public static final int dispose_cancel= BusinessOperTypeEnum.dispose_cancel.getValue();
    /**
     * 已处置
     */
    public static final int dispose_Ok= BusinessOperTypeEnum.dispose_Ok.getValue();
}
