package com.dqys.business.service.constant;

/**
 * 资料实勘枚举
 * Created by yan on 16-9-19.
 */
public enum  SourceInfoEnum {
    //资料实勘类型
    CERTIFICATE_TYPE(0,"证件信息"),
    ENTITY_TYPE(1, "资料实勘"),
    FOLLOW_UP_TYPE(2,"跟进信息"),
    //是否展示在外网
    SHOW_ENABLE(1,"可以展示在外网"),
    SHOW_UNABLE(0),
    //是否对外公开

    //

    private Integer value;
    private String name;

    public static String getSourceInfoEnum(Integer value) {
        for (SourceInfoEnum e : SourceInfoEnum.values()) {
            if (e.getValue().equals(value)) {
                return e.getName();
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

    SourceInfoEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
