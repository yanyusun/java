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
     */
    ServiceResult<BufferedImage> genImgCaptcha();

    /**
     * 校验图片验证码
     * @param capText
     * @return
     */
    ServiceResult validImgCaptcha(String capText);
}
