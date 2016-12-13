package com.dqys.business.controller;

import com.dqys.business.orm.pojo.organization.OrganizationCompanyQuery;
import com.dqys.business.service.service.organization.OrganizationCompanyService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 机构管理
 * Created by mkfeng on 2016/12/13.
 */
@Controller
@RequestMapping("/organiz")
public class OrganizationCompanyController {

    @Autowired
    private OrganizationCompanyService organizationService;

    /**
     * @api {post} organiz/organizList 获取机构管理列表
     * @apiName organiz/organizList
     * @apiSampleRequest organiz/organizList
     * @apiGroup organiz
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping("/organizList")
    @ResponseBody
    public JsonResponse organizList(OrganizationCompanyQuery query) {
        Map map = organizationService.organizList(query);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure("查询失败");
        }
    }


}
