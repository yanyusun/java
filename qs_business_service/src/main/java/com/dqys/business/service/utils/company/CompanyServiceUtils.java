package com.dqys.business.service.utils.company;

import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.service.dto.company.*;
import com.dqys.business.service.service.CompanyService;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.NoSQLWithRedisTool;
import com.dqys.core.utils.SysPropertyTool;
import com.rabbitmq.http.client.domain.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/6/30.
 */
public class CompanyServiceUtils {

    public static List<CompanyDTO> toCompanyDTO(List<TCompanyInfo> companyInfos) {
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        companyInfos.forEach(companyInfo -> {
            companyDTOList.add(toCompanyDTO(companyInfo));
        });
        return companyDTOList;
    }

    public static CompanyDTO toCompanyDTO(TCompanyInfo companyInfo) {
        if (companyInfo == null) {
            return null;
        }
        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setId(companyInfo.getId());
        companyDTO.setName(companyInfo.getCompanyName());
        companyDTO.setProvince(AreaTool.getAreaById(companyInfo.getProvince()).getLabel());
        companyDTO.setCity(AreaTool.getAreaById(companyInfo.getCity()).getLabel());
        companyDTO.setDistrict(AreaTool.getAreaById(companyInfo.getArea()).getLabel());
        companyDTO.setCompanyType(companyInfo.getBusinessType());
        return companyDTO;
    }

    public static List<OrganizationDTO> toOrganizationDTO(List<Organization> organizationList) {
        List<OrganizationDTO> organizationDTOList = new ArrayList<>();

        organizationList.forEach(organization -> {
            organizationDTOList.add(toOrganizationDTO(organization));
        });

        return organizationDTOList;
    }

    public static Organization toOrganization(OrganizationInsertDTO organizationInsertDTO) {
        if (organizationInsertDTO == null) {
            return null;
        }
        Organization organization = new Organization();

        organization.setType(organizationInsertDTO.getType());
        organization.setName(organizationInsertDTO.getName());
        organization.setCompanyId(organizationInsertDTO.getCompanyId());
        organization.setUserId(organizationInsertDTO.getUserId());
        organization.setRemark(organizationInsertDTO.getRemark());
        organization.setPid(organizationInsertDTO.getPid());

        return organization;
    }

    public static OrganizationDTO toOrganizationDTO(Organization organization) {
        if (organization == null) {
            return null;
        }
        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setUserId(organization.getUserId());

        return organizationDTO;
    }

    /**
     * 转化公司关系为DTO
     *
     * @param companyRelationList
     * @return
     */
    public static List<CompanyRelationDTO> toCompanyRelationDTO(List<CompanyRelation> companyRelationList) {
        if (companyRelationList == null || companyRelationList.size() == 0) {
            return null;
        }
        List<CompanyRelationDTO> companyRelationDTOList = new ArrayList<>();
        companyRelationList.forEach(companyRelation -> {
            companyRelationDTOList.add(toCompanyRelationDTO(companyRelation, null, null));
        });
        return companyRelationDTOList;
    }

    /**
     * 转化公司关系为DTO
     *
     * @param companyRelation
     * @param aName
     * @param bName
     * @return
     */
    public static CompanyRelationDTO toCompanyRelationDTO(CompanyRelation companyRelation, String aName, String bName) {
        if (companyRelation == null) {
            return null;
        }
        CompanyRelationDTO companyRelationDTO = new CompanyRelationDTO();

        companyRelationDTO.setId(companyRelation.getId());
        companyRelationDTO.setaId(companyRelation.getCompanyAId());
        companyRelationDTO.setbId(companyRelation.getCompanyBId());
        companyRelationDTO.setCreateAt(companyRelation.getCreateAt());
        companyRelationDTO.setaName(aName);
        companyRelationDTO.setbName(bName);

        return companyRelationDTO;
    }

    public static CompanyTeamReDTO toCompanyTeamReDTO(CompanyTeamRe companyTeamRe, CompanyDetailInfo companyDetailInfo,
                                                      String rate, Integer task) {
        if (companyTeamRe == null) {
            return null;
        }
        if (companyDetailInfo == null) {
            companyDetailInfo = new CompanyDetailInfo();
        }
        CompanyTeamReDTO companyTeamReDTO = new CompanyTeamReDTO();
        companyTeamReDTO.setCompanyId(companyDetailInfo.getCompanyId());
        companyTeamReDTO.setCompanyName(companyDetailInfo.getCompanyName());
        companyTeamReDTO.setId(companyTeamRe.getId());
        companyTeamReDTO.setTime(companyTeamRe.getUpdateAt());
        companyTeamReDTO.setUserId(companyTeamRe.getAccepterId());
        companyTeamReDTO.setAvg(companyDetailInfo.getAvg());
        companyTeamReDTO.setAddress(AreaTool.getAreaById(companyDetailInfo.getProvince()).getLabel()
                + AreaTool.getAreaById(companyDetailInfo.getCity()).getLabel()
                + AreaTool.getAreaById(companyDetailInfo.getDistrict()).getLabel());
        companyTeamReDTO.setRate(rate);
        companyTeamReDTO.setContact(companyDetailInfo.getName());
        companyTeamReDTO.setStatus(companyTeamRe.getStatus());
        companyTeamReDTO.setTask(task);
        companyTeamReDTO.setStateflag(companyTeamRe.getStateflag().intValue());
        companyTeamReDTO.setUserType(companyDetailInfo.getType());
        switch (companyDetailInfo.getType()) {
            case 1:
                companyTeamReDTO.setType("平台");
                break;
            case 0:
                companyTeamReDTO.setType("普通用户");
                break;
            case 31:
                companyTeamReDTO.setType("催收方");
                break;
            case 32:
                companyTeamReDTO.setType("律所");
                break;
            case 33:
                companyTeamReDTO.setType("中介");
                break;
            case 2:
                companyTeamReDTO.setType("委托方");
                break;
        }
        return companyTeamReDTO;
    }


}
