package com.dqys.business.service.constant.ObjectEnum;

/**
 * 资产包
 * Created by mkfeng on 2016/7/7.
 */
public enum AssetPackageEnum {
    DELETE(102, "删除"),
    UPDATE(101, "编辑"),
    ADD(100, "添加");
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

    AssetPackageEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
