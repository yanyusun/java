package com.dqys.core.constant;

/**
 * Created by mkfeng on 2016/11/1.
 */
public enum NavUnviewEnum {
    ROLE(1, "t_nav_unview_role表"),
    USER_TYPE(2, "t_nav_unview_usertype表"),
    COMPANY(3, "t_nav_unview_company表"),
    USER_INFO(4, "t_nav_unview_userinfo表");
    private Integer value;
    private String name;

    NavUnviewEnum(Integer value, String name) {
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
