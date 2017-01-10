package com.dqys.sale.service.constant;

/**
 * Created by mkfeng on 2016/12/29.
 */
public enum NewsEnum {
    status_draft(0, "草稿"),
    status_wait(1, "待发布"),
    status_publish(2, "发布"),
    status_invalid(-1, "无效");
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

    NewsEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
