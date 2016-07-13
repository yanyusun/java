package com.dqys.business.service.constant.ObjectEnum;

/**
 * 用户信息
 * Created by mkfeng on 2016/7/7.
 */
public enum UserInfoEnum {
    ADD_COMMON_USER(991, "添加普通用户"),
    ADD_ADMIN(992, "添加管理者"),
    UPDATE_COMMON_USER(993, "修改普通用户"),
    UPDATE_ADMIN(994, "修改管理者账号"),
    LOOK_USER(995, "查看用户列表"),;
    private Integer value;
    private String name;

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

    UserInfoEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
