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

    @Autowired @Qualifier("b_companyService")
    private CompanyService companyService;
    @Autowired
    private DistributionService distributionService;

    /**
     * @api {GET} http://{url}/api/company/listCompany 查看特定类型的公司
     * @apiName listCompany
     * @apiGroup organization
     * @apiParam {number} [type] 公司类型
//     * @apiSuccess {companyDTO} data 公司信息集合
//     * @apiUse companyDTO
     */
    @RequestMapping(value = "/listCompany")
    public JsonResponse listCompany(@RequestParam(required = false) Integer type){
        return CommonUtil.responseBack(companyService.listByType(type));
    }


    /**
     * @api {GET} http://{url}/api/company/listOrganization 查看组织列表(部门|团队)
     * @apiName listOrganization
     * @apiGroup organization
     * @apiParam {number} companyId 公司Id
     * @apiParam {type} type 组织类型
     * @apiSuccess {OrganizationDTO} data 组织架构
     * @apiUse OrganizationDTO
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
     * @api {POST} http://{url}/api/company/addOrganization 新增组织(部门|团队)
     * @apiName addOrganization
     * @apiGroup organization
     * @apiUse Organization
     * @apiSuccess {number} data 新增的ID
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
     * @api {POST} http://{url}/api/company/updateOrganization 修改组织(部门|团队)
     * @apiName updateOrganization
     * @apiGroup organization
     * @apiUse Organization
     * @apiSuccess {number} data 修改后的ID
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
     * @api {get} http://{url}/api/company/deleteOrganization 删除组织(部门|团队)
     * @apiName deleteOrganization
     * @apiGroup organization
     * @apiParam {number} id 组织ID
     */
    @RequestMapping(value = "/deleteOrganization")
    public JsonResponse addOrganization(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.deleteOrganization(id);
    }

    /**
     * @api {get} http://{url}/api/company/getOrganization 获取组织(部门|团队)
     * @apiName getOrganization
     * @apiGroup organization
     * @apiParam {string} type 组织类型
     * @apiParam {number} id 组织ID
     * @apiSuccess {OrganizationDTO} data 返回组织架构
     * @apiUse OrganizationDTO
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
     * @api {get} http://{url}/api/company/getDistribution 获取该对象的分配器列表
     * @apiName getDistribution
     * @apiGroup distribution
     * @apiParam {number} type 分配对象类型(如:资产包asset)
     * @apiParam {number} id 分配对象ID
     */
    @RequestMapping(value = "/getDistribution")
    public JsonResponse listDistribution(@RequestParam(required = true) Integer type,
                                        @RequestParam(required = true) Integer id) throws BusinessLogException{
        if (CommonUtil.checkParam(type, id)) {
            return JsonResponseTool.success("参数错误");
        }
        if (ObjectTypeEnum.getObjectTypeEnum(type) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(distributionService.listDistribution_tx(type, id));
    }

    /**
     * @api {get} http://{url}/api/company/joinDistribution 申请加入分配器
     * @apiName joinDistribution
     * @apiGroup distribution
     * @apiParam {number} id 分配器ID
     * @apiSuccess {number} id
     */
    @RequestMapping(value = "/joinDistribution")
    public JsonResponse joinDistribution(@RequestParam(required = true) Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.success("参数错误");
        }
        return CommonUtil.responseBack(distributionService.joinDistribution(id));
    }

    /**
     * @api {get} http://{url}/api/company/inviteDistribution 邀请加入分配器
     * @apiName inviteDistribution
     * @apiGroup distribution
     * @apiParam {number} id 分配器ID
     * @apiParam {number} companyId 公司ID
     * @apiSuccess {number} id
     */
    @RequestMapping(value = "/inviteDistribution")
    public JsonResponse inviteDistribution(@RequestParam(required = true) Integer id,
                                           @RequestParam(required = true) Integer companyId) throws BusinessLogException{
        if (CommonUtil.checkParam(companyId, id)) {
            return JsonResponseTool.success("参数错误");
        }
        return CommonUtil.responseBack(distributionService.inviteDistribution(
                id, companyId));
    }

    /**
     * @api {get} http://{url}/api/company/designDistribution 决定加入分配器(同意或者拒绝)
     * @apiName designDistribution
     * @apiGroup distribution
     * @apiParam {number} id 被邀请ID
     * @apiParam {number} status 操作类型(接收1|拒绝0)
     * @apiSuccess {number} id
     */
    @RequestMapping(value = "/designDistribution")
    public JsonResponse designDistribution(@RequestParam(required = true) Integer id,
                                           @RequestParam(required = true) Integer status) throws BusinessLogException {
        if (CommonUtil.checkParam(id, status)) {
            return JsonResponseTool.success("参数错误");
        }
        if(!status.equals(ObjectAcceptTypeEnum.accept.getValue())){
            // 如果不是接受状态,全部设置为拒绝
            status = ObjectAcceptTypeEnum.refuse.getValue();
        }
        return CommonUtil.responseBack(distributionService.updateDistribution_tx(id, status));
    }


    /**
     * @api {get} http://{url}/api/company/exitDistribution 退出分配器
     * @apiName exitDistribution
     * @apiGroup distribution
     * @apiParam {number} id 分配器成员ID
     * @apiSuccess {number} data
     */
    @RequestMapping(value = "/exitDistribution")
    public JsonResponse exitDistribution(@RequestParam(required = true) Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.success("参数错误");
        }
        return CommonUtil.responseBack(distributionService.exitDistribution_tx(id));
    }

    /**
     * @api {get} http://{url}/api/company/getRelation 获取公司间合作关系
     * @apiName getRelation
     * @apiGroup companyRelation
     * @apiParam {number} id
     */
    @RequestMapping(value = "/getRelation")
    public JsonResponse getRelation(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.success("参数错误");
        }
        return JsonResponseTool.success(companyService.getListRelation(id));
    }

    /**
     * @api {get} http://{url}/api/company/listByService 根据业务类型获取公司
     * @apiName listByService
     * @apiGroup companyRelation
     * @apiParam {number} type 业务流转类型(催收1,处置2,司法3,催收处置4,催收司法5,全6)
     */
    @RequestMapping(value = "/listByService")
    public JsonResponse listCompanyByServiceType(Integer type){
        if(type == null || type < 1){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(companyService.listCompanyByServiceType(type));
    }

    /**
     * @api {get} http://{url}/api/company/listRelationByService 根据业务类型获取公司联系关系
     * @apiName listRelationByService
     * @apiGroup companyRelation
     * @apiParam {number} type 业务流转类型(催收1,处置2,司法3,催收处置4,催收司法5,全6)
     * @apiParam {number} id 公司ID
     */
    @RequestMapping(value = "/listRelationByService")
    public JsonResponse listRelationByServiceType(@RequestParam("type")Integer type, @RequestParam(required = false)Integer id){
        if(type == null || type < 1){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(companyService.listRelationByServiceType(type, id));
    }

    /**
     * @api {get} http://{url}/api/company/addBusinessService 平台为申请业务流转的公司添加业务流转伙伴接口
     * @apiName addBusinessService
     * @apiGroup companyRelation
     * @apiParam {number} type 业务流转类型
     * @apiParam {number} id 公司ID
     * @apiParam {number} distributionId 分配器ID
     * @apiParam {number} businessType 业务流转类型(催收1,处置2,司法3,催收处置4,催收司法5,全6)
     * @apiParam {number} companyId 被邀请公司ID
     */
    @RequestMapping(value = "/addBusinessService")
    public JsonResponse addBusinessService(@RequestParam Integer type, @RequestParam Integer id,
                                           @RequestParam Integer distributionId, @RequestParam Integer businessType,
                                           @RequestParam Integer companyId) throws BusinessLogException{
        if(CommonUtil.checkParam(type, id, distributionId, businessType, companyId)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(distributionService.addBusinessService(type, id, distributionId, businessType, companyId));
    }

    /**
     * @api {get} http://{url}/api/company/designBusinessService 被添加公司接受或者拒绝业务流转邀请
     * @apiName designBusinessService
     * @apiGroup companyRelation
     * @apiParam {number} type 业务流转类型(催收1,处置2,司法3,催收处置4,催收司法5,全6)
     * @apiParam {number} id 公司ID
     * @apiParam {number} distributionId 分配器ID
     * @apiParam {number} businessType 业务流转类型
     * @apiParam {number} status 接收1拒绝2
     */
    @RequestMapping(value = "/designBusinessService")
    public JsonResponse designBusinessService(@RequestParam Integer type, @RequestParam Integer id,
                                              @RequestParam Integer distributionId, @RequestParam Integer businessType,
                                              @RequestParam Integer status) throws BusinessLogException{
        if(CommonUtil.checkParam(type, id, distributionId, businessType, status)){
            return JsonResponseTool.paramErr("参数错误");
        }
        if(!status.equals(ObjectAcceptTypeEnum.accept.getValue())){
            // 如果不是接受状态,全部设置为拒绝
            status = ObjectAcceptTypeEnum.refuse.getValue();
        }
        return JsonResponseTool.success(
                distributionService.updateBusinessService(type, id, distributionId, businessType, status));
    }


}
