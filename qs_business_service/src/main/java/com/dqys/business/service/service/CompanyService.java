package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
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

    /**
     * ID获取组织
     * @param id
     * @return
     */
    JsonResponse getOrganization(Integer id);

    /**
     * 增加公司合作关系
     * @param aId 甲方公司
     * @param bId 乙方公司
     * @return
     */
    Integer addRelation_tx(Integer aId, Integer bId);

    /**
     * 删除公司间的合作关系
     * @param id
     * @return
     */
    Boolean deleteRelation_tx(Integer id);

    /**
     * 根据两个公司Id删除两者之间的联系
     * @param aId 甲方公司
     * @param bId 乙方公司
     * @return
     */
    Boolean deleteRelation_tx(Integer aId, Integer bId);

    /**
     * 遍历该公司的所有合作关系
     * @param companyId
     * @return
     */
    List<CompanyRelation> getListRelation(Integer companyId);

    /**
     * 获取公司之间的关联关系
     * @param aId
     * @param bId
     * @return null 不存在关联关系
     */
    CompanyRelation get(Integer aId, Integer bId);

}
