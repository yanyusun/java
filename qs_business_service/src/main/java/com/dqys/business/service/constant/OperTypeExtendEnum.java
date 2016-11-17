package com.dqys.business.service.constant;

/**
 * Created by yan on 16-11-17.
 */
public enum OperTypeExtendEnum {
    APPLY_COMPANYTEAM(10001, "加入案组"),
    ACCEPT_AGREE(10002,"同意"),
    ACCEPT_REJECT(10003,"拒绝")
    ;
    private Integer value;
    private String name;

    OperTypeExtendEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
