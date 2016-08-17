package com.dqys.business.service.service.followUp;

import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-8-11.
 */
public interface FollowUpReadStatusService {

    int insert(FollowUpReadstatus record);

    /**
     *像数据库中添加与对象关联的为更进数据更进未读信息
     * @param msg [0]对象id,[1]对像类型,[2]清收阶段
     */
    void addUnReadMessage(String[] msg);

    /**查询对象所有阶段的未读留言数量 key为清搜阶段,value为未读信息数量
     *
     * @param ObjectId 对象id
     * @param ObjectId 对象类型
     * @return
     */
    List<Map<String,String>> getCountMap(int ObjectId,int ObjectType);

    /**
     * 取消未读
     * @param objectId 对象id
     * @param objectType 对象类型
     * @param liquidateStage 对象阶段
     */
    void cancelUnread(int objectId,int objectType,int liquidateStage);
}


