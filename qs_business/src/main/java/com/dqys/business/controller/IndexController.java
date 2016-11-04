package com.dqys.business.controller;

import com.dqys.business.service.service.index.IndexService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkfeng on 2016/8/1.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexSerice;

    /**
     * @api {post} index/general 任务统计
     * @apiSampleRequest index/general
     * @apiGroup INDEX
     * @apiName index/general
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "await": 0,//待完成任务-------------
     * "finish": 0,//已完成------------
     * "unfinished": 2,//正在进行中------------
     * "join": 1, //参与的任务
     * "detail": {
     * "roleName": "管理员",//角色类型
     * "realName": "测试丰",//姓名
     * "adminName": "测试丰",//管理员姓名
     * "finishRate": "0%",//业绩比率
     * "lastTime": null,//上一次登入时间
     * "province": "北京市",//省份
     * "city": "市辖区",//城市
     * "area": "东城区",//区县
     * "address": null //地址
     * },
     * "notActivated": 0,//未激活的员工人数
     * "enterToday": 3,//当天登入的员工人数
     * "totalPeople": 2 //公司总人数
     * }
     * }
     */
    @RequestMapping("/general")
    @ResponseBody
    public JsonResponse general() {
        Map map = new HashMap<>();
        try {
            Integer userId = UserSession.getCurrent().getUserId();
            indexSerice.getStatistic(map, userId);
            indexSerice.getUserDetail(map, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} index/getObjectNo 获取各种编号
     * @apiSampleRequest index/getObjectNo
     * @apiGroup INDEX
     * @apiName index/getObjectNo
     * @apiParam {string} code 编号规则（QS：资产包；LC：案件；IO：借据；BO：借款人；CO：抵押物；ZC：资产源；YZ：业主 ；CZ：个人债权；QZ：企业债权；YQ：逾期贷款）
     */
    @RequestMapping("/getObjectNo")
    @ResponseBody
    public JsonResponse getObjectNo(String code) {
        String no = RandomUtil.getCode(code);
        if (no == null) {
            return JsonResponseTool.failure("参数有误");
        } else {
            return JsonResponseTool.success(no);
        }
    }


}
