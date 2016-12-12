package com.dqys.business.service.constant.ObjectEnum;

/**
 * 案件
 * Created by mkfeng on 2016/7/7.
 */
public enum CaseEnum implements ObjectEnumBase{
    CASE_FOLLOW(151, "输入案件跟进信息"),
    NEGOTIATE(152, "进行了谈判"),
    MEDIATION_ONE(153, "进行了一审调解"),
    MEDIATION_TWO(154, "进行了二审调解"),
    MEDIATION_THREE(155, "进行了三审调解"),
    JUDGMENT_ONE(156, "进行了一审判决"),
    JUDGMENT_TWO(157, "进行了二审判决"),
    JUDGMENT_THREE(158, "进行了三审判决"),
    EXECUTE(159, "执行"),
    NEW_JUDGE(1510, "添加新法官"),
    LOOK_PARTICIPANTS(1511, "查看参与人员"),
    SPLIT(1512, "拆分"),
    REMARK(1513, "填写备注"),
    ALLOT_CASE(1514, "分配案件"),
    OPERATION_LOG(1515, "操作日志"),
    REPAY_YET(1516,"已还款"),
    ADD(1517, "新增"),
    UPDATE(1518, "修改"),
    DELETE(1519, "删除"),
    DUE_DILIGENCE(1520,"尽调");
    private Integer value;
    private String name;

    CaseEnum(Integer value, String name) {
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
