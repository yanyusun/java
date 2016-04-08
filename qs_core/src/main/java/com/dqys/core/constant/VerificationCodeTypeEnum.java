package com.dqys.core.constant;

/**
 * @author by pan on 16-3-18.
 */
public enum VerificationCodeTypeEnum {

    REGISTER(1, "注册"),
    LOGIN(2, "登陆"),;

    private Integer value;
    private String desc;

    VerificationCodeTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static VerificationCodeTypeEnum findByValue(Integer type) {
        for (VerificationCodeTypeEnum e : VerificationCodeTypeEnum.values()) {
            if (type == e.getValue()) {
                return e;
            }
        }

        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
