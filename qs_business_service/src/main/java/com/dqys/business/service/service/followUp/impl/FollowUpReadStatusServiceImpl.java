package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpReadstatusMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-8-11.
 */
@Service
@Primary
public class FollowUpReadStatusServiceImpl implements FollowUpReadStatusService {
    @Autowired
    private FollowUpReadstatusMapper followUpReadstatusMapper;

    @Override
    public int insert(FollowUpReadstatus record) {
        //// TODO: 16-8-16
        return 0;
    }

    @Override
    public void addUnReadMessage(String[] msg) {

        // TODO: 16-8-11 mkf

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

    @Override
    public void cancelUnread(int objectId, int objectType, int liquidateStage) {
        // TODO: 16-8-16 完成该接口 mkf del

    }

    @Override
    public Map<String, String> getCountMap(int ObjectId, int ObjectType) {
        UserSession userSession=UserSession.getCurrent();
        int userId=userSession.getUserId();
        // TODO: 16-8-16 mkf 查询
        return null;
    }
}
