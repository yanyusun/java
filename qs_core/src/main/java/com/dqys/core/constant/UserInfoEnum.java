package com.dqys.core.constant;

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
    DEL_USER(996, "删除用户"),

    COORDINATOR_ADD_USER(9901, "案组添加用户"),
    COORDINATOR_DEL_USER(9902, "案组删除用户"),
    COORDINATOR_REPLACE_USER(9903, "案组替换用户"),
    COORDINATOR_ACCEPT_ADD_USER(9904, "被同意添加进案组"),
    COORDINATOR_REJECT_ADD_USER(9905, "被拒绝添加进案组"),

    DISTRIBUTION_ADD_THEIR(9906, "清收案组添加所属人"),
    DISTRIBUTION_DEL_THEIR(9907, "清收案组删除所属人"),
    DISTRIBUTION_ADD_GENERAL(9908, "清收案组添加用户"),//清收案组添加业务流转用户
    DISTRIBUTION_DEL_GENERAL(9909, "清收案组删除用户"),//清收案组删除业务流转用户
    DISTRIBUTION_ACCEPT_ADD(99010, "同意添加进清收案组"),
    DISTRIBUTION_REJECT_DEL(99011, "拒绝添加进清收案组"),


    USER_TYPE_COMMON(0, "普通员工"),
    USER_TYPE_ADMIN(1, "平台方"),
    USER_TYPE_ENTRUST(2, "委托方"),
    USER_TYPE_COLLECTION(31, "处置方-催收"),
    USER_TYPE_JUDICIARY(32, "处置方-律所"),
    USER_TYPE_INTERMEDIARY(33, "处置方-中介");
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

    public static UserInfoEnum getUserInfoEnum(Integer value) {
        if (value != null) {
            for (UserInfoEnum userInfoEnum : UserInfoEnum.values()) {
                if (userInfoEnum.getValue().equals(value)) {
                    return userInfoEnum;
                }
            }
        }
        return null;
    }
}
