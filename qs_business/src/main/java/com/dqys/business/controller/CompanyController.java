package com.dqys.business.controller;

import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.OrganizationInsertDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.CompanyService;
import com.dqys.business.service.service.DistributionService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yvan on 16/6/30.
 * <p/>
 * 公司&组织架构管理
 */
@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {

    @Autowired
    @Qualifier("b_companyService")
    private CompanyService companyService;
    @Autowired
    private DistributionService distributionService;

    /**
     * 查看特定类型的公司
     *
     * @param type 特定类型
     * @return
     */
    @RequestMapping(value = "/listCompany")
    public JsonResponse listCompany(@RequestParam(required = false) Integer type) {
        return CommonUtil.responseBack(companyService.listByType(type));
    }


    /**
     * 查看组织列表(部门|团队)
     *
     * @param companyId 公司ID
     * @param type      组织类型
     * @return
     */
    @RequestMapping(value = "/listOrganization")
    public JsonResponse listTeam(@RequestParam(required = true) Integer companyId,
                                 @RequestParam(required = true) String type) {
        if (CommonUtil.checkParam(companyId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        OrganizationTypeEnum organizationTypeEnum = OrganizationTypeEnum.getOrganizationTypeEnum(type);
        if (organizationTypeEnum == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.listOrganization(companyId, organizationTypeEnum);
    }

    /**
     * 新增组织(部门|团队)
     *
     * @param organizationInsertDTO 新增组织
     * @return
     */
    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public JsonResponse addOrganization(@ModelAttribute OrganizationInsertDTO organizationInsertDTO) {
        if (CommonUtil.checkParam(organizationInsertDTO, organizationInsertDTO.getType(), organizationInsertDTO.getName(),
                organizationInsertDTO.getCompanyId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (OrganizationTypeEnum.getOrganizationTypeEnum(organizationInsertDTO.getType()) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.addOrganization(organizationInsertDTO);
    }

    /**
     * 修改组织(部门|团队)
     *
     * @param organizationInsertDTO 修改组织信息
     * @return
     */
    @RequestMapping(value = "/updateOrganization", method = RequestMethod.POST)
    public JsonResponse updateOrganization(@ModelAttribute OrganizationInsertDTO organizationInsertDTO) {
        if (CommonUtil.checkParam(organizationInsertDTO, organizationInsertDTO.getType(), organizationInsertDTO.getName(),
                organizationInsertDTO.getCompanyId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (OrganizationTypeEnum.getOrganizationTypeEnum(organizationInsertDTO.getType()) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.updateOrganization(organizationInsertDTO);
    }

    /**
     * 删除组织(部门|团队)
     *
     * @param id 组织ID
     * @return
     */
    @RequestMapping(value = "/deleteOrganization")
    public JsonResponse addOrganization(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.deleteOrganization(id);
    }

    /**
     * 获取组织(部门|团队)
     *
     * @param type 组织类型
     * @param id   公司ID
     * @return
     */
    @RequestMapping(value = "/getOrganization")
    public JsonResponse getOrganization(@RequestParam(required = true) String type,
                                        @RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(type, id)) {
            return JsonResponseTool.success("参数错误");
        }
        if (OrganizationTypeEnum.getOrganizationTypeEnum(type) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.getOrganization(id);
    }

    /**
     * 获取该对象的分配器列表
     *
     * @param type 对象类型
     * @param id   对象ID
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/getDistribution")
    public JsonResponse listDistribution(@RequestParam(required = true) Integer type,
                                         @RequestParam(required = true) Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id)) {
            return JsonResponseTool.success("参数错误");
        }
        if (ObjectTypeEnum.getObjectTypeEnum(type) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(distributionService.listDistribution_tx(type, id));
    }

    /**
     * 申请加入分配器
     *
     * @param id 分配器ID
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/joinDistribution")
    public JsonResponse joinDistribution(@RequestParam(required = true) Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.success("参数错误");
        }
        return distributionService.joinDistribution(id);
    }

    /**
     * 邀请加入分配器
     *
     * @param id        分配器ID
     * @param companyId 被邀请公司ID
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/inviteDistribution")
    public JsonResponse inviteDistribution(@RequestParam(required = true) Integer id,
                                           @RequestParam(required = true) Integer companyId) throws BusinessLogException {
        if (CommonUtil.checkParam(companyId, id)) {
            return JsonResponseTool.success("参数错误");
        }
        return distributionService.inviteDistribution(id, companyId);
    }

    /**
     * 决定加入分配器(同意或者拒绝)
     *
     * @param id     分配器成员id
     * @param status 状态
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/designDistribution")
    public JsonResponse designDistribution(@RequestParam(required = true) Integer id,
                                           @RequestParam(required = true) Integer status) throws BusinessLogException {
        if (CommonUtil.checkParam(id, status)) {
            return JsonResponseTool.success("参数错误");
        }
        if (!status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
            // 如果不是接受状态,全部设置为拒绝
            status = ObjectAcceptTypeEnum.refuse.getValue();
        }
        return distributionService.designDistribution(id,status);
    }


    /**
     * 退出分配器
     *
     * @param id 分配器成员ID
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/exitDistribution")
    public JsonResponse exitDistribution(@RequestParam(required = true) Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.success("参数错误");
        }
        return distributionService.exitDistribution_tx(id);
    }

    /**
     * 获取公司间合作关系
     *
     * @param id 公司Id
     * @return
     */
    @RequestMapping(value = "/getRelation")
    public JsonResponse getRelation(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.success("参数错误");
        }
        return JsonResponseTool.success(companyService.getListRelation(id));
    }

    /**
     * 根据业务类型获取公司
     *
     * @param type 业务流转类型
     * @return
     */
    @RequestMapping(value = "/listByService")
    public JsonResponse listCompanyByServiceType(Integer type) {
        if (type == null || type < 1) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(companyService.listCompanyByServiceType(type));
    }

    /**
     * 根据业务类型获取公司联系关系
     *
     * @param type 业务流转类型
     * @param id   公司ID
     * @return
     */
    @RequestMapping(value = "/listRelationByService")
    public JsonResponse listRelationByServiceType(@RequestParam("type") Integer type, @RequestParam(required = false) Integer id) {
        if (type == null || type < 1) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(companyService.listRelationByServiceType(type, id));
    }

    /**
     * 平台为申请业务流转的公司添加业务流转伙伴接口
     *
     * @param type              对象类型
     * @param id                对象ID
     * @param distributionId    分配器ID
     * @param businessType      业务流转类型
     * @param companyId         公司Id
     * @param businessRequestId 业务请求用户id
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/addBusinessService")
    public JsonResponse addBusinessService(@RequestParam Integer type, @RequestParam Integer id,
                                           @RequestParam Integer distributionId, @RequestParam Integer businessType,
                                           @RequestParam Integer companyId, @RequestParam Integer businessRequestId) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id, distributionId, businessType, companyId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return distributionService.addBusinessService(type, id, distributionId, businessType, companyId, businessRequestId);
    }

    /**
     * 被添加公司接受或者拒绝业务流转邀请
     *
     * @param type           对象类型
     * @param id             对象ID
     * @param distributionId 分配器成员ID
     * @param businessType   业务流转类型
     * @param status         状态码
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/designBusinessService")
    public JsonResponse designBusinessService(@RequestParam Integer type, @RequestParam Integer id,
                                              @RequestParam Integer distributionId, @RequestParam Integer businessType,
                                              @RequestParam Integer status) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id, distributionId, businessType, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (!status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
            // 如果不是接受状态,全部设置为拒绝
            status = ObjectAcceptTypeEnum.refuse.getValue();
        }
        return distributionService.updateBusinessService(type, id, distributionId, businessType, status);
    }

    /**
     * 删除业务流转的被添加公司
     *
     * @param id         分配器成员id
     * @param targetType 对象类型
     * @param targetId   对象ID
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/exitBusinessService")
    public JsonResponse exitBusinessService(@RequestParam(required = true) Integer id,
                                            @RequestParam(required = true) Integer targetType,
                                            @RequestParam(required = true) Integer targetId) throws BusinessLogException {
        if (CommonUtil.checkParam(id, targetType, targetId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return distributionService.exitBusinessService(id, targetType, targetId);
    }


}
