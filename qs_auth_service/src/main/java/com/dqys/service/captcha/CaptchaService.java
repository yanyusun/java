package com.dqys.service.captcha;

import com.dqys.core.model.ServiceResult;

import java.awt.image.BufferedImage;

/**
 * @author by pan on 16-4-6.
 */
public interface CaptchaService {

    /**
     * 生成图片验证码
     * @return
     * @param key
     */
    ServiceResult<BufferedImage> genImgCaptcha(String key);

    /**
     * 校验图片验证码
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
}
