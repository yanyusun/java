package com.dqys.business.controller;

import com.dqys.business.service.service.ParticipationService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 参与的机构
 * Created by mkfeng on 2016/11/1.
 */
@Controller
@RequestMapping("/part")
public class ParticipationController {

    @Autowired
    private ParticipationService participationService;

    /**
     * /**
     *
     * @api {post} part/getList 操作接口
     * @apiName part/getList
     * @apiSampleRequest part/getList
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型
     * @apiGroup　 part
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",
     * "list": [
     * {
     * "companyName": "风腾律所",//公司名称
     * "companyId": 419,//公司id
     * "companyContent": "说走就走，走一个",//简介
     * "companyJoinTime": "2016-10-21",//参与时间
     * "joinPeopleNum": 0,//参与人数
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/getList")
    @ResponseBody
    public JsonResponse findList(@RequestParam Integer objectId, @RequestParam Integer objectType) {
        Map map = participationService.findList(objectId, objectType);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * /**
     *
     * @api {post} part/getDetail 详情信息
     * @apiName part/getDetail
     * @apiSampleRequest part/getDetail
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型
     * @apiParam {int} companyId 公司id
     * @apiGroup　 part
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",
     * "list": [
     * {
     * "companyName": "风腾律所",//公司名称
     * "companyId": 419,//公司id
     * "companyContent": "说走就走，走一个",//简介
     * "companyJoinTime": "2016-10-21",//参与时间
     * "joinPeopleNum": 0,//参与人数
     * "comapanyTotal": 36,//公司总人数（规模）
     * "companyAddress": "北京市市辖区东城区",//地址
     * "rate": "0",//处置效率
     * "companyImg": null //公司logo
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/getDetail")
    @ResponseBody
    public JsonResponse getDetail(@RequestParam Integer objectId, @RequestParam Integer objectType, @RequestParam Integer companyId) {
        Map map = participationService.getDetail(objectId, objectType, companyId);
        return CommonUtil.jsonResponse(map);
    }
}
