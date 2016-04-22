package com.dqys.auth.controller;

import com.dqys.auth.service.constant.MailVerifyTypeEnum;
import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.service.facade.UserService;
import com.dqys.captcha.service.facade.CaptchaService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import javax.servlet.http.HttpSession;
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
    private ObjectMapper objectMapper;

    //@Cacheable(cacheNames = "auth_m_session_id", key = "#httpSession.id")
    @RequestMapping(value = "/session_id", produces = "text/plain;charset=utf-8")
    public Callable<String> querySessionId(HttpSession httpSession, @RequestParam String jsonPCallable) {
        return () -> {
            StringBuffer result = new StringBuffer(jsonPCallable);
            result.append("(").append(objectMapper.writeValueAsString(JsonResponseTool.success(httpSession.getId()))).append(");");
            return result.toString();
        };
    }

    /**
     *  图片验证码
     * @param httpSession
     * @return
     */
    @RequestMapping("/captcha")
    public Callable<ResponseEntity<StreamingResponseBody>> genImgCaptcha(HttpSession httpSession) {
        return () -> {
            ServiceResult<BufferedImage> biResult = this.captchaService.genImgCaptcha(httpSession.getId());
            if(!biResult.getFlag()) {
                return null;
            }

            // FIXME: 16-4-18 临时查看sessionid
            LogManager.getRootLogger().debug(httpSession.getId());

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

            // FIXME: 16-4-13  临时查看验证码
            LogManager.getRootLogger().debug(smsCodeResult.getData());

            // TODO: 16-4-13  发送短信

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
            ServiceResult<UserDTO> userDTOServiceResult = userService.userRegister_tx(userName, mobile, email, pwd);
            if(!userDTOServiceResult.getFlag()) {
                return JsonResponseTool.failure(userDTOServiceResult.getMessage());
            }

            if(StringUtils.isNotBlank(email)) {
                userService.sendConfirmMail(MailVerifyTypeEnum.EMAIL_CONFIRM, userDTOServiceResult.getData().getUserId());
            }

            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    userDTOServiceResult.getData().getUserId(),
                    userDTOServiceResult.getData().getUserType(),
                    userDTOServiceResult.getData().getRoleId(),
                    userDTOServiceResult.getData().getStatus(),
                    userDTOServiceResult.getData().getCertified()
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
                    userServiceResult.getData().getUserType(),
                    userServiceResult.getData().getRoleId(),
                    userServiceResult.getData().getStatus(),
                    userServiceResult.getData().getCertified())
            );
        };
    }

    @RequestMapping(value = "/login_p", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String userLogin(@RequestParam(required = false) String userName,
                                            @RequestParam(required = false) String mobile,
                                            @RequestParam(required = false) String email,
                                            @RequestParam String pwd,
                                            @RequestParam(required = false) String smsCode,
                                            @RequestParam(required = false) String captcha,
                                            @RequestParam(required = false) String captchaKey,
                                            @RequestParam String  jsonPCallable) throws Exception {
        JsonResponse jsonResponse = this.userLogin(userName, mobile, email, pwd, smsCode, captcha, captchaKey).call();
        StringBuffer resutStr = new StringBuffer(jsonPCallable);
        resutStr.append("(").append(objectMapper.writeValueAsString(jsonResponse)).append(");");
        return resutStr.toString();
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
                    userServiceResult.getData().getUserType(),
                    userServiceResult.getData().getRoleId(),
                    userServiceResult.getData().getStatus(),
                    userServiceResult.getData().getCertified()
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
                    userServiceResult.getData().getUserType(),
                    userServiceResult.getData().getRoleId(),
                    userServiceResult.getData().getStatus(),
                    userServiceResult.getData().getCertified()
            ));
        };
    }

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
                    userServiceResult.getData().getUserType(),
                    userServiceResult.getData().getRoleId(),
                    userServiceResult.getData().getStatus(),
                    userServiceResult.getData().getCertified()
            ));
        };
    }

    @RequestMapping(value = "/send_mail", method = RequestMethod.POST)
    public Callable<JsonResponse> sendConfirmEmail() {
        Integer uid = UserSession.getCurrent().getUserId();
        return () -> {
            userService.sendConfirmMail(MailVerifyTypeEnum.EMAIL_CONFIRM, uid);

            return JsonResponseTool.success(null);
        };
    }

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
}
