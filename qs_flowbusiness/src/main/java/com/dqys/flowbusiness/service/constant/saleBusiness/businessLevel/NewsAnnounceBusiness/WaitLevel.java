package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.NewsAnnounceBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-28.
 */
public class WaitLevel implements BusinessLevel{
    @Override
    public Integer getLevel() {
        return 1220;
    }

    @Override
    public String getName() {
        return "待发布";
    }

    /**
     * 发布
     */
    public final static int announce= BusinessOperTypeEnum.news_announce_announce.getValue();
    /**
     * 草稿
     */
    public final static int draft= BusinessOperTypeEnum.news_announce_draft.getValue();

}
