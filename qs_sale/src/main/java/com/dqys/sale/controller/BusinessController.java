package com.dqys.sale.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.service.facade.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 业务操作管理
 * Created by mkfengs on 2016/12/26.
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 前台收藏操作
     *
     * @param status
     * @param objectId
     * @param objectType
     * @return
     */
    @RequestMapping("/collect")
    @ResponseBody
    public JsonResponse collect(Integer status, Integer objectId, Integer objectType) {
        Map map = businessService.collect(status, objectId, objectType);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * 前台处置申请操作
     *
     * @param status
     * @param objectId
     * @param objectType
     * @return
     */
    @RequestMapping("/applyDispose")
    @ResponseBody
    public JsonResponse applyDispose(Integer status, Integer objectId, Integer objectType) {
        Map map = businessService.applyDispose(status, objectId, objectType);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * 发布业务
     *
     * @return
     */
    @RequestMapping("/release")
    @ResponseBody
    public JsonResponse release(@RequestParam Integer businessId, @RequestParam Integer businessType, @RequestParam Integer businessLevel, @RequestParam Integer operType) {
        Map map = businessService.release(businessId,businessType, businessLevel, operType);
        return CommonUtil.jsonResponse(map);
    }


}
