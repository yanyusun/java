package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.mapper.followUp.FollowUpSourceMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.OURelation;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import com.dqys.business.service.service.followUp.FollowUpMessageService;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.business.service.utils.followUp.FollowUpUtil;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.FileTool;
import com.dqys.core.utils.RabbitMQProducerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
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

    @Autowired
    private TeammateReMapper teammateReMapper;

    @Autowired
    private OURelationMapper ouRelationMapper;

    @Autowired
    private AssetInfoMapper assetInfoMapper;

    @Autowired
    private FollowUpSourceMapper followUpSourceMapper;

    @Override
    public int insert(FollowUpMessageDTO followUpMessageDTO) throws IOException {
        FollowUpMessage followUpMessage = FollowUpUtil.toFollowUpMessage(followUpMessageDTO);
        Date curDate = new Date();
        UserSession userSession = UserSession.getCurrent();
        Integer userId = userSession.getUserId();
        followUpMessage.setUserId(userId);
        OURelation ouRelation = new OURelation();
        ouRelation.setUserId(userSession.getUserId());
        ouRelation.setObjectType(followUpMessage.getObjectType());
        ouRelation.setObjectId(followUpMessage.getObjectId());
        ouRelationMapper.selectBySelective(ouRelation);
        if (ouRelation.getStatus() != OURelationEnum.STATUS_FOLLOW.getValue()) {//如果未跟进修改为已跟进
            ouRelation.setStatus(OURelationEnum.STATUS_FOLLOW.getValue());
            ouRelationMapper.updateByPrimaryKey(ouRelation);
        }
        //得到团队id
        int teamId = getTeamId(followUpMessage.getObjectId(), followUpMessage.getObjectType(), followUpMessage.getUserId());
        followUpMessage.setTeamId(teamId);
        followUpMessageMapper.insertSelective(followUpMessage);
        if (followUpMessage.getObjectType() == ObjectTypeEnum.LENDER.getValue()) {//如果一级跟进对象是借款人, 增加跟进次数
            LenderInfo lenderInfo = lenderInfoMapper.get(followUpMessage.getObjectId());
            /* 增加借款人跟进次数 */
            lenderInfo.setFollowUpDate(curDate);
            int followtime = 1;
            if (lenderInfo.getFollowUpTime() != null) {
                followtime = lenderInfo.getFollowUpTime() + 1;
            }
            lenderInfo.setFollowUpTime(followtime);
            TeammateRe teammateRe = teammateReMapper.selectByObjectAndUser(ObjectTypeEnum.LENDER.getValue(), followUpMessage.getObjectId(), userSession.getUserId());
            if (teammateRe != null && teammateRe.getType() == TeammateReEnum.TYPE_AUXILIARY.getValue()) {//如果是所属人增加所属人跟进次数
                lenderInfo.setBelongFollowTime(curDate);
                int belongFollowUpTime = 1;
                if (lenderInfo.getBelongFollowTimes() != null && lenderInfo.getBelongFollowTimes() != 0) {
                    belongFollowUpTime = lenderInfo.getBelongFollowTimes() + 1;
                }
                lenderInfo.setBelongFollowTimes(belongFollowUpTime);
            }
            lenderInfoMapper.update(lenderInfo);
            if (null != lenderInfo.getAssetId() && 0 != lenderInfo.getAssetId()) {//如果有关联的资产包
                AssetInfo assetInfo = assetInfoMapper.get(lenderInfo.getAssetId());
                assetInfo.setFollowUpTime(curDate);
                int afollowtime = 1;
                if (assetInfo.getFollowUpTimes() != null) {
                    afollowtime = assetInfo.getFollowUpTimes() + 1;
                }
                assetInfo.setFollowUpTimes(afollowtime);
                TeammateRe aTeammateRe = teammateReMapper.selectByObjectAndUser(ObjectTypeEnum.ASSETPACKAGE.getValue(), followUpMessage.getObjectId(), userSession.getUserId());
                if (aTeammateRe != null & teammateRe.getType() == TeammateReEnum.TYPE_AUXILIARY.getValue()) {//如果是所属人增加所属人跟进次数
                    assetInfo.setBelongUpTime(curDate);
                    int aBelongFollowUpTime = 1;
                    if (assetInfo.getBelongUpTimes() != null && lenderInfo.getBelongFollowTimes() != 0) {
                        aBelongFollowUpTime = lenderInfo.getBelongFollowTimes() + 1;
                    }
                    assetInfo.setFollowUpTimes(aBelongFollowUpTime);
                }
            }
        }
        //插入跟进上传的资源,并保存文件
        List<FollowUpSource> fileList = followUpMessageDTO.getFileList();
        if (fileList != null) {
            insertBatchInsertSource(fileList, followUpMessage.getId());
        }
        //向mq中增加未读信息
        String[] unReadMessage = {followUpMessage.getObjectId().toString(), followUpMessage.getObjectType().toString(), followUpMessage.getLiquidateStage().toString()};
        RabbitMQProducerTool.addToFollowUnReadMessage(unReadMessage);
        return followUpMessage.getId();
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
    public List<FollowUpMessage> getlistWithAll(FollowUpMessageQuery followUpMessageQuery) {
        if (followUpMessageQuery.isMine()) {
            int userId = UserSession.getCurrent().getUserId();
            followUpMessageQuery.setUserId(userId);
        } else if(followUpMessageQuery.isTeam()){
            int userId = UserSession.getCurrent().getUserId();
            int teamId=getTeamId(followUpMessageQuery.getObjectId(),followUpMessageQuery.getObjectType(),userId);
            followUpMessageQuery.setTeamId(teamId);
        }
        return followUpMessageMapper.getlistWithALL(followUpMessageQuery);
    }

    @Override
    public List<FollowUpMessage> listAndCancelUnread(FollowUpMessageQuery followUpMessageQuery) {
        followUpReadStatusService.cancelUnread(followUpMessageQuery.getObjectId(), followUpMessageQuery.getObjectType(), followUpMessageQuery.getLiquidateStage());
        return getlistWithAll(followUpMessageQuery);
    }

    @Override
    public void insertBatchInsertSource(List<FollowUpSource> fileList, Integer followUpId) throws IOException {
        for (FollowUpSource followUpSource : fileList) {
            followUpSource.setFollowUpMessageId(followUpId);
            followUpSourceMapper.insertSelective(followUpSource);
            //// TODO: 16-9-23 前端测试完必后去掉注释 
            FileTool.saveFileSync(followUpSource.getPathFilename());
        }
    }

    /**
     * 得到团队id
     *
     * @param objectId
     * @param ObjectType
     * @param userId
     * @return
     */
    private int getTeamId(int objectId, int ObjectType, int userId) {
        Integer teamid = 0;
        if (ObjectType == ObjectTypeEnum.LENDER.getValue()) {//当协作器是以借款人建立时先查借款人,没有就查资产包的协作器
            teamid = followUpMessageMapper.getTeamId(objectId, ObjectType, userId);
            if (teamid == null|| teamid==0) {
                AssetInfo assetInfo = assetInfoMapper.get(objectId);
                teamid = followUpMessageMapper.getTeamId(assetInfo.getId(), ObjectTypeEnum.ASSETPACKAGE.getValue(), userId);
            }
        } else {
            teamid = followUpMessageMapper.getTeamId(objectId, ObjectType, userId);
        }
        if(teamid == null){
            teamid=0;
        }
        return teamid;
    }
}
