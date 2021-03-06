package com.dqys.auth.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TMessageMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.*;
import com.dqys.auth.orm.query.TUserTagQuery;
import com.dqys.auth.service.constant.MailVerifyTypeEnum;
import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.service.facade.UserService;
import com.dqys.auth.service.utils.MessageUtils;
import com.dqys.auth.service.utils.UserUtils;
import com.dqys.core.constant.*;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.utils.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author by pan on 16-4-6.
 */
@Service
@Primary
public class UserServiceImpl implements UserService {

    private static final String MAIL_CONFIRM_KEY = "mail_confirm_";

    @Autowired
    private TUserInfoMapper tUserInfoMapper;

    @Autowired
    private TUserTagMapper tUserTagMapper;

    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;
    @Autowired
    private TMessageMapper messageMapper;


    @Override
    public ServiceResult<Integer> validateUser(String account, String mobile, String email) throws Exception {
        TUserInfo tUserInfo = this.queryUser(account, mobile, email);
        if (null == tUserInfo) {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

        return ServiceResult.success(tUserInfo.getId());
    }

    @Override
    public ServiceResult<Integer> validateUser(String account, String mobile, String email, Integer userType) throws Exception {
        List<TUserInfo> infos = tUserInfoMapper.verifyUser2(account, mobile, email, userType);
        if (infos != null && infos.size() > 0) {
            return ServiceResult.success(infos.get(0).getId());
        } else {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

    }

    @Override
    public ServiceResult<UserDTO> userRegister_tx(String account, String mobile, String email, String pwd) throws Exception {
        TUserInfo tUserInfo = this.queryUser(account, mobile, email);
        if (null != tUserInfo) {
            return ServiceResult.failure("用户已注册", ObjectUtils.NULL);
        }
        tUserInfo = this.addUser(account, mobile, email, pwd);
        if (null == tUserInfo) {
            return ServiceResult.failure("新增用户失败", ObjectUtils.NULL);
        }

        TUserTag tUserTag = this.addTag(tUserInfo.getId());
        if (null == tUserTag) {
            throw new UnexpectedRollbackException("新增用户失败");
        }

        return ServiceResult.success(UserUtils.toUserDTO(tUserInfo, tUserTag));
    }

    @Override
    public ServiceResult<UserDTO> userLogin(Integer uid, String userName, String mobile, String email, String pwd) throws Exception {
        TUserInfo tUserInfo;
        if (null != uid) {
            tUserInfo = this.queryUser(uid);
        } else {
            tUserInfo = this.queryUser(userName, mobile, email);
        }
        return getUserDTOServiceResult(pwd, tUserInfo);
    }

    @Override
    public ServiceResult<UserDTO> userLogin(Integer uid, String userName, String mobile, String email, String pwd, Integer userType) throws Exception {
        TUserInfo tUserInfo;
        if (null != uid) {
            tUserInfo = this.queryUser(uid);
        } else {
            tUserInfo = this.queryUser(userName, mobile, email, userType);
        }
        return getUserDTOServiceResult(pwd, tUserInfo);
    }

    private ServiceResult<UserDTO> getUserDTOServiceResult(String pwd, TUserInfo tUserInfo) throws Exception {
        if (null == tUserInfo) {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

        if (!tUserInfo.getPassword().equals(this.encodePassword(pwd, tUserInfo.getSalt()))) {
            return ServiceResult.failure("密码错误", ObjectUtils.NULL);
        }
        if (tUserInfo.getUseStatus() != null && tUserInfo.getUseStatus() > 0) {
            return ServiceResult.failure("该帐号已被禁止登入，请联系管理员", ObjectUtils.NULL);
        }
        List<TUserTag> tUserTags = this.tUserTagMapper.selectByUserId(tUserInfo.getId());
        if (null == tUserTags) {
            return ServiceResult.failure("用户数据异常", ObjectUtils.NULL);
        }

        return ServiceResult.success(UserUtils.toUserDTO(tUserInfo, tUserTags));
    }

    @Override
    public ServiceResult userReset(Integer uid, String userName, String email, String mobile, String pwd) throws Exception {
        TUserInfo tUserInfo;
        if (null != uid) {
            tUserInfo = this.queryUser(uid);
        } else {
            tUserInfo = this.queryUser(userName, email, mobile);
        }
        if (null == tUserInfo) {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

        tUserInfo.setPassword(encodePassword(pwd, tUserInfo.getSalt()));
        Integer count = this.tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo);
        if (count.equals(0)) {
            return ServiceResult.failure("重置失败", ObjectUtils.NULL);
        }

        return ServiceResult.success(ObjectUtils.NULL);
    }

    @Override
    public void sendConfirmMail(MailVerifyTypeEnum e, Integer uid) {
        TUserInfo tUserInfo = this.tUserInfoMapper.selectByPrimaryKey(uid);
        if (null != tUserInfo && StringUtils.isNotBlank(tUserInfo.getEmail())) {
            String code = RandomStringUtils.randomAlphanumeric(20) + e.getValue();
            //NoSQLWithRedisTool.sendMailToChannel(tUserInfo.getEmail(), code);     //redis
            MailUtil mailUtil = new MailUtil();
            mailUtil.sendMail(MailEnum.ACTIVATE.getValue(), tUserInfo.getEmail(), tUserInfo.getEmail(), MqClientEnum.EMAIL_URL.getName() + code);
            NoSQLWithRedisTool.setValueObject(MAIL_CONFIRM_KEY + tUserInfo.getEmail(), code, 2, TimeUnit.DAYS);
        }
    }

    @Override
    public ServiceResult confirmMail(MailVerifyTypeEnum e, Integer uid, String confirmKey, String pwd) throws Exception {
        TUserInfo tUserInfo = this.queryUser(uid);
        if (null == tUserInfo || StringUtils.isBlank(tUserInfo.getEmail())) {
            return ServiceResult.failure("无效验证", ObjectUtils.NULL);
        }

        String code = NoSQLWithRedisTool.getValueObject(MAIL_CONFIRM_KEY + tUserInfo.getEmail());
        if (!confirmKey.equals(code)) {
            return ServiceResult.failure("验证码无效，请重新发送验证邮件。", ObjectUtils.NULL);
        }
        //最后一位操作类型校验码
        if (!code.endsWith(e.getValue().toString())) {
            return ServiceResult.failure("验证码无效，请重新发送验证邮件。", ObjectUtils.NULL);
        }

        switch (e) {
            case EMAIL_CONFIRM:
                tUserInfo.setStatus(NumberUtils.INTEGER_ONE);
                break;

            case USER_RESET:
                tUserInfo.setPassword(encodePassword(pwd, tUserInfo.getSalt()));
                break;
        }

        //更新用户信息
        Integer count = this.tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo);
        if (!count.equals(1)) {
            return ServiceResult.failure("验证失败,请稍后重试", ObjectUtils.NULL);
        }

        NoSQLWithRedisTool.removeValueObject(MAIL_CONFIRM_KEY + tUserInfo.getEmail());
        TUserTag tUserTag = tUserTagMapper.selectByUserId(uid).get(0);//更新头部信息
        return ServiceResult.success(UserUtils.toUserDTO(tUserInfo, tUserTag));
    }

    @Override
    public ServiceResult<TUserInfo> queryUserById(Integer uid) {
        TUserInfo tUserInfo = this.tUserInfoMapper.selectByPrimaryKey(uid);
        if (null == tUserInfo) {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

        return ServiceResult.success(tUserInfo);
    }

    @Override
    public ServiceResult<TUserInfo> registerAdmin_tx(Integer userType, TUserInfo tUserInfo) {
        Integer count = this.tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo);
        if (!count.equals(1)) {
            return ServiceResult.failure("更新用户信息失败", ObjectUtils.NULL);
        }

        TUserTagQuery query = new TUserTagQuery();
        query.setUserId(tUserInfo.getId());
        // TODO: 16-8-29 按照目前的设计一个人员只拥有一种角色,因此暂时注释掉userType
        //query.setUserType(userType);
        List<TUserTag> tUserTags = this.tUserTagMapper.selectByQuery(query);
        if (null == tUserTags || tUserTags.isEmpty()) {
            //add
            TUserTag tUserTag = new TUserTag();
            tUserTag.setUserId(tUserInfo.getId());
            tUserTag.setRoleId(NumberUtils.toByte(SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_ADMINISTRATOR_KEY).getPropertyValue()));
            tUserTag.setUserType(userType.byteValue());
            tUserTag.setIsCertified(Boolean.FALSE);
            count = this.tUserTagMapper.insertSelective(tUserTag);
        } else {
            TUserTag tUserTag = tUserTags.get(0);
            tUserTag.setRoleId(NumberUtils.toByte(SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_ADMINISTRATOR_KEY).getPropertyValue()));
            tUserTag.setUserType(userType.byteValue());
            count = this.tUserTagMapper.updateByPrimaryKeySelective(tUserTag);
        }

        if (1 != count) {
            return ServiceResult.failure("注册运营者信息失败", ObjectUtils.NULL);
        }

        return ServiceResult.success(tUserInfo);
    }

