package com.dqys.business.orm.constant.partner;

/**
 * Created by mkfeng on 2016/12/14.
 */
public enum PartnerEnum {
    relation_status_wait(0, "待合作"),
    relation_status_agree(1, "同意合作"),
    relation_status_refush(2, "拒绝合作"),
    relation_status_over(3, "终止合作");

    private Integer value;
    private String name;

    PartnerEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
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
}
