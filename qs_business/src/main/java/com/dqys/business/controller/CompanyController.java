package com.dqys.business.controller;

import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.service.CompanyService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * 查询部门信息
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/listApartment")
    @ResponseBody
    public JsonResponse listApartment(@RequestParam(required = true) Integer companyId){
        if(CommonUtil.checkParam(companyId)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(companyService.listOrganization(companyId, OrganizationTypeEnum.apartment));
    }

    /**
     * 查询团队信息
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/listTeam")
    @ResponseBody
    public JsonResponse listTeam(@RequestParam(required = true) Integer companyId){
        if(CommonUtil.checkParam(companyId)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(companyService.listOrganization(companyId, OrganizationTypeEnum.team));
    }
}
