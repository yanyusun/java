package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
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
public class FollowUpMessageServiceImpl implements FollowUpMessageService {
    @Autowired
    private FollowUpMessageMapper followUpMessageMapper;

    @Autowired
    private FollowUpReadStatusService followUpReadStatusService;

    @Autowired
    private LenderInfoMapper lenderInfoMapper;

    @Override
    public int insert(FollowUpMessage followUpMessage) {
        UserSession userSession = UserSession.getCurrent();
        followUpMessage.setUserId(userSession.getUserId());
        int teamId = 0;
        Integer teamid = followUpMessageMapper.getTeamId(followUpMessage.getObjectId(), followUpMessage.getObjectType(), followUpMessage.getUserId());
        if (teamid != null) {
            teamId = teamid;
        }
        followUpMessage.setTeamId(teamId);
        int re = followUpMessageMapper.insert(followUpMessage);
        if(followUpMessage.getObjectType()== ObjectTypeEnum.LENDER.getValue()){//如果一级跟进对象是借款人, 增加跟进次数
            LenderInfo lenderInfo=lenderInfoMapper.get(followUpMessage.getObjectId());
            // TODO: 16-8-19 是否是借款人的所诉人
            //// TODO: 16-8-19  增加借款的催收次数与资产包的跟进次数
            if(null==lenderInfo.getAssetId()||0==lenderInfo.getAssetId()){//没有资产包的借款人只增加借款人跟进次数
                //// TODO: 16-8-19 查询是否是资产包的所属人
            }
        }
        //向mq中增加未读信息
        String[] unReadMessage = {followUpMessage.getObjectId().toString(), followUpMessage.getObjectType().toString(), followUpMessage.getLiquidateStage().toString()};
        RabbitMQProducerTool.addToFollowUnReadMessage(unReadMessage);
        return re;
    }


    @Override
    public List<FollowUpMessage> list(FollowUpMessageQuery followUpMessageQuery) {
        return followUpMessageMapper.list(followUpMessageQuery);
    }

    @Override
    public List<FollowUpMessage> getlistWithUserAndTeam(FollowUpMessageQuery followUpMessageQuery) {
        return followUpMessageMapper.getlistWithUserAndTeam(followUpMessageQuery);
    }

    @Override
    public List<FollowUpMessage> listAndCancelUnread(FollowUpMessageQuery followUpMessageQuery) {
        followUpReadStatusService.cancelUnread(followUpMessageQuery.getObjectId(),followUpMessageQuery.getObjectType(),followUpMessageQuery.getLiquidateStage());
        return getlistWithUserAndTeam(followUpMessageQuery);
    }

}
