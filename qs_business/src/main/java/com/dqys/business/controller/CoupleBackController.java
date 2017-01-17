package com.dqys.business.controller;

import com.dqys.business.orm.pojo.coupleBack.CoupleBack;
import com.dqys.business.orm.pojo.coupleBack.CoupleBackMessage;
import com.dqys.business.orm.pojo.coupleBack.dto.CoupleBackDTO;
import com.dqys.business.service.service.coupleBack.CoupleBackService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 反馈管理
 * Created by mkfeng on 2016/11/16.
 */
@Controller
@RequestMapping("/back")
public class CoupleBackController {

    @Autowired
    private CoupleBackService coupleBackService;

    /**
     * @api {post} back/addCoupleBack 添加反馈信息
     * @apiUse CoupleBack
     * @apiSampleRequest back/addCoupleBack
     * @apiGroup CoupleBack
     * @apiName back/addCoupleBack
     */
    @RequestMapping("/addCoupleBack")
    @ResponseBody
    public JsonResponse addCoupleBack(CoupleBack coupleBack) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        coupleBack.setCreateUser(userId);
        Map map = coupleBackService.addCoupleBack(coupleBack);
        return CommonUtil.jsonResponse(map);
    }
    /**
     * @api {post} back/c/addCoupleBack 添加反馈信息
     * @apiUse CoupleBack
     * @apiSampleRequest back/addCoupleBack
     * @apiGroup CoupleBack
     * @apiName back/addCoupleBack
     */
    @RequestMapping("/c/addCoupleBack")
    @ResponseBody
    public JsonResponse addCoupleBackC(CoupleBack coupleBack) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        coupleBack.setCreateUser(userId);
        Map map = coupleBackService.addCoupleBack(coupleBack);
        return CommonUtil.jsonResponse(map);
    }
    /**
     * @api {post} back/delCoupleBack 删除反馈信息
     * @apiParam {int} id 反馈信息id
     * @apiSampleRequest back/delCoupleBack
     * @apiGroup CoupleBack
     * @apiName back/delCoupleBack
     */
    @RequestMapping("/delCoupleBack")
    @ResponseBody
    public JsonResponse delCoupleBack(@RequestParam Integer id) {
        Map map = coupleBackService.delCoupleBack(id);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * @api {post} back/addMessage 添加回复信息
     * @apiUse CoupleBackMessage
     * @apiSampleRequest back/addMessage
     * @apiGroup CoupleBack
     * @apiName back/addMessage
     */
    @RequestMapping("/addMessage")
    @ResponseBody
    public JsonResponse addMessage(CoupleBackMessage message) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (message.getLeaveUserId() != null) {
            message.setMessType(1);
        }
        message.setUserId(userId);
        Map map = coupleBackService.addMessage(message);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * @api {post} back/delMessage 删除回复信息
     * @apiParam {int} id 回复信息id
     * @apiSampleRequest back/delMessage
     * @apiGroup CoupleBack
     * @apiName back/delMessage
     */
    @RequestMapping("/delMessage")
    @ResponseBody
    public JsonResponse delMessage(@RequestParam Integer id) {
        Map map = coupleBackService.delMessage(id);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * @api {post} back/listBack 反馈信息列表
     * @apiParam {int} type 类型（0默认1资讯2建议3其他）
     * @apiSampleRequest back/listBack
     * @apiGroup CoupleBack
     * @apiName back/listBack
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",
     * "list": [
     * {
     * "id": 1,//反馈信息id
     * "userName": "麦克风",//用户名
     * "realName": "麦克风",//真实姓名
     * "createUser": 409,//用户id
     * "createTime": "2016-11-16 16:10:10",//反馈时间
     * "content": "1",//内容
     * "type": 1 //类型（0默认1资讯2建议3其他）
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/listBack")
    @ResponseBody
    public JsonResponse listBack(CoupleBackDTO coupleBackDTO) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        coupleBackDTO.setCreateUser(userId);
        Map map = coupleBackService.listBack(coupleBackDTO);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * @api {post} back/listMessage 反馈信息回复列表
     * @apiParam {int} tcbId 反馈信息id
     * @apiSampleRequest back/listMessage
     * @apiGroup CoupleBack
     * @apiName back/listMessage
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",
     * "list": [
     * {
     * "userId": 411,//回复用户的用户id
     * "tcbId": 1,//反馈信息的id
     * "contentMessage": "就是这么嗨",//回复内容
     * "leaveUserId": 409,//被回复用户的用户id（这个被回复用户只有在messType为1才有数据）
     * "messType": 1,//信息类型（0主动留言1回复留言）
     * "createTime": "2016-11-16 18:52:05",//回复信息时间
     * "leaveUserName": "麦克风",//被回复用户的用户名
     * "userName": null,//回复用户的用户名
     * "leaveRealName": "麦克风",//被回复用户的真实姓名
     * "realName": "龚子鸣"//回复用户的真实姓名
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/listMessage")
    @ResponseBody
    public JsonResponse listMessage(@RequestParam Integer tcbId) {
        Map map = coupleBackService.listMessage(tcbId);
        return CommonUtil.jsonResponse(map);
    }

}
