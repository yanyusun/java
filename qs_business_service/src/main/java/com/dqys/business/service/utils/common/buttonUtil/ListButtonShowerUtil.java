package com.dqys.business.service.utils.common.buttonUtil;

import com.dqys.business.service.constant.asset.ObjectTabEnum;

/**
 * 列表界面按钮控制显示类
 * Created by yan on 16-9-24.
 */
public class ListButtonShowerUtil {
    // TODO: 16-9-26  后期改为从数据库查询完成并且加入缓存,实现可以调控
    static {
        //待审核 平台
        ObjectTabEnum.check.getValue()+"";
    }
    ListButtonShowerBean get(Integer objectType, Integer objectId ,Integer navId){
        ListButtonShowerBean buttonShowerBean = new ListButtonShowerBean();
        return buttonShowerBean;
    }

    private static String getKey(ObjectTabEnum objectTab,){

    }
}
