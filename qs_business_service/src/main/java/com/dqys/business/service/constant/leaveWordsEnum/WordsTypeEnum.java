package com.dqys.business.service.constant.leaveWordsEnum;

/**
 * 留言类型
 * Created by mkfeng on 2016/7/8.
 */
public enum WordsTypeEnum {
    FOLLOW_UP(0, "跟进"),
    QS_PLAN(1, "清收计划"),
    ASSESSMENT_REPORT(2, "评估报告"),
    SOLUTION(3, "化解方案"),
    REIMBURSEMENT(4, "还款"),
    REGULAR_COLLECTION_REMARK(5, "常规催收备注"),
    JUSTICE_RESOLVE_REMARK(6, "司法化解备注"),
    MARKET_DISPOSITION_REMARK(7, "市场处置备注"),
    OTHER_REMARK(8, "其他备注"),
    AWAIT_LOOK(9, "待看"),
    STATUS(10, "状态"),
    SIGN_CONTRACT(11, "签约/成交"),;
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

    WordsTypeEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
