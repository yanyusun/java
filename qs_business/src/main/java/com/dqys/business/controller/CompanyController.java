package com.dqys.business.controller;

import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.OrganizationInsertDTO;
import com.dqys.business.service.service.CompanyService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yvan on 16/6/30.
 * <p/>
 * 公司&组织架构管理
 */
@Controller
@RequestMapping(value = "/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

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
    @ResponseBody
    public JsonResponse listTeam(@RequestParam(required = true) Integer companyId, @RequestParam(required = true) String type) {
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
    @ResponseBody
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
    @ResponseBody
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
     * @api {POST} http://{url}/api/company/deleteOrganization 删除组织(部门|团队)
     * @apiName deleteOrganization
     * @apiGroup organization
     * @apiParam {number} id 组织ID
     */
    @RequestMapping(value = "/deleteOrganization")
    @ResponseBody
    public JsonResponse addOrganization(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.deleteOrganization(id);
    }

    /**
     * @api {POST} http://{url}/api/company/getOrganization 获取组织(部门|团队)
     * @apiName getOrganization
     * @apiGroup organization
     * @apiParam {string} type 组织类型
     * @apiParam {number} id 组织ID
     * @apiSuccess {OrganizationDTO} data 返回组织架构
     * @apiUse OrganizationDTO
     */
    @RequestMapping(value = "/getOrganization")
    @ResponseBody
    public JsonResponse getOrganization(@RequestParam(required = true) String type,
                                        @RequestParam(required = true) Integer id){
        if(CommonUtil.checkParam(type, id)){
            return JsonResponseTool.success("参数错误");
        }
        if(OrganizationTypeEnum.getOrganizationTypeEnum(type) == null){
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.getOrganization(id);
    }

}
