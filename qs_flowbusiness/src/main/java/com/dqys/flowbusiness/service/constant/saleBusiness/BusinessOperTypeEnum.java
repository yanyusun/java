package com.dqys.flowbusiness.service.constant.saleBusiness;

/**
 * Created by yan on 16-12-26.
 */
public enum BusinessOperTypeEnum {
    //----------------------->资产发布业务
    announce(101, "发布");










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

    BusinessOperTypeEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
