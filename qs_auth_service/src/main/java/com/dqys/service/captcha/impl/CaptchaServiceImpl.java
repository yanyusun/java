package com.dqys.service.captcha.impl;

import com.dqys.core.model.ServiceResult;
import com.dqys.service.captcha.CaptchaService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
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

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Override
    public ServiceResult<BufferedImage> genImgCaptcha() {
        return ServiceResult.success(this.captchaProducer.createImage(this.captchaProducer.createText()));
    }

    @Override
    public ServiceResult validImgCaptcha(String capText) {
        return null;
    }
}
