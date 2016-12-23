package com.dqys.sale.service.constant;

/**
 * Created by mkfeng on 2016/12/21.
 */
public enum ObjectTypeEnum {
    fixed_asset(10, "固定资产"),
    user_bond(11, "个人债权"),
    overdue_asset(12, "逾期贷款"),
    judicial_sale(13, "司法拍卖"),
    company_bond(14, "企业债权"),
    attention_asset(15, "关注类资产"),
    asset_package(16, "资产包");
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
