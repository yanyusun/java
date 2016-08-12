package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpReadstatusMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;
import com.dqys.business.service.service.followUp.FollowUpReadStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-8-11.
 */
@Service
@Primary
public class FollowUpReadStatusServiceImpl implements FollowUpReadStatus {
    @Autowired
    private FollowUpReadstatusMapper followUpReadstatusMapper;

    @Override
    public int insert(FollowUpReadstatus record) {
        return 0;
    }

    @Override
    public void addUnReadMessage(String[] msg) {

        // TODO: 16-8-11

        /**
         *1.查找object_user_relation表中关联的所有userid
         *
         */
        List<Integer> userIdList=null;
        //循环调用
        for(Integer userid :  userIdList){
            //生成
            FollowUpReadstatus record=new FollowUpReadstatus();
            insert(record);
        }

    }
}
