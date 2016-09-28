package com.dqys.business.service.constant.ObjectEnum;

/**
 * 借款人
 * Created by mkfeng on 2016/7/7.
 */
public enum LenderEnum {
    ADD(110, "新增"),
    UPDATE_EDIT(111, "修改/编辑"),
    ADD_ATTENTION(112, "添加关注"),
    INTERNAL_RATING(113, "评优/内部评级"),
    LAWYER_LETTER(114, "申请律师函"),
    ADJOURNMENT(115, "申请延期"),
    INVALID_SET(116, "设置为无效"),
    FOLLOW_UP(117, "录跟进"),
    ADD_COMMENT(118, "增加注释"),
    UPDATE_LOG(119, "修改日志"),
    OPERATION_LOG(1110, "操作日志"),
    ADD_REIMBURSEMENT(1111, "添加一条还款"),
    DISTRIBUTION_BORROWER(1112, "分配借款人"),
    DELETE(1113, "删除"),
    //    以下为借款人状态
    STATUS_CHECK_PENDING(0, "待审核"),
    STATUS_COLLECTION(1, "常规催收"),
    STATUS_JUDICIARY(2, "司法化解"),
    STATUS_BAZAAR(3, "市场处置"),
    STATUS_OTHER(99, "其他处置方式"),

    //列表显示枚举
    VIEW_OPERATION_LOG(1100, "操作日志"),
    VIEw_ACCPET(1101,"同意接收"),
    VIEW_REJECT(1102,"拒绝接收"),
    VIEW_REAPPLY(1103,"重新申请")
    ;
    private Integer value;
    private String name;

    LenderEnum(Integer value, String name) {
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
