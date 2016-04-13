package com.dqys.constant;

/**
 * @author by pan on 16-4-13.
 */
public enum MailVerifyTypeEnum {

    EMAIL_CONFIRM(1),
    USER_RESET(2),
    ;

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    MailVerifyTypeEnum(Integer value) {
        this.value = value;
    }
}
