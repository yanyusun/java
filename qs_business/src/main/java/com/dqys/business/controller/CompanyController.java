package com.dqys.business.controller;

import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.OrganizationInsertDTO;
import com.dqys.business.service.service.CompanyService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Yvan on 16/6/30.
 *
 * 公司&组织架构管理
 */
@Controller
@RequestMapping(value = "/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    /**
     * 查看组织列表(部门|团队)
     * @param companyId
     * @param type
     * @return
     */
    @RequestMapping(value = "/listOrganization")
    @ResponseBody
    public JsonResponse listTeam(@RequestParam(required = true) Integer companyId, String type){
        if(CommonUtil.checkParam(companyId)){
            return JsonResponseTool.paramErr("参数错误");
        }
        OrganizationTypeEnum organizationTypeEnum = OrganizationTypeEnum.getOrganizationTypeEnum(type);
        if(organizationTypeEnum == null){
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.listOrganization(companyId, organizationTypeEnum);
    }

    /**
     * 新增组织(部门|团队)
     * @param organizationInsertDTO
     * @return
     */
    @RequestMapping(value = "/addOrganization")
    @ResponseBody
    public JsonResponse addOrganization(@ModelAttribute OrganizationInsertDTO organizationInsertDTO){
        if(CommonUtil.checkParam(organizationInsertDTO, organizationInsertDTO.getType(), organizationInsertDTO.getName(),
                organizationInsertDTO.getCompanyId())){
            return JsonResponseTool.paramErr("参数错误");
        }
        if(OrganizationTypeEnum.getOrganizationTypeEnum(organizationInsertDTO.getType()) == null){
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.addOrganization(organizationInsertDTO);
    }

    /**
     * 修改组织(部门|团队)
     * @param organizationInsertDTO
     * @return
     */
    @RequestMapping(value = "/updateOrganization")
    @ResponseBody
    public JsonResponse updateOrganization(@ModelAttribute OrganizationInsertDTO organizationInsertDTO){
        if(CommonUtil.checkParam(organizationInsertDTO, organizationInsertDTO.getType(), organizationInsertDTO.getName(),
                organizationInsertDTO.getCompanyId())){
            return JsonResponseTool.paramErr("参数错误");
        }
        if(OrganizationTypeEnum.getOrganizationTypeEnum(organizationInsertDTO.getType()) == null){
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.updateOrganization(organizationInsertDTO);
    }

    /**
     * 删除组织(部门|团队)
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteOrganization")
    @ResponseBody
    public JsonResponse addOrganization(@RequestParam(required = true) Integer id){
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return companyService.deleteOrganization(id);
    }


}
