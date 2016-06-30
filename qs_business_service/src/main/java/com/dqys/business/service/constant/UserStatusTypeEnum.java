package com.dqys.business.service.constant;

/**
 * Created by Yvan on 16/6/30.
 *
 * 成员用户状态枚举
 */
public enum UserStatusTypeEnum {

    UNACTIVE(0, "未激活"),
    NORMAL(1, "正常"),
    NOLOGIN(2, "禁止登陆"),
    STOP(3, "停用");

    private Integer value;
    private String name;

    UserStatusTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static UserStatusTypeEnum getUserStatusTypeEnum(Integer value){
        if(value != null){
            for(UserStatusTypeEnum userStatusTypeEnum: UserStatusTypeEnum.values()){
                if(userStatusTypeEnum.equals(value)){
                    return userStatusTypeEnum;
                }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
