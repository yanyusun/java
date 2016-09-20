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
    SHOW_UNABLE(0,"不可以展示在外网"),
    //是否对外公开
    OPEN_ENABLE(1,"可以公开"),
    OPEN_UNABLE(0,"不可以公开"),
    //资源编号前缀
    FOLLOW_UP_CODE_PRE("fu_"),//资源编号跟进前缀
    //公共分类时
    COMMUN_NAV_LENDER_ID(0);
    private Integer value;
    private String name;
    private Object objectValue;

    SourceInfoEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
    SourceInfoEnum(Object objectValue) {
        this.objectValue = objectValue;
    }


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

    public Object getObjectValue() {
        return objectValue;
    }

    public void getObjectValue(Object objectValue) {
        this.objectValue = objectValue;
    }
}
