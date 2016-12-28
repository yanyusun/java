package com.dqys.auth.controller;

import com.dqys.auth.orm.pojo.LoginLog;
import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.pojo.saleUser.SaleUserModel;
import com.dqys.auth.orm.pojo.saleUser.SaleUserTag;
import com.dqys.auth.service.facade.SaleUserService;
import com.dqys.auth.service.facade.UserService;
import com.dqys.captcha.service.facade.CaptchaService;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.JsonResponseTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
     * @apiParam {String} code 验证码
     * @apiParam {String} key 验证码唯一标识
     */
    @RequestMapping(value = "/enterLogin", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse enterLogin(@RequestParam String account, @RequestParam String paw, @RequestParam String code, @RequestParam String key, String ip, HttpServletRequest request) throws Exception {
        ServiceResult result = captchaService.validImgCaptcha(key, code);
        if (!result.getFlag()) {
            return JsonResponseTool.failure(result.getMessage());
        }
        Map map = saleUserService.enterLogin(account, paw);
        if ("yes".equals(map.get("result"))) {
            addLoginLog(Integer.valueOf(map.get("userId").toString()), request, ip);//添加登入日志
        }
        return (JsonResponse) map.get("date");
    }

    private void addLoginLog(Integer userId, HttpServletRequest request, String ip) {
        LoginLog log = new LoginLog();
        log.setUserId(userId);
        if (ip == null || "".equals(ip)) {
            log.setIp(request.getRemoteAddr().toString());
        } else {
            log.setIp(ip);
        }
        saleUserService.addLoginLog(log);//添加登入记录
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
     * @apiParam {String} code 验证码
     * @apiParam {String} key 验证码唯一标识
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse register(@ModelAttribute SaleUserModel saleUserModel) throws Exception {
        if (CommonUtil.checkParam(saleUserModel) || CommonUtil.checkParam(saleUserModel.getKey(), saleUserModel.getCode())) {
            return JsonResponseTool.failure("请把信息填写完整");
        }
        //验证图片验证码
        ServiceResult result = captchaService.validImgCaptcha(saleUserModel.getKey(), saleUserModel.getCode());
        if (!result.getFlag()) {
            return JsonResponseTool.failure(result.getMessage());
        }
        toSaleUserAndTag(saleUserModel);//对象转换
        String msg = saleUserService.verifyUserMessage(saleUserModel);//验证数据的有效性
        if (!"".equals(msg)) {
            return JsonResponseTool.failure(msg);
        }
        JsonResponse response = saleUserService.verifyUser(saleUserModel.getAccount(), saleUserModel.getEmail(), saleUserModel.getMobile());//验证帐号是否允许使用
        if (response.getCode() != ResponseCodeEnum.SUCCESS.getValue().intValue()) {
            return response;
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
        return saleUserService.verifyUser(account, email, mobile);
    }

}
