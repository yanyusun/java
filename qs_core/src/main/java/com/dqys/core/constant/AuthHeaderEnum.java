package com.dqys.core.constant;

/**
 * 自定义头部
 *
 * @author by pan on 9/21/15.
 */
public enum AuthHeaderEnum {

    X_QS_MEMBER("x-qs-member"),       //会员头名称
    X_QS_IDENTITY("x-qs-identity"),       //会员身份
    X_QS_DATETIME("x-qs-datetime"),       //会员登陆日期时间
    ;

    private String value;

    AuthHeaderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
