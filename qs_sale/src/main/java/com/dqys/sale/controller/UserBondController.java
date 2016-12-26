package com.dqys.sale.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.service.dto.UserBondDTO;
import com.dqys.sale.orm.query.UserBondQuery;
import com.dqys.sale.service.facade.UserBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return userBondService.bondList(query);
    }

    /**
     * @api {post} bond/noVerify/getDetail 获取债权详情
     * @apiName bond/noVerify/getDetail
     * @apiSampleRequest bond/noVerify/getDetail
     * @apiGroup　 bond
     */
    @RequestMapping("/noVerify/getDetail")
    @ResponseBody
    public JsonResponse getDetail(Integer bondId) {
        return userBondService.getDetail(bondId);
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
