package com.dqys.sale.service.constant;

/**
 * Created by yan on 16-12-23.
 */
public enum RoleTypeEnum {
    NORMAL_USER(0, "普通用户"),
    ADMIN(1, "管理员");
    private Integer value;
    private String name;

    RoleTypeEnum(Integer value, String name) {
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
