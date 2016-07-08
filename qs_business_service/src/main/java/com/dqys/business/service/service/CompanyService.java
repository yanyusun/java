package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.CompanyDTO;
import com.dqys.business.service.dto.company.OrganizationInsertDTO;
import com.dqys.core.model.JsonResponse;

import java.util.List;

/**
 * Created by Yvan on 16/6/30.
 */
public interface CompanyService {

    /**
     * 获取公司下所有特定类型的组织<部门|团队|职业>
     *
     * @param companyId
     * @return
     */
    JsonResponse listOrganization(Integer companyId, OrganizationTypeEnum organizationTypeEnum);

    /**
     * 获取公司信息
     *
     * @param companyId
     * @return
     */
    CompanyDTO get(Integer companyId);

    /**
     * 根据用户ID获取数据
     * @param userId
     * @return
     */
    CompanyDTO getByUserId(Integer userId);

    /**
     * 新增组织
     * @param organizationInsertDTO
     * @return
     */
    JsonResponse addOrganization(OrganizationInsertDTO organizationInsertDTO);

    /**
     * 删除组织
     * @param id
     * @return
     */
    JsonResponse deleteOrganization(Integer id);

    /**
     * 修改组织
     * @param organizationInsertDTO
     * @return
     */
    JsonResponse updateOrganization(OrganizationInsertDTO organizationInsertDTO);



}
