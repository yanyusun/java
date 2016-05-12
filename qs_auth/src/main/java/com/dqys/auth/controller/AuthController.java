package com.dqys.auth.controller;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.service.constant.MailVerifyTypeEnum;
import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.service.facade.CompanyService;
import com.dqys.auth.service.facade.UserService;
import com.dqys.captcha.service.facade.CaptchaService;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

/**
 * @author by pan on 16-4-8.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private CompanyService companyService;

    /**
     *  图片验证码
     * @param key
     * @return
     */
    @RequestMapping("/captcha")
    public Callable<ResponseEntity<StreamingResponseBody>> genImgCaptcha(@RequestParam String key) {
        return () -> {
            ServiceResult<BufferedImage> biResult = this.captchaService.genImgCaptcha(key);
            if(!biResult.getFlag()) {
                return null;
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>((StreamingResponseBody) outputStream -> ImageIO.write(biResult.getData(), "png", outputStream), httpHeaders, HttpStatus.OK);
        };
    }

    /**
     * 短信验证码
     * @param mobile
     * @return
     */
    @RequestMapping("/sms_code")
    public Callable<JsonResponse> sendSmsCode(@RequestParam String mobile) {
        return () -> {
            //验证手机
            if(StringUtils.isNotBlank(mobile)) {
                if(!FormatValidateTool.checkMobile(mobile)) {
                    return JsonResponseTool.paramErr("手机号无效");
                }
            }

            ServiceResult<String> smsCodeResult = this.captchaService.genSmsCaptcha(mobile);
            if(!smsCodeResult.getFlag()) {
                return JsonResponseTool.failure(smsCodeResult.getMessage());
            }

            // TODO: 16-4-13  发送短信
            LogManager.getRootLogger().debug(smsCodeResult.getData());

            return JsonResponseTool.success(null);
        };
    }

    /**
     * 验证用户有效性
     *
     * @param userName
     * @param mobile
     * @param email
     * @return
     */
    @RequestMapping(value = "/validate_user")
    public Callable<JsonResponse> velidateUser(@RequestParam(required = false) String userName,
                                               @RequestParam(required = false) String mobile,
                                               @RequestParam(required = false) String email) {

        return () -> {
            if(StringUtils.isBlank(userName) && StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
                return JsonResponseTool.paramErr("参数无效");
            }

            ServiceResult<Integer> serviceResult = this.userService.validateUser(userName, mobile, email);
            if(!serviceResult.getFlag()) {
                return JsonResponseTool.success(0);
            }

            return JsonResponseTool.success(serviceResult.getData());
        };
    }


    /**
     * 用户注册
     * @param userName
     * @param mobile
     * @param email
     * @param pwd
     * @param smsCode
     * @param captcha
     * @param captchaKey
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Callable<JsonResponse> userRegister(@RequestParam(required = false) String userName,
                                               @RequestParam(required = false) String mobile,
                                               @RequestParam(required = false) String email,
                                               @RequestParam String pwd,
                                               @RequestParam(required = false) String smsCode,
                                               @RequestParam(required = false) String captcha,
                                               @RequestParam(required = false) String captchaKey) {
        return () -> {
            if(StringUtils.isBlank(userName) && StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
                return JsonResponseTool.paramErr("参数无效");
            }
            if(StringUtils.isBlank(captcha) && StringUtils.isBlank(smsCode)) {
                return JsonResponseTool.paramErr("验证码无效");
            }

            //验证手机
            if(StringUtils.isNotBlank(mobile)) {
                if(!FormatValidateTool.checkMobile(mobile)) {
                    return JsonResponseTool.paramErr("手机号无效");
                }
            }
            //验证邮箱
            if(StringUtils.isNotBlank(email)) {
                if(!FormatValidateTool.checkEmail(email)) {
                    return JsonResponseTool.paramErr("邮箱格式无效");
                }
            }

            //验证图文验证码
            if(StringUtils.isNotBlank(captcha)) {
                ServiceResult validServiceResult = this.captchaService.validImgCaptcha(captchaKey, captcha);
                if(!validServiceResult.getFlag()) {
                    return JsonResponseTool.paramErr(validServiceResult.getMessage());
                }
            }

            //验证短信验证码
            if(StringUtils.isNotBlank(smsCode)) {
                ServiceResult validServiceResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
                if(!validServiceResult.getFlag()) {
                    return JsonResponseTool.paramErr(validServiceResult.getMessage());
                }
            }

            //用户注册
            ServiceResult<UserDTO> userServiceResult = userService.userRegister_tx(userName, mobile, email, pwd);
            if(!userServiceResult.getFlag()) {
                return JsonResponseTool.failure(userServiceResult.getMessage());
            }

            if(StringUtils.isNotBlank(email)) {
                userService.sendConfirmMail(MailVerifyTypeEnum.EMAIL_CONFIRM, userServiceResult.getData().getUserId());
            }

            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    userServiceResult.getData().getUserId(),
                    userServiceResult.getData().getUserTypes(),
                    userServiceResult.getData().getRoleIds(),
                    userServiceResult.getData().getIsCertifieds(),
                    userServiceResult.getData().getStatus()
            ));
        };
    }

    /**
     * 用户登录
     * @param userName
     * @param mobile
     * @param email
     * @param pwd
     * @param smsCode
     * @param captcha
     * @param captchaKey
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Callable<JsonResponse> userLogin(@RequestParam(required = false) String userName,
                                            @RequestParam(required = false) String mobile,
                                            @RequestParam(required = false) String email,
                                            @RequestParam String pwd,
                                            @RequestParam(required = false) String smsCode,
                                            @RequestParam(required = false) String captcha,
                                            @RequestParam(required = false) String captchaKey) {

        return () -> {
            if(StringUtils.isBlank(userName) && StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
                return JsonResponseTool.paramErr("参数无效");
            }
            if(StringUtils.isBlank(captcha) && StringUtils.isBlank(smsCode)) {
                return JsonResponseTool.paramErr("验证码无效");
            }

            //验证手机
            if(StringUtils.isNotBlank(mobile)) {
                if(!FormatValidateTool.checkMobile(mobile)) {
                    return JsonResponseTool.paramErr("手机号无效");
                }
            }
            //验证邮箱
            if(StringUtils.isNotBlank(email)) {
                if(!FormatValidateTool.checkEmail(email)) {
                    return JsonResponseTool.paramErr("邮箱格式无效");
                }
            }

            //验证图文验证码
            if(StringUtils.isNotBlank(captcha)) {
                ServiceResult validServiceResult = this.captchaService.validImgCaptcha(captchaKey, captcha);
                if(!validServiceResult.getFlag()) {
                    return JsonResponseTool.paramErr(validServiceResult.getMessage());
                }
            }

            //验证短信验证码
            if(StringUtils.isNotBlank(smsCode)) {
                ServiceResult validServiceResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
                if(!validServiceResult.getFlag()) {
                    return JsonResponseTool.paramErr(validServiceResult.getMessage());
                }
            }

            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(null, userName, mobile, email, pwd);
            if(!userServiceResult.getFlag()) {
                return JsonResponseTool.authFailure(userServiceResult.getMessage());
            }

            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    userServiceResult.getData().getUserId(),
                    userServiceResult.getData().getUserTypes(),
                    userServiceResult.getData().getRoleIds(),
                    userServiceResult.getData().getIsCertifieds(),
                    userServiceResult.getData().getStatus()
                    )
            );
        };
    }

    /**
     * 根据旧密码重置新密码
     *
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public Callable<JsonResponse> userReset(@RequestParam String pwd) {
        Integer uid = UserSession.getCurrent().getUserId();
        return () -> {
            ServiceResult serviceResult = userService.userReset(uid, null, null, null, pwd);
            if(!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            //登录
            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(uid, null, null, null, pwd);
            if(!userServiceResult.getFlag()) {
                return JsonResponseTool.authFailure(userServiceResult.getMessage());
            }

            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    userServiceResult.getData().getUserId(),
                    userServiceResult.getData().getUserTypes(),
                    userServiceResult.getData().getRoleIds(),
                    userServiceResult.getData().getIsCertifieds(),
                    userServiceResult.getData().getStatus()
            ));
        };
    }

    /**
     * 通过手机重置密码
     * @param mobile
     * @param smsCode
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/reset_mobile", method = RequestMethod.POST)
    public Callable<JsonResponse>  userReset(@RequestParam String mobile, @RequestParam String smsCode,  @RequestParam String pwd) {

        return () -> {
            //新密码
            if(StringUtils.isBlank(pwd)) {
                return JsonResponseTool.paramErr("新密码无效");
            }
            //通过手机
            if(!FormatValidateTool.checkMobile(mobile)) {
                return JsonResponseTool.paramErr("手机号无效");
            }

            //校验验证码
            ServiceResult validServiceResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
            if(!validServiceResult.getFlag()) {
                return JsonResponseTool.paramErr(validServiceResult.getMessage());
            }

            //重置密码
            ServiceResult serviceResult = this.userService.userReset(null, null, null, mobile, pwd);
            if(!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            //登录
            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(null, null, mobile, null, pwd);
            if(!userServiceResult.getFlag()) {
                return JsonResponseTool.authFailure(userServiceResult.getMessage());
            }

            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    userServiceResult.getData().getUserId(),
                    userServiceResult.getData().getUserTypes(),
                    userServiceResult.getData().getRoleIds(),
                    userServiceResult.getData().getIsCertifieds(),
                    userServiceResult.getData().getStatus()
            ));
        };
    }

    /**
     * 从邮件重置密码
     * @param uid
     * @param confirmKey
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/reset_mail")
    public Callable<JsonResponse> userReset(@RequestParam Integer uid, @RequestParam String confirmKey, @RequestParam String pwd) {
        return () -> {
            //新密码
            if(StringUtils.isBlank(pwd)) {
                return JsonResponseTool.paramErr("新密码无效");
            }

            ServiceResult serviceResult = this.userService.confirmMail(MailVerifyTypeEnum.USER_RESET, uid, confirmKey, pwd);
            if(!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            //登录
            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(uid, null, null, null, pwd);
            if(!userServiceResult.getFlag()) {
                return JsonResponseTool.authFailure(userServiceResult.getMessage());
            }

            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    userServiceResult.getData().getUserId(),
                    userServiceResult.getData().getUserTypes(),
                    userServiceResult.getData().getRoleIds(),
                    userServiceResult.getData().getIsCertifieds(),
                    userServiceResult.getData().getStatus()
            ));
        };
    }

    /**
     * 发送邮件
     * @return
     */
    @RequestMapping(value = "/send_mail", method = RequestMethod.POST)
    public Callable<JsonResponse> sendConfirmEmail() {
        Integer uid = UserSession.getCurrent().getUserId();
        return () -> {
            userService.sendConfirmMail(MailVerifyTypeEnum.EMAIL_CONFIRM, uid);

            return JsonResponseTool.success(null);
        };
    }

    /**
     * 发送确认邮件
     * @param confirmKey
     * @return
     */
    @RequestMapping(value = "/confirm_mail")
    public Callable<JsonResponse> confirmEmail(@RequestParam String confirmKey) {
        Integer uid = UserSession.getCurrent().getUserId();
        return () -> {
            ServiceResult serviceResult = userService.confirmMail(MailVerifyTypeEnum.EMAIL_CONFIRM, uid, confirmKey, null);
            if(!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            return JsonResponseTool.success(null);
        };
    }

    /**
     * 添加公司信息
     * @param companyName
     * @param credential
     * @param licence
     * @param province
     * @param city
     * @param area
     * @param address
     * @return
     */
    @RequestMapping(value = "/add_company", method = RequestMethod.POST)
    public Callable<JsonResponse> addCompany(@RequestParam String companyName, @RequestParam String credential, @RequestParam String licence,
                                             @RequestParam Integer province, @RequestParam Integer city, @RequestParam Integer area, @RequestParam String address) {
        return () -> {
            //验证区域有效性
            String verifyArea = AreaTool.validateArea(province, city, area);
            if(StringUtils.isNotBlank(verifyArea)) {
                return JsonResponseTool.paramErr(verifyArea);
            }

            if(StringUtils.isBlank(companyName)) {
                return JsonResponseTool.paramErr("公司名不能为空");
            }
            if(StringUtils.isBlank(credential)) {
                return JsonResponseTool.paramErr("统一信用代码不能为空");
            }
            if(StringUtils.isBlank(licence)) {
                return JsonResponseTool.paramErr("营业执照未上传");
            }

            //验证公司有效性
            ServiceResult<Integer> companyResult = companyService.validateCompany(credential);
            if(companyResult.getFlag()) {
                return JsonResponseTool.failure(String.valueOf(companyResult.getData()));
            }

            //增加公司信息
            TCompanyInfo tCompanyInfo = new TCompanyInfo();
            tCompanyInfo.setCompanyName(companyName);
            tCompanyInfo.setCredential(credential);
            tCompanyInfo.setLicence(licence);
            tCompanyInfo.setProvince(province);
            tCompanyInfo.setCity(city);
            tCompanyInfo.setArea(area);
            tCompanyInfo.setAddress(address);
            companyResult = companyService.addCompany_tx(tCompanyInfo);
            if(!companyResult.getFlag()) {
                return JsonResponseTool.failure(companyResult.getMessage());
            }

            return JsonResponseTool.success(companyResult.getData());
        };
    }

    /**
     * 注册运营者(管理员)
     * @param userId
     * @param userType
     * @param companyId
     * @param realName
     * @param identity
     * @param mobile
     * @param smsCode
     * @return
     */
    @RequestMapping(value = "/register_admin", method = RequestMethod.POST)
    public Callable<JsonResponse> registerAdmin(@RequestParam(defaultValue = "0") Integer userId, @RequestParam Integer userType,
                                                @RequestParam(defaultValue = "0") Integer companyId, @RequestParam String realName,
                                                @RequestParam String identity, @RequestParam String mobile, @RequestParam String smsCode) {
        Integer uid = UserSession.getCurrent().getUserId();
        //操作他人帐号 需要平台管理员权限
        if(0 != userId) {
            if(!ProtocolTool.validateSysManager(String.valueOf(UserSession.getCurrent().getUserType()),
                    String.valueOf(UserSession.getCurrent().getRoleId()),
                    String.valueOf(UserSession.getCurrent().getIsCertified()),
                    UserSession.getCurrent().getStatus())) {
                return () -> JsonResponseTool.authFailure("没有权限");
            }
        }
        return () -> {
            if(0 == uid && 0 == userId) {
                return JsonResponseTool.paramErr("用户无效");
            }
            if(!FormatValidateTool.checkMobile(mobile)) {
                return JsonResponseTool.paramErr("手机号无效");
            }
            if(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, null, String.valueOf(userType)).isEmpty()) {
                return JsonResponseTool.paramErr("用户类型无效");
            }
            if(StringUtils.isBlank(realName)) {
                return JsonResponseTool.paramErr("姓名无效");
            }
            String identityMsg = FormatValidateTool.idCardValidate(identity);
            if(!StringUtils.isBlank(identityMsg)) {
                return JsonResponseTool.paramErr("身份证无效," + identityMsg);
            }
            ServiceResult codeValidResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
            if(!codeValidResult.getFlag()) {
                return JsonResponseTool.paramErr(codeValidResult.getMessage());
            }

            ServiceResult<TUserInfo> userServiceResult = this.userService.queryUserById(0==userId?uid:userId);      //userId优先
            if(!userServiceResult.getFlag()) {
                return JsonResponseTool.failure(userServiceResult.getMessage());
            }

            if(0 != companyId) {
                userServiceResult.getData().setCompanyId(companyId);
            }
            userServiceResult.getData().setRealName(realName);
            userServiceResult.getData().setIdentity(identity);
            userServiceResult.getData().setMobile(mobile);
            userServiceResult = this.userService.registerAdmin_tx(userType, userServiceResult.getData());
            if(!userServiceResult.getFlag()) {
                return JsonResponseTool.failure(userServiceResult.getMessage());
            }

            return JsonResponseTool.success(null);
        };
    }

}
