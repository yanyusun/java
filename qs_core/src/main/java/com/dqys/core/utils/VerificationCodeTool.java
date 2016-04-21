package com.dqys.core.utils;

import com.dqys.core.constant.VerificationCodeTypeEnum;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author by pan on 16-3-18.
 */
public abstract class VerificationCodeTool {

    /**
     * 生成验证码
     *
     * @param e
     * @return
     */
    public static String genCode(VerificationCodeTypeEnum e) {
        String code = "";
        switch (e) {
            case REGISTER:
                //生存6为数字+字母密码
                code = RandomStringUtils.randomAlphanumeric(6);
                break;

            default:
                break;
        }

        return code;
    }
}
