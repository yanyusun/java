package com.dqys.business.service.constant;

/**
 * '
 * 操作类型
 * Created by mkfeng on 2016/7/4.
 */
public enum OperTypeEnum {

    USER_INFO(99, "用户信息"),;

    private Integer value;
    private String name;

    OperTypeEnum(Integer value, String name) {
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
