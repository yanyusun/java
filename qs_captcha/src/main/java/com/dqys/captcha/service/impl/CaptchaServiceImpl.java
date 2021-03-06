package com.dqys.captcha.service.impl;

import com.dqys.captcha.service.facade.CaptchaService;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.utils.NoSQLWithRedisTool;
import com.dqys.core.utils.SysPropertyTool;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @author by pan on 16-4-6.
 */
@Service
@Primary
public class CaptchaServiceImpl implements CaptchaService {

    private static final String CAPTCHA_KEY = "captcha_";
    private static final String SMSCODE_KEY = "smscode_";
    private static final String MAILCODE_KEY = "mailcode_";
    private static final String SUB_CODE = "code";
    private static final String SUB_FRE = "fre";
    private static final String SUB_RETRY = "retry";
    private static final int KEY_EXPIRE = 600;

    @Autowired(required = false)
    private DefaultKaptcha captchaProducer;


    @Override
    public ServiceResult validImgCaptcha(String key, String capText) {
        if (this.validCaptcha(CAPTCHA_KEY + key, capText.toLowerCase())) {
            return ServiceResult.success(ObjectUtils.NULL);
        }
        return ServiceResult.failure("验证码错误", ObjectUtils.NULL);
    }

    @Override
    public ServiceResult validSmsCaptcha(String mobile, String smsCode) {
        Object o = valCaptchaRetry(SMSCODE_KEY + mobile);
        if (o instanceof String) {
            return ServiceResult.failure((String) o, ObjectUtils.NULL);
        }

        if (this.validCaptcha(SMSCODE_KEY + mobile, smsCode)) {
            return ServiceResult.success(ObjectUtils.NULL);
        }

        NoSQLWithRedisTool.setHashObject(SMSCODE_KEY + mobile, SUB_RETRY, ((Integer) o) + 1);

        return ServiceResult.failure("验证码错误", ObjectUtils.NULL);
    }

    @Override
    public ServiceResult<BufferedImage> genImgCaptcha(String key) {
        String code = this.captchaProducer.createText();

        Integer fre = NoSQLWithRedisTool.getHashObject(CAPTCHA_KEY + key, SUB_FRE);
        if (null != fre && fre >= Integer.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_CAPTCHA_FRE_LIMIT).getPropertyValue())) {
            return ServiceResult.failure("发送太频繁,请稍后再试", ObjectUtils.NULL);
        } else if (null == fre) {
            fre = 0;
        }

        setCaptcha(CAPTCHA_KEY + key, code, fre++);

        return ServiceResult.success(this.captchaProducer.createImage(code));
    }

    @Override
    public ServiceResult<String> genSmsCaptcha(String mobile) {
        String code = RandomStringUtils.randomNumeric(6);

        Integer fre = NoSQLWithRedisTool.getHashObject(CAPTCHA_KEY + mobile, SUB_FRE);
        if (null != fre && fre >= Integer.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_SMS_FRE_LIMIT).getPropertyValue())) {
            return ServiceResult.failure("发送太频繁,请稍后再试", ObjectUtils.NULL);
        } else if (null == fre) {
            fre = 0;
        }

        setCaptcha(SMSCODE_KEY + mobile, code, fre++);

        return ServiceResult.success(code);
    }

    @Override
    public ServiceResult<String> createSmsCode(String mobile, Integer smsType) {
        String code = RandomStringUtils.randomNumeric(6);

        Integer fre = NoSQLWithRedisTool.getHashObject(SMSCODE_KEY + mobile + smsType, SUB_FRE);
        if (null != fre && fre >= Integer.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_SMS_FRE_LIMIT).getPropertyValue())) {
            return ServiceResult.failure("发送太频繁,请稍后再试", ObjectUtils.NULL);
        } else if (null == fre) {
            fre = 0;
        }

        setCaptcha(SMSCODE_KEY + mobile + smsType, code, fre++);

        return ServiceResult.success(code);
    }

    @Override
    public ServiceResult validSmsCaptcha(String mobile, String smsCode, Integer smsType) {
        Object o = valCaptchaRetry(SMSCODE_KEY + mobile + smsType);
        if (o instanceof String) {
            return ServiceResult.failure((String) o, ObjectUtils.NULL);
        }

        if (this.validCaptcha(SMSCODE_KEY + mobile + smsType, smsCode)) {
            return ServiceResult.success(ObjectUtils.NULL);
        }

        NoSQLWithRedisTool.setHashObject(SMSCODE_KEY + mobile + smsType, SUB_RETRY, ((Integer) o) + 1);

        return ServiceResult.failure("手机验证码输入错误", ObjectUtils.NULL);
    }

    @Override
    public ServiceResult<String> createMailCode(String email, Integer mailType) {
        String code = RandomStringUtils.randomNumeric(6);

        Integer fre = NoSQLWithRedisTool.getHashObject(MAILCODE_KEY + email + mailType, SUB_FRE);
        if (null != fre && fre >= Integer.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_MAIL_FRE_LIMIT).getPropertyValue())) {
            return ServiceResult.failure("发送太频繁,请稍后再试", ObjectUtils.NULL);
        } else if (null == fre) {
            fre = 0;
        }
        setCaptcha(MAILCODE_KEY + email + mailType, code, fre++);
        return ServiceResult.success(code);
    }

    @Override
    public ServiceResult validMailCaptcha(String email, String mailCode, Integer mailType) {
        Object o = valCaptchaRetry(MAILCODE_KEY + email + mailType);
        if (o instanceof String) {
            return ServiceResult.failure((String) o, ObjectUtils.NULL);
        }
        if (this.validCaptcha(MAILCODE_KEY + email + mailType, mailCode)) {
            return ServiceResult.success(ObjectUtils.NULL);
        }
        NoSQLWithRedisTool.setHashObject(MAILCODE_KEY + email + mailType, SUB_RETRY, ((Integer) o) + 1);
        return ServiceResult.failure("验证码错误", ObjectUtils.NULL);
    }

    /* 验证频率 */
    private Object valCaptchaFre(String key) {
        Integer fre = NoSQLWithRedisTool.getHashObject(key, SUB_FRE);
        if (null != fre && fre >= 3) {
            return "发送太频繁,请稍后再试";
        } else if (null == fre) {
            fre = 0;
        }
        return fre;
    }

    /* 错误次数 */
    private Object valCaptchaRetry(String k) {
        Integer retry = NoSQLWithRedisTool.getHashObject(k, SUB_RETRY);
        if (null != retry && retry >= 3) {
            return "错误次数超限";
        } else if (null == retry) {
            retry = 0;
        }

        return retry;
    }

    /* 设置值 */
    private void setCaptcha(String key, String code, Integer fre) {
        //NoSQLWithRedisTool.setHashObjectInPipe(key, new String[] {SUB_CODE, SUB_FRE}, new Object[] {code, fre++}, KEY_EXPIRE, null);      //集群暂不支持piple
        NoSQLWithRedisTool.setHashObject(key, SUB_CODE, code);
        NoSQLWithRedisTool.setHashObject(key, SUB_FRE, fre);
        NoSQLWithRedisTool.setExpire(key, KEY_EXPIRE);
    }

    /* 校验验证码 */
    private boolean validCaptcha(String key, String code) {
        Object cacheCode = NoSQLWithRedisTool.getHashObject(key, SUB_CODE);
        if (code.equals(NoSQLWithRedisTool.getHashObject(key, SUB_CODE))) {
            NoSQLWithRedisTool.removeValueObject(key);
            return true;
        }

        return false;
    }
}
