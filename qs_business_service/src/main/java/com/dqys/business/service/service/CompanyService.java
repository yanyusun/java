package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.CompanyDTO;

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
    List<Organization> listOrganization(Integer companyId, OrganizationTypeEnum organizationTypeEnum);

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
}
