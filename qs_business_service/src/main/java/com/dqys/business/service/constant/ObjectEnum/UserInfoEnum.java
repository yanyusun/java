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

    COORDINATOR_ADD_USER(9901,"协作添加用户"),
    COORDINATOR_DEL_USER(9902,"协作删除用户"),
    COORDINATOR_REPLACE_USER(9903,"协作替换用户"),
    COORDINATOR_ACCEPT_ADD_USER(9901,"协作同意被添加"),
    COORDINATOR_REJECT_ADD_USER(9901,"协作拒绝被添加"),

    DISTRIBUTION_ADD_THEIR(9904,"分配添加所属人"),
    DISTRIBUTION_DEL_THEIR(9904,"分配删除所属人"),
    DISTRIBUTION_ADD_GENERAL(9905,"分配添加业务流转用户"),
    DISTRIBUTION_DEL_GENERAL(9906,"分配添加用户"),
    DISTRIBUTION_ACCEPT_ADD(9904,"分配添加用户"),
    DISTRIBUTION__DEL(9906,"分配添加用户"),


    USER_TYPE_COMMON(0,"普通员工"),
    USER_TYPE_ADMIN(1,"平台管理员"),
    USER_TYPE_ENTRUST(2,"委托方"),
    USER_TYPE_COLLECTION(31,"催收方"),
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

    public static UserInfoEnum getUserInfoEnum(Integer value){
        if(value != null){
            for (UserInfoEnum userInfoEnum : UserInfoEnum.values()) {
                if(userInfoEnum.getValue().equals(value)){
                    return userInfoEnum;
                }
            }
        }
        return null;
    }
}
