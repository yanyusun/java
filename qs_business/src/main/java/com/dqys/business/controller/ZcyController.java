package com.dqys.business.controller;

import com.dqys.business.orm.pojo.zcy.*;
import com.dqys.business.service.service.ZcyService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产源录入
 * Created by mkfeng on 2016/7/27.
 */
@Controller
@RequestMapping("/zcy")
public class ZcyController {

    @Autowired
    private ZcyService zcyService;

    /**
     * @api {post} zcy/get 获取信息
     * @apiParam {int} id 资产信息id
     * @apiParam {int} type 请求类型（1资产信息2业主信息3维护信息4实勘信息5钥匙信息6速卖信息7证件消息）
     * @apiSampleRequest zcy/get
     * @apiGroup ZCY
     * @apiName zcy/get
     */
    @RequestMapping("/get")
    @ResponseBody
    public JsonResponse get(@RequestParam("id") Integer id, @RequestParam("type") Integer type) {
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(id, type)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (type == 1) {
            map = zcyService.getEstates(id);
        } else if (type == 2) {
            map = zcyService.getOwner(id);
        } else if (type == 3) {
            map = zcyService.getMaintain(id);
        } else if (type == 4) {

        } else if (type == 5) {
            map = zcyService.getKey(id);
        } else if (type == 6) {
            map = zcyService.getExpress(id);
        } else if (type == 7) {

        } else {
            return JsonResponseTool.paramErr("类型错误");
        }
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/addEstates 添加资产信息
     * @apiUse ZcyEstates
     * @apiUse ZcyEstatesAddress
     * @apiUse ZcyEstatesFacility
     * @apiSampleRequest zcy/addEstates
     * @apiGroup ZCY
     * @apiName zcy/addEstates
     */
    @RequestMapping("/addEstates")
    @ResponseBody
    public JsonResponse addEstates(@ModelAttribute ZcyEstates zcyEstates, @ModelAttribute List<ZcyEstatesAddress> zcyEstatesAddressList,
                                   @ModelAttribute List<ZcyEstatesFacility> zcyEstatesFacilities) {
        Map map = new HashMap<>();
        map = zcyService.addEstates(zcyEstates, zcyEstatesAddressList, zcyEstatesFacilities);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/addOwner 添加业主信息
     * @apiUse ZcyOwner
     * @apiUse ZcyOwnerContacts
     * @apiSampleRequest zcy/addOwner
     * @apiGroup ZCY
     * @apiName zcy/addOwner
     */
    @RequestMapping("/addOwner")
    @ResponseBody
    public JsonResponse addOwner(@ModelAttribute ZcyOwner zcyOwner, @ModelAttribute List<ZcyOwnerContacts> zcyOwnerContactses) {
        Map map = new HashMap<>();
        map = zcyService.addOwner(zcyOwner, zcyOwnerContactses);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/addMaintain 添加维护信息
     * @apiUse ZcyMaintain
     * @apiUse ZcyMaintainOther
     * @apiUse ZcyMaintainTax
     * @apiSampleRequest zcy/addMaintain
     * @apiGroup ZCY
     * @apiName zcy/addMaintain
     */
    @RequestMapping("/addMaintain")
    @ResponseBody
    public JsonResponse addMaintain(@ModelAttribute ZcyMaintain zcyMaintain, @ModelAttribute List<ZcyMaintainOther> zcyMaintainOthers, @ModelAttribute List<ZcyMaintainTax> zcyMaintainTaxes) {
        Map map = new HashMap<>();
        map = zcyService.addMaintain(zcyMaintain, zcyMaintainOthers, zcyMaintainTaxes);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/addKey 添加钥匙信息
     * @apiUse ZcyKey
     * @apiSampleRequest zcy/addKey
     * @apiGroup ZCY
     * @apiName zcy/addKey
     */
    @RequestMapping("/addKey")
    @ResponseBody
    public JsonResponse addKey(@ModelAttribute ZcyKey zcyKey) {
        Map map = new HashMap<>();
        map = zcyService.addKey(zcyKey);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/addExpress 添加速卖信息
     * @apiUse ZcyExpress
     * @apiSampleRequest zcy/addExpress
     * @apiGroup ZCY
     * @apiName zcy/addExpress
     */
    @RequestMapping("/addExpress")
    @ResponseBody
    public JsonResponse addExpress(@ModelAttribute ZcyExpress zcyExpress) {
        Map map = new HashMap<>();
        map = zcyService.addExpress(zcyExpress);
        return JsonResponseTool.success(map);
    }

}
