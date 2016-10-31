package com.dqys.captcha.service.facade;

import com.dqys.core.model.ServiceResult;

import java.awt.image.BufferedImage;

/**
 * @author by pan on 16-4-6.
 */
public interface CaptchaService {

    /**
     * 生成图片验证码
     *
     * @param key
     * @return
     */
    ServiceResult<BufferedImage> genImgCaptcha(String key);

    /**
     * 校验图片验证码
     *
     * @param capText
     * @return
     */
    ServiceResult validImgCaptcha(String key, String capText);

    /**
     * 生成短信验证码
     *
     * @param mobile
     * @return
     */
    ServiceResult<String> genSmsCaptcha(String mobile);

    /**
     * 检验短信验证码
     *
     * @param mobile
     * @param smsCode
     * @return
     */
    ServiceResult validSmsCaptcha(String mobile, String smsCode);

    /**
     * 生成手机短信验证码
     *
     * @param mobile
     * @return
     */
    ServiceResult<String> createSmsCode(String mobile, Integer smsType);

    /**
     * 检验手机短信验证码
     *
     * @param mobile
     * @param smsCode
     * @return
     */
    ServiceResult validSmsCaptcha(String mobile, String smsCode, Integer smsType);

    /**
     * 生成邮箱邮件验证码
     *
     * @param email
     * @return
     */
    ServiceResult<String> createMailCode(String email, Integer mailType);

    /**
     * 检验邮箱邮件验证码
     *
     * @param email
     * @param mailCode
     * @return
     */
    ServiceResult validMailCaptcha(String email, String mailCode, Integer mailType);
}
