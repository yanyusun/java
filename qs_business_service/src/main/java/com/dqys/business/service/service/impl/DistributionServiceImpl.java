package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectBusinessTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.business.BusinessMapper;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.message.MessageMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.query.asset.LenderQuery;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;
import com.dqys.business.service.constant.MessageBTEnum;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.PawnEnum;
import com.dqys.business.service.dto.company.BusinessServiceDTO;
import com.dqys.business.service.dto.company.CompanyTeamReDTO;
import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.DistributionService;
import com.dqys.business.service.utils.company.CompanyServiceUtils;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.SmsUtil;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessObjReMapper businessObjReMapper;
    @Autowired
    private CompanyRelationMapper companyRelationMapper;

    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;

    @Autowired
    private CoordinatorService coordinatorService;

    @Override
    public DistributionDTO listDistribution_tx(Integer type, Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id)) {
            return null;
        }
        // 查询分配器是否存在
        CompanyTeam isExist = companyTeamMapper.getByTypeId(type, id);
        if (isExist == null) {
            // 分配器不存在,判断是否存在该对象
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(type)) {
                AssetInfo assetInfo = assetInfoMapper.get(id);
                if (assetInfo != null) {
                    // 对象存在，创建分配器
                    Integer result = addDistribution(type, id);
                    if (CommonUtil.checkResult(result)) {
                        return null;
                    }
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(type)) {
                LenderInfo lenderInfo = lenderInfoMapper.get(id);
                if (lenderInfo != null) {
                    // 借款人存在，创建分配器
                    Integer result = addDistribution(type, id);
                    if (CommonUtil.checkResult(result)) {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }

        CompanyTeam companyTeam = companyTeamMapper.getByTypeId(type, id);
        // 查询分配器成员
        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(companyTeam.getId());
        List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
        List<CompanyTeamReDTO> companyTeamReDTOList = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        DistributionDTO distributionDTO = new DistributionDTO();
        distributionDTO.setId(companyTeam.getId());
        int index = 0;
        Map<Integer, Integer> keyMap = new HashMap<>();
        for (CompanyTeamRe companyTeamRe : companyTeamReList) {
            // 填充业绩比率,当前任务
            Map<String, Object> map = coordinatorService.getTaskCount(1,
                    UserSession.getCurrent().getUserId(),
                    ObjectTypeEnum.ASSETPACKAGE.getValue());
            Integer total = MessageUtils.transMapToInt(map, "total");
            Integer finish = MessageUtils.transMapToInt(map, "finish");
            String rate = "";
            if(total.equals(0)){
                rate += 0;
            }else{
                rate += finish * 100 / total + "%";
            }
            if (ObjectBusinessTypeEnum.join.getValue().equals(companyTeamRe.getType())
                    || ObjectBusinessTypeEnum.platform.getValue().equals(companyTeamRe.getType())
                    || ObjectBusinessTypeEnum.create.getValue().equals(companyTeamRe.getType())) {
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
                companyTeamReDTOList.add(CompanyServiceUtils.toCompanyTeamReDTO(companyTeamRe, companyDetailInfo, rate, total));
            } else if (ObjectBusinessTypeEnum.mechanism.getValue().equals(companyTeamRe.getType())) {
                // 业务流转做准备
                keyMap.put(companyTeamRe.getAccepterId(), index);
                companyTeamReList.get(index).setVersion(total);
                companyTeamReList.get(index).setStatus(finish);
            }
            index++;
        }
        distributionDTO.setCompanyTeamReDTOList(companyTeamReDTOList);

        // 添加业务流成员(objectUserRelation.UserId=CompanyTeamRe.acceptId & objectUserRelation.objectId的借款人ID=companyTeam.objectId)
        List<BusinessServiceDTO> serviceDTOList = new ArrayList<>();
        ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
        objectUserRelationQuery.setUserIds(userIds);
        objectUserRelationQuery.setStatus(ObjectUserStatusEnum.handled.getValue());
        List<ObjectUserRelation> relationList = objectUserRelationMapper.list(objectUserRelationQuery);
        relationList.forEach(objectUserRelation -> {
            if (ObjectTypeEnum.PAWN.getValue().equals(objectUserRelation.getObjectType())) {
                PawnInfo pawnInfo = pawnInfoMapper.get(objectUserRelation.getObjectId());
                if (pawnInfo != null) {
                    if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                        LenderInfo lenderInfo = lenderInfoMapper.get(pawnInfo.getLenderId());
                        if (lenderInfo != null && lenderInfo.getAssetId() != null) {
                            if (lenderInfo.getAssetId().equals(companyTeam.getObjectId())) {
                                CompanyTeamRe ctr = companyTeamReList.get(keyMap.get(objectUserRelation.getUserId()));
                                CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(ctr.getAcceptCompanyId());
                                if (ctr != null && detail != null) {
                                    serviceDTOList.add(createBSDTO(ctr, detail, pawnInfo.getName(), objectUserRelation.getCreateAt()));
                                }
                            }
                        }
                    } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                        if (pawnInfo.getLenderId().equals(companyTeam.getObjectId())) {
                            CompanyTeamRe ctr = companyTeamReList.get(keyMap.get(objectUserRelation.getUserId()));
                            CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(ctr.getAcceptCompanyId());
                            if (ctr != null && detail != null) {
                                serviceDTOList.add(createBSDTO(ctr, detail, pawnInfo.getName(), objectUserRelation.getCreateAt()));
                            }
                        }
                    }
                }
            } else if (ObjectTypeEnum.IOU.getValue().equals(objectUserRelation.getObjectType())) {
                IOUInfo iouInfo = iouInfoMapper.get(objectUserRelation.getObjectId());
                if (iouInfo != null) {
                    if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                        LenderInfo lenderInfo = lenderInfoMapper.get(iouInfo.getLenderId());
                        if (lenderInfo != null && lenderInfo.getAssetId() != null) {
                            if (lenderInfo.getAssetId().equals(companyTeam.getObjectId())) {
                                CompanyTeamRe ctr = companyTeamReList.get(keyMap.get(objectUserRelation.getUserId()));
                                CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(ctr.getAcceptCompanyId());
                                if (ctr != null && detail != null) {
                                    serviceDTOList.add(createBSDTO(ctr, detail, iouInfo.getName(), objectUserRelation.getCreateAt()));
                                }
                            }
                        }
                    } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                        if (iouInfo.getLenderId().equals(companyTeam.getObjectId())) {
                            CompanyTeamRe ctr = companyTeamReList.get(keyMap.get(objectUserRelation.getUserId()));
                            CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(ctr.getAcceptCompanyId());
                            if (ctr != null && detail != null) {
                                serviceDTOList.add(createBSDTO(ctr, detail, iouInfo.getName(), objectUserRelation.getCreateAt()));
                            }
                        }
                    }
                }
            }
        });
        distributionDTO.setBusinessServiceDTOList(serviceDTOList);

        return distributionDTO;
    }

    private BusinessServiceDTO createBSDTO(CompanyTeamRe companyTeamRe, CompanyDetailInfo detail, String name,
                                           Date time) {
        BusinessServiceDTO result = new BusinessServiceDTO();

        result.setId(companyTeamRe.getId());
        result.setStatus(companyTeamRe.getStatus());
        result.setAvg(detail.getAvg());
        result.setType(detail.getType().toString());
        result.setName(detail.getCompanyName());
        result.setAddress(AreaTool.getAreaById(detail.getProvince()).getLabel()
                + AreaTool.getAreaById(detail.getCity()).getLabel()
                + AreaTool.getAreaById(detail.getDistrict()).getLabel());
        result.setRate(companyTeamRe.getStatus() + "/" + companyTeamRe.getVersion()); // 临时存储,version全部,status完成数
        result.setTask(companyTeamRe.getVersion() - companyTeamRe.getStatus());
        result.setTarget(name);
        result.setTime(time);

        return result;
    }

    @Override
    public Integer addDistribution(Integer type, Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id)) {
            return null;
        }
        // 判断该对象是否审核
        BusinessObjRe businessObjRe = businessObjReMapper.getByObject(type, id);
        if (businessObjRe == null) {
            return null;
        }
        Business business = businessMapper.get(businessObjRe.getBusinessId());
        if (!business.getStatus().equals(BusinessStatusEnum.platform_pass.getValue())) {
            return null; // 当前对象还未审核通过
        }
        // 查询分配器
        CompanyTeam companyTeam = companyTeamMapper.getByTypeId(type, id);
        if (companyTeam == null) {
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
                // 对象类型不符合
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
            // 添加操作记录
//            businessLogService.add(teamId, ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.add.getValue(),
//                    "", "创建分配器", 0, 0);
            // 添加本家分配记录
            CompanyDetailInfo detailInfo = companyInfoMapper.getDetailByUserId(UserSession.getCurrent().getUserId());
            if (detailInfo != null && detailInfo.getCompanyId() != null) {
                CompanyTeamRe companyTeamRe = new CompanyTeamRe();
                companyTeamRe.setCompanyTeamId(teamId);
                companyTeamRe.setAcceptCompanyId(detailInfo.getCompanyId());
                companyTeamRe.setStatus(ObjectAcceptTypeEnum.accept.getValue());
                companyTeamRe.setType(ObjectBusinessTypeEnum.create.getValue());
                companyTeamRe.setAccepterId(detailInfo.getUserId()); // 接收人为创建者
                companyTeamReMapper.insert(companyTeamRe);
                // 添加平台参与记录
                List<TCompanyInfo> managerList = companyInfoMapper.listByType(Integer.valueOf(
                        SysPropertyTool.getProperty(
                            SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM).getPropertyValue()));
                if(managerList != null && managerList != null){
                    // 当前只有一个平台方的管理员
                    detailInfo = companyInfoMapper.getDetailByCompanyId(managerList.get(0).getId());
                    companyTeamRe.setAcceptCompanyId(detailInfo.getCompanyId());
                    companyTeamRe.setStatus(ObjectAcceptTypeEnum.accept.getValue());
                    companyTeamRe.setType(ObjectBusinessTypeEnum.join.getValue());
                    companyTeamRe.setAccepterId(detailInfo.getUserId());
                    companyTeamReMapper.insert(companyTeamRe);
                }

            }
            return teamId;
        }
        return null;
    }

    @Override
    public Integer joinDistribution(Integer id) throws BusinessLogException {
        if (id != null) {
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
            if (countList != null && countList.size() > 3) {
                // 获取不到数据或已经超过3个参与(处置方只能有一个)
                return null;
            }

            companyTeamReQuery.setCompanyId(userInfo.getCompanyId());
            List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
            if (companyTeamReList != null && companyTeamReList.size() > 0) {
                // 已经存在在该分配器中无需再次申请或邀请
                return null;
            }
            CompanyTeamRe companyTeamRe = new CompanyTeamRe();
            companyTeamRe.setCompanyTeamId(id);
            companyTeamRe.setAcceptCompanyId(userInfo.getCompanyId());
            companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
            companyTeamRe.setType(ObjectBusinessTypeEnum.join.getValue());
            companyTeamRe.setAccepterId(userInfo.getId());
//            companyTeamRe.setRoleType(companyDetailInfo.getType());
//            companyTeamRe.setRequesterId(companyDetailInfo.getCompanyId());
            Integer result = companyTeamReMapper.insert(companyTeamRe);
            if (CommonUtil.checkResult(result)) {
                return null;
            } else {
                // 添加操作记录
//                businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(),
//                        ObjectLogEnum.join.getValue(),
//                        "", "主动加入分配器", 0, 0);
                // 发送短信提醒
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId()); // 创建人信息
                SmsUtil smsUtil = new SmsUtil();
                List<TSysProperty> propertyList = SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE);
                TSysProperty property = null;
                for (TSysProperty tSysProperty : propertyList){
                    if(companyDetailInfo.getType().toString().equals(tSysProperty.getPropertyValue())){
                        property = tSysProperty;
                        break;
                    }
                }
                String[] msg = {
                        creator.getRealName(),
                        companyDetailInfo.getCompanyName(),
                        property.getPropertyName(),
                        userInfo.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        companyTeam.getObjectId().toString()
                };
                smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_JOIN_CODE, creator.getMobile(), msg);
                // 添加消息
                String code = ""; // 对象编号
                if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                    AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                    if (assetInfo != null) {
                        code = assetInfo.getAssetNo();
                    }
                } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                    LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                    if (lenderInfo != null) {
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
        if (CommonUtil.checkParam(id, companyId)) {
            return null;
        }
        if (!CommonUtil.isManage()) {
            return null; // 不是平台管理员
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

        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(id);
        List<CompanyTeamRe> countList = companyTeamReMapper.queryList(companyTeamReQuery);
        if (countList != null && countList.size() > 3) {
            // 获取不到数据或已经超过3个参与(处置方只能有一个)
            return null;
        }

        companyTeamReQuery.setCompanyId(companyId);
        List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
        if (companyTeamReList != null && companyTeamReList.size() > 0) {
            // 已经存在在该分配器中无需再次申请或邀请
            return null;
        }

        CompanyDetailInfo companyDetailInfo1 = companyInfoMapper.getDetailByCompanyId(companyId); // 被邀请公司信息
        if (companyDetailInfo1 == null) {
            return null; // 找不到目标公司
        }

        CompanyTeamRe companyTeamRe = new CompanyTeamRe();
        companyTeamRe.setCompanyTeamId(id);
        companyTeamRe.setAcceptCompanyId(companyId);
        companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
        companyTeamRe.setType(ObjectBusinessTypeEnum.platform.getValue());
        companyTeamRe.setAccepterId(companyDetailInfo1.getUserId());
//        companyTeamRe.setRoleType(companyDetailInfo.getType());
//        companyTeamRe.setRequesterId(companyDetailInfo.getCompanyId());
        Integer result = companyTeamReMapper.insert(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
//            businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(),
//                    ObjectLogEnum.join.getValue(),
//                    "", "邀请加入分配器", 0, 0);
            // 发送短信提醒
            SmsUtil smsUtil = new SmsUtil();
            List<TSysProperty> propertyList = SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE);
            TSysProperty property = null;
            for (TSysProperty tSysProperty : propertyList){
                if(companyDetailInfo.getType().toString().equals(tSysProperty.getPropertyValue())){
                    property = tSysProperty;
                    break;
                }
            }
            String[] msg = {
                    companyDetailInfo1.getName(),
                    companyDetailInfo.getCompanyName(),
                    property.getPropertyName(),
                    userInfo.getRealName(),
                    ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                    companyTeam.getObjectId().toString()
            };
            smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_INVITE_CODE, companyDetailInfo1.getPhone(), msg);
            // 添加消息
            String code = ""; // 对象编号
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                if (assetInfo != null) {
                    code = assetInfo.getAssetNo();
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                if (lenderInfo != null) {
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

        if(companyTeamRe.getType().equals(ObjectBusinessTypeEnum.platform.getValue())){
            // 平台分配
            if(!companyTeamRe.getAcceptCompanyId().equals(userInfo.getCompanyId())){
                return null; // 不是本公司的，无法审核
            }
        }else if(companyTeamRe.getType().equals(ObjectBusinessTypeEnum.join.getValue())){
            // 主动加入
            if (!CommonUtil.isManage()) {
                return null; // 不是平台方管理员，无法审核
            }
        }

        companyTeamRe.setStatus(status);
        Integer result = companyTeamReMapper.update(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
//            businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.update.getValue(),
//                    "", "操作(同意|拒绝)加入分配器对象成员", 0, 0);
            // 提醒消息
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId()); // 分配器信息
            String code = ""; // 对象编号
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                if (assetInfo != null) {
                    code = assetInfo.getAssetNo();
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                if (lenderInfo != null) {
                    code = lenderInfo.getLenderNo();
                }
            }
            if (ObjectBusinessTypeEnum.join.getValue().equals(companyTeamRe.getType())) {
                // 发送短信提醒
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeamRe.getAccepterId()); // 申请人信息
                SmsUtil smsUtil = new SmsUtil();
                String[] msg = {
                        creator.getRealName(),
                        userInfo.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        companyTeam.getObjectId().toString()
                };
                Message message = new Message();
                if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                    smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_JOIN_PASS_CODE, creator.getMobile(), msg);
                    message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_JOIN_PASS_CODE, msg));
                } else {
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
            } else if (ObjectBusinessTypeEnum.mechanism.getValue().equals(companyTeamRe.getType())
                    || ObjectBusinessTypeEnum.platform.getValue().equals(companyTeamRe.getType())) {
                // 发送短信提醒
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId()); // 创建人信息
                CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId()); // 被邀请公司信息
                List<TSysProperty> propertyList = SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE);
                TSysProperty property = null;
                for (TSysProperty tSysProperty : propertyList){
                    if(companyDetailInfo.getType().toString().equals(tSysProperty.getPropertyValue())){
                        property = tSysProperty;
                        break;
                    }
                }
                SmsUtil smsUtil = new SmsUtil();
                String[] msg = {
                        creator.getRealName(),
                        companyDetailInfo.getCompanyName(),
                        property.getPropertyName(),
                        userInfo.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        companyTeam.getObjectId().toString()
                };
                Message message = new Message();
                if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                    smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_PASS_CODE, creator.getMobile(), msg);
                    message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_PASS_CODE, msg));
                } else {
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
            // 判断是否为接收邀请,是则添加对象关系和实体类的标识
            if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                // 业务
                Integer businessId = null;
                BusinessObjRe businessObjRe = businessObjReMapper.getByObject(companyTeam.getObjectType(), companyTeam.getObjectId());
                if (businessObjRe != null) {
                    businessId = businessObjRe.getBusinessId();
                }
                if (companyTeam != null) {
                    // 修改业务状态
                    Business business = businessMapper.get(businessObjRe.getBusinessId());
                    business.setStatus(BusinessStatusEnum.dispose.getValue());
                    businessMapper.update(business);

                    // 添加公司之间的关系
                    TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId());
                    CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
                    CompanyRelation companyRelation = new CompanyRelation();
                    companyRelation.setCompanyAId(creator.getCompanyId());
                    companyRelation.setCompanyBId(companyTeamRe.getAcceptCompanyId());
                    companyRelationMapper.insert(companyRelation);

                    if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                        // 借款人类型
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
                        createObjectUserRelation(companyTeam, companyDetailInfo.getUserId(), companyTeam.getId(), businessId);
                    } else if (companyTeam.getObjectType().equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                        // 资产包类型
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
                        createObjectUserRelation(companyTeam, companyDetailInfo.getUserId(), companyTeam.getId(), businessId);
                        LenderQuery lenderQuery = new LenderQuery();
                        lenderQuery.setAssetId(companyTeam.getObjectId());
                        List<LenderInfo> lenderInfoList = lenderInfoMapper.queryList(lenderQuery);
                        for (LenderInfo lenderInfo : lenderInfoList) {
                            CompanyTeam companyTeam1 = companyTeamMapper.getByTypeId(ObjectTypeEnum.LENDER.getValue(), lenderInfo.getId());
                            if (companyTeam1 == null) {
                                companyTeam1 = new CompanyTeam();
                                companyTeam1.setObjectType(ObjectTypeEnum.LENDER.getValue());
                                companyTeam1.setObjectId(lenderInfo.getId());
                                createObjectUserRelation(companyTeam1, companyDetailInfo.getUserId(), companyTeam.getId(), businessId);
                            }
                        }
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
        if (!CommonUtil.isManage()) {
            return null;
        }
        // 存在该分配数据
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(id);
        if (companyTeamRe == null) {
            return null;
        }
        // 校验公司是否存在
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
        if (companyDetailInfo == null) {
            return null;
        }
        Integer result = companyTeamReMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
//            businessLogService.add(id, ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.exit.getValue(),
//                    "", "移除分配器内容对象成员", 0, 0);
            // 发送短信提醒
            TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeamRe.getAccepterId()); // 申请人信息
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId());
            SmsUtil smsUtil = new SmsUtil();
            String[] msg = {
                    creator.getRealName(),
                    ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                    companyTeam.getObjectId().toString()
            };
            Message message = new Message();
            smsUtil.sendSms(SysProperty.SMS_OUT_CODE, creator.getMobile(), msg);
            message.setContent(smsUtil.getSendContent(SysProperty.SMS_OUT_CODE, msg));

            // 去除公司的协作器
            coordinatorService.delCoordinator(companyTeamRe.getAcceptCompanyId(), companyTeam.getObjectId(), companyTeam.getObjectType());
            // 添加消息
            String code = ""; // 对象编号
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                if (assetInfo != null) {
                    code = assetInfo.getAssetNo();
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                if (lenderInfo != null) {
                    code = lenderInfo.getLenderNo();
                }
            }
            message.setTitle(MessageEnum.TASK.getName() + " > "
                            + ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName() + " > "
                            + code
            ); // 业务类型 对象类型 对象编号
            message.setSenderId(UserSession.getCurrent().getUserId());
            message.setReceiveId(companyTeamRe.getAccepterId());
            message.setLabel(null);
            message.setStatus(0);
            message.setType(MessageEnum.TASK.getValue());
            message.setBusinessType(MessageBTEnum.COMPANY_JOIN.getValue());
            message.setOperUrl(null);
            messageMapper.add(message);
            // 去除介入信息
            if (companyTeam != null) {
                // 删除操作者与操作事物的关联(包含业务流转)
                ObjectUserRelationQuery query = new ObjectUserRelationQuery();
                query.setEmployerId(companyTeam.getId());
                query.setUserId(companyDetailInfo.getUserId());
                query.setType(BusinessRelationEnum.company.getValue());
                List<ObjectUserRelation> relationList = objectUserRelationMapper.list(query);
                // 理论上这里只有一条
                relationList.forEach(objectUserRelation -> {
                    objectUserRelationMapper.deleteByPrimaryKey(objectUserRelation.getId());
                });

                // todo 这里资产包情况需要修复一下


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

    private Integer createObjectUserRelation(CompanyTeam companyTeam, Integer userId,
                                             Integer distrbutionId, Integer businessId) {
        // 增加对象与操作事物的联系
        ObjectUserRelation objectUserRelation = new ObjectUserRelation();
        objectUserRelation.setObjectType(companyTeam.getObjectType());
        objectUserRelation.setObjectId(companyTeam.getObjectId());
        objectUserRelation.setUserId(userId); // 只有管理员才可以接收
        objectUserRelation.setType(BusinessRelationEnum.company.getValue());
        objectUserRelation.setVisibleType(SysProperty.BOOLEAN_TRUE); // 可见
        objectUserRelation.setEmployerId(distrbutionId); // 分配器ID
        objectUserRelation.setVisibleType(businessId); // 业务ID
        Integer result = objectUserRelationMapper.insert(objectUserRelation);
        if (result == null) {
            // 添加事物关系失败
            return null;
        }
        return result;
    }

    @Override
    public Integer addBusinessService(Integer type, Integer id, Integer distributionId,
                                      Integer businessType, Integer companyId) throws BusinessLogException {
        if (!CommonUtil.isManage()) {
            return null; // 不是平台管理员
        }
        if (!ObjectTypeEnum.IOU.getValue().equals(type) && !ObjectTypeEnum.PAWN.getValue().equals(type)) {
            return null; // 流转对象不对
        }
        CompanyTeam companyTeam = companyTeamMapper.get(distributionId);
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
        CompanyDetailInfo companyDetailInfo1 = companyInfoMapper.getDetailByCompanyId(companyId); // 被邀请公司信息
        if (companyDetailInfo1 == null) {
            return null; // 找不到目标公司
        }

        // 对象的关系
        BusinessObjRe businessObjRe = businessObjReMapper.getByObject(companyTeam.getObjectType(), companyTeam.getObjectId());
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setObjectType(type);
        query.setObjectId(id);
        query.setUserId(companyDetailInfo1.getUserId()); // 只有管理员才可以接收
        query.setType(BusinessRelationEnum.dispose.getValue());
        query.setEmployerId(companyTeam.getId());
        query.setBusinessId(businessObjRe.getBusinessId());
        List<ObjectUserRelation> relationList = objectUserRelationMapper.list(query);
        if (relationList != null || relationList.size() > 0) {
            // 已经增加
            return null;
        } else {
            // 增加对象关系
            ObjectUserRelation objectUserRelation = new ObjectUserRelation();
            objectUserRelation.setObjectType(type);
            objectUserRelation.setObjectId(id);
            objectUserRelation.setUserId(companyDetailInfo1.getUserId()); // 只有管理员才可以接收
            objectUserRelation.setType(BusinessRelationEnum.dispose.getValue());
            objectUserRelation.setStatus(SysProperty.BOOLEAN_TRUE); // 这里填充1为未接受
            objectUserRelation.setVisibleType(SysProperty.BOOLEAN_TRUE); // 可见
            objectUserRelation.setEmployerId(companyTeam.getId());
            objectUserRelation.setBusinessId(businessObjRe.getBusinessId());
            objectUserRelationMapper.insert(objectUserRelation);
            // 增加所属借款人的对象
            query.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (ObjectTypeEnum.IOU.getValue().equals(type)) {
                IOUInfo iouInfo = iouInfoMapper.get(id);
                query.setObjectId(iouInfo.getLenderId());
                relationList = objectUserRelationMapper.list(query);
                if (relationList == null || relationList.size() == 0) {
                    objectUserRelation.setObjectType(ObjectTypeEnum.LENDER.getValue());
                    objectUserRelation.setObjectId(iouInfo.getLenderId());
                    objectUserRelationMapper.insert(objectUserRelation);
                }
            } else if (ObjectTypeEnum.PAWN.getValue().equals(type)) {
                PawnInfo pawnInfo = pawnInfoMapper.get(id);
                query.setObjectId(pawnInfo.getLenderId());
                relationList = objectUserRelationMapper.list(query);
                if (relationList == null || relationList.size() == 0) {
                    objectUserRelation.setObjectType(ObjectTypeEnum.LENDER.getValue());
                    objectUserRelation.setObjectId(pawnInfo.getLenderId());
                    objectUserRelationMapper.insert(objectUserRelation);
                }
            }
            // 添加公司
            Integer result = addCompanyTeamRe(distributionId, companyDetailInfo, companyDetailInfo1);
            if (CommonUtil.checkResult(result)) {
                return null;
            } else {
                // 添加操作记录
//                businessLogService.add(result, ObjectTypeEnum.DISTRIBUTION.getValue(),
//                        ObjectLogEnum.join.getValue(),
//                        "", "增加业务流转", 0, 0);

                // 发送短信提醒
                SmsUtil smsUtil = new SmsUtil();
                List<TSysProperty> propertyList = SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE);
                TSysProperty property = null;
                for (TSysProperty tSysProperty : propertyList){
                    if(companyDetailInfo.getType().toString().equals(tSysProperty.getPropertyValue())){
                        property = tSysProperty;
                        break;
                    }
                }
                String code = ""; // 对象编号
                if (ObjectTypeEnum.PAWN.getValue().equals(companyTeam.getObjectType())) {
                    PawnInfo pawnInfo = pawnInfoMapper.get(id);
                    if (pawnInfo != null) {
                        code = pawnInfo.getPawnNo();
                    }
                } else if (ObjectTypeEnum.IOU.getValue().equals(companyTeam.getObjectType())) {
                    IOUInfo iouInfo = iouInfoMapper.get(id);
                    if (iouInfo != null) {
                        code = iouInfo.getIouNo();
                    }
                }
                String[] msg = {
                        companyDetailInfo1.getName(),
                        companyDetailInfo.getCompanyName(),
                        property.getPropertyName(),
                        userInfo.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(type).getName(),
                        code
                };
                smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_INVITE_CODE, companyDetailInfo1.getPhone(), msg);
                // 添加消息
                Message message = new Message();
                message.setTitle(MessageEnum.TASK.getName() + " > "
                                + ObjectTypeEnum.getObjectTypeEnum(type).getName() + " > "
                                + code
                ); // 业务类型 对象类型 对象编号
                message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_INVITE_CODE, msg));
                message.setSenderId(userInfo.getId());
                message.setReceiveId(companyDetailInfo1.getUserId());
                message.setLabel(null);
                message.setStatus(0);
                message.setType(MessageEnum.TASK.getValue());
                message.setBusinessType(MessageBTEnum.COMPANY_BETWEEN.getValue());
                message.setOperUrl("{\"accpet\":\"/api/company/updateBusinessService?type=" + type
                                + "&id=" + id + "&distributionId=" + result + "&businessType=" + businessType
                                + "&status=1\",\"reject\":\"/api/company/designBusinessService?type=" + type
                                + "&id=" + id + "&distributionId=" + result + "&businessType=" + businessType
                                + "&status=2}"
                );
                messageMapper.add(message);
                return result;
            }
        }
    }

    @Override
    public Integer updateBusinessService(Integer type, Integer id, Integer distributionId,
                                         Integer businessType, Integer status) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id, distributionId, businessType, status)) {
            return null;
        }
        if (!ObjectAcceptTypeEnum.accept.getValue().equals(status) && !ObjectAcceptTypeEnum.refuse.getValue().equals(status)) {
            return null; // 操作状态不对
        }
        if (!type.equals(ObjectTypeEnum.PAWN.getValue()) && !type.equals(ObjectTypeEnum.IOU.getValue())) {
            return null;
        }
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(distributionId);
        if (companyTeamRe == null) {
            return null; // 分配器记录不存在
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if (userInfo == null || userInfo.getCompanyId() == null) {
            return null; // 当前用户存在异常
        }
        companyTeamRe.setStatus(status);
        Integer result = companyTeamReMapper.update(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
//            businessLogService.add(distributionId, ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.update.getValue(),
//                    "", "决定(同意|拒绝)加入业务流转", 0, 0);
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId());
            if (companyTeam != null) {
                // 提醒消息
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId()); // 创建人信息
                CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId()); // 被邀请公司信息
                List<TSysProperty> propertyList = SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE);
                TSysProperty property = null;
                for (TSysProperty tSysProperty : propertyList){
                    if(companyDetailInfo.getType().toString().equals(tSysProperty.getPropertyValue())){
                        property = tSysProperty;
                        break;
                    }
                }
                SmsUtil smsUtil = new SmsUtil();
                String[] msg = {
                        creator.getRealName(),
                        companyDetailInfo.getCompanyName(),
                        property.getPropertyName(),
                        userInfo.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        companyTeam.getObjectId().toString()
                };
                Message message = new Message();
                // 修改对象关系
                ObjectUserRelationQuery query = new ObjectUserRelationQuery();
                query.setObjectType(type);
                query.setObjectId(id);
                query.setType(BusinessRelationEnum.dispose.getValue());
                query.setEmployerId(companyTeam.getId());
                query.setUserId(userInfo.getId());
                List<ObjectUserRelation> list = objectUserRelationMapper.list(query);
                if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                    // 修改数据对象
                    if (list != null && list.size() > 0) {
                        // 理论上只有一条
                        ObjectUserRelation relation = list.get(0);
                        relation.setStatus(SysProperty.BOOLEAN_FALSE); // 这里0为接收
                        objectUserRelationMapper.update(relation);
                        if (ObjectTypeEnum.PAWN.getValue().equals(type)) {
                            PawnInfo pawnInfo = pawnInfoMapper.get(id);
                            query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                            query.setObjectId(pawnInfo.getLenderId());
                            list = objectUserRelationMapper.list(query);
                            if (list != null && list.size() > 0) {
                                // 设置借款人为接受
                                if (SysProperty.BOOLEAN_TRUE.equals(list.get(0).getStatus())) {
                                    list.get(0).setStatus(SysProperty.BOOLEAN_FALSE);
                                    objectUserRelationMapper.update(list.get(0));
                                }
                            }
                        } else if (ObjectTypeEnum.IOU.getValue().equals(type)) {
                            IOUInfo iouInfo = iouInfoMapper.get(id);
                            query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                            query.setObjectId(iouInfo.getLenderId());
                            list = objectUserRelationMapper.list(query);
                            if (list != null && list.size() > 0) {
                                // 设置借款人为接受
                                if (SysProperty.BOOLEAN_TRUE.equals(list.get(0).getStatus())) {
                                    list.get(0).setStatus(SysProperty.BOOLEAN_FALSE);
                                    objectUserRelationMapper.update(list.get(0));
                                }
                            }
                        }
                    }
                    smsUtil.sendSms(SysProperty.SMS_DISTRIBUTION_PASS_CODE, creator.getMobile(), msg);
                    message.setContent(smsUtil.getSendContent(SysProperty.SMS_DISTRIBUTION_PASS_CODE, msg));
                } else {
                    if (list != null && list.size() > 0) {
                        // 理论上只有一条
                        objectUserRelationMapper.deleteByPrimaryKey(list.get(0).getId());
                        // 检查是否最后一条数据
                        query = new ObjectUserRelationQuery();
                        query.setObjectType(ObjectTypeEnum.IOU.getValue());
                        query.setType(BusinessRelationEnum.dispose.getValue());
                        query.setEmployerId(companyTeam.getId());
                        query.setUserId(userInfo.getId());
                        list = objectUserRelationMapper.list(query);
                        if (list == null || list.size() == 0) {
                            if (list == null || list.size() == 0) {
                                if (ObjectTypeEnum.PAWN.getValue().equals(type)) {
                                    PawnInfo pawnInfo = pawnInfoMapper.get(id);
                                    query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                                    query.setObjectId(pawnInfo.getLenderId());
                                    list = objectUserRelationMapper.list(query);
                                    if (list != null && list.size() > 0) {
                                        // 删除借款人(理论上只有一条)
                                        objectUserRelationMapper.deleteByPrimaryKey(list.get(0).getId());
                                    }
                                } else if (ObjectTypeEnum.IOU.getValue().equals(type)) {
                                    IOUInfo iouInfo = iouInfoMapper.get(id);
                                    query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                                    query.setObjectId(iouInfo.getLenderId());
                                    list = objectUserRelationMapper.list(query);
                                    if (list != null && list.size() > 0) {
                                        // 删除借款人(理论上只有一条)
                                        objectUserRelationMapper.deleteByPrimaryKey(list.get(0).getId());
                                    }
                                }
                            }
                        }
                    }
                    // 删除协作器
                    coordinatorService.delCoordinator(companyTeamRe.getAcceptCompanyId(), type, id);
                    // 删除业务流转数据
                    CompanyTeamRe ctr = new CompanyTeamRe();
                    ctr.setId(companyTeamRe.getId());
                    ctr.setStatus(ObjectAcceptTypeEnum.refuse.getValue());
                    companyTeamReMapper.update(ctr);

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
                if (SysProperty.BOOLEAN_TRUE.equals(status)) {
                    // 添加公司之间的关系
                    CompanyRelation companyRelation = companyRelationMapper.getByCompanyId(creator.getCompanyId(),
                            companyTeamRe.getAcceptCompanyId());
                    if (companyRelation == null) {
                        companyRelation = new CompanyRelation();
                        companyRelation.setCompanyAId(creator.getCompanyId());
                        companyRelation.setCompanyBId(companyTeamRe.getAcceptCompanyId());
                        companyRelationMapper.insert(companyRelation);
                    }
                    // 转换对象状态
                    if (type.equals(ObjectTypeEnum.PAWN.getValue())) {
                        // 当前流转的是抵押物
                        PawnInfo pawnInfo = pawnInfoMapper.get(id);
                        if (pawnInfo != null) {
                            if (PawnEnum.MAINTAIN_REGULAR.getValue().equals(businessType)) {
                                // 常规催收
                                pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                                pawnInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
                                pawnInfoMapper.update(pawnInfo);
                            } else if (PawnEnum.MARKET_DISPOSITION.getValue().equals(businessType)) {
                                // 市场处置
                                pawnInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                                pawnInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
                                pawnInfoMapper.update(pawnInfo);
                            } else if (PawnEnum.CM_SIMULTANEOUS.getValue().equals(businessType)) {
                                // 市场处置&常规催收
                                if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                                        .equals(companyDetailInfo.getType())) {
                                    // 催收
                                    pawnInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                                } else if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_INTERMEDIARY)
                                        .equals(companyDetailInfo.getType())) {
                                    // 市场
                                    pawnInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                                }
                                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
                                pawnInfoMapper.update(pawnInfo);
                            } else if (PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue().equals(businessType)) {
                                // 司法化解
                                pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                                pawnInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                                pawnInfoMapper.update(pawnInfo);
                            } else if (PawnEnum.CJ_SIMULTANEOUS.getValue().equals(businessType)) {
                                // 司法化解&常规催收
                                if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                                        .equals(companyDetailInfo.getType())) {
                                    // 催收
                                    pawnInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                                } else if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                                        .equals(companyDetailInfo.getType())) {
                                    // 律所
                                    pawnInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                                }
                                pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                                pawnInfoMapper.update(pawnInfo);
                            } else if (PawnEnum.CMJ_SIMULTANEOUS.getValue().equals(businessType)) {
                                // 市场处置&司法化解&常规催收
                                pawnInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                                pawnInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                                pawnInfoMapper.update(pawnInfo);
                            }
                        }
                    } else if (type.equals(ObjectTypeEnum.IOU.getValue())) {
                        // 当前流转的是借据
                        IOUInfo iouInfo = iouInfoMapper.get(id);
                        if (iouInfo != null) {
                            if (PawnEnum.MAINTAIN_REGULAR.getValue().equals(businessType)) {
                                // 常规催收
                                iouInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                                iouInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                                iouInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
                                iouInfoMapper.update(iouInfo);
                            } else if (PawnEnum.MARKET_DISPOSITION.getValue().equals(businessType)) {
                                // 市场处置
                                iouInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                                iouInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                                iouInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
                                iouInfoMapper.update(iouInfo);
                            } else if (PawnEnum.CM_SIMULTANEOUS.getValue().equals(businessType)) {
                                // 市场处置&常规催收
                                if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                                        .equals(companyDetailInfo.getType())) {
                                    // 催收
                                    iouInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                                } else if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_INTERMEDIARY)
                                        .equals(companyDetailInfo.getType())) {
                                    // 市场
                                    iouInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                                }
                                iouInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
                                iouInfoMapper.update(iouInfo);
                            } else if (PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue().equals(businessType)) {
                                // 司法化解
                                iouInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                                iouInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                                iouInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                                iouInfoMapper.update(iouInfo);
                            } else if (PawnEnum.CJ_SIMULTANEOUS.getValue().equals(businessType)) {
                                // 司法化解&常规催收
                                if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                                        .equals(companyDetailInfo.getType())) {
                                    // 催收
                                    iouInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                                } else if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                                        .equals(companyDetailInfo.getType())) {
                                    // 律所
                                    iouInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                                }
                                iouInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                                iouInfoMapper.update(iouInfo);
                            } else if (PawnEnum.CMJ_SIMULTANEOUS.getValue().equals(businessType)) {
                                // 市场处置&司法化解&常规催收
                                iouInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                                iouInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                                iouInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                                iouInfoMapper.update(iouInfo);
                            }
                        }
                    }
                }

            }
            return result;
        }
    }

    /**
     * 添加分配器里业务流转的对象数据
     *
     * @param distributionId 分配器ID
     * @param own            当前操作人公司信息
     * @param opposite       被分配公司信息
     * @return
     */
    private Integer addCompanyTeamRe(Integer distributionId, CompanyDetailInfo own,
                                     CompanyDetailInfo opposite) {
        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(distributionId);
        companyTeamReQuery.setCompanyId(opposite.getCompanyId());
        companyTeamReQuery.setType(ObjectBusinessTypeEnum.mechanism.getValue());
        List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
        if (companyTeamReList == null || companyTeamReList.size() == 0) {
            CompanyTeamRe companyTeamRe = new CompanyTeamRe();
            companyTeamRe.setCompanyTeamId(distributionId);
            companyTeamRe.setAcceptCompanyId(opposite.getCompanyId());
            companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
            companyTeamRe.setType(ObjectBusinessTypeEnum.mechanism.getValue());
            companyTeamRe.setAccepterId(opposite.getUserId());
            companyTeamRe.setRoleType(own.getType());
            companyTeamRe.setRequesterId(own.getCompanyId());
            Integer result = companyTeamReMapper.insert(companyTeamRe);
            if (CommonUtil.checkResult(result)) {
                return null;
            } else {
                return companyTeamRe.getId();
            }
        } else {
            // 理论上只有一条
            return companyTeamReList.get(0).getId();
        }
    }
}
