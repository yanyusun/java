package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class BeAnnouncedAdmin implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1040;
    }

    @Override
    public String getName() {
        return "待发布";//平台待发布
    }
    public static final int AnnounceOperType= BusinessOperTypeEnum.announce.getValue();
    public static final int unable=BusinessOperTypeEnum.unable.getValue();
}
