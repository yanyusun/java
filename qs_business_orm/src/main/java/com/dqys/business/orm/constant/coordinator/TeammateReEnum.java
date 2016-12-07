package com.dqys.business.orm.constant.coordinator;

/**
 * Created by mkfeng on 2016/7/18.
 */
public enum TeammateReEnum {
    TYPE_ADMIN(0, "管理者"),
    TYPE_AUXILIARY(1, "所属人"),
    TYPE_PARTICIPATION(2, "参与者"),
    STATUS_INIT(0, "待接收"),
    STATUS_ACCEPT(1, "已接收"),
    STATUS_REFUSE(2, "拒绝"),
    STATUS_DELETE(99, "删除"),
    JOIN_TYPE_PASSIVITY(0, "被分配"),
    JOIN_TYPE_INITIATIVE(1, "主动加入"),
    JOIN_TYPE_ADD(2, "录入人"),
    BUSINESS_TYPE_TASK(0, "分配任务消息"),
    BUSINESS_TYPE_COMPANY(1, "公司分配消息"),;
    private Integer value;
    private String name;

    public static String get(Integer value) {
        for (TeammateReEnum te : TeammateReEnum.values()) {
            if (te.getValue().equals(value)) {
                return te.getName();
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

    TeammateReEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
