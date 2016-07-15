package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectBusinessTypeEnum;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.company.OrganizationMapper;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;
import com.dqys.business.orm.query.company.OrganizationQuery;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.*;
import com.dqys.business.service.service.CompanyService;
import com.dqys.business.service.utils.company.CompanyServiceUtils;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/6/30.
 */
@Repository
@Primary
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private TCompanyInfoMapper companyInfoMapper;
    @Autowired
    private CompanyRelationMapper companyRelationMapper;
    @Autowired
    private TUserInfoMapper userInfoMapper;
    @Autowired
    private CompanyTeamMapper companyTeamMapper;
    @Autowired
    private CompanyTeamReMapper companyTeamReMapper;

    @Override
    public JsonResponse listOrganization(Integer companyId, OrganizationTypeEnum organizationTypeEnum) {
        OrganizationQuery organizationQuery = new OrganizationQuery();

        organizationQuery.setCompanyId(companyId);
        organizationQuery.setType(organizationTypeEnum.name());

        return JsonResponseTool.success(
                CompanyServiceUtils.toOrganizationDTO(organizationMapper.list(organizationQuery)));
    }

    @Override
    public CompanyDTO get(Integer companyId) {
        if (companyId == null) {
            return null;
        }
        TCompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        if (companyInfo != null) {
            return CompanyServiceUtils.toCompanyDTO(companyInfo);
        }
        return null;
    }

    @Override
    public CompanyDTO getByUserId(Integer userId) {
        if (CommonUtil.checkParam(userId)) {
            return null;
        }
        TUserInfo tUserInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (tUserInfo != null && tUserInfo.getCompanyId() != null) {
            return CompanyServiceUtils.toCompanyDTO(
                    companyInfoMapper.selectByPrimaryKey(tUserInfo.getCompanyId()));
        }
        return null;
    }

    @Override
    public JsonResponse addOrganization(OrganizationInsertDTO organizationInsertDTO) {
        if (CommonUtil.checkParam(organizationInsertDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Organization organization = CompanyServiceUtils.toOrganization(organizationInsertDTO);
        return CommonUtil.responseBack(organizationMapper.insert(organization));
    }

    @Override
    public JsonResponse deleteOrganization(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return CommonUtil.responseBack(organizationMapper.deleteByPrimaryKey(id));
    }

    @Override
    public JsonResponse updateOrganization(OrganizationInsertDTO organizationInsertDTO) {
        if (CommonUtil.checkParam(organizationInsertDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Organization organization = CompanyServiceUtils.toOrganization(organizationInsertDTO);
        return CommonUtil.responseBack(organizationMapper.update(organization));
    }

    @Override
    public JsonResponse getOrganization(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(CompanyServiceUtils.toOrganizationDTO(organizationMapper.get(id)));
    }

    @Override
    public List<CompanyRelationDTO> getListRelation(Integer companyId) {
        if(CommonUtil.checkParam(companyId)){
            return null;
        }
        return CompanyServiceUtils.toCompanyRelationDTO(companyRelationMapper.listByCompanyId(companyId));
    }

    @Override
    public DistributionDTO getDistribution_tx(Integer type, Integer id) {
        if(CommonUtil.checkParam(type, id)){
            return null;
        }
        // 查询分配器
        CompanyTeam companyTeam = companyTeamMapper.getByTypeId(type, id);
        if(companyTeam == null){
            // 分配器不存在,创建分配器
            companyTeam = new CompanyTeam();
            companyTeam.setObjectId(id);
            companyTeam.setSenderId(UserSession.getCurrent().getUserId());
            companyTeam.setObjectType(type);
            Integer result = companyTeamMapper.insert(companyTeam);
            if(CommonUtil.checkResult(result)){
                // 创建失败
                return null;
            }
            Integer teamId = companyTeam.getId();
            companyTeam = companyTeamMapper.get(teamId);
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
            if(companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_PLATFORM))){
                distributionDTO.setPlatformNum(distributionDTO.getPlatformNum()+1); // 平台方
            }else if(companyDetailInfo.getType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_ENTRUST))){
                distributionDTO.setMechanismNum(distributionDTO.getMechanismNum()+1); // 机构方
            }else{
                distributionDTO.setDisposeNum(distributionDTO.getDisposeNum()+1); // 处置方
            }
            // todo  这里需要填充业绩比率,当前任务
            companyTeamReDTOList.add(CompanyServiceUtils.toCompanyTeamReDTO(companyTeamRe, companyDetailInfo, null, null));
        });
        distributionDTO.setCompanyTeamReDTOList(companyTeamReDTOList);
        return distributionDTO;
    }

    @Override
    public Integer joinDistribution_tx(Integer id, Integer type, String text) {
        if(CommonUtil.checkParam(id, type)){
            return null;
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if(userInfo == null || userInfo.getCompanyId() == null){
            return null;
        }
        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(id);
        companyTeamReQuery.setCompanyId(userInfo.getCompanyId());
        List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
        if(companyTeamReList != null || companyTeamReList.size() > 0){
            // 已经存在在该分配器中无需再次申请或邀请
            return null;
        }
        CompanyTeamRe companyTeamRe = new CompanyTeamRe();
        companyTeamRe.setCompanyTeamId(id);
        companyTeamRe.setAcceptCompanyId(userInfo.getCompanyId());
        companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
        companyTeamRe.setType(type);
        Integer result = companyTeamReMapper.insert(companyTeamRe);
        if(CommonUtil.checkResult(result)){
            return null;
        }else{
            // todo 发送短信内容

            return companyTeamRe.getId();
        }
    }

    @Override
    public Integer updateDistribution_tx(Integer id, Integer status) {
        if(CommonUtil.checkParam(id, status)){
            return null;
        }
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(id);
        if(companyTeamRe == null){
            return null;
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if(userInfo == null || userInfo.getCompanyId() == null){
            return null;
        }
        if(!userInfo.getCompanyId().equals(companyTeamRe.getAcceptCompanyId())){
            // 避免非公司人员操作
            return null;
        }
        companyTeamRe.setStatus(status);
        companyTeamRe.setAccepterId(userInfo.getId());
        return companyTeamReMapper.update(companyTeamRe);
    }

    @Override
    public Integer exitDistribution_tx(Integer id) {
        if(id == null){
            return null;
        }
        // 存在该分配数据
        CompanyTeamRe companyTeamRe =companyTeamReMapper.get(id);
        if(companyTeamRe == null){
            return null;
        }
        // 校验是否是该公司的管理员
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.get(companyTeamRe.getAcceptCompanyId());
        if(companyDetailInfo == null || !companyDetailInfo.getUserId().equals(UserSession.getCurrent().getUserId())){
            return null;
        }
        return companyTeamReMapper.deleteByPrimaryKey(id);
    }
}
