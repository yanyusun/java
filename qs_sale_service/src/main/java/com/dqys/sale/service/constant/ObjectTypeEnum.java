package com.dqys.sale.service.constant;

/**
 * Created by mkfeng on 2016/12/21.
 */
public enum ObjectTypeEnum {
    fixed_asset(10, "固定资产"),
    user_bond(11, "个人债权"),
    overdue_asset(12, "逾期贷款"),
    company_bond(13, "企业债权"),
    judicial_sale(14, "司法拍卖"),
    attention_asset(15, "关注类资产"),
    asset_package(16, "资产包"),
    news(21, "新闻");
    private Integer value;
    private String name;

    ObjectTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getNameByValue(Integer value) {
        for (ObjectTypeEnum objectTypeEnum : ObjectTypeEnum.values()) {
            if (objectTypeEnum.getValue() == value) {
                return objectTypeEnum.getName();
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
