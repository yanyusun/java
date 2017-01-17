package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.mapper.followUp.FollowUpReadstatusMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.UserSession;
import org.apache.logging.log4j.LogManager;
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

    @Autowired
    private FollowUpMessageMapper followUpMessageMapper;

    @Override
    public int insert(FollowUpReadstatus record) {
        return followUpReadstatusMapper.insert(record);
    }

    @Override
    public void addUnReadMessage(String[] msg) {
        /**
         *1.查找object_user_relation表中关联的所有userid
         *
         */
        List<Integer> userIdList = null;

        try {
            userIdList = followUpReadstatusMapper.selectByUseridList(Integer.parseInt(msg[0]),Integer.parseInt(msg[1]));
            //循环调用
            for (Integer userid : userIdList) {
                //生成
                FollowUpReadstatus record = new FollowUpReadstatus();
                record.setUserId(userid);
                record.setObjectId(MessageUtils.transStringToInt(msg[0]));
                record.setObjectType(MessageUtils.transStringToInt(msg[1]));
                record.setMoment(MessageUtils.transStringToInt(msg[2]));
                record.setSecondObjectId(MessageUtils.transStringToInt(msg[3]));
                record.setSecondObjectType(MessageUtils.transStringToInt(msg[4]));
                record.setSecondLiquidateStage(MessageUtils.transStringToInt(msg[5]));
                insert(record);
                FollowUpMessage followUpMessage = new FollowUpMessage();
                followUpMessage.setObjectType(record.getObjectType());
                followUpMessage.setObjectId(record.getObjectId());
                followUpMessage.setUserId(record.getUserId());
                followUpMessage.setSendStatus(1);
                followUpMessageMapper.updateBySendStatus(followUpMessage);
            }
        }catch (Exception e){
            e.printStackTrace();
            LogManager.getRootLogger().error("跟进未读处理失败"+msg);
        }


    }

    @Override
    public void cancelUnread(int objectId, int objectType, int liquidateStage) {
        UserSession userSession = UserSession.getCurrent();
        int userId = userSession.getUserId();
        followUpReadstatusMapper.deleteByOOL(objectId, objectType, liquidateStage,userId);
    }

    @Override
    public List<Map<String, String>> getCountMap(int objectId, int objectType) {
        UserSession userSession = UserSession.getCurrent();
        int userId = userSession.getUserId();
        return followUpReadstatusMapper.getCountMap(objectId, objectType, userId);
    }
}
