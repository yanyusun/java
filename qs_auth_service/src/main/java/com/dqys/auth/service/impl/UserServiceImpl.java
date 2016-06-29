package com.dqys.auth.service.impl;

import com.dqys.auth.orm.query.TUserQuery;
import com.dqys.auth.service.facade.UserService;
import com.dqys.auth.service.constant.MailVerifyTypeEnum;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.utils.NoSQLWithRedisTool;
import com.dqys.core.utils.RabbitMQProducerTool;
import com.dqys.core.utils.SignatureTool;
import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.auth.service.utils.UserUtils;
import com.dqys.core.utils.SysPropertyTool;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.util.List;
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

    @Override
    public ServiceResult<Integer> validateUser(String account, String mobile, String email) throws Exception {
        TUserInfo tUserInfo = this.queryUser(account, mobile, email);
        if(null == tUserInfo) {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

        return ServiceResult.success(tUserInfo.getId());
    }

    @Override
    public ServiceResult<UserDTO> userRegister_tx(String account, String mobile, String email, String pwd) throws Exception {
        TUserInfo tUserInfo = this.queryUser(account, mobile, email);
        if(null != tUserInfo) {
            return ServiceResult.failure("用户已注册", ObjectUtils.NULL);
        }
        tUserInfo = this.addUser(account, mobile, email, pwd);
        if(null == tUserInfo) {
            return ServiceResult.failure("新增用户失败", ObjectUtils.NULL);
        }

        TUserTag tUserTag = this.addTag(tUserInfo.getId());
        if(null == tUserTag) {
            throw new UnexpectedRollbackException("新增用户失败");
        }

        return ServiceResult.success(UserUtils.toUserDTO(tUserInfo, tUserTag));
    }

    @Override
    public ServiceResult<UserDTO> userLogin(Integer uid, String userName, String mobile, String email, String pwd) throws Exception {
        TUserInfo tUserInfo;
        if(null != uid) {
            tUserInfo = this.queryUser(uid);
        } else {
            tUserInfo = this.queryUser(userName, mobile, email);
        }
        if(null == tUserInfo) {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

        if(!tUserInfo.getPassword().equals(this.encodePassword(pwd, tUserInfo.getSalt()))) {
            return ServiceResult.failure("密码错误", ObjectUtils.NULL);
        }

        List<TUserTag> tUserTags = this.tUserTagMapper.selectByUserId(tUserInfo.getId());
        if(null == tUserTags) {
            return ServiceResult.failure("用户数据异常", ObjectUtils.NULL);
        }

        return ServiceResult.success(UserUtils.toUserDTO(tUserInfo, tUserTags));
    }

    @Override
    public ServiceResult userReset(Integer uid, String userName, String email, String mobile, String pwd) throws Exception {
        TUserInfo tUserInfo;
        if(null != uid) {
            tUserInfo = this.queryUser(uid);
        } else {
            tUserInfo = this.queryUser(userName, email, mobile);
        }
        if(null == tUserInfo) {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

        tUserInfo.setPassword(encodePassword(pwd, tUserInfo.getSalt()));
        Integer count = this.tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo);
        if(count.equals(0)) {
            return ServiceResult.failure("重置失败", ObjectUtils.NULL);
        }

        return ServiceResult.success(ObjectUtils.NULL);
    }

    @Override
    public void sendConfirmMail(MailVerifyTypeEnum e, Integer uid) {
        TUserInfo tUserInfo = this.tUserInfoMapper.selectByPrimaryKey(uid);
        if(null != tUserInfo && StringUtils.isNotBlank(tUserInfo.getEmail())) {
            String code = RandomStringUtils.randomAlphanumeric(20) + e.getValue();
            //NoSQLWithRedisTool.sendMailToChannel(tUserInfo.getEmail(), code);     //redis


            RabbitMQProducerTool.addToMailSendQueue(tUserInfo.getEmail(), code);
            NoSQLWithRedisTool.setValueObject(MAIL_CONFIRM_KEY + tUserInfo.getEmail(), code, 1, TimeUnit.DAYS);
        }
    }

    @Override
    public ServiceResult confirmMail(MailVerifyTypeEnum e, Integer uid, String confirmKey, String pwd) throws Exception {
        TUserInfo tUserInfo = this.queryUser(uid);
        if(null == tUserInfo || StringUtils.isBlank(tUserInfo.getEmail())) {
            return ServiceResult.failure("无效验证", ObjectUtils.NULL);
        }

        String code = NoSQLWithRedisTool.getValueObject(MAIL_CONFIRM_KEY + tUserInfo.getEmail());
        if(!confirmKey.equals(code)) {
            return ServiceResult.failure("验证码无效", ObjectUtils.NULL);
        }
        //最后一位操作类型校验码
        if(!code.endsWith(e.getValue().toString())) {
            return ServiceResult.failure("验证码无效", ObjectUtils.NULL);
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
        if(!count.equals(1)) {
            return ServiceResult.failure("验证失败", ObjectUtils.NULL);
        }

        NoSQLWithRedisTool.removeValueObject(MAIL_CONFIRM_KEY + tUserInfo.getEmail());

        return ServiceResult.success(ObjectUtils.NULL);
    }

    @Override
    public ServiceResult<TUserInfo> queryUserById(Integer uid) {
        TUserInfo tUserInfo = this.tUserInfoMapper.selectByPrimaryKey(uid);
        if(null == tUserInfo) {
            return ServiceResult.failure("用户不存在", ObjectUtils.NULL);
        }

        return ServiceResult.success(tUserInfo);
    }

    @Override
    public ServiceResult<TUserInfo> registerAdmin_tx(Integer userType, TUserInfo tUserInfo) {
        Integer count = this.tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo);
        if(!count.equals(1)) {
            return ServiceResult.failure("更新用户信息失败", ObjectUtils.NULL);
        }

        TUserQuery query = new TUserQuery();
        query.setUserId(tUserInfo.getId());
        query.setUserType(userType.byteValue());
        List<TUserTag> tUserTags = this.tUserTagMapper.selectByQuery(query);
        if(null == tUserTags || tUserTags.isEmpty()) {
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

        if(1 != count) {
            return ServiceResult.failure("注册运营者信息失败", ObjectUtils.NULL);
        }

        return ServiceResult.success(tUserInfo);
    }

    /* 验证用户存在性 */
    private TUserInfo queryUser(String account, String mobile, String email) throws Exception {
        List<TUserInfo> tUserInfos = this.tUserInfoMapper.verifyUser(account, mobile, email);
        if(null == tUserInfos || tUserInfos.isEmpty()) {
            return null;
        }

        return tUserInfos.get(0);
    }
    private TUserInfo queryUser(Integer uid) {
        return this.tUserInfoMapper.selectByPrimaryKey(uid);
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
        if(count.equals(1)) {
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

        int count =this.tUserTagMapper.insertSelective(tUserTag);
        if(1 == count) {
            return tUserTag;
        }

        return null;
    }
}
