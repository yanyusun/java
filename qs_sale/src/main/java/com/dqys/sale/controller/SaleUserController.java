package com.dqys.sale.controller;

import com.dqys.auth.orm.query.SaleUserQuery;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.sale.orm.pojo.saleUser.SaleUser;
import com.dqys.sale.orm.pojo.saleUser.SaleUserModel;
import com.dqys.sale.orm.pojo.saleUser.SaleUserTag;
import com.dqys.sale.service.facade.TSaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private TSaleUserService tSaleUserService;

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
        return tSaleUserService.detail(userId);
    }

    /**
     * @api {post} saleUser/list 获取用户列表
     * @apiName saleUser/list
     * @apiSampleRequest saleUser/list
     * @apiGroup　 saleUser
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse list(SaleUserQuery query) {
        query.setUserId(UserSession.getCurrent().getUserId());
        return tSaleUserService.list(query);
    }

    /**
     * @api {post} saleUser/add 添加或修改用户信息
     * @apiName saleUser/add
     * @apiSampleRequest saleUser/add
     * @apiGroup　 saleUser
     */
    @RequestMapping("/add")
    @ResponseBody
    public JsonResponse add(@ModelAttribute SaleUserModel model) {
        toSaleUserAndTag(model);
        return tSaleUserService.add(model);
    }

    private void toSaleUserAndTag(SaleUserModel saleUserModel) {
        if (saleUserModel != null && saleUserModel.getSaleUser() == null) {
            SaleUser user = new SaleUser();
            user.setPassword(saleUserModel.getPassword());
            user.setMobile(saleUserModel.getMobile());
            user.setEmail(saleUserModel.getEmail());
            user.setAccount(saleUserModel.getAccount());
            user.setSex(saleUserModel.getSex());
            user.setName(saleUserModel.getName());
            saleUserModel.setSaleUser(user);
        }
        if (saleUserModel != null && saleUserModel.getSaleUserTag() == null) {
            SaleUserTag tag = new SaleUserTag();
            tag.setArea(saleUserModel.getArea());
            tag.setCity(saleUserModel.getCity());
            tag.setProvince(saleUserModel.getProvince());
            saleUserModel.setSaleUserTag(tag);
        }

    }

}
