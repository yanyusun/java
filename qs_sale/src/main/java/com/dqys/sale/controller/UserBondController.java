package com.dqys.sale.controller;

import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.ProtocolTool;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetBusiness;
import com.dqys.sale.orm.query.UserBondQuery;
import com.dqys.sale.service.dto.UserBondDTO;
import com.dqys.sale.service.facade.UserBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 个人债权，逾期贷款，企业债权管理
 * Created by mkfeng on 2016/12/22.
 */
@Controller
@RequestMapping("/bond")
public class UserBondController {
    @Autowired
    private UserBondService userBondService;

    /**
     * @api {post} bond/noVerify/bondList 获取债权列表
     * @apiName bond/noVerify/bondList
     * @apiSampleRequest bond/noVerify/bondList
     * @apiGroup　 bond
     */
    @RequestMapping("/noVerify/bondList")
    @ResponseBody
    public JsonResponse bondList(UserBondQuery query) {
        query.setBusinessStatus(AssetBusiness.getHasAnnouncedLevel().getLevel());
        return userBondService.bondList(query);
    }

    /**
     * @api {post} bond/noVerify/getDetail 获取债权详情
     * @apiName bond/noVerify/getDetail
     * @apiSampleRequest bond/noVerify/getDetail
     * @apiGroup　 bond
     * @apiParam {int} bondId 债权id
     */
    @RequestMapping("/noVerify/getDetail")
    @ResponseBody
    public JsonResponse getDetail(Integer bondId, HttpServletRequest httpServletRequest) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        return userBondService.getDetail(bondId);
    }

    /**
     * @api {post} bond/list 获取债权列表
     * @apiName bond/list
     * @apiSampleRequest bond/list
     * @apiGroup　 bond
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse list(UserBondQuery query) {
        if (!UserSession.getCurrent().getUserType().equals("1,")) {
            query.setUserId(UserSession.getCurrent().getUserId());
        }
        return userBondService.list(query);
    }

    /**
     * @api {post} bond/addBond 添加债权
     * @apiName bond/addBond
     * @apiSampleRequest bond/addBond
     * @apiGroup　 bond
     */
    @RequestMapping("/addBond")
    @ResponseBody
    public JsonResponse addBond(@ModelAttribute UserBondDTO userBondDTO) {
        return userBondService.addBond_tx(userBondDTO);
    }

    /**
     * @api {post} bond/updateBond 修改债权
     * @apiName bond/updateBond
     * @apiSampleRequest bond/updateBond
     * @apiGroup　 bond
     */
    @RequestMapping("/updateBond")
    @ResponseBody
    public JsonResponse updateBond(@ModelAttribute UserBondDTO userBondDTO) {
        return userBondService.updateBond_tx(userBondDTO);
    }

}
