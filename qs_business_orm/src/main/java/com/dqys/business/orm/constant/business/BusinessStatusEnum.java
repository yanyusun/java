package com.dqys.business.orm.constant.business;

/**
 * Created by Yvan on 16/7/15.
 */
public enum BusinessStatusEnum {
    not_publish(-1, "未发布"),
    init(0, "待审核"),
    platform_pass(1, "平台审核通过"),
    platform_refuse(2, "平台审核未通过"),
    dispose(3, "处置中"),
    end(100, "已完成");

    private Integer value;
    private String name;

    BusinessStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static BusinessStatusEnum getBusinessTypeEnum(Integer value) {
        if (value != null) {
            for (BusinessStatusEnum businessStatusEnum : BusinessStatusEnum.values()) {
                if (businessStatusEnum.getValue().equals(value)) {
                    return businessStatusEnum;
                }
            }
        }
        return null;
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