    @Override
    public JsonResponse registerStep5(Integer userId, String name, String introduction, Integer province, Integer city, Integer district) {
        TUserInfo userInfo = queryUser(userId);//注册用户
        if (userInfo == null) {
            return JsonResponseTool.paramErr("用户信息错误，请重新登录");
        }
        if (userInfo.getCompanyId() == null) {
            return JsonResponseTool.failure("用户信息错误，请关联公司信息");
        } else {
            TCompanyInfo companyInfo = queryCompany(userInfo.getCompanyId());
            if (companyInfo == null) {
                return JsonResponseTool.failure("公司不存在，请返回上一步完善公司信息");
            }
            Integer count = 0;
            // 修改用户信息
//            userInfo.setUserName(name);
//            count = this.tUserInfoMapper.updateByPrimaryKeySelective(userInfo);
//            if (!count.equals(1)) {
//                return JsonResponseTool.failure("更新用户信息失败");
//            }
            // 修改公司信息
            companyInfo.setProvince(province);
            companyInfo.setCity(city);
            companyInfo.setArea(district);
            companyInfo.setRemark(introduction);
            companyInfo.setCompanyAccount(name);
            count = this.tCompanyInfoMapper.updateByPrimaryKeySelective(companyInfo);
            if (!count.equals(1)) {
                return JsonResponseTool.failure("更新公司信息失败");
            }
            //发送消息给平台管理员请求认证
            TUserTag tag = getAdmin();
            if (tag != null) {
                TUserInfo info = tUserInfoMapper.selectByPrimaryKey(tag.getUserId());//平台管理员
                SmsUtil smsUtil = new SmsUtil();
                String content = smsUtil.sendSms(SmsEnum.REGISTER_AUDIT.getValue(), info.getMobile(),
                        info.getRealName(),
                        userInfo.getRealName() == null ? "" : userInfo.getRealName(),
                        userInfo.getMobile(),
                        getCompayTypeToString(userInfo.getId()),
                        name,
                        companyInfo.getCompanyName() == null ? "" : companyInfo.getCompanyName(),
                        companyInfo.getCredential());
                String operUrl = MessageUtils.setOperUrl("/api/user/registerAudit?status=1&userId=" + userId, null,
                        "/api/user/registerAudit?status=2&userId=" + userId, null, null);
                Message message = new Message("注册请求认证帐号:" + userInfo.getEmail(), content, userId, info.getId(), null, null, MessageBTEnum.register.getValue(), null, 0, operUrl, 0);
                messageMapper.add(message);
            }
        }
        Map map = new HashMap<>();
        map.put("realName", userInfo.getRealName());
        return JsonResponseTool.success(map);
    }

