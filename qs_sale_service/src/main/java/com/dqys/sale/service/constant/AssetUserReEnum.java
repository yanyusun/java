package com.dqys.sale.service.constant;

/**
 * Created by mkfeng on 2016/12/26.
 */
public enum AssetUserReEnum {
    is_collection_no(0, "未收藏"),
    is_collection_yes(1, "已收藏"),
    is_dispose_no(0, "未申请处置"),
    is_dispose_yes(0, "已申请处置");
    private Integer value;
    private String name;

    AssetUserReEnum(Integer value, String name) {
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
