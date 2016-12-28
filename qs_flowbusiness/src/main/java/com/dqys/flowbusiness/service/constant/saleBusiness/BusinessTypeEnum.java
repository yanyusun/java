package com.dqys.flowbusiness.service.constant.saleBusiness;

/**
 * Created by yan on 16-12-26.
 */
public enum BusinessTypeEnum {
    /**
     * 资产发布业务
     */
    asset_announce(10, "资产发布业务"),
    asset_dispose(11,"资产处置业务"),
    news_announce(12,"资产发布业务");


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

    BusinessTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
