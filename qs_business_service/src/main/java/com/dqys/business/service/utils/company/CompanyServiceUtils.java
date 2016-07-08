package com.dqys.business.service.utils.company;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.service.dto.company.CompanyDTO;
import com.dqys.business.service.dto.company.OrganizationDTO;
import com.dqys.business.service.dto.company.OrganizationInsertDTO;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.SysPropertyTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/6/30.
 */
public class CompanyServiceUtils {

    public static CompanyDTO toCompanyDTO(TCompanyInfo companyInfo){
        CompanyDTO companyDTO = new CompanyDTO();

        companyDTO.setId(companyInfo.getId());
        companyDTO.setName(companyInfo.getCompanyName());
        companyDTO.setProvince(AreaTool.getAreaById(companyInfo.getProvince()).getName());
        companyDTO.setCity(AreaTool.getAreaById(companyInfo.getCity()).getName());
        companyDTO.setDistrict(AreaTool.getAreaById(companyInfo.getArea()).getName());

        return companyDTO;
    }

    public static List<OrganizationDTO> toOrganizationDTO(List<Organization> organizationList){
        List<OrganizationDTO> organizationDTOList = new ArrayList<>();

        organizationList.forEach(organization -> {
            organizationDTOList.add(toOrganizationDTO(organization));
        });
        
        return organizationDTOList;
    }

    public static Organization toOrganization(OrganizationInsertDTO organizationInsertDTO){
        Organization organization = new Organization();

        organization.setType(organizationInsertDTO.getType());
        organization.setName(organizationInsertDTO.getName());
        organization.setCompanyId(organizationInsertDTO.getCompanyId());
        organization.setUserId(organizationInsertDTO.getUserId());
        organization.setRemark(organizationInsertDTO.getRemark());
        organization.setPid(organizationInsertDTO.getPid());

        return organization;
    }

    public static OrganizationDTO toOrganizationDTO(Organization organization){
        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setUserId(organization.getUserId());

        return organizationDTO;
    }
}
