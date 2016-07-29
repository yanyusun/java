package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectBusinessTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;
import com.dqys.business.service.constant.ObjectLogEnum;
import com.dqys.business.service.dto.company.CompanyTeamReDTO;
import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.DistributionService;
import com.dqys.business.service.utils.company.CompanyServiceUtils;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    private TUserInfoMapper userInfoMapper;
    @Autowired
    private TCompanyInfoMapper companyInfoMapper;

    @Autowired
    private BusinessLogService businessLogService;

    @Override
    public DistributionDTO getDistribution_tx(Integer type, Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id)) {
            return null;
        }
        // 查询分配器
        CompanyTeam companyTeam = companyTeamMapper.getByTypeId(type, id);
        if (companyTeam == null) {
            // 分配器不存在,创建分配器
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
            CompanyDetailInfo companyDetailInfo = companyInfoMapper.get(companyTeamRe.getId());
            if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_PLATFORM))) {
                distributionDTO.setPlatformNum(distributionDTO.getPlatformNum() + 1); // 平台方
            } else if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_ENTRUST))) {
                distributionDTO.setMechanismNum(distributionDTO.getMechanismNum() + 1); // 机构方
            } else {
                distributionDTO.setDisposeNum(distributionDTO.getDisposeNum() + 1); // 处置方
            }
            // todo  这里需要填充业绩比率,当前任务
            companyTeamReDTOList.add(CompanyServiceUtils.toCompanyTeamReDTO(companyTeamRe, companyDetailInfo, null, null));
        });
        distributionDTO.setCompanyTeamReDTOList(companyTeamReDTOList);
        return distributionDTO;
    }

    @Override
    public Integer joinDistribution_tx(Integer id, Integer type, String text) throws BusinessLogException {
        if (CommonUtil.checkParam(id, type)) {
            return null;
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if (userInfo == null || userInfo.getCompanyId() == null) {
            return null; // 用户不存在
        }
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.get(userInfo.getCompanyId());
        if (companyDetailInfo == null) {
            return null; // 公司不存在
        }
        CompanyTeam companyTeam = companyTeamMapper.get(id);
        if (companyTeam == null) {
            return null; // 分配器不存在
        }

        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(id);
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
        companyTeamRe.setType(type);
        companyTeamRe.setAccepterId(companyDetailInfo.getUserId());
        Integer result = companyTeamReMapper.insert(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
            businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.join.getValue(),
                    "", "", 0, id);
            // 增加对象与操作事物的联系
            ObjectUserRelation objectUserRelation = new ObjectUserRelation();
            objectUserRelation.setObjectType(companyTeam.getObjectType());
            objectUserRelation.setObjectId(companyTeam.getObjectId());
            objectUserRelation.setUserId(companyDetailInfo.getUserId());
            objectUserRelation.setStatus(ObjectUserStatusEnum.handled.getValue());
            objectUserRelation.setType(BusinessRelationEnum.company.getValue());
            result = objectUserRelationMapper.insert(objectUserRelation);
            if (result == null) {
                // 添加事物关系失败

            }
            // todo 发送短信内容
            if (text == null) {
                text = ""; // 默认消息
            }

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
            return null;
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if (userInfo == null || userInfo.getCompanyId() == null) {
            return null;
        }
        if (!userInfo.getCompanyId().equals(companyTeamRe.getAcceptCompanyId())) {
            // 避免非公司人员操作
            return null;
        }

        companyTeamRe.setStatus(status);
        companyTeamRe.setAccepterId(userInfo.getId());
        Integer result = companyTeamReMapper.update(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            // 添加操作记录
            businessLogService.add(companyTeamRe.getId(), ObjectTypeEnum.DISTRIBUTION.getValue(), ObjectLogEnum.update.getValue(),
                    "", "", 0, id);
            // 判断是否接收邀请
            if (status.equals(1)) {
                CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId());
                if (companyTeam != null) {
                    if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                        // 借款人类型
                        CompanyDetailInfo companyDetailInfo = companyInfoMapper.get(companyTeamRe.getAcceptCompanyId());
                        if (companyDetailInfo != null) {
                            if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_LAW))) {
                                // 律所
                                LenderInfo lenderInfo = new LenderInfo();
                                lenderInfo.setId(companyTeam.getObjectId());
                                lenderInfo.setIsLawyer(SysProperty.BOOLEAN_TRUE);
                                lenderInfoMapper.update(lenderInfo);
                            }else if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_URGE))){
                                // 催收
                                LenderInfo lenderInfo = new LenderInfo();
                                lenderInfo.setId(companyTeam.getObjectId());
                                lenderInfo.setIsCollection(SysProperty.BOOLEAN_TRUE);
                                lenderInfoMapper.update(lenderInfo);
                            }else if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_INTERMEDIARY))){
                                // 中介
                                LenderInfo lenderInfo = new LenderInfo();
                                lenderInfo.setId(companyTeam.getObjectId());
                                lenderInfo.setIsAgent(SysProperty.BOOLEAN_TRUE);
                                lenderInfoMapper.update(lenderInfo);
                            }
                        }
                    }else if (companyTeam.getObjectType().equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                        // 资产包类型
                        CompanyDetailInfo companyDetailInfo = companyInfoMapper.get(companyTeamRe.getAcceptCompanyId());
                        if (companyDetailInfo != null) {
                            if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_LAW))) {
                                // 律所

                            }else if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_URGE))){
                                // 催收

                            }else if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_INTERMEDIARY))){
                                // 中介
                                
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
        // 存在该分配数据
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(id);
        if (companyTeamRe == null) {
            return null;
        }
        // 校验是否是该公司的管理员
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.get(companyTeamRe.getAcceptCompanyId());
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
                if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                    // 借款人类型
                    if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_LAW))) {
                        // 律所
                        LenderInfo lenderInfo = new LenderInfo();
                        lenderInfo.setId(companyTeam.getObjectId());
                        lenderInfo.setIsLawyer(SysProperty.BOOLEAN_FALSE);
                        lenderInfoMapper.update(lenderInfo);
                    }else if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_URGE))){
                        // 催收
                        LenderInfo lenderInfo = new LenderInfo();
                        lenderInfo.setId(companyTeam.getObjectId());
                        lenderInfo.setIsCollection(SysProperty.BOOLEAN_FALSE);
                        lenderInfoMapper.update(lenderInfo);
                    }else if (companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_INTERMEDIARY))){
                        // 中介
                        LenderInfo lenderInfo = new LenderInfo();
                        lenderInfo.setId(companyTeam.getObjectId());
                        lenderInfo.setIsAgent(SysProperty.BOOLEAN_FALSE);
                        lenderInfoMapper.update(lenderInfo);
                    }
                }
            }
            return result;
        }
    }
}
