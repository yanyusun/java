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
    LOOK_USER(995, "查看用户列表"),
    USER_TYPE_COMMON(0,"普通员工"),
    USER_TYPE_ADMIN(1,"平台管理员"),
    USER_TYPE_ENTRUST(2,"委托"),
    USER_TYPE_COLLECTION(31,"催收"),
    USER_TYPE_JUDICIARY(32,"律所司法"),
    USER_TYPE_INTERMEDIARY(33,"中介");
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
