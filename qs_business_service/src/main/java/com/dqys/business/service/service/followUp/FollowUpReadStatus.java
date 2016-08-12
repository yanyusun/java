package com.dqys.business.service.service.followUp;

import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;

/**
 * Created by yan on 16-8-11.
 */
public interface FollowUpReadStatus {

    int insert(FollowUpReadstatus record);

    /**
     *
     * @param msg [0]对象id,[1]对像类型,[2]清收阶段
     */
    void addUnReadMessage(String[] msg);
}


