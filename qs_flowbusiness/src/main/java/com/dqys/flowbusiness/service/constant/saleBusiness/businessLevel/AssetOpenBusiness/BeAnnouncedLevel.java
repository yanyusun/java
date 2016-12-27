package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by yan on 16-12-26.
 */
public class BeAnnouncedLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1010;
    }

    @Override
    public String getName() {
        return "待发布";
    }//用户待发布

    public static final int AnnounceOperType= BusinessOperTypeEnum.announce.getValue();

    public static final int unable=BusinessOperTypeEnum.unable.getValue();

}
