package com.dqys.sale.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.sale.service.facade.SaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 * Created by mkfeng on 2016/12/23.
 */
@Controller
@RequestMapping("/saleUser")
public class SaleUserController {

    @Autowired
    private SaleUserService saleUserService;

    /**
     * @api {post} saleUser/detail 用户详情信息
     * @apiName saleUser/detail
     * @apiSampleRequest saleUser/detail
     * @apiGroup　 saleUser
     */
    @RequestMapping("/detail")
    @ResponseBody
    public JsonResponse detail(Integer userId) {
        if (userId == null) {
            userId = UserSession.getCurrent().getUserId();
        }
        return saleUserService.detail(userId);
    }


}
