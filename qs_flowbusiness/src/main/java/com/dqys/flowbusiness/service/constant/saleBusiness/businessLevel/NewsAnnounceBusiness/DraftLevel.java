package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.NewsAnnounceBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-28.
 */
public class DraftLevel implements BusinessLevel {

    @Override
    public Integer getLevel() {
        return 1210;
    }

    @Override
    public String getName() {
        return "草稿";
    }

    /**
     * 发布
     */
    public final static int announce=BusinessOperTypeEnum.news_announce_announce.getValue();
    /**
     * 无效
     */
    public final static int unable= BusinessOperTypeEnum.news_announce_ubable.getValue();



}
