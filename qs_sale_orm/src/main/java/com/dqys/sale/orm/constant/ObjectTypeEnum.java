package com.dqys.sale.orm.constant;

/**
 * Created by mkfeng on 2016/12/21.
 */
public enum ObjectTypeEnum {
    fixed_asset(10,"固定资产")
    ;
    private Integer value;
    private String name;

    ObjectTypeEnum(Integer value, String name) {
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
