package com.dqys.business.service.constant;

/**
 * 被操作对象类型
 * Created by mkfeng on 2016/7/4.
 */
public enum ObjectTypeEnum {
    USER_INFO(99,"用户信息"),
    ;

    private Integer value;
    private String name;

    ObjectTypeEnum(Integer value, String name) {
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
