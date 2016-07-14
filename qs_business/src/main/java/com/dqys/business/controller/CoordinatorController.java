package com.dqys.business.controller;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 协作器（跨部门、跨机构协作工具）
 * Created by mkfeng on 2016/7/12.
 */
@Controller
@RequestMapping("/coordinator")
public class CoordinatorController {

    @Autowired
    private CoordinatorService coordinatorService;

    /**
     * @api {post} coordinator/list 借款人中的参与者
     * @apiParam {int} companyId 公司ID
     * @apiParam {int} lenderId 借款人id
     * @apiParam {int} assetId 资产包id
     * @apiDescription 查询借款人信息
     * @apiSampleRequest coordinator/list
     * @apiGroup Coordinator
     * @apiName coordinator/list
     */
    @RequestMapping("/list")
    public JsonResponse coordinatorList(Integer companyId, Integer lenderId, Integer assetId) {
        Map<String, Object> map = new HashMap<>();
        if (lenderId != null) {
            coordinatorService.readByLenderOrAsset(map, companyId, lenderId, ObjectTypeEnum.LENDER.getValue());
        }
        if (assetId != null) {
            coordinatorService.readByLenderOrAsset(map, companyId, assetId, ObjectTypeEnum.ASSETPACKAGE.getValue());
        }
        if (map.get("result").toString().equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.noData();
        }
    }


}
