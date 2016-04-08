package com.dqys.service.captcha.impl;


import com.dqys.service.base.BaseTest;
import com.dqys.service.captcha.CaptchaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by pan on 16-4-7.
 */
public class CaptchaServiceImplTest extends BaseTest {

    @Autowired
    private CaptchaService captchaService;

    @Test
    public void test() {
        System.out.println(this.captchaService.genImgCaptcha());
    }

}