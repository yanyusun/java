package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.CompanyDTO;
import com.dqys.business.service.dto.company.CompanyRelationDTO;
import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.dto.company.OrganizationInsertDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
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

    /**
     * ID获取组织
     * @param id
     * @return
     */
    JsonResponse getOrganization(Integer id);

    /**
     * 遍历该公司的所有合作关系
     * @param companyId
     * @return
     */
    List<CompanyRelationDTO> getListRelation(Integer companyId);

    /**
     * 获取分配器列表(没有分配器则创建分配器)
     * @param type
     * @param id
     * @return
     */
    DistributionDTO getDistribution_tx(Integer type, Integer id) throws BusinessLogException;

    /**
     * 加入分配器
     * @param id 分配器ID
     * @param type 加入类型(邀请|被邀请)
     * @param text 邀请内容
     * @return
     */
    Integer joinDistribution_tx(Integer id, Integer type, String text) throws BusinessLogException;

    /**
     * 修改分配器(同意加入|拒绝加入)
     * @param id
     * @param type
     * @return
     */
    Integer updateDistribution_tx(Integer id, Integer type) throws BusinessLogException;

    /**
     * 退出该分配记录
     * @param id
     * @return
     */
    Integer exitDistribution_tx(Integer id) throws BusinessLogException;
}
