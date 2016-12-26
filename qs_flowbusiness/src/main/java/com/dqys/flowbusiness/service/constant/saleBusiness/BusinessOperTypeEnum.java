package com.dqys.flowbusiness.service.constant.saleBusiness;

/**
 * Created by yan on 16-12-26.
 */
public enum BusinessOperTypeEnum {
    //----------------------->资产发布业务
    announce(101, "发布"),
    re_announce(102,"重新发布"),
    unable(103,"无效"),
    check_OK(104,"审核通过"),
    reject(105,"驳回"),
    under_line(106,"下架"),
    on_line(107,"上架");
    //---------------------->资产处置业务
//    dispose_unable(111,"无效"),
//    cancel(112,"取消"),
//    //dispose_re_announce(),

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
