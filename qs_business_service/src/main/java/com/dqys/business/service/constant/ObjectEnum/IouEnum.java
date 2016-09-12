package com.dqys.business.service.constant.ObjectEnum;

/**
 * 借据
 * Created by mkfeng on 2016/7/7.
 */
public enum IouEnum {
    MAINTAIN_REGULAR(130, "维持常规催收"),
    MARKET_DISPOSITION(131, "市场处置（变卖）"),
    CM_SIMULTANEOUS(132, "催收/市场同时进行"),
    EXECUTE_JUSTICE_RESOLVE(133, "执行司法化解"),
    CJ_SIMULTANEOUS(134, "催收/司法化解同时进行"),
    CMJ_SIMULTANEOUS(135, "催收、市场、司法同时进行"),
    SUBROGATION_OUTSIDE(136, "将债权转到外网"),
    REIMBURSEMENT(137, "还款"),
    DISPOSAL_FINISH(138, "已处置"),
    INVALID_SET_EFFECTIVELY(139, "无效/设置为有效"),
    OPERATION_RECORD(1310, "操作记录"),
    ADD(1311, "新增"),
    UPDATE(1312, "修改"),
    DELETE(1313, "删除");
    private Integer value;
    private String name;

    IouEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }


    public IouEnum getIouEnum(Integer value) {
        if (value != null) {
            for (IouEnum iouEnum : IouEnum.values()) {
                if (iouEnum.getValue().equals(value)) {
                    return iouEnum;
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
