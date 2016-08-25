package com.dqys.core.constant;

/**
 * Created by Administrator on 2016/8/25.
 */
public enum RoleTypeEnum {
    ADMIN(1, "管理员"),
    REGULATOR(2, "管理者"),
    GENERAL(3, "普通员工"),
    THEIR(4, "所属人"),;
    private Integer value;
    private String name;

    public String get(Integer value) {
        for (RoleTypeEnum role : RoleTypeEnum.values()) {
            if (role.getValue().equals(value)) {
                return role.getName();
            }
        }
        return "";
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

    RoleTypeEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
