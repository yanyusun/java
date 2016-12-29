package com.dqys.sale.service.constant;

/**
 * Created by yan on 16-12-29.
 */
public enum  NewsTypeEnum {

    infomation(0,"最新资讯"),
    dynamic(1,"行业动态"),
    business(2,"业务信息");

    private Integer value;
    private String name;

    public static String getEnumByValue(Integer value) {
        for (MessageEnum e : MessageEnum.values()) {
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

    NewsTypeEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
