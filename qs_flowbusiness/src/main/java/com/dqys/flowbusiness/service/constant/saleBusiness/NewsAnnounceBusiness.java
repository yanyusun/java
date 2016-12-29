package com.dqys.flowbusiness.service.constant.saleBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.NewsAnnounceBusiness.DraftLevel;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.NewsAnnounceBusiness.OkLevel;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.NewsAnnounceBusiness.UnableLevel;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.NewsAnnounceBusiness.WaitLevel;

/**
 *  新闻发布
 * Created by yan on 16-12-28.
 */
public class NewsAnnounceBusiness {
    /**
     * 业务类型
     */
    public static int type = BusinessTypeEnum.news_announce.getValue();

    /**
     * 草稿
     */
    private static DraftLevel draftLevel=null;
    /**
     * 待发布
     */
    private static WaitLevel waitLevel=null;
    /**
     * 已发布
     */
    private static OkLevel okLevel=null;
    /**
     * 无效
     */
    private static UnableLevel unableLevel=null;

    public static DraftLevel getDraftLevel(){
        if(draftLevel==null){
            draftLevel=new DraftLevel();
        }
        return draftLevel;
    }

    public static WaitLevel getWaitLevel(){
        if(waitLevel==null){
            waitLevel=new WaitLevel();
        }
        return waitLevel;
    }
    public static OkLevel getOkLevel(){
        if(okLevel==null){
            okLevel=new OkLevel();
        }
        return okLevel;
    }
    public static UnableLevel unableLevel(){
        if(unableLevel==null){
            unableLevel=new UnableLevel();
        }
        return unableLevel;
    }
}
