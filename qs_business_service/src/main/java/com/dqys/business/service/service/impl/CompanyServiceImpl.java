package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.mapper.company.OrganizationMapper;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.query.company.OrganizationQuery;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.CompanyDTO;
import com.dqys.business.service.dto.company.OrganizationInsertDTO;
import com.dqys.business.service.service.CompanyService;
import com.dqys.business.service.utils.company.CompanyServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

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
    public Integer addRelation_tx(Integer aId, Integer bId) {
        if(CommonUtil.checkParam(aId, bId)){
            return null;
        }
        CompanyRelation companyRelation = new CompanyRelation();
        companyRelation.setCompanyAId(aId);
        companyRelation.setCompanyBId(bId);

        Integer result = companyRelationMapper.insert(companyRelation);
        if(!CommonUtil.checkResult(result)){
            return companyRelation.getId();
        }
        return null;
    }

    @Override
    public Boolean deleteRelation_tx(Integer id) {
        if(CommonUtil.checkParam(id)){
            return null;
        }
        if(CommonUtil.checkResult(companyRelationMapper.deleteByPrimaryKey(id))){
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteRelation_tx(Integer aId, Integer bId) {
        if(CommonUtil.checkParam(aId, bId)){
            return null;
        }
        if(CommonUtil.checkResult(companyRelationMapper.deleteByCompanyId(aId, bId))){
            return false;
        }
        return true;
    }

    @Override
    public List<CompanyRelation> getListRelation(Integer companyId) {
        if(CommonUtil.checkParam(companyId)){
            return null;
        }
        return null;
    }

    @Override
    public CompanyRelation get(Integer aId, Integer bId) {
        if(CommonUtil.checkParam(aId, bId)){
            return null;
        }
        return null;
    }
}
