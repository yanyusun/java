package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-26.
 */
public class HasUnderLine implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1060;
    }

    @Override
    public String getName() {
        return "已下架";
    }

    /**
     * 重新发布
     */
    public static final int re_announce = BusinessOperTypeEnum.re_announce.getValue();
    /**
     * 上架
     */
    public static final int on_line = BusinessOperTypeEnum.on_line.getValue();
    /**
     * 无效
     */
    public static final int unable = BusinessOperTypeEnum.unable.getValue();
}
