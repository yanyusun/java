package com.dqys.business.service.service.followUp;

import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-8-11.
 */
public interface FollowUpReadStatus {

    int insert(FollowUpReadstatus record);

    /**
     *像数据库中添加与对象关联的为更进数据更进未读信息
     * @param msg [0]对象id,[1]对像类型,[2]清收阶段
     */
    void addUnReadMessage(String[] msg);

    Map<String,String> list(int liquidateStage);
}


