package com.dqys.business.controller;

import com.dqys.business.service.service.CoordinatorService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * @apiParam {int} type 参与者的处置方
     * @apiDescription 查询借款人信息
     * @apiSampleRequest coordinator/list
     * @apiGroup Coordinator
     * @apiName coordinator/list
     */
    @RequestMapping("/list")
    public JsonResponse coordinatorList(Integer type) {
        return JsonResponseTool.success(0);
    }



}
