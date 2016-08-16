package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import com.dqys.business.service.service.followUp.FollowUpMessageService;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.RabbitMQProducerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-8-12.
 */
@Service
@Primary
public class FollowUpMessageServiceImpl implements FollowUpMessageService{
    @Autowired
    private FollowUpMessageMapper followUpMessageMapper;

    @Autowired
    private FollowUpReadStatusService followUpReadStatusService;

    @Override
    public int insert(FollowUpMessage followUpMessage) {
        UserSession userSession=UserSession.getCurrent();
        followUpMessage.setUserId(userSession.getUserId());
        followUpMessage.setUserId(1);
        // TODO: 16-8-15 获取teamid mkf
        int teamId = 0;
        followUpMessage.setTeamId(teamId);
        int re=followUpMessageMapper.insert(followUpMessage);
        //向mq中增加未读信息
        String[] unReadMessage = {followUpMessage.getObjectId().toString(),followUpMessage.getObjectType().toString(),followUpMessage.getLiquidateStage().toString()};
        RabbitMQProducerTool.addToFollowUnReadMessage(unReadMessage);
        return re;
    }



    @Override
    public List<FollowUpMessage> list(FollowUpMessageQuery followUpMessageQuery) {
        return followUpMessageMapper.list(followUpMessageQuery);
    }

    @Override
    public List<FollowUpMessage> listAndCancelUnread(FollowUpMessageQuery followUpMessageQuery) {
        followUpReadStatusService.cancelUnread(followUpMessageQuery.getObjectId(),followUpMessageQuery.getObjectType(),followUpMessageQuery.getLiquidateStage());
        return list(followUpMessageQuery);
    }

}
