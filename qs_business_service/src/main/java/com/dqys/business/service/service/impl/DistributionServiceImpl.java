package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectBusinessTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.message.MessageMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.query.asset.LenderQuery;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;
import com.dqys.business.service.constant.MessageBTEnum;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectLogEnum;
import com.dqys.business.service.dto.company.CompanyTeamReDTO;
import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.DistributionService;
import com.dqys.business.service.utils.company.CompanyServiceUtils;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.NoSQLWithRedisTool;
import com.dqys.core.utils.SmsUtil;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/7/21.
 */
@Repository
@Primary
public class DistributionServiceImpl implements DistributionService {


    @Autowired
    private CompanyTeamMapper companyTeamMapper;
    @Autowired
    private CompanyTeamReMapper companyTeamReMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private TUserInfoMapper userInfoMapper;
    @Autowired
    private TCompanyInfoMapper companyInfoMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private CoordinatorService coordinatorService;

    @Override
    public DistributionDTO getDistribution_tx(Integer type, Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id)) {
            return null;
        }
        // 查询分配器
        CompanyTeam companyTeam = companyTeamMapper.getByTypeId(type, id);
        if (companyTeam == null) {
            // 分配器不存在,判断是否存在该对象
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(type)) {
                AssetInfo assetInfo = assetInfoMapper.get(id);
                if (assetInfo == null) {
                    return null;
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(type)) {
                LenderInfo lenderInfo = lenderInfoMapper.get(id);
                if (lenderInfo == null) {
                    return null;
                }
            } else {
                // 没有该对象
                return null;
            }
            // 存在该对象,创建分配器
            companyTeam = new CompanyTeam();
            companyTeam.setObjectId(id);
            companyTeam.setSenderId(UserSession.getCurrent().getUserId());
            companyTeam.setObjectType(type);
            Integer result = companyTeamMapper.insert(companyTeam);
            if (CommonUtil.checkResult(result)) {
                // 创建失败
                return null;
            }
            Integer teamId = companyTeam.getId();
            companyTeam = companyTeamMapper.get(teamId);
            // 添加操作记录
            businessLogService.add(teamId, ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.add.getValue(),
                    "", "", 0, 0);
            // 添加本家分配记录
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
            if (userInfo != null && userInfo.getCompanyId() != null) {
                Integer companyId = userInfo.getCompanyId();
                CompanyTeamRe companyTeamRe = new CompanyTeamRe();
                companyTeamRe.setCompanyTeamId(teamId);
                companyTeamRe.setAcceptCompanyId(companyId);
                companyTeamRe.setStatus(ObjectAcceptTypeEnum.accept.getValue());
                companyTeamRe.setType(ObjectBusinessTypeEnum.create.getValue());
                companyTeamRe.setAccepterId(UserSession.getCurrent().getUserId()); // 接收人为创建者
                result = companyTeamReMapper.insert(companyTeamRe);
                if (!CommonUtil.checkResult(result)) {
                    // 添加操作记录
                    businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.join.getValue(),
                            "", "", 0, teamId);
                }
            }
        }
        // 查询分配器成员
        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(companyTeam.getId());
        List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
        List<CompanyTeamReDTO> companyTeamReDTOList = new ArrayList<>();
        DistributionDTO distributionDTO = new DistributionDTO();
        distributionDTO.setId(companyTeam.getId());
        companyTeamReList.forEach(companyTeamRe -> {
            CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
            if (companyDetailInfo.getType().equals(Integer.valueOf(
                    SysPropertyTool.getProperty(
                            SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM)
                            .getPropertyValue()))) {
                distributionDTO.setPlatformNum(distributionDTO.getPlatformNum() + 1); // 平台方
            } else if (companyDetailInfo.getType().equals(Integer.valueOf(
                    SysPropertyTool.getProperty(
                            SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_ENTRUST)
                            .getPropertyValue()))) {
                distributionDTO.setMechanismNum(distributionDTO.getMechanismNum() + 1); // 机构方
            } else {
                distributionDTO.setDisposeNum(distributionDTO.getDisposeNum() + 1); // 处置方
            }
            // 填充业绩比率,当前任务
            Map<String, Object> map = coordinatorService.getTaskCount(1,
                    UserSession.getCurrent().getUserId(),
                    ObjectTypeEnum.ASSETPACKAGE.getValue());
            Integer total = MessageUtils.transMapToInt(map, "total");
            Integer finish = MessageUtils.transMapToInt(map, "finish");
            String rate = finish * 100 / total + "%";
            companyTeamReDTOList.add(CompanyServiceUtils.toCompanyTeamReDTO(companyTeamRe, companyDetailInfo, rate, total));
        });
        distributionDTO.setCompanyTeamReDTOList(companyTeamReDTOList);
        return distributionDTO;
    }

    @Override
    public Integer joinDistribution(Integer id) throws BusinessLogException {
        if(id != null){
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
            if (userInfo == null || userInfo.getCompanyId() == null) {
                return null; // 用户不存在
            }
            CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId());
            if (companyDetailInfo == null) {
                return null; // 公司不存在
            }
            CompanyTeam companyTeam = companyTeamMapper.get(id);
            if (companyTeam == null) {
                return null; // 分配器不存在
            }

            CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
            companyTeamReQuery.setTeamId(id);
            List<CompanyTeamRe> countList = companyTeamReMapper.queryList(companyTeamReQuery);
            if(countList != null && countList.size() > 5){
                // 获取不到数据或已经超过5个参与
                return null;
            }

            companyTeamReQuery.setCompanyId(userInfo.getCompanyId());
            List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
            if (companyTeamReList != null || companyTeamReList.size() > 0) {
                // 已经存在在该分配器中无需再次申请或邀请
                return null;
            }
            CompanyTeamRe companyTeamRe = new CompanyTeamRe();
            companyTeamRe.setCompanyTeamId(id);
            companyTeamRe.setAcceptCompanyId(userInfo.getCompanyId());
            companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
            companyTeamRe.setType(ObjectBusinessTypeEnum.join.getValue());
            companyTeamRe.setAccepterId(userInfo.getId());
            Integer result = companyTeamReMapper.insert(companyTeamRe);
            if (CommonUtil.checkResult(result)) {
                return null;
            } else {
                // 添加操作记录
                businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(),
                        ObjectLogEnum.join.getValue(),
                        "", "", 0, id);
                // 发送短信提醒
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId()); // 创建人信息
                SmsUtil smsUtil = new SmsUtil();
                TSysProperty property = SysPropertyTool.getProperty(
                        SysPropertyTypeEnum.USER_TYPE, companyDetailInfo.getType().toString()); //
                String[] msg = {
                        creator.getUserName(),
                        companyDetailInfo.getCompanyName(),
                        property.getPropertyName(),
                        userInfo.getUserName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        companyTeam.getObjectId().toString()
                };
                smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_JOIN_CODE, creator.getMobile(), msg);
                // 添加消息
                String code = ""; // 对象编号
                if(ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())){
                    AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                    if(assetInfo != null){
                        code = assetInfo.getAssetNo();
                    }
                }else if(ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())){
                    LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                    if(lenderInfo != null){
                        code = lenderInfo.getLenderNo();
                    }
                }
                Message message = new Message();
                message.setTitle(MessageEnum.TASK.getName() + " > "
                        + ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName() + " > "
                        + code
                ); // 业务类型 对象类型 对象编号
                message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_JOIN_CODE, msg));
                message.setSenderId(userInfo.getId());
                message.setReceiveId(companyTeam.getSenderId());
                message.setLabel(null);
                message.setStatus(0);
                message.setType(MessageEnum.TASK.getValue());
                message.setBusinessType(MessageBTEnum.COMPANY_JOIN.getValue());
                message.setOperUrl("/api/company/designDistribution?id=" + id);
                messageMapper.add(message);

                return companyTeamRe.getId();
            }
        }
        return null;
    }

    @Override
    public Integer inviteDistribution(Integer id, Integer companyId) throws BusinessLogException {
        if(CommonUtil.checkParam(id, companyId)){
            return null;
        }
        CompanyTeam companyTeam = companyTeamMapper.get(id);
        if (companyTeam == null) {
            return null; // 分配器不存在
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if (userInfo == null || userInfo.getCompanyId() == null) {
            return null; // 用户不存在
        }
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId());
        if (companyDetailInfo == null) {
            return null; // 公司不存在
        }
        Integer type = ObjectBusinessTypeEnum.platform.getValue(); // 默认平台
        if(!companyDetailInfo.getCompanyType().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM).getPropertyValue())){
            type = ObjectBusinessTypeEnum.mechanism.getValue(); // 机构分配
        }

        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(id);
        List<CompanyTeamRe> countList = companyTeamReMapper.queryList(companyTeamReQuery);
        if(countList != null && countList.size() > 5){
            // 获取不到数据或已经超过5个参与
            return null;
        }

        companyTeamReQuery.setCompanyId(companyId);
        List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
        if (companyTeamReList != null || companyTeamReList.size() > 0) {
            // 已经存在在该分配器中无需再次申请或邀请
            return null;
        }

        CompanyDetailInfo companyDetailInfo1 = companyInfoMapper.getDetailByCompanyId(companyId); // 被邀请公司信息
        if(companyDetailInfo1 == null){
            return null; // 找不到目标公司
        }

        CompanyTeamRe companyTeamRe = new CompanyTeamRe();
        companyTeamRe.setCompanyTeamId(id);
        companyTeamRe.setAcceptCompanyId(companyId);
        companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
        companyTeamRe.setType(type);
        companyTeamRe.setAccepterId(companyDetailInfo1.getUserId());
        Integer result = companyTeamReMapper.insert(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
            businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(),
                    ObjectLogEnum.join.getValue(),
                    "", "", 0, id);
            // 发送短信提醒
            SmsUtil smsUtil = new SmsUtil();
            TSysProperty property = SysPropertyTool.getProperty(
                    SysPropertyTypeEnum.USER_TYPE, companyDetailInfo.getType().toString()); // 公司类型
            String[] msg = {
                    companyDetailInfo1.getName(),
                    companyDetailInfo.getCompanyName(),
                    property.getPropertyName(),
                    userInfo.getUserName(),
                    ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                    companyTeam.getObjectId().toString()
            };
            smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_INVITE_CODE, companyDetailInfo1.getPhone(), msg);
            // 添加消息
            String code = ""; // 对象编号
            if(ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())){
                AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                if(assetInfo != null){
                    code = assetInfo.getAssetNo();
                }
            }else if(ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())){
                LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                if(lenderInfo != null){
                    code = lenderInfo.getLenderNo();
                }
            }
            Message message = new Message();
            message.setTitle(MessageEnum.TASK.getName() + " > "
                            + ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName() + " > "
                            + code
            ); // 业务类型 对象类型 对象编号
            message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_INVITE_CODE, msg));
            message.setSenderId(userInfo.getId());
            message.setReceiveId(companyDetailInfo1.getUserId());
            message.setLabel(null);
            message.setStatus(0);
            message.setType(MessageEnum.TASK.getValue());
            message.setBusinessType(MessageBTEnum.COMPANY_BETWEEN.getValue());
            message.setOperUrl("/api/company/designDistribution?id=" + id);
            messageMapper.add(message);

            return companyTeamRe.getId();
        }
    }

    @Override
    public Integer updateDistribution_tx(Integer id, Integer status) throws BusinessLogException {
        if (CommonUtil.checkParam(id, status)) {
            return null;
        }
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(id);
        if (companyTeamRe == null) {
            return null; // 分配器记录不存在
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if (userInfo == null || userInfo.getCompanyId() == null) {
            return null; // 当前用户存在异常
        }
        if (!userInfo.getId().equals(companyTeamRe.getAccepterId())) {
            // 避免非接受人员操作
            return null;
        }

        companyTeamRe.setStatus(status);
        Integer result = companyTeamReMapper.update(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
            businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.update.getValue(),
                    "", "", 0, id);
            // 提醒消息
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId()); // 分配器信息
            String code = ""; // 对象编号
            if(ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())){
                AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                if(assetInfo != null){
                    code = assetInfo.getAssetNo();
                }
            }else if(ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())){
                LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                if(lenderInfo != null){
                    code = lenderInfo.getLenderNo();
                }
            }
            if(ObjectBusinessTypeEnum.join.getValue().equals(companyTeamRe.getType())){
                // 发送短信提醒
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeamRe.getAccepterId()); // 申请人信息
                SmsUtil smsUtil = new SmsUtil();
                String[] msg = {
                        creator.getUserName(),
                        userInfo.getUserName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        companyTeam.getObjectId().toString()
                };
                Message message = new Message();
                if(status.equals(ObjectAcceptTypeEnum.accept.getValue())){
                    smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_JOIN_PASS_CODE, creator.getMobile(), msg);
                    message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_JOIN_PASS_CODE, msg));
                }else{
                    smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_JOIN_REFUSE_CODE, creator.getMobile(), msg);
                    message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_JOIN_REFUSE_CODE, msg));
                }
                // 添加消息
                message.setTitle(MessageEnum.TASK.getName() + " > "
                                + ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName() + " > "
                                + code
                ); // 业务类型 对象类型 对象编号
                message.setSenderId(userInfo.getId());
                message.setReceiveId(companyTeamRe.getAccepterId());
                message.setLabel(null);
                message.setStatus(0);
                message.setType(MessageEnum.TASK.getValue());
                message.setBusinessType(MessageBTEnum.COMPANY_JOIN.getValue());
                message.setOperUrl(null);
                messageMapper.add(message);
            }else if(ObjectBusinessTypeEnum.mechanism.getValue().equals(companyTeamRe.getType())
                    || ObjectBusinessTypeEnum.platform.getValue().equals(companyTeamRe.getType())){
                // 发送短信提醒
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId()); // 创建人信息
                CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId()); // 被邀请公司信息
                TSysProperty property = SysPropertyTool.getProperty(
                        SysPropertyTypeEnum.USER_TYPE, companyDetailInfo.getType().toString()); // 公司类型
                SmsUtil smsUtil = new SmsUtil();
                String[] msg = {
                        creator.getUserName(),
                        companyDetailInfo.getCompanyName(),
                        property.getPropertyName(),
                        userInfo.getUserName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        companyTeam.getObjectId().toString()
                };
                Message message = new Message();
                if(status.equals(ObjectAcceptTypeEnum.accept.getValue())){
                    smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_PASS_CODE, creator.getMobile(), msg);
                    message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_PASS_CODE, msg));
                }else{
                    smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_REFUSE_CODE, creator.getMobile(), msg);
                    message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_REFUSE_CODE, msg));
                }
                message.setSenderId(userInfo.getId());
                message.setReceiveId(companyTeamRe.getAccepterId());
                message.setLabel(null);
                message.setStatus(0);
                message.setType(MessageEnum.TASK.getValue());
                message.setBusinessType(MessageBTEnum.COMPANY_BETWEEN.getValue());
                message.setOperUrl(null);
                messageMapper.add(message);
            }

            Integer lawType = Integer.valueOf(
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                            .getPropertyValue());
            Integer urgeType = Integer.valueOf(
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                            .getPropertyValue());
            Integer intermediaryType = Integer.valueOf(
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                            .getPropertyValue());
            // 判断是否为接收邀请
            if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                if (companyTeam != null) {
                    if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                        // 借款人类型
                        CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
                        if (companyDetailInfo != null) {
                            if (companyDetailInfo.getType().equals(lawType)) {
                                // 律所
                                LenderInfo lenderInfo = new LenderInfo();
                                lenderInfo.setId(companyTeam.getObjectId());
                                lenderInfo.setIsLawyer(SysProperty.BOOLEAN_TRUE);
                                lenderInfoMapper.update(lenderInfo);
                            } else if (companyDetailInfo.getType().equals(urgeType)) {
                                // 催收
                                LenderInfo lenderInfo = new LenderInfo();
                                lenderInfo.setId(companyTeam.getObjectId());
                                lenderInfo.setIsCollection(SysProperty.BOOLEAN_TRUE);
                                lenderInfoMapper.update(lenderInfo);
                            } else if (companyDetailInfo.getType().equals(intermediaryType)) {
                                // 中介
                                LenderInfo lenderInfo = new LenderInfo();
                                lenderInfo.setId(companyTeam.getObjectId());
                                lenderInfo.setIsAgent(SysProperty.BOOLEAN_TRUE);
                                lenderInfoMapper.update(lenderInfo);
                            }
                        }
                        // 添加操作者与操作事物的关联
                        createObjectUserRelation(companyTeam);
                    } else if (companyTeam.getObjectType().equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                        // 资产包类型
                        CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
                        if (companyDetailInfo != null) {
                            if (companyDetailInfo.getType().equals(lawType)) {
                                // 律所
                                AssetInfo assetInfo = new AssetInfo();
                                assetInfo.setId(companyTeam.getObjectId());
                                assetInfo.setIsLawyer(SysProperty.BOOLEAN_TRUE);
                                assetInfoMapper.update(assetInfo);
                            } else if (companyDetailInfo.getType().equals(urgeType)) {
                                // 催收
                                AssetInfo assetInfo = new AssetInfo();
                                assetInfo.setId(companyTeam.getObjectId());
                                assetInfo.setIsCollection(SysProperty.BOOLEAN_TRUE);
                                assetInfoMapper.update(assetInfo);
                            } else if (companyDetailInfo.getType().equals(intermediaryType)) {
                                // 中介
                                AssetInfo assetInfo = new AssetInfo();
                                assetInfo.setId(companyTeam.getObjectId());
                                assetInfo.setIsAgent(SysProperty.BOOLEAN_TRUE);
                                assetInfoMapper.update(assetInfo);
                            }
                        }
                        createObjectUserRelation(companyTeam);
                        LenderQuery lenderQuery = new LenderQuery();
                        lenderQuery.setAssetId(companyTeam.getObjectId());
                        List<LenderInfo> lenderInfoList = lenderInfoMapper.queryList(lenderQuery);
                        for (LenderInfo lenderInfo : lenderInfoList) {
                            CompanyTeam companyTeam1 = companyTeamMapper.getByTypeId(ObjectTypeEnum.LENDER.getValue(), lenderInfo.getId());
                            if (companyTeam1 == null) {
                                companyTeam1 = new CompanyTeam();
                                companyTeam1.setObjectType(ObjectTypeEnum.LENDER.getValue());
                                companyTeam1.setObjectId(lenderInfo.getId());
                                createObjectUserRelation(companyTeam1);
                            }
                        }
                    } else if (companyTeam.getObjectType().equals(ObjectTypeEnum.PAWN.getValue())) {
                        createObjectUserRelation(companyTeam);
                    }
                }
            }
            return result;
        }
    }

    @Override
    public Integer exitDistribution_tx(Integer id) throws BusinessLogException {
        if (id == null) {
            return null;
        }
        // 存在该分配数据
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(id);
        if (companyTeamRe == null) {
            return null;
        }
        // 校验是否是该公司的管理员
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
        if (companyDetailInfo == null || !companyDetailInfo.getUserId().equals(UserSession.getCurrent().getUserId())) {
            return null;
        }
        Integer result = companyTeamReMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
            businessLogService.add(id, ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.exit.getValue(),
                    "", "", 0, companyTeamRe.getCompanyTeamId());
            // 去除介入信息
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId());
            if (companyTeam != null) {
                Integer lawType = Integer.valueOf(
                        SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                                .getPropertyValue());
                Integer urgeType = Integer.valueOf(
                        SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                                .getPropertyValue());
                Integer intermediaryType = Integer.valueOf(
                        SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                                .getPropertyValue());
                if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                    // 借款人类型
                    if (companyDetailInfo.getType().equals(lawType)) {
                        // 律所
                        LenderInfo lenderInfo = new LenderInfo();
                        lenderInfo.setId(companyTeam.getObjectId());
                        lenderInfo.setIsLawyer(SysProperty.BOOLEAN_FALSE);
                        lenderInfoMapper.update(lenderInfo);
                    } else if (companyDetailInfo.getType().equals(urgeType)) {
                        // 催收
                        LenderInfo lenderInfo = new LenderInfo();
                        lenderInfo.setId(companyTeam.getObjectId());
                        lenderInfo.setIsCollection(SysProperty.BOOLEAN_FALSE);
                        lenderInfoMapper.update(lenderInfo);
                    } else if (companyDetailInfo.getType().equals(intermediaryType)) {
                        // 中介
                        LenderInfo lenderInfo = new LenderInfo();
                        lenderInfo.setId(companyTeam.getObjectId());
                        lenderInfo.setIsAgent(SysProperty.BOOLEAN_FALSE);
                        lenderInfoMapper.update(lenderInfo);
                    }
                } else if (companyTeam.getObjectType().equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                    // 资产包类型
                    if (companyDetailInfo != null) {
                        if (companyDetailInfo.getType().equals(lawType)) {
                            // 律所
                            AssetInfo assetInfo = new AssetInfo();
                            assetInfo.setId(companyTeam.getObjectId());
                            assetInfo.setIsLawyer(SysProperty.BOOLEAN_FALSE);
                            assetInfoMapper.update(assetInfo);
                        } else if (companyDetailInfo.getType().equals(urgeType)) {
                            // 催收
                            AssetInfo assetInfo = new AssetInfo();
                            assetInfo.setId(companyTeam.getObjectId());
                            assetInfo.setIsCollection(SysProperty.BOOLEAN_FALSE);
                            assetInfoMapper.update(assetInfo);
                        } else if (companyDetailInfo.getType().equals(intermediaryType)) {
                            // 中介
                            AssetInfo assetInfo = new AssetInfo();
                            assetInfo.setId(companyTeam.getObjectId());
                            assetInfo.setIsAgent(SysProperty.BOOLEAN_FALSE);
                            assetInfoMapper.update(assetInfo);
                        }
                    }
                }
            }
            return result;
        }
    }

    private Integer createObjectUserRelation(CompanyTeam companyTeam) {
        // 增加对象与操作事物的联系
        ObjectUserRelation objectUserRelation = new ObjectUserRelation();
        objectUserRelation.setObjectType(companyTeam.getObjectType());
        objectUserRelation.setObjectId(companyTeam.getObjectId());
        objectUserRelation.setUserId(UserSession.getCurrent().getUserId()); // 只有管理员才可以接收
        objectUserRelation.setType(BusinessRelationEnum.company.getValue());
        Integer result = objectUserRelationMapper.insert(objectUserRelation);
        if (result == null) {
            // 添加事物关系失败
            return null;
        }
        return result;
    }
}
