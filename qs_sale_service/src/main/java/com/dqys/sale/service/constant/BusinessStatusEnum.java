package com.dqys.sale.service.constant;

/**
 * Created by mkfeng on 2016/12/30.
 */
public enum BusinessStatusEnum {
    //发布状态
    publish_wait_terrace(1040, "平台待发布"),//平台审核通过后的状态
    publish_wait_user(1010, "用户待发布"),//用户录入后的状态
    publish_already(1050, "已发布"),//平台发布后的状态
    publish_wait_audit(1020, "待审核"),//用户发布后的状态
    publish_audit_reject(1030, "已驳回"),//平台审核不通过后的状态
    publish_sold_out(1060, "下架"),//平台或用户操作下架的状态
    publish_asset_invalid(1070, "无效"),//平台或用户操作无效的状态
    //处置状态

    dispose_wait_audit(1110, "待审核"),//平台对用户申请的审核操作状态

    dispose_started(1120, "正在处置"),//平台审核通过后的状态

    dispose_invitation(1100, "招标中"),

    dispose_invalid(1160, "无效"),

    dispose_already(1140, "已处置"),

    dispose_cancel(1150, "已取消"),

    dispose_audit_reject(1130, "已驳回"),;

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

    BusinessStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
