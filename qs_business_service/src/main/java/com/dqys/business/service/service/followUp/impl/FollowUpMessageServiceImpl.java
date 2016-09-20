package com.dqys.business.service.service.followUp.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.followUp.FollowUpMessageMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.orm.pojo.coordinator.OURelation;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.business.service.constant.SourceInfoEnum;
import com.dqys.business.service.dto.common.SourceDTO;
import com.dqys.business.service.dto.common.SourceInfoDTO;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import com.dqys.business.service.service.common.SourceService;
import com.dqys.business.service.service.followUp.FollowUpMessageService;
import com.dqys.business.service.service.followUp.FollowUpReadStatusService;
import com.dqys.business.service.utils.common.NavUtil;
import com.dqys.business.service.utils.followUp.FollowUpUtil;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.RabbitMQProducerTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private SourceService sourceService;

    @Override
    public int insert(FollowUpMessageDTO followUpMessageDTO) {
        FollowUpMessage followUpMessage = FollowUpUtil.toFollowUpMessage(followUpMessageDTO);
        Date curDate=new Date();
        UserSession userSession = UserSession.getCurrent();
        Integer userId=userSession.getUserId();
        followUpMessage.setUserId(userId);
        OURelation ouRelation =new OURelation();
        ouRelation.setUserId(userSession.getUserId());
        ouRelation.setObjectType(followUpMessage.getObjectType());
        ouRelation.setObjectId(followUpMessage.getObjectId());
        ouRelationMapper.selectBySelective(ouRelation);
        if(ouRelation.getStatus()!= OURelationEnum.STATUS_FOLLOW.getValue()){//如果未跟进修改为已跟进
            ouRelation.setStatus(OURelationEnum.STATUS_FOLLOW.getValue());
            ouRelationMapper.updateByPrimaryKey(ouRelation);
        }
        int teamId = 0;
        Integer teamid = followUpMessageMapper.getTeamId(followUpMessage.getObjectId(), followUpMessage.getObjectType(), followUpMessage.getUserId());
        if (teamid != null) {
            teamId = teamid;
        }
        followUpMessage.setTeamId(teamId);
        //增加资料实勘
        SourceInfoDTO sourceInfoDTO=createSourceInfo(followUpMessageDTO.getFileList(),userId,followUpMessage.getObjectId());
        int sourceInfoDTOId=sourceService.addSource(sourceInfoDTO);
        followUpMessage.setSourceInfoId(sourceInfoDTOId);
        int re = followUpMessageMapper.insert(followUpMessage);
        if(followUpMessage.getObjectType()== ObjectTypeEnum.LENDER.getValue()){//如果一级跟进对象是借款人, 增加跟进次数
            LenderInfo lenderInfo=lenderInfoMapper.get(followUpMessage.getObjectId());
            /* 增加借款人跟进次数 */
            lenderInfo.setFollowUpDate(curDate);
            int followtime=1;
            if(lenderInfo.getFollowUpTime()!=null){
                followtime=lenderInfo.getFollowUpTime()+1;
            }
            lenderInfo.setFollowUpTime(followtime);
            TeammateRe teammateRe=teammateReMapper.selectByObjectAndUser(ObjectTypeEnum.LENDER.getValue(),followUpMessage.getObjectId(),userSession.getUserId());
            if(teammateRe!=null&&teammateRe.getType()== TeammateReEnum.TYPE_AUXILIARY.getValue()){//如果是所属人增加所属人跟进次数
                lenderInfo.setBelongFollowTime(curDate);
                int belongFollowUpTime=1;
                if(lenderInfo.getBelongFollowTimes()!=null&&lenderInfo.getBelongFollowTimes()!=0){
                    belongFollowUpTime=lenderInfo.getBelongFollowTimes()+1;
                }
                lenderInfo.setBelongFollowTimes(belongFollowUpTime);
            }
            lenderInfoMapper.update(lenderInfo);
            if(null!=lenderInfo.getAssetId()&&0!=lenderInfo.getAssetId()){//如果有关联的资产包
                AssetInfo assetInfo=assetInfoMapper.get(lenderInfo.getAssetId());
                assetInfo.setFollowUpTime(curDate);
                int afollowtime=1;
                if(assetInfo.getFollowUpTimes()!=null){
                    afollowtime=assetInfo.getFollowUpTimes()+1;
                }
                assetInfo.setFollowUpTimes(afollowtime);
                TeammateRe aTeammateRe=teammateReMapper.selectByObjectAndUser(ObjectTypeEnum.ASSETPACKAGE.getValue(),followUpMessage.getObjectId(),userSession.getUserId());
                if(aTeammateRe!=null&teammateRe.getType()==TeammateReEnum.TYPE_AUXILIARY.getValue()){//如果是所属人增加所属人跟进次数
                    assetInfo.setBelongUpTime(curDate);
                    int aBelongFollowUpTime=1;
                    if(assetInfo.getBelongUpTimes()!=null&&lenderInfo.getBelongFollowTimes()!=0){
                        aBelongFollowUpTime=lenderInfo.getBelongFollowTimes()+1;
                    }
                    assetInfo.setFollowUpTimes(aBelongFollowUpTime);
                }
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
    public List<FollowUpMessage> getlistWithAll(FollowUpMessageQuery followUpMessageQuery) {
        return followUpMessageMapper.getlistWithALL(followUpMessageQuery);
    }

    @Override
    public List<FollowUpMessage> listAndCancelUnread(FollowUpMessageQuery followUpMessageQuery) {
        followUpReadStatusService.cancelUnread(followUpMessageQuery.getObjectId(),followUpMessageQuery.getObjectType(),followUpMessageQuery.getLiquidateStage());
        return getlistWithAll(followUpMessageQuery);
    }

    /**
     * 根据文件名生成资源信息
     * @param fileList 上传文件信息list
     * @return
     */
    private SourceInfoDTO createSourceInfo(List<String> fileList,Integer userId,Integer lenderId){
        SourceInfoDTO sourceInfoDTO = new SourceInfoDTO();
        SourceNavigation nav=NavUtil.getCommonSourceNavigation(SourceInfoEnum.FOLLOW_UP_TYPE.getValue());
        sourceInfoDTO.setNavId(nav.getId());
        sourceInfoDTO.setCode(SourceInfoEnum.FOLLOW_UP_TYPE.getObjectValue()+userId.toString());
        sourceInfoDTO.setIsshow(SourceInfoEnum.SHOW_UNABLE.getValue());
        sourceInfoDTO.setOpen(SourceInfoEnum.OPEN_ENABLE.getValue());
        sourceInfoDTO.setLenderId(lenderId);//公共分类时借款人id为0
        List<SourceDTO> sourceDTOList = new ArrayList<>();
        for(String file:fileList){
            SourceDTO sourceDTO  = new SourceDTO();
            sourceDTO.setFileName(file);
            sourceDTOList.add(sourceDTO);
        }
        sourceInfoDTO.setSourceDTOList(sourceDTOList);
        return  sourceInfoDTO;
    };

}
