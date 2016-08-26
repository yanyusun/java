package com.dqys.business.controller;

import com.dqys.business.orm.pojo.zcy.ZcyModule;
import com.dqys.business.orm.query.coordinator.ZcyListQuery;
import com.dqys.business.service.service.ZcyService;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
     * @apiParam {ZcyEstatesAddress} zcyEstatesAddressList 资产信息房产地址集合
     * @apiParam {ZcyEstatesFacility} zcyEstatesFacilities 标签集合
     * @apiUse ZcyEstates
     * @apiUse ZcyEstatesAddress
     * @apiUse ZcyEstatesFacility
     * @apiSampleRequest zcy/addEstates
     * @apiGroup ZCY
     * @apiName zcy/addEstates
     */
    @RequestMapping("/addEstates")
    @ResponseBody
    public JsonResponse addEstates(@ModelAttribute ZcyModule zcyModule) {
        Map map = new HashMap<>();
        map = zcyService.addEstates(zcyModule.getZcyEstates(), zcyModule.getZcyEstatesAddressList(), zcyModule.getZcyEstatesFacilities());
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/addOwner 添加业主信息
     * @apiParam {ZcyOwnerContacts} zcyOwnerContactses 业主信息联系方式集合
     * @apiUse ZcyOwner
     * @apiUse ZcyOwnerContacts
     * @apiSampleRequest zcy/addOwner
     * @apiGroup ZCY
     * @apiName zcy/addOwner
     */
    @RequestMapping("/addOwner")
    @ResponseBody
    public JsonResponse addOwner(@ModelAttribute ZcyModule zcyModule) {
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(zcyModule.getZcyOwner().getEstatesId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        /*
        List<ZcyOwnerContacts> zcyOwnerContactses = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> list = objectMapper.readValue(ownerContacts, List.class);
            for (int i = 0; i < list.size(); i++) {
                Map con = list.get(i);
                ZcyOwnerContacts zcyOwnerContacts = new ZcyOwnerContacts();
                zcyOwnerContacts.setName(MessageUtils.transMapToString(con, "name"));
                zcyOwnerContacts.setEmail(MessageUtils.transMapToString(con, "email"));
                zcyOwnerContacts.setPhone(MessageUtils.transMapToString(con, "phone"));
                zcyOwnerContacts.setPhoneType(MessageUtils.transMapToString(con, "phoneType"));
                zcyOwnerContacts.setSex(MessageUtils.transMapToString(con, "sex"));
                zcyOwnerContacts.setType(MessageUtils.transMapToString(con, "type"));
                zcyOwnerContactses.add(zcyOwnerContacts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        map = zcyService.addOwner(zcyModule.getZcyOwner(), zcyModule.getZcyOwnerContactses());
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/addMaintain 添加维护信息
     * @apiParam {ZcyMaintainOther} zcyMaintainOthers 维护信息标签集合
     * @apiParam {ZcyMaintainTax} zcyMaintainTaxes 维护信息税收集合
     * @apiUse ZcyMaintain
     * @apiUse ZcyMaintainOther
     * @apiUse ZcyMaintainTax
     * @apiSampleRequest zcy/addMaintain
     * @apiGroup ZCY
     * @apiName zcy/addMaintain
     */
    @RequestMapping("/addMaintain")
    @ResponseBody
    public JsonResponse addMaintain(@ModelAttribute ZcyModule zcyModule) {
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(zcyModule.getZcyMaintain().getEstatesId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        map = zcyService.addMaintain(zcyModule.getZcyMaintain(), zcyModule.getZcyMaintainOthers(), zcyModule.getZcyMaintainTaxes());
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
    public JsonResponse addKey(@ModelAttribute ZcyModule zcyModule) {
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(zcyModule.getZcyKey().getEstatesId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        map = zcyService.addKey(zcyModule.getZcyKey());
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
    public JsonResponse addExpress(@ModelAttribute ZcyModule zcyModule) {
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(zcyModule.getZcyExpress().getEstatesId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        map = zcyService.addExpress(zcyModule.getZcyExpress());
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/awaitReceive 资产源列表
     * @apiUse ZcyListQuery
     * @apiSampleRequest zcy/awaitReceive
     * @apiGroup ZCY
     * @apiName zcy/awaitReceive
     */
    @RequestMapping("/awaitReceive")
    @ResponseBody
    public JsonResponse awaitReceive(@ModelAttribute ZcyListQuery zcyListQuery, HttpServletRequest httpServletRequest) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        Map map = new HashMap<>();
        if (zcyListQuery.getPage() < 0) {
            zcyListQuery.setPage(0);
        }
        map = zcyService.awaitReceive(userId, zcyListQuery);
        return JsonResponseTool.success(map);
    }
}
