package com.dqys.business.controller;

import com.dqys.business.orm.pojo.zcy.ZcyModule;
import com.dqys.business.orm.query.coordinator.ZcyListQuery;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.ZcyService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @apiSuccessExample {json} Data-Response:
     * {
     * "estates":{},//资产源信息
     * "address":{},//房源地址
     * "facility":{},//配套设施
     * "feature":{},//房源特色
     * "defect":{},//房源缺点
     * "contacts":{},//联系人
     * "owner":{},//业主信息
     * "maintain":{},//维护信息
     * "tax":{},//税率
     * "school":{},//可上学校
     * "lookHouse":{},//看房时间;
     * "key":{},//钥匙信息
     * "express":{}//速卖信息
     * }
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
    public JsonResponse addEstates(@ModelAttribute ZcyModule zcyModule) throws Exception {
        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        zcyModule.getZcyEstates().setOperator(userId.toString());
        //验证对象格式
        map = zcyService.verifyEstates(zcyModule.getZcyEstates(), zcyModule.getZcyEstatesAddressList(), zcyModule.getZcyEstatesFacilities());
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            map = zcyService.addEstates(zcyModule.getZcyEstates(), zcyModule.getZcyEstatesAddressList(), zcyModule.getZcyEstatesFacilities());
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            }
        }
        return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
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
        map = zcyService.verifyOwner(zcyModule.getZcyOwner(), zcyModule.getZcyOwnerContactses());
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            map = zcyService.addOwner(zcyModule.getZcyOwner(), zcyModule.getZcyOwnerContactses());
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            }
        }
        return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
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
        map = zcyService.verifyMaintain(zcyModule.getZcyMaintain(), zcyModule.getZcyMaintainOthers(), zcyModule.getZcyMaintainTaxes());
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            map = zcyService.addMaintain(zcyModule.getZcyMaintain(), zcyModule.getZcyMaintainOthers(), zcyModule.getZcyMaintainTaxes());
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            }
        }
        return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
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
        map = zcyService.verifyKey(zcyModule.getZcyKey());
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            map = zcyService.addKey(zcyModule.getZcyKey());
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            }
        }
        return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
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
        map = zcyService.verifyExpress(zcyModule.getZcyExpress());
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            map = zcyService.addExpress(zcyModule.getZcyExpress());
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            }
        }
        return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
    }

    /**
     * @api {post} zcy/awaitReceive 资产源列表
     * @apiUse ZcyListQuery
     * @apiSampleRequest zcy/awaitReceive
     * @apiGroup ZCY
     * @apiName zcy/awaitReceive
     * @apiSuccessExample {json} Data-Response:
     * {
     * "count":12,//总数量
     * "zcyPawnDTOs":
     * {
     * "pawnId":"",//抵押物id
     * "estatesId":"",//资产信息id
     * "keyId":"",// 钥匙信息id
     * "title":"",//标题
     * "houseNo":"",//编号
     * "place":"",//位置
     * "label":"",//标签
     * "houseType":"",//户型
     * "orientation":"",//朝向
     * "floor":"",//楼层
     * "acreage":"",//面积
     * "priceTotal":"",//总价（万）
     * "priceUnit":"",//单价(万)
     * "hangShingle":"",//挂牌
     * "daiKan":"",//带看
     * "daiKanLately ":"",//最近带看
     * "keKan":"",//可看
     * "shiKan":"",//实勘
     * "maintaining":"",//维护人
     * "currentBooking":"",//当前预约
     * "realityDynamic":"",//实际动态
     * "entrustTime":""//委托时间
     * }
     * }
     */
    @RequestMapping("/awaitReceive")
    @ResponseBody
    public JsonResponse awaitReceive(@ModelAttribute ZcyListQuery zcyListQuery) throws Exception {
        if (CommonUtil.checkParam(zcyListQuery.getStatus())) {
            return JsonResponseTool.paramErr("参数有误");
        }
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (CommonUtil.isManage()) {//平台方不用根据用户id查询
            userId = null;
        }
        Map map = new HashMap<>();
        if (zcyListQuery.getPage() < 0) {
            zcyListQuery.setPage(0);
        }
        zcyListQuery.setUserId(userId);
        map = zcyService.awaitReceive(zcyListQuery);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/zcyDetail 资产源详情
     * @apiParam {int} estatesId 资产源id
     * @apiSampleRequest zcy/zcyDetail
     * @apiGroup ZCY
     * @apiName zcy/zcyDetail
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",
     * "zcyPawnDTO": {
     * "pawnId": 16,//抵押物id
     * "estatesId": 6,//资产信息id
     * "keyId": 3,钥匙信息id
     * "title": "1",//标题
     * "houseNo": "131345",//编号
     * "place": "11111111111",//位置
     * "label": [],//标签
     * "houseType": "131-31-33-131",//户型
     * "orientation": "3",//朝向
     * "floor": null,//楼层
     * "acreage": "313.00",//面积
     * "priceTotal": "5.00",//总价（万）
     * "priceUnit": "13.00",//单价(万)
     * "hangShingle": "0",//挂牌
     * "daiKan": 0,//带看
     * "daiKanLately": 0,//最近带看
     * "keKan": 0,//可看
     * "shiKan": 0,
     * "maintaining": "",//维护人
     * "currentBooking": 0,
     * "realityDynamic": 0,
     * "entrustTime": null //委托时间
     * },
     * "detail": {
     * "area": "11",//市区
     * "decade": "2016-09-26",//年代
     * "buildType": "6",//建筑类型
     * "heatingWay": "1",//供暖方式
     * "acreage": 313,//面积
     * "guidePrice": 13,//过户指导价(元/m2)
     * "houseBelong": "4",//房屋权属
     * "tenementPrice": 1,//物业费
     * "facility": "31",//嫌恶设施
     * "houseUse": "65"， //房屋用途
     * "originalPrice": ，//原购房价格（单位：万）
     * "entrustSource":,//委托来源
     * "expressPrice":,//速卖价格
     * "expressStartTime":,//速卖起始时间
     * "expressEndTime":,//速卖结束时间
     * }
     * }
     * }
     */
    @RequestMapping("/zcyDetail")
    @ResponseBody
    public JsonResponse zcyDetail(Integer estatesId) throws Exception {
        if (CommonUtil.checkParam(estatesId)) {
            return JsonResponseTool.paramErr("参数有误");
        }
        Map map = new HashMap<>();
        map = zcyService.zcyDetail(estatesId);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} zcy/receivePawn 接收抵押物
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型
     * @apiParam {int} status 状态（0接收1拒绝）
     * @apiSampleRequest zcy/receivePawn
     * @apiGroup ZCY
     * @apiName zcy/receivePawn
     */
    @RequestMapping("/receivePawn")
    @ResponseBody
    public JsonResponse receivePawn(Integer objectId, Integer objectType, Integer status) throws BusinessLogException {
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(objectId, objectType, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (status != 0 && status != 1) {
            return JsonResponseTool.paramErr("状态错误");
        }
        map = zcyService.receivePawn(objectId, objectType, status);
        if ("yes".equals(MessageUtils.transMapToString(map, "result"))) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "result"));
        }
    }

}
