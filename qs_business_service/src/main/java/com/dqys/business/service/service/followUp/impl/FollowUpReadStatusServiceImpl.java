package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.mapper.followUp.FollowUpReadstatusMapper;
import com.dqys.business.orm.mapper.followUp.impl.FollowUpMessageMapperImpl;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.pojo.followUp.FollowUpReadstatus;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.UserSession;
import org.aspectj.bridge.MessageUtil;
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
        //// TODO: 16-8-16
        return followUpReadstatusMapper.insert(record);
    }

    @Override
    public void addUnReadMessage(String[] msg) {

        // TODO: 16-8-11 mkf

        /**
         *1.查找object_user_relation表中关联的所有userid
         *
         */
        List<Integer> userIdList = null;
        userIdList = followUpReadstatusMapper.selectByUseridList();
        //循环调用
        for (Integer userid : userIdList) {
            //生成
            FollowUpReadstatus record = new FollowUpReadstatus();
            record.setUserId(userid);
            record.setObjectId(MessageUtils.transStringToInt(msg[0]));
            record.setObjectType(MessageUtils.transStringToInt(msg[1]));
            record.setMoment(MessageUtils.transStringToInt(msg[2]));
            insert(record);
            FollowUpMessage followUpMessage = new FollowUpMessage();
            followUpMessage.setObjectType(record.getObjectType());
            followUpMessage.setObjectId(record.getObjectId());
            followUpMessage.setUserId(record.getUserId());
            followUpMessage.setSendStatus(1);
            followUpMessageMapper.updateBySendStatus(followUpMessage);
        }

    }

    @Override
    public void cancelUnread(int objectId, int objectType, int liquidateStage) {
        // TODO: 16-8-16 完成该接口 mkf del
        followUpReadstatusMapper.deleteByOOL(objectId, objectType, liquidateStage);
    }

    @Override
    public Map<String, String> getCountMap(int objectId, int objectType) {
        UserSession userSession = UserSession.getCurrent();
        int userId = userSession.getUserId();
        // TODO: 16-8-16 mkf 查询
        return followUpReadstatusMapper.getCountMap(objectId, objectType, userId);
    }
}
