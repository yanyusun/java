package com.dqys.business.service.constant.ObjectEnum;

/**
 * 抵押物
 * Created by mkfeng on 2016/7/7.
 */
public enum PawnEnum implements ObjectEnumBase {
    ADD(120, "新增"),
    MAINTAIN_REGULAR(121, "常规催收"),
    MARKET_DISPOSITION(122, "市场处置"),
    CM_SIMULTANEOUS(123, "催收/市场处置同时进行"),
    EXECUTE_JUSTICE_RESOLVE(124, "司法化解"),
    CJ_SIMULTANEOUS(125, "催收/司法化解同时进行"),
    CMJ_SIMULTANEOUS(126, "催收/司法/市场同时进行"),
    SUBROGATION_OUTSIDE(127, "发布到清搜网"),
    LAWYER_LETTER(129, "申请律师函"),
    REIMBURSEMENT(1210, "进行还款"),
    DISPOSAL_FINISH(1211, "已处置"),
    INVALID_SET_EFFECTIVELY(1212, "无效/设置为有效"),
    OPERATION_RECORD(1213, "查看操作记录"),
    ASSESSMENT_REPORT(1214, "评估报告"),
    FOLLOW_UP(1215, "填写跟进"),
    RECOVERY_PLAN(1216, "填写清收计划"),
    SOLUTION(1217, "填写化解方案"),
    REMARK(1218, "填写备注"),
    DELETE(1219, "删除"),
    UPDATE(1220, "修改"),
    SIMULTANEOUS_TANEOUS(1221, "司法化解/市场处置同时进行");
    ;
    private Integer value;
    private String name;

    PawnEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static PawnEnum getPawnEnum(Integer value) {
        if (value != null) {
            for (PawnEnum pawnEnum : PawnEnum.values()) {
                if (pawnEnum.getValue().equals(value)) {
                    return pawnEnum;
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
