package com.dqys.sale.controller;

import com.dqys.auth.orm.query.SaleUserQuery;
import com.dqys.auth.service.facade.SaleUserService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.pojo.saleUser.SaleUserModel;
import com.dqys.auth.orm.pojo.saleUser.SaleUserTag;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.service.facade.TSaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 用户管理
 * Created by mkfeng on 2016/12/23.
 */
@Controller
@RequestMapping("/saleUser")
public class SaleUserController {

    @Autowired
    private TSaleUserService tSaleUserService;
    @Autowired
    private SaleUserService saleUserService;

    /**
     * @api {post} saleUser/personal 个人中心
     * @apiName saleUser/personal
     * @apiSampleRequest saleUser/personal
     * @apiGroup　 saleUser
     */
    @RequestMapping("/personal")
    @ResponseBody
    public JsonResponse personal() {
        Integer userId = UserSession.getCurrent().getUserId();
        Map map = tSaleUserService.personal(userId);
        return CommonUtil.jsonResponse(map);
    }

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
        return tSaleUserService.list(query);
    }

    /**
     * @api {post} saleUser/add 添加用户信息
     * @apiName saleUser/add
     * @apiSampleRequest saleUser/add
     * @apiGroup　 saleUser
     */
    @RequestMapping("/add")
    @ResponseBody
    public JsonResponse addOrEdit(@ModelAttribute SaleUserModel model) throws Exception {
        toSaleUserAndTag(model);
        String msg = saleUserService.verifyUserMessage(model);//验证数据的有效性
        if (!"".equals(msg)) {
            return JsonResponseTool.failure(msg);
        }
        return tSaleUserService.addOrEdit(model);
    }

    /**
     * @api {post} saleUser/edit 修改用户信息
     * @apiName saleUser/edit
     * @apiSampleRequest saleUser/edit
     * @apiGroup　 saleUser
     */
    @RequestMapping("/edit")
    @ResponseBody
    public JsonResponse edit(@ModelAttribute SaleUserModel model) throws Exception {
        toSaleUserAndTag(model);
        if (model.getSaleUser().getId() == null) {
            return JsonResponseTool.failure("缺少所需要的数据");
        }
        String msg = saleUserService.verifyUserMessage(model);//验证数据的有效性
        if (!"".equals(msg)) {
            return JsonResponseTool.failure(msg);
        }
        return tSaleUserService.addOrEdit(model);
    }

    /**
     * @api {post} saleUser/del 删除用户信息
     * @apiName saleUser/del
     * @apiSampleRequest saleUser/del
     * @apiGroup　 saleUser
     */
    @RequestMapping("/del")
    @ResponseBody
    public JsonResponse del(@RequestParam Integer userId) throws Exception {
        return tSaleUserService.del(userId);
    }

    /**
     * @api {post} saleUser/setLogin 设置是否可以登入
     * @apiName saleUser/setLogin
     * @apiSampleRequest saleUser/setLogin
     * @apiParam {int[]} ids 用户id，集合
     * @apiParam {int} status 状态（0禁止登入1正常正如）
     * @apiGroup　 saleUser
     */
    @RequestMapping("/setLogin")
    @ResponseBody
    public JsonResponse setLogin(@ModelAttribute SaleUserQuery query) throws Exception {
        if (query == null || query.getIds() == null || query.getStatus() == null) {
            return JsonResponseTool.failure("所需要的信息不全");
        }
        Map map = tSaleUserService.setLogin(query.getIds(), query.getStatus());
        return CommonUtil.jsonResponse(map);
    }


    private void toSaleUserAndTag(SaleUserModel saleUserModel) {
        if (saleUserModel != null) {
            SaleUser user = null;
            if (saleUserModel.getSaleUser() == null) {
                user = new SaleUser();
            } else {
                user = saleUserModel.getSaleUser();
            }
            if (user.getId() == null) {
                user.setId(saleUserModel.getId());
            }
            if (user.getPassword() == null) {
                user.setPassword(saleUserModel.getPassword());
            }
            if (user.getMobile() == null) {
                user.setMobile(saleUserModel.getMobile());
            }
            if (user.getEmail() == null) {
                user.setEmail(saleUserModel.getEmail());
            }
            if (user.getAccount() == null) {
                user.setAccount(saleUserModel.getAccount());
            }
            if (user.getName() == null) {
                user.setName(saleUserModel.getName());
            }
            if (user.getSex() == null) {
                user.setSex(saleUserModel.getSex());
            }
            saleUserModel.setSaleUser(user);


            SaleUserTag tag = null;
            if (saleUserModel.getSaleUserTag() == null) {
                tag = new SaleUserTag();
            } else {
                tag = saleUserModel.getSaleUserTag();
            }
            tag.setArea(saleUserModel.getArea());
            tag.setCity(saleUserModel.getCity());
            tag.setProvince(saleUserModel.getProvince());
            saleUserModel.setSaleUserTag(tag);
        }
    }


}


