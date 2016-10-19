package com.dqys.business.orm.constant.repay;

/**
 * Created by mkdfeng on 2016/7/19.
 */
public enum RepayEnum {
    OBJECT_IOU(1, "借据"),
    OBJECT_PAWN(2, "抵押物"),
    OBJECT_UNLIMITED(3, "不限对象"),
    OBJECT_CASE(4, "案件"),
    TYPE_ACCRUAL(0, "还利息"),
    TYPE_PRINCIPAL(1, "还本金"),
    TYPE_A_P(2, "还利息加本金"),
    WAY_DIRECT(0, "直接还款"),
    WAY_ENLENDING(1, "转贷还款"),
    WAY_SELL(2, "变卖还款"),
    WAY_OTHER(3, "其他方式"),
    REPAY_STATUS_NO(1, "未还完"),
    REPAY_STATUS_YES(0, "还完");

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

    RepayEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}

