package com.dqys.core.constant;

/**
 * 自定义头部
 *
 * @author by pan on 9/21/15.
 */
public enum AuthHeaderEnum {

    X_QS_USER("x-qs-user"),       //会员头名称
    X_QS_TYPE("x-qs-type"),       //会员身份
    X_QS_ROLE("x-qs-role"),
    X_QS_STATUS("x-qs-status"),
    X_QS_CERTIFIED("x-qs-certified")
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