    /* 验证用户存在性 */
    @Override
    public TUserInfo queryUser(String account, String mobile, String email) throws Exception {
        List<TUserInfo> tUserInfos = this.tUserInfoMapper.verifyUser(account, mobile, email);
        if (null == tUserInfos || tUserInfos.isEmpty()) {
            return null;
        }
        return tUserInfos.get(0);
    }

    /* 验证用户存在性 */
    @Override
    public TUserInfo queryUser(String account, String mobile, String email, Integer userType) throws Exception {
        List<TUserInfo> tUserInfos = this.tUserInfoMapper.verifyUser2(account, mobile, email, userType);
        if (null == tUserInfos || tUserInfos.isEmpty()) {
            return null;
        }

        return tUserInfos.get(0);
    }

    private TUserInfo queryUser(Integer uid) {
        return this.tUserInfoMapper.selectByPrimaryKey(uid);
    }

    private TCompanyInfo queryCompany(Integer companyId) {
        return this.tCompanyInfoMapper.selectByPrimaryKey(companyId);
    }

    /* 添加用户 */
    private TUserInfo addUser(String account, String mobile, String email, String pwd) throws Exception {
        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setAccount(account);
        tUserInfo.setUserName(account);
        tUserInfo.setMobile(mobile);
        tUserInfo.setEmail(email);
        tUserInfo.setSalt(RandomStringUtils.randomAlphabetic(6));
        tUserInfo.setPassword(this.encodePassword(pwd, tUserInfo.getSalt()));
        tUserInfo.setStatus(NumberUtils.INTEGER_ZERO);
        Integer count = this.tUserInfoMapper.insertSelective(tUserInfo);
        if (count.equals(1)) {
            return tUserInfo;
        }

        return null;
    }

