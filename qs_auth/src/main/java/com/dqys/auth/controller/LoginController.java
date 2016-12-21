package com.dqys.auth.controller;

import com.dqys.auth.orm.pojo.SaleUser;
import com.dqys.auth.orm.pojo.SaleUserModel;
import com.dqys.auth.orm.pojo.SaleUserTag;
import com.dqys.auth.service.facade.SaleUserService;
import com.dqys.auth.service.facade.UserService;
import com.dqys.captcha.service.facade.CaptchaService;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.JsonResponseTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 登入或注册管理
 * Created by mkfeng on 2016/12/20.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseApiContorller {

    @Autowired
    private SaleUserService saleUserService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private UserService userService;

    /**
     * @api {post} login/enterLogin 登入
     * @apiSampleRequest login/enterLogin
     * @apiName login/enterLogin
     * @apiGroup login
     * @apiParam {String} account 帐号
     * @apiParam {String} paw 密码
     */
    @RequestMapping(value = "/enterLogin", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse enterLogin(@RequestParam String account, @RequestParam String paw) throws Exception {
        return saleUserService.enterLogin(account, paw);
    }

    /**
     * @api {post} login/register 注册
     * @apiSampleRequest login/register
     * @apiName login/register
     * @apiGroup login
     * @apiParam {String} name 姓名
     * @apiParam {String} account 用户名
     * @apiParam {String} email 邮箱
     * @apiParam {String} mobile 手机号
     * @apiParam {String} password 密码
     * @apiParam {int} sex 性别
     * @apiParam {int} province 省
     * @apiParam {int} city 市
     * @apiParam {int} area 区县
     * @apiParam {String} smsCode 手机短信验证码
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse register(@ModelAttribute SaleUserModel saleUserModel) throws Exception {
        if (CommonUtil.checkParam(saleUserModel)) {
            return JsonResponseTool.failure("参数有误");
        }
        toSaleUserAndTag(saleUserModel);//对象转换
        String msg = saleUserService.verifyUserMessage(saleUserModel);//验证数据的有效性
        if (!"".equals(msg)) {
            return JsonResponseTool.failure(msg);
        }
        //验证短信验证码
        if (StringUtils.isNotBlank(saleUserModel.getSmsCode())) {
            ServiceResult validServiceResult = captchaService.validSmsCaptcha(saleUserModel.getMobile(), saleUserModel.getSmsCode(),
                    SmsEnum.REGISTER.getValue());
            if (!validServiceResult.getFlag()) {
                return JsonResponseTool.paramErr(validServiceResult.getMessage());
            }
        }
        return saleUserService.register(saleUserModel);
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

    /**
     * @api {post} login/verifyUser 注册帐号的有效性
     * @apiSampleRequest login/verifyUser
     * @apiName login/verifyUser
     * @apiGroup login
     * @apiParam {String} [account] 用户名
     * @apiParam {String} [email] 邮箱
     * @apiParam {String} [mobile] 手机号
     */
    @RequestMapping(value = "/verifyUser", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse verifyUser(String account, String email, String mobile) throws Exception {
        if (account != null && (FormatValidateTool.checkEmail(account) || FormatValidateTool.checkEmail(account))) {
            return JsonResponseTool.failure("用户名不能为邮箱或手机号");
        }
        if (email != null && !FormatValidateTool.checkEmail(email)) {
            return JsonResponseTool.failure("邮箱格式有误");
        }
        if (mobile != null && !FormatValidateTool.checkEmail(mobile)) {
            return JsonResponseTool.failure("手机号格式有误");
        }
        if (account != null && saleUserService.queryUser(account, null, null) != null) {
            return JsonResponseTool.failure("用户名已存在");
        }
        if (email != null && (saleUserService.queryUser(null, null, email) != null || userService.queryUser(null, null, email) != null)) {
            return JsonResponseTool.failure("邮箱已注册");
        }
        if (mobile != null && saleUserService.queryUser(null, mobile, null) != null) {
            return JsonResponseTool.failure("手机号已存在");
        }
        return JsonResponseTool.success(null);
    }


}
