package com.dqys.auth.controller;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.service.constant.MailVerifyTypeEnum;
import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.service.facade.CompanyService;
import com.dqys.auth.service.facade.UserService;
import com.dqys.captcha.service.facade.CaptchaService;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.base.SysProperty;
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
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

/**
 * @author by pan on 16-4-8.
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseApiContorller {

    @Autowired
    private UserService userService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TUserInfoMapper tUserInfoMapper;

    /**
     * @api {GET} http://{url}/auth/captcha 图片验证码
     * @apiName captcha
     * @apiGroup Auth
     * @apiDescription This is the Description.
     * @apiParam {String} key 验证码标签key
     * @apiSuccess {Object} data png图片
     */
    @RequestMapping("/captcha")
    public Callable<ResponseEntity<StreamingResponseBody>> genImgCaptcha(@RequestParam String key) {
        return () -> {
            ServiceResult<BufferedImage> biResult = this.captchaService.genImgCaptcha(key);
            if (!biResult.getFlag()) {
                return null;
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>((StreamingResponseBody) outputStream -> ImageIO.write(biResult.getData(), "png", outputStream), httpHeaders, HttpStatus.OK);
        };
    }

    /**
     * @api {GET} http://{url}/auth/sms_code 短信验证码
     * @apiName smsCode
     * @apiGroup Auth
     * @apiDescription 手机校验短信
     * @apiParam {String} mobile 手机号
     * @apiUse JsonResponse
     * @apiSuccess {Object} data 返回数据
     */
    @RequestMapping("/sms_code")
    public Callable<JsonResponse> sendSmsCode(@RequestParam String mobile) {
        return () -> {
            //验证手机
            if (StringUtils.isNotBlank(mobile)) {
                if (!FormatValidateTool.checkMobile(mobile)) {
                    return JsonResponseTool.paramErr("手机号无效");
                }
            }

            ServiceResult<String> smsCodeResult = this.captchaService.genSmsCaptcha(mobile);
            if (!smsCodeResult.getFlag()) {
                return JsonResponseTool.failure(smsCodeResult.getMessage());
            }

            SmsUtil smsUtil = new SmsUtil();
            smsUtil.sendSms(SysProperty.SMS_VERIFICATION_CODE, mobile, smsCodeResult.getData());
            LogManager.getRootLogger().debug(smsCodeResult.getData());

            return JsonResponseTool.success(null);
        };
    }

    /**
     * @api {GET} http://{url}/auth/validate_user 验证用户有效性
     * @apiName validateUser
     * @apiGroup Auth
     * @apiDescription 验证当前账号是否有效
     * @apiParam {String} [userName] 账号<三选一必选>
     * @apiParam {String} [mobile] 手机号三选一必选>
     * @apiParam {String} [email] 邮箱三选一必选>
     * @apiUse JsonResponse
     * @apiSuccess {json} data 返回数据(0,用户可用;Object,已存在且读取出数据)
     * @apiErrorExample {json} Error-Response:
     * HTTP/1.1 2000 ok
     * {
     * code:2000,
     * msg:'成功',
     * data:0
     * }
     */
    @RequestMapping(value = "/validate_user")
    public Callable<JsonResponse> velidateUser(@RequestParam(required = false) String userName,
                                               @RequestParam(required = false) String mobile,
                                               @RequestParam(required = false) String email) {

        return () -> {
            if (StringUtils.isBlank(userName) && StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
                return JsonResponseTool.paramErr("参数无效");
            }

            ServiceResult<Integer> serviceResult = this.userService.validateUser(userName, mobile, email);
            if (!serviceResult.getFlag()) {
                return JsonResponseTool.success(0);
            }

            return JsonResponseTool.success(serviceResult.getData());
        };
    }

    /**
     * @apiDefine RequestAccount
     * @apiParam {String} [account] 账号<三选一必选>
     * @apiParam {String} [mobile] 手机号<三选一必选>
     * @apiParam {String} [email] 邮箱<三选一必选>
     * @apiParam {String} pwd 密码
     * @apiParam {String} [smsCode] 手机短信验证码<三选一必选>
     * @apiParam {String} [captcha] 图片验证码<三选一必选>
     * @apiParam {String} [captchaKey] 图片验证码Key<三选一必选>
     */

    /**
     * @apiDefine ResponseHeader
     * @apiSuccess {json} data 返回头信息内容
     *
     * @apiSuccessExample {json} Data-Success-Response:
     * {
     *     'x-qs-user':'SDFBSKDFNSDFNSKJFSDFNLSDFNLSDFNK=',
     *     'x-qs-type':'0,',
     *     'x-qs-role':'0,',
     *     'x-qs-certified':'true,',
     *     'x-qs-status':'0,'
     * }
     */

    /**
     * @api {POST} http://{url}/auth/register 注册
     * @apiName register
     * @apiGroup Auth
     * @apiDescription 用户注册
     * @apiUse RequestAccount
     * @apiUse JsonResponse
     * @apiSuccess ResponseHeader
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Callable<JsonResponse> userRegister(@RequestParam(required = false) String account,
                                               @RequestParam(required = false) String mobile,
                                               @RequestParam(required = false) String email,
                                               @RequestParam String pwd,
                                               @RequestParam(required = false) String smsCode,
                                               @RequestParam(required = false) String captcha,
                                               @RequestParam(required = false) String captchaKey) {
        return () -> {
            if (StringUtils.isBlank(account) && StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
                return JsonResponseTool.paramErr("参数无效");
            }
            if (StringUtils.isBlank(captcha) && StringUtils.isBlank(smsCode)) {
                return JsonResponseTool.paramErr("验证码无效");
            }

            //验证手机
            if (StringUtils.isNotBlank(mobile)) {
                if (!FormatValidateTool.checkMobile(mobile)) {
                    return JsonResponseTool.paramErr("手机号无效");
                }
            }
            //验证邮箱
            if (StringUtils.isNotBlank(email)) {
                if (!FormatValidateTool.checkEmail(email)) {
                    return JsonResponseTool.paramErr("邮箱格式无效");
                }
            }

            //验证图文验证码
            if (StringUtils.isNotBlank(captcha)) {
                ServiceResult validServiceResult = this.captchaService.validImgCaptcha(captchaKey, captcha);
                if (!validServiceResult.getFlag()) {
                    return JsonResponseTool.paramErr(validServiceResult.getMessage());
                }
            }

            //验证短信验证码
            if (StringUtils.isNotBlank(smsCode)) {
                ServiceResult validServiceResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
                if (!validServiceResult.getFlag()) {
                    return JsonResponseTool.paramErr(validServiceResult.getMessage());
                }
            }

            //用户注册
            ServiceResult<UserDTO> userServiceResult = userService.userRegister_tx(account, mobile, email, pwd);
            if (!userServiceResult.getFlag()) {
                return JsonResponseTool.failure(userServiceResult.getMessage());
            }

            if (StringUtils.isNotBlank(email)) {
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
     * @api {POST} http://{url}/auth/login 用户登陆
     * @apiName login
     * @apiGroup Auth
     * @apiDescription 用户登录模块返回信息包含独有信息, 具体参考Login-Success-Response
     * @apiUse RequestAccount
     * @apiUse JsonResponse
     * @apiUse ResponseHeader
     * @apiSuccess {json} data
     * @apiSuccessExample {json} Login-Success-Response:
     * {
     * 'x-qs-user':'SDFBSKDFNSDFNSKJFSDFNLSDFNLSDFNK=',
     * 'x-qs-type':'0,',
     * 'x-qs-role':'0,',
     * 'x-qs-certified':'true,',
     * 'x-qs-status':'0,',
     * 'x-qs-step':'true'
     * }
     * 参数step说明:false:无效账户,active:激活邮箱,adminCompany:未完善信息,authentication:企业未认证,true:信息完善
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
            if (StringUtils.isBlank(userName) && StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
                return JsonResponseTool.paramErr("参数无效");
            }
            if (StringUtils.isBlank(captcha) && StringUtils.isBlank(smsCode)) {
                return JsonResponseTool.paramErr("验证码无效");
            }

            //验证手机
            if (StringUtils.isNotBlank(mobile)) {
                if (!FormatValidateTool.checkMobile(mobile)) {
                    return JsonResponseTool.paramErr("手机号无效");
                }
            }
            //验证邮箱
            if (StringUtils.isNotBlank(email)) {
                if (!FormatValidateTool.checkEmail(email)) {
                    return JsonResponseTool.paramErr("邮箱格式无效");
                }
            }

            //验证图文验证码
            if (StringUtils.isNotBlank(captcha)) {
                ServiceResult validServiceResult = this.captchaService.validImgCaptcha(captchaKey, captcha);
                if (!validServiceResult.getFlag()) {
                    return JsonResponseTool.paramErr(validServiceResult.getMessage());
                }
            }

            //验证短信验证码
            if (StringUtils.isNotBlank(smsCode)) {
                ServiceResult validServiceResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
                if (!validServiceResult.getFlag()) {
                    return JsonResponseTool.paramErr(validServiceResult.getMessage());
                }
            }

            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(null, userName, mobile, email, pwd);
            if (!userServiceResult.getFlag()) {
                return JsonResponseTool.authFailure(userServiceResult.getMessage());
            }

            // auto 验证注册信息是否完善<这里后期完善>
            String step = "false"; // false:无效账户,active:激活邮箱,adminCompany:未完善信息,authentication:未认证,true:信息完善
            Integer status = userServiceResult.getData().getStatus();
            Integer companyId = userServiceResult.getData().getCompanyId();
            if (status.equals(0)) {
                step = "active";
            } else if (companyId == null || companyId.equals("")) {
                step = "adminCompany";
            } else if (companyService.get(companyId).getIsAuth().equals(0)) {
                step = "authentication";
            } else {
                step = "true";
            }

            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                            userServiceResult.getData().getUserId(),
                            userServiceResult.getData().getUserTypes(),
                            userServiceResult.getData().getRoleIds(),
                            userServiceResult.getData().getIsCertifieds(),
                            userServiceResult.getData().getStatus(),
                            step
                    )
            );
        };
    }

    /**
     * @apiDefine CommonHeader
     * @apiHeader {String} x-qs-user 账号加密字符串
     * @apiHeader {String} x-qs-type 账号类型集
     * @apiHeader {String} x-qs-role 账号角色权限集
     * @apiHeader {String} x-qs-certified 账号认证情况集合
     * @apiHeader {String} x-qs-status 账号状态
     */

    /**
     * @api {POST} http://{url}/auth/reset 重置密码
     * @apiName reset
     * @apiGroup Auth
     * @apiParam {String} pwd 新密码
     * @apiUse CommonHeader
     * @apiUse JsonResponse
     * @apiUse ResponseHeader
     */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public Callable<JsonResponse> userReset(@RequestParam String pwd) {
        Integer uid = UserSession.getCurrent().getUserId();
        return () -> {
            ServiceResult serviceResult = userService.userReset(uid, null, null, null, pwd);
            if (!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            //登录
            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(uid, null, null, null, pwd);
            if (!userServiceResult.getFlag()) {
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
     * @api {POST} http://{url}/auth/reset_mobile 手机重置密码
     * @apiName reset_mobile
     * @apiGroup Auth
     * @apiParam {String} mobile 手机号
     * @apiParam {String} smsCode 验证码
     * @apiParam {String} pwd 新密码
     * @apiUse JsonResponse
     * @apiUse ResponseHeader
     */
    @RequestMapping(value = "/reset_mobile", method = RequestMethod.POST)
    public Callable<JsonResponse> userReset(@RequestParam String mobile, @RequestParam String smsCode, @RequestParam String pwd) {

        return () -> {
            //新密码
            if (StringUtils.isBlank(pwd)) {
                return JsonResponseTool.paramErr("新密码无效");
            }
            //通过手机
            if (!FormatValidateTool.checkMobile(mobile)) {
                return JsonResponseTool.paramErr("手机号无效");
            }

            //校验验证码
            ServiceResult validServiceResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
            if (!validServiceResult.getFlag()) {
                return JsonResponseTool.paramErr(validServiceResult.getMessage());
            }

            //重置密码
            ServiceResult serviceResult = this.userService.userReset(null, null, null, mobile, pwd);
            if (!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            //登录
            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(null, null, mobile, null, pwd);
            if (!userServiceResult.getFlag()) {
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
     * @api {GET} http://{url}/auth/reset_mail 邮件重置密码
     * @apiName reset_mail
     * @apiGroup Auth
     * @apiParam {number} uid 用户Id
     * @apiParam {String} confirmKey 验证key
     * @apiParam {String} pwd 新密码
     * @apiUse JsonResponse
     * @apiUse ResponseHeader
     */
    @RequestMapping(value = "/reset_mail")
    public Callable<JsonResponse> userReset(@RequestParam Integer uid, @RequestParam String confirmKey, @RequestParam String pwd) {
        return () -> {
            //新密码
            if (StringUtils.isBlank(pwd)) {
                return JsonResponseTool.paramErr("新密码无效");
            }

            ServiceResult serviceResult = this.userService.confirmMail(MailVerifyTypeEnum.USER_RESET, uid, confirmKey, pwd);
            if (!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            //登录
            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(uid, null, null, null, pwd);
            if (!userServiceResult.getFlag()) {
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
     * @api {POST} http://{url}/auth/send_mail 发送邮件
     * @apiName send_mail
     * @apiGroup Auth
     * @apiDescription 发送至当前用户绑定的邮箱
     * @apiUse CommonHeader
     * @apiUse JsonResponse
     */
    @RequestMapping(value = "/send_mail", method = RequestMethod.POST)
    public JsonResponse sendConfirmEmail() {
        Integer uid = UserSession.getCurrent().getUserId();

        userService.sendConfirmMail(MailVerifyTypeEnum.EMAIL_CONFIRM, uid);

        return JsonResponseTool.success(null);

    }

    /**
     * @api {GET} http://{url}/auth/confirm_mail 邮箱激活
     * @apiName confirm_mail
     * @apiGroup Auth
     * @apiParam {String} confirmKey 加密Key
     * @apiUse JsonResponse
     */
    @RequestMapping(value = "/confirm_mail")
    public Callable<JsonResponse> confirmEmail(@RequestParam String confirmKey) {
        Integer uid = UserSession.getCurrent().getUserId();
        return () -> {
            ServiceResult serviceResult = userService.confirmMail(MailVerifyTypeEnum.EMAIL_CONFIRM, uid, confirmKey, null);
            if (!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            return JsonResponseTool.success(null);
        };
    }


    /**
     * @api {POST} http://{url}/auth/addCompany 完善公司信息
     * @apiName addCompany
     * @apiGroup Auth
     * @apiParam {String} companyName 公司名称
     * @apiParam {String} credential 加密Key
     * @apiParam {String} licence 加密Key
     * @apiParam {number} province 省份Id
     * @apiParam {number} city 城市Id
     * @apiParam {number} area 区域Id
     * @apiParam {String} address 详细地址
     * @apiUse CommonHeader
     * @apiUse JsonResponse
     */
    @RequestMapping(value = "/add_company", method = RequestMethod.POST)
    public Callable<JsonResponse> addCompany(@RequestParam String companyName, @RequestParam String credential, @RequestParam String licence, @RequestParam Integer type,
                                             @RequestParam Integer province, @RequestParam Integer city, @RequestParam Integer area, @RequestParam String address, HttpServletRequest httpServletRequest) {
        Integer userId = UserSession.getCurrent() != null ? UserSession.getCurrent().getUserId() : 0;
        return () -> {
            //验证区域有效性
            String verifyArea = AreaTool.validateArea(province, city, area);
            if (StringUtils.isNotBlank(verifyArea)) {
                return JsonResponseTool.paramErr(verifyArea);
            }

            if (StringUtils.isBlank(companyName)) {
                return JsonResponseTool.paramErr("公司名不能为空");
            }
            if (StringUtils.isBlank(credential)) {
                return JsonResponseTool.paramErr("统一信用代码不能为空");
            }
            if (StringUtils.isBlank(licence)) {
                return JsonResponseTool.paramErr("营业执照未上传");
            }
            if (CompanyTypeEnum.getCompanyTypeEnum(type) == null) {
                return JsonResponseTool.paramErr("公司类型错误");
            }

            //验证公司有效性
            ServiceResult<Integer> companyResult = companyService.validateCompany(credential);
            if (companyResult.getFlag()) {
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
            tCompanyInfo.setType(type);
            companyResult = companyService.addCompany_tx(tCompanyInfo);
            if (!companyResult.getFlag()) {
                return JsonResponseTool.failure(companyResult.getMessage());
            }
            TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(userId);
            if (tUserInfo != null) {
                tUserInfo.setCompanyId(companyResult.getData());
                tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo);
            }
            return JsonResponseTool.success(companyResult.getData());
        };
    }

    /**
     * @api {POST} http://{url}/auth/register_admin 完善管理员信息
     * @apiName register_admin
     * @apiGroup Auth
     * @apiParam {number} [userId] 用户ID
     * @apiParam {number} userType 用户类型
     * @apiParam {number} [companyId] 公司ID
     * @apiParam {number} confirmKey 加密Key
     * @apiParam {String} realName 真实姓名
     * @apiParam {String} identity 身份证
     * @apiParam {String} mobile 手机号
     * @apiParam {String} smsCode 验证码
     * @apiUse CommonHeader
     * @apiUse JsonResponse
     */
    @RequestMapping(value = "/register_admin", method = RequestMethod.POST)
    public Callable<JsonResponse> registerAdmin(@RequestParam(defaultValue = "0") Integer userId, @RequestParam Integer userType,
                                                @RequestParam(defaultValue = "0") Integer companyId, @RequestParam String realName,
                                                @RequestParam String identity, @RequestParam String mobile, @RequestParam String smsCode) {
        Integer uid = UserSession.getCurrent().getUserId();
        //操作他人帐号 需要平台管理员权限
        if (0 != userId) {
            if (!ProtocolTool.validateSysManager(String.valueOf(UserSession.getCurrent().getUserType()),
                    String.valueOf(UserSession.getCurrent().getRoleId()),
                    String.valueOf(UserSession.getCurrent().getIsCertified()),
                    UserSession.getCurrent().getStatus())) {
                return () -> JsonResponseTool.authFailure("没有权限");
            }
        }
        return () -> {
            if (0 == uid && 0 == userId) {
                return JsonResponseTool.paramErr("用户无效");
            }
            if (!FormatValidateTool.checkMobile(mobile)) {
                return JsonResponseTool.paramErr("手机号无效");
            }
            if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, null, String.valueOf(userType)).isEmpty()) {
                return JsonResponseTool.paramErr("用户类型无效");
            }
            if (StringUtils.isBlank(realName)) {
                return JsonResponseTool.paramErr("姓名无效");
            }
            String identityMsg = FormatValidateTool.idCardValidate(identity);
            if (!StringUtils.isBlank(identityMsg)) {
                return JsonResponseTool.paramErr("身份证无效," + identityMsg);
            }
            ServiceResult codeValidResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
            if (!codeValidResult.getFlag()) {
                return JsonResponseTool.paramErr(codeValidResult.getMessage());
            }

            ServiceResult<TUserInfo> userServiceResult = this.userService.queryUserById(0 == userId ? uid : userId);      //userId优先
            if (!userServiceResult.getFlag()) {
                return JsonResponseTool.failure(userServiceResult.getMessage());
            }

            if (0 != companyId) {
                userServiceResult.getData().setCompanyId(companyId);
            }
            userServiceResult.getData().setRealName(realName);
            userServiceResult.getData().setIdentity(identity);
            userServiceResult.getData().setMobile(mobile);
            userServiceResult = this.userService.registerAdmin_tx(userType, userServiceResult.getData());
            if (!userServiceResult.getFlag()) {
                return JsonResponseTool.failure(userServiceResult.getMessage());
            }

            return JsonResponseTool.success(null);
        };
    }

}
