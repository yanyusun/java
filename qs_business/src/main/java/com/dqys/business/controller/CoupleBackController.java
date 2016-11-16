package com.dqys.business.controller;

import com.dqys.business.orm.pojo.coupleBack.CoupleBack;
import com.dqys.business.orm.pojo.coupleBack.CoupleBackMessage;
import com.dqys.business.orm.pojo.coupleBack.dto.CoupleBackDTO;
import com.dqys.business.service.service.coupleBack.CoupleBackService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/addCoupleBack")
    @ResponseBody
    public JsonResponse addCoupleBack(CoupleBack coupleBack) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        coupleBack.setCreateUser(userId);
        Map map = coupleBackService.addCoupleBack(coupleBack);
        return CommonUtil.jsonResponse(map);
    }

    @RequestMapping("/delCoupleBack")
    @ResponseBody
    public JsonResponse delCoupleBack(Integer id) {
        Map map = coupleBackService.delCoupleBack(id);
        return CommonUtil.jsonResponse(map);
    }

    @RequestMapping("/addMessage")
    @ResponseBody
    public JsonResponse addMessage(CoupleBackMessage message) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (message.getLeaveUserId() == null) {
            message.setMessType(1);
        }
        message.setUserId(userId);
        Map map = coupleBackService.addMessage(message);
        return CommonUtil.jsonResponse(map);
    }

    @RequestMapping("/delMessage")
    @ResponseBody
    public JsonResponse delMessage(Integer id) {
        Map map = coupleBackService.delMessage(id);
        return CommonUtil.jsonResponse(map);
    }

    @RequestMapping("/listBack")
    @ResponseBody
    public JsonResponse listBack(CoupleBackDTO coupleBackDTO) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        coupleBackDTO.setCreateUser(userId);
        Map map = coupleBackService.listBack(coupleBackDTO);
        return CommonUtil.jsonResponse(map);
    }

    @RequestMapping("/listMessage")
    @ResponseBody
    public JsonResponse listMessage(Integer tcbId) {
        Map map = coupleBackService.listMessage(tcbId);
        return CommonUtil.jsonResponse(map);
    }

}
