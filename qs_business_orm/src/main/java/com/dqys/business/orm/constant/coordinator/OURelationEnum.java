package com.dqys.business.orm.constant.coordinator;

/**
 * Created by mkfeng on 2016/7/18.
 */
public enum OURelationEnum {
    STATUS_INIT(1, "待接收"),
    STATUS_ACCEPT(2, "已接收"),
    STATUS_FOLLOW(3, "已跟进"),
    TYPE_ALLOCATION_ONESELF(0, "自己分配"),
    TYPE_ALLOCATION_TEAM(1, "团队分配"),
    TYPE_ALLOCATION_COMPANY(2, "公司分配"),
    ACCEPT_STATUS_INIT(0, "已接收"),
    ACCEPT_STATUS_ACCEPT(1, "未接收");
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

    OURelationEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