    /* 编码密码 */
    private String encodePassword(String pwd, String salt) throws Exception {
        return SignatureTool.md5Encode(SignatureTool.md5Encode(pwd, "utf-8") + salt, "utf-8");
    }

    /* 添加用户标签 */
    private TUserTag addTag(int userId) {
        TUserTag tUserTag = new TUserTag();
        tUserTag.setUserId(userId);
        tUserTag.setUserType((byte) 0);
        tUserTag.setRoleId((byte) 0);
        tUserTag.setIsCertified(false);

        int count = this.tUserTagMapper.insertSelective(tUserTag);
        if (1 == count) {
            return tUserTag;
        }

        return null;
    }

    private TUserTag getAdmin() {
        TUserTagQuery tUserTagQuery = new TUserTagQuery();
        tUserTagQuery.setUserType(1);
        tUserTagQuery.setRole(RoleTypeEnum.ADMIN.getValue());
        List<TUserTag> list = tUserTagMapper.selectByQuery(tUserTagQuery);//查询平台管理员
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    //人员角色
    @Override
    public String getRoleNameToString(Integer userId) {
        UserDetail detail1 = tUserInfoMapper.getUserDetail(userId);
        String roleName = "所属人";
        if (detail1.getRold() == RoleTypeEnum.ADMIN.getValue().intValue()) {
            roleName = RoleTypeEnum.ADMIN.getName();
        } else if (detail1.getRold() == RoleTypeEnum.REGULATOR.getValue().intValue()) {
            roleName = RoleTypeEnum.REGULATOR.getName();
        }
        return roleName;
    }

    //参与方类型
    @Override
    public String getCompayTypeToString(Integer userId) {
        UserDetail detail = tUserInfoMapper.getUserDetail(userId);
        if (detail.getUserType() == UserInfoEnum.USER_TYPE_ADMIN.getValue().intValue()) {
            return UserInfoEnum.USER_TYPE_ADMIN.getName();
        } else if (detail.getUserType() == UserInfoEnum.USER_TYPE_ENTRUST.getValue().intValue()) {
            return UserInfoEnum.USER_TYPE_ENTRUST.getName();
        } else if (detail.getUserType() == UserInfoEnum.USER_TYPE_COLLECTION.getValue().intValue()) {
            return UserInfoEnum.USER_TYPE_COLLECTION.getName();
        } else if (detail.getUserType() == UserInfoEnum.USER_TYPE_JUDICIARY.getValue().intValue()) {
            return UserInfoEnum.USER_TYPE_JUDICIARY.getName();
        } else if (detail.getUserType() == UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().intValue()) {
            return UserInfoEnum.USER_TYPE_INTERMEDIARY.getName();
        }
        return "";
    }
}
