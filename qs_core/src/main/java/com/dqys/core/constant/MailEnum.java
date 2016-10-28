package com.dqys.core.constant;

/**
 * Created by mkfeng on 2016/10/27.
 */
public enum MailEnum {
    RESET_PAWW(101, "重置密码");
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

    MailEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
