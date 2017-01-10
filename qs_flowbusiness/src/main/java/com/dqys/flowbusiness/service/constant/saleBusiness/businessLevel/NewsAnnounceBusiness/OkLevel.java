package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.NewsAnnounceBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.BusinessOperTypeEnum;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * Created by pan on 16-12-28.
 */
public class OkLevel implements BusinessLevel {
    @Override
    public Integer getLevel() {
        return 1230;
    }

    @Override
    public String getName() {
        return "已发布";
    }
    /**
     * 无效
     */
    public final static int ubable= BusinessOperTypeEnum.news_announce_ubable.getValue();
    /**
     * 草稿
     */
    public final static int draft= BusinessOperTypeEnum.news_announce_draft.getValue();
}
