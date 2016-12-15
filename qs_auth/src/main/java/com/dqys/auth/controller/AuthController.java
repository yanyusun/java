package com.dqys.auth.controller;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TMessageMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.LoginLog;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.service.constant.MailVerifyTypeEnum;
import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.service.facade.CompanyService;
import com.dqys.auth.service.facade.UserService;
import com.dqys.captcha.service.facade.CaptchaService;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.MailEnum;
import com.dqys.core.constant.SmsEnum;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.List;
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

    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;
    @Autowired
    private TMessageMapper messageMapper;

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
    public Callable<JsonResponse> validateUser(@RequestParam(required = false) String userName,
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
                                            String ip,
                                            @RequestParam(required = false) String captcha,
                                            @RequestParam(required = false) String captchaKey,
                                            HttpServletRequest request) {

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
            addLoginLog(userServiceResult, request, ip);  //保存登入日志
            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                            userServiceResult.getData().getUserId(),
                            userServiceResult.getData().getUserTypes(),
                            userServiceResult.getData().getRoleIds(),
                            userServiceResult.getData().getIsCertifieds(),
                            userServiceResult.getData().getStatus(),
                            verifyUserStep(userServiceResult)
                    )
            );
        };
    }

    private void addLoginLog(ServiceResult<UserDTO> userServiceResult, HttpServletRequest request, String ip) {
        LoginLog log = new LoginLog();
        log.setUserId(userServiceResult.getData().getUserId());
        if (ip == null || "".equals(ip)) {
            log.setIp(request.getRemoteAddr().toString());
        } else {
            log.setIp(ip);
        }
        messageMapper.addLoginLog(log);//添加登入记录
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
     * @api {POST} http://{url}/auth/reset_mobile 使用手机短信验证码来重置密码
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
            ServiceResult validServiceResult = this.captchaService.validSmsCaptcha(mobile, smsCode, SmsEnum.RESET_PAWW.getValue());
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
            ServiceResult<UserDTO> serviceResult = userService.confirmMail(MailVerifyTypeEnum.EMAIL_CONFIRM, uid, confirmKey, null);
            if (!serviceResult.getFlag()) {
                return JsonResponseTool.failure(serviceResult.getMessage());
            }

            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    serviceResult.getData().getUserId(),
                    serviceResult.getData().getUserTypes(),
                    serviceResult.getData().getRoleIds(),
                    serviceResult.getData().getIsCertifieds(),
                    serviceResult.getData().getStatus(),
                    verifyUserStep(serviceResult)
            ));
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
                                             @RequestParam Integer province, @RequestParam Integer city, @RequestParam Integer area, @RequestParam String address) {
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
            if (companyResult.getFlag()) {//防止重复存在
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


    /**
     * 第五步
     *
     * @api {POST} http://{url}/auth/fixCompanyInfo 完善公司以及管理人员信息
     * @apiName register_admin
     * @apiGroup Auth
     * @apiParam {string} name 名称（相当于公司简称）
     * @apiParam {string} introduction 简介
     * @apiParam {number} province 省份
     * @apiParam {number} city 城市
     * @apiParam {number} district 区域
     * @apiSuccess {number} data 空返回值
     */
    @RequestMapping(value = "/fixCompanyInfo", method = RequestMethod.POST)
    public JsonResponse fixCompanyInfo(String name, String introduction, Integer province, Integer city, Integer district) {
        if (CommonUtil.checkParam(name, introduction, province, city, district)) {
            return JsonResponseTool.paramErr("参数错误");
        }

        Integer userId = UserSession.getCurrent().getUserId();
        if (userId == null) {
            return JsonResponseTool.paramErr("用户信息错误，请重新登录");
        }

        return userService.registerStep5(userId, name, introduction, province, city, district);
    }

    private String verifyUserStep(ServiceResult<UserDTO> userServiceResult) {
        // todo 验证注册信息是否完善<这里后期完善>
        String step = "false"; // false:无效账户,active:激活邮箱,company:完善公司信息,administrator:完善公司信息,authentication:未认证(暂时不校验),true:信息完善
        if (!userServiceResult.getFlag()) {
            return step;
        }
        Integer status = userServiceResult.getData().getStatus();
        Integer companyId = userServiceResult.getData().getCompanyId();
        TCompanyInfo info = new TCompanyInfo();
        if (companyId != null) {
            info = companyService.get(companyId);
        }
        if (status == null || status.equals(SysProperty.DEFAULT)) {
            step = "active";
        } else if (companyId == null || companyId.equals("")) {
            step = "company";
        } else if (companyId != null && info.getCompanyAccount() == null) {
            step = "administrator";
        } else if (info.getIsAuth().equals(0)) {
            step = "authentication";
        } else if (info.getIsAuth().equals(2)) {
            step = "authentication_no";
        } else {
            step = "true";
        }
        return step;
    }

    /**
     * 第四步（信息登记）
     *
     * @api {POST} http://{url}/auth/addCompany_four 完善公司信息
     * @apiName addCompany
     * @apiGroup Auth
     * @apiParam {String} companyName 公司名称
     * @apiParam {String} credential 统一社会信用代码
     * @apiParam {String} licence 营业执照扫描件
     * @apiParam {number} type 公司类型
     * @apiParam {number} userType 用户类型
     * @apiParam {string} realName 姓名
     * @apiParam {string} identity 身份证
     * @apiParam {String} legalPerson 企业法人扫描件
     * @apiParam {string} mobile 手机号
     * @apiParam {string} smsCode 验证码
     * @apiUse CommonHeader
     * @apiUse JsonResponse
     */
    @RequestMapping(value = "/addCompany_four", method = RequestMethod.POST)
    public JsonResponse addCompany_four(@RequestParam String companyName, @RequestParam String credential, @RequestParam String licence,
                                        @RequestParam Integer type, @RequestParam Integer userType, @RequestParam String realName, String legalPerson,
                                        @RequestParam String identity, @RequestParam String mobile, @RequestParam String smsCode) throws Exception {
        Integer userId = UserSession.getCurrent() != null ? UserSession.getCurrent().getUserId() : 0;
        if (0 == userId) {
            return JsonResponseTool.paramErr("用户无效");
        }
        //用户存在了继续下面的格式判断
        ServiceResult<TUserInfo> userServiceResult = this.userService.queryUserById(userId);      //userId优先
        if (!userServiceResult.getFlag()) {
            return JsonResponseTool.failure(userServiceResult.getMessage());
        } else {
            TUserInfo userInfo = userServiceResult.getData();
            if (CompanyTypeEnum.getCompanyTypeEnum(type) == null) {
                return JsonResponseTool.paramErr("公司类型错误");
            }
            if ((userType == 1) || (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, null, String.valueOf(userType)).isEmpty())) {
                return JsonResponseTool.paramErr("用户类型无效");
            }
            if (StringUtils.isBlank(companyName)) {
                return JsonResponseTool.paramErr("公司名不能为空");
            }
            if (StringUtils.isBlank(credential)) {
                return JsonResponseTool.paramErr("营业执照注册号不能为空");
            }
            if (StringUtils.isBlank(licence)) {
                return JsonResponseTool.paramErr("营业执照未上传");
            }
            if (StringUtils.isBlank(realName)) {
                return JsonResponseTool.paramErr("姓名无效");
            }
            String identityMsg = FormatValidateTool.idCardValidate(identity);
            if (!StringUtils.isBlank(identityMsg)) {
                return JsonResponseTool.paramErr("身份证无效," + identityMsg);
            }
            if (!FormatValidateTool.checkMobile(mobile)) {
                return JsonResponseTool.paramErr("手机号无效");
            }
            ServiceResult<Integer> companyResult = new ServiceResult<>();
            //验证公司有效性
            companyResult = companyService.validateCompany(credential);
            if (companyResult.getFlag()) {//防止重复存在
                //返回营业执照注册号已经注册
                if (userInfo.getCompanyId() == null || companyResult.getData().intValue() != userInfo.getCompanyId().intValue()) {
                    return JsonResponseTool.failure("营业执照注册号已经注册");
                }
            }
            //验证手机短信验证码
            ServiceResult codeValidResult = this.captchaService.validSmsCaptcha(mobile, smsCode);
            if (!codeValidResult.getFlag()) {
                return JsonResponseTool.paramErr(codeValidResult.getMessage());
            }
            TCompanyInfo tCompanyInfo = new TCompanyInfo();
            //公司信息
            tCompanyInfo.setCompanyName(companyName);
            tCompanyInfo.setCredential(credential);
            tCompanyInfo.setLicence(licence);
            tCompanyInfo.setType(type);
            tCompanyInfo.setLegalPerson(legalPerson);
            tCompanyInfo.setBusinessType(userType);
            if (userInfo.getCompanyId() != null) {
                TCompanyInfo companyInfo = companyService.get(userInfo.getCompanyId());
                if (companyInfo != null) {
                    //修改公司信息
                    if (companyInfo.getIsAuth() == 2) {
                        tCompanyInfo.setIsAuth(0);
                    }
                    tCompanyInfo.setId(companyInfo.getId());
                    companyResult = companyService.addCompany_tx(tCompanyInfo);
                } else {
                    return JsonResponseTool.paramErr("公司信息修改失败");
                }
            } else {
                companyResult = companyService.addCompany_tx(tCompanyInfo);//添加公司信息
                if (!companyResult.getFlag()) {
                    return JsonResponseTool.failure(companyResult.getMessage());
                }
                userInfo.setCompanyId(companyResult.getData());
                tUserInfoMapper.updateByPrimaryKeySelective(userInfo);
            }
            userInfo.setRealName(realName);
            userInfo.setUserName(realName);
            userInfo.setIdentity(identity);
            userInfo.setMobile(mobile);
            userServiceResult = this.userService.registerAdmin_tx(userType, userInfo);
            if (!userServiceResult.getFlag()) {
                return JsonResponseTool.failure(userServiceResult.getMessage());
            }
            return JsonResponseTool.success("成功");
        }
    }

    /**
     * @api {POST} http://{url}/auth/verifyLoginName 验证自定义帐号、邮箱、手机号三者唯一性，
     * 只是验证其中一个唯一性的就传相应的参数即可
     * @apiName verifyLoginName
     * @apiGroup Auth
     * @apiParam {string} [account] 自定义帐号
     * @apiParam {string} [mobile] 手机号
     * @apiParam {string} [email] 邮箱
     */
    @RequestMapping(value = "/verifyLoginName", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse verifyLoginName(String account, String mobile, String email) throws Exception {
        String msg = "";
        TUserInfo info = null;
        if (account != null && !"".equals(account)) {
            info = userService.queryUser(account, null, null);
            if (info != null) {
                msg += "自定义帐号已存在;";
            }
        }
        if (mobile != null && !"".equals(mobile)) {
            if (!FormatValidateTool.checkMobile(mobile)) {
                msg = "手机号格式错误";
                return JsonResponseTool.failure(msg);
            } else {
                info = userService.queryUser(null, mobile, null);
                if (info != null) {
                    msg += "手机号已存在;";
                }
            }
        }
        if (email != null && !"".equals(email)) {
            if (!FormatValidateTool.checkEmail(email)) {
                msg = "邮箱格式错误";
                return JsonResponseTool.failure(msg);
            } else {
                info = userService.queryUser(null, null, email);
                if (info != null) {
                    msg += "邮箱已存在;";
                }
            }
        }
        if ("".equals(msg)) {
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure(msg);
        }
    }

    /**
     * @api {POST} http://{url}/auth/sendEmaiCode 发送邮箱邮件验证码
     * @apiName sendEmaiCode
     * @apiGroup Auth
     * @apiParam {string} email 邮箱
     * @apiParam {int} mailType 邮箱验证码类型（101重置密码）
     */
    @RequestMapping(value = "/sendEmaiCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse sendEmaiCode(@RequestParam String email, @RequestParam Integer mailType) {
        //发送邮箱验证码
        if (!FormatValidateTool.checkEmail(email)) {
            return JsonResponseTool.failure("邮箱无效");
        }
        ServiceResult<String> serviceResult = captchaService.createMailCode(email, mailType);
        if (serviceResult.getFlag()) {
            String mailCode = serviceResult.getData();
            MailUtil mailUtil = new MailUtil();
            boolean flag = false;
            if (mailType == MailEnum.RESET_PAWW.getValue().intValue()) {//重置密码邮件验证码
                flag = true;
            }
            if (flag) {
                mailUtil.sendMail(mailType, email, mailCode);
            }
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("验证码生成失败");
        }
    }

    /**
     * @api {POST} http://{url}/auth/validMailCode 验证邮箱邮件验证码
     * @apiName validMailCode
     * @apiGroup Auth
     * @apiParam {string} email 邮箱
     * @apiParam {string} mailCode 邮件验证码
     * @apiParam {int} mailType 验证码类型（101重置密码）
     */
    @RequestMapping(value = "/validMailCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse validMailCode(@RequestParam String email, @RequestParam String mailCode, @RequestParam Integer mailType) {
        if (!FormatValidateTool.checkEmail(email)) {
            return JsonResponseTool.paramErr("邮箱无效");
        }
        ServiceResult result = captchaService.validMailCaptcha(email, mailCode, mailType);
        if (result.getFlag()) {
            ServiceResult<String> resultTwo = captchaService.createMailCode(email, mailType);
            return JsonResponseTool.success(resultTwo.getData());
        } else {
            return JsonResponseTool.failure(result.getMessage());
        }
    }

    /**
     * @api {POST} http://{url}/auth/verifyImgCode 验证图片验证码
     * @apiName verifyImgCode
     * @apiGroup Auth
     * @apiParam {string} code 验证码
     * @apiParam {string} captchaKey 图片验证码的key
     */
    @RequestMapping(value = "/verifyImgCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse verifyImgCode(@RequestParam String captchaKey, @RequestParam String code) {
        ServiceResult result = captchaService.validImgCaptcha(captchaKey, code);
        if (result.getFlag()) {
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure(result.getMessage());
        }
    }

    /**
     * @api {POST} http://{url}/auth/verifyEmailAndCode 找回密码，邮箱邮件验证码验证
     * @apiName verifyEmailAndCode
     * @apiGroup Auth
     * @apiParam {string} email 邮箱
     * @apiParam {string} mailCode 邮件验证码
     * @apiParam {string} code 验证码
     * @apiParam {string} captchaKey 图片验证码的key
     */
    @RequestMapping(value = "/verifyEmailAndCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse verifyEmailAndCode(@RequestParam String email, @RequestParam String mailCode, @RequestParam String captchaKey, @RequestParam String code) {
        if (!FormatValidateTool.checkEmail(email)) {
            return JsonResponseTool.failure("邮箱无效");
        }
        ServiceResult result = captchaService.validImgCaptcha(captchaKey, code);
        if (result.getFlag()) {
            return validMailCode(email, mailCode, MailEnum.RESET_PAWW.getValue());
        } else {
            return JsonResponseTool.failure("图片验证码输入错误");
        }
    }

    /**
     * @api {POST} http://{url}/auth/resetMail 邮箱重置密码
     * @apiName resetMail
     * @apiGroup Auth
     * @apiParam {string} email 邮箱
     * @apiParam {string} mailCode 邮件验证码
     * @apiParam {string} pwd 密码
     */
    @RequestMapping(value = "/resetMail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse resetMail(@RequestParam String email, @RequestParam String mailCode, @RequestParam String pwd) throws Exception {
        if (!FormatValidateTool.checkEmail(email)) {
            return JsonResponseTool.failure("邮箱无效");
        }
        ServiceResult result = captchaService.validMailCaptcha(email, mailCode, MailEnum.RESET_PAWW.getValue());
        if (!result.getFlag()) {
            return JsonResponseTool.failure(result.getMessage());
        }
        List<TUserInfo> infos = tUserInfoMapper.verifyUser(null, null, email);
        if (infos != null && infos.size() > 0) {
            //修改密码
            TUserInfo info = infos.get(0);
            info.setPassword(SignatureTool.md5Encode(SignatureTool.md5Encode(pwd, "utf-8") + info.getSalt(), "utf-8"));
            tUserInfoMapper.updateByPrimaryKeySelective(info);
            //登录
            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(info.getId(), null, null, null, pwd);
            if (!userServiceResult.getFlag()) {
                return JsonResponseTool.authFailure(userServiceResult.getMessage());
            }
            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    userServiceResult.getData().getUserId(),
                    userServiceResult.getData().getUserTypes(),
                    userServiceResult.getData().getRoleIds(),
                    userServiceResult.getData().getIsCertifieds(),
                    userServiceResult.getData().getStatus(),
                    verifyUserStep(userServiceResult)
            ));
        } else {
            return JsonResponseTool.failure("重置密码失败，邮箱填写错误");
        }
    }

    /**
     * @api {POST} http://{url}/auth/updatePaw 根据原密码进行修改密码
     * @apiName updatePaw
     * @apiGroup Auth
     * @apiParam {string} oldPaw 旧密码
     * @apiParam {string} newPaw 新密码
     */
    @RequestMapping(value = "/updatePaw", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updatePaw(@RequestParam String oldPaw, @RequestParam String newPaw) throws Exception {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        TUserInfo info = tUserInfoMapper.selectByPrimaryKey(userId);
        if (info == null) {
            return JsonResponseTool.failure("用户不存在，请重新登入");
        }
        if (!SignatureTool.md5Encode(SignatureTool.md5Encode(oldPaw, "utf-8") + info.getSalt(), "utf-8").equals(info.getPassword())) {
            return JsonResponseTool.failure("原密码填写错误，请重新填写");
        }
        info.setPassword(SignatureTool.md5Encode(SignatureTool.md5Encode(newPaw, "utf-8") + info.getSalt(), "utf-8"));
        tUserInfoMapper.updateByPrimaryKeySelective(info);
        //登录
        ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(info.getId(), null, null, null, newPaw);
        if (!userServiceResult.getFlag()) {
            return JsonResponseTool.authFailure(userServiceResult.getMessage());
        }
        return JsonResponseTool.success(ProtocolTool.createUserHeader(
                userServiceResult.getData().getUserId(),
                userServiceResult.getData().getUserTypes(),
                userServiceResult.getData().getRoleIds(),
                userServiceResult.getData().getIsCertifieds(),
                userServiceResult.getData().getStatus(),
                verifyUserStep(userServiceResult)
        ));
    }

    /**
     * @api {POST} http://{url}/auth/resetMobile 根据邮箱进行手机验证码重置密码
     * @apiName resetMobile
     * @apiGroup Auth
     * @apiParam {string} email 邮箱
     * @apiParam {string} smsCode 手机验证码
     * @apiParam {string} pwd 密码
     */
    @RequestMapping(value = "/resetMobile", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse resetMobile(@RequestParam String email, @RequestParam String smsCode, @RequestParam String pwd) throws Exception {
        if (!FormatValidateTool.checkEmail(email)) {
            return JsonResponseTool.failure("邮箱无效");
        }
        List<TUserInfo> infos = tUserInfoMapper.verifyUser(null, null, email);
        if (infos != null && infos.size() > 0) {
            //修改密码
            TUserInfo info = infos.get(0);
            ServiceResult result = captchaService.validSmsCaptcha(info.getMobile(), smsCode, SmsEnum.RESET_PAWW.getValue());
            if (!result.getFlag()) {
                return JsonResponseTool.failure(result.getMessage());
            }
            info.setPassword(SignatureTool.md5Encode(SignatureTool.md5Encode(pwd, "utf-8") + info.getSalt(), "utf-8"));
            tUserInfoMapper.updateByPrimaryKeySelective(info);
            //登录
            ServiceResult<UserDTO> userServiceResult = this.userService.userLogin(info.getId(), null, null, null, pwd);
            if (!userServiceResult.getFlag()) {
                return JsonResponseTool.authFailure(userServiceResult.getMessage());
            }
            return JsonResponseTool.success(ProtocolTool.createUserHeader(
                    userServiceResult.getData().getUserId(),
                    userServiceResult.getData().getUserTypes(),
                    userServiceResult.getData().getRoleIds(),
                    userServiceResult.getData().getIsCertifieds(),
                    userServiceResult.getData().getStatus(),
                    verifyUserStep(userServiceResult)
            ));
        } else {
            return JsonResponseTool.failure("重置密码失败，邮箱填写错误");
        }
    }

    /**
     * @api {POST} http://{url}/auth/sendSmsByCode 发送手机短信验证吗
     * @apiName sendSmsByCode
     * @apiGroup Auth
     * @apiParam {string} mobile 手机号
     * @apiParam {int} smsType 验证码类型（101注册验证码。138找回密码）
     */
    @RequestMapping(value = "/sendSmsByCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse sendSmsByCode(@RequestParam String mobile, @RequestParam Integer smsType) {
        if (!FormatValidateTool.checkMobile(mobile)) {
            return JsonResponseTool.failure("手机号无效");
        }
        ServiceResult<String> result = captchaService.createSmsCode(mobile, smsType);
        if (result.getFlag()) {
            String code = result.getData();//验证码
            SmsUtil smsUtil = new SmsUtil();
            boolean flag = false;
            if (smsType == SmsEnum.REGISTER.getValue().intValue()) {//注册短信验证码
                flag = true;
            } else if (smsType == SmsEnum.RESET_PAWW.getValue().intValue()) {//找回密码，重置密码短信
                flag = true;
            }
            if (flag) {
                smsUtil.sendSms(smsType, mobile, code);
            }
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("验证码生成失败");
        }
    }

    /**
     * @api {POST} http://{url}/auth/validSmsCode 验证手机短信验证吗
     * @apiName validSmsCode
     * @apiGroup Auth
     * @apiParam {string} mobile 手机号
     * @apiParam {string} smsCode 手机验证码
     * @apiParam {int} smsType 验证码类型（101注册验证码。138找回密码）
     */
    @RequestMapping(value = "/validSmsCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse validSmsCode(@RequestParam String mobile, @RequestParam String smsCode, @RequestParam Integer smsType) {
        if (!FormatValidateTool.checkMobile(mobile)) {
            return JsonResponseTool.paramErr("手机号无效");
        }
        ServiceResult result = captchaService.validSmsCaptcha(mobile, smsCode, smsType);
        if (result.getFlag()) {
            ServiceResult<String> resultTwo = captchaService.createSmsCode(mobile, smsType);
            return JsonResponseTool.success(resultTwo.getData());
        } else {
            return JsonResponseTool.failure(result.getMessage());
        }
    }

    /**
     * @api {POST} http://{url}/auth/sendSmsCodeByEmail 根据邮箱发送手机验证码进行重置密码操作
     * @apiName sendSmsCodeByEmail
     * @apiGroup Auth
     * @apiParam {string} email 邮箱
     */
    @RequestMapping(value = "/sendSmsCodeByEmail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse sendSmsCodeByEmail(@RequestParam String email) {
        if (!FormatValidateTool.checkEmail(email)) {
            return JsonResponseTool.failure("邮箱无效");
        }
        List<TUserInfo> infos = tUserInfoMapper.verifyUser(null, null, email);
        if (infos != null && infos.size() > 0) {
            TUserInfo info = infos.get(0);
            return sendSmsByCode(info.getMobile(), SmsEnum.RESET_PAWW.getValue());
        } else {
            return JsonResponseTool.failure("邮箱帐号有误");
        }
    }

    /**
     * @api {POST} http://{url}/auth/validSmsCodeByEmail 根据邮箱获取手机号，并手机验证码进行验证
     * @apiName validSmsCodeByEmail
     * @apiGroup Auth
     * @apiParam {string} email 邮箱
     * @apiParam {string} smsCode 手机验证码
     */
    @RequestMapping(value = "/validSmsCodeByEmail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse validSmsCodeByEmail(@RequestParam String email, @RequestParam String smsCode) {
        if (!FormatValidateTool.checkEmail(email)) {
            return JsonResponseTool.failure("邮箱无效");
        }
        List<TUserInfo> infos = tUserInfoMapper.verifyUser(null, null, email);
        if (infos != null && infos.size() > 0) {
            TUserInfo info = infos.get(0);
            return validSmsCode(info.getMobile(), smsCode, SmsEnum.RESET_PAWW.getValue());
        } else {
            return JsonResponseTool.failure("邮箱帐号有误");
        }
    }


}
