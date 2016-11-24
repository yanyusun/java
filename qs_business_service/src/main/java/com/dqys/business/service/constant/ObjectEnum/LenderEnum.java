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
    DELETE(1119, "删除"),
    AUDIT_YES(1113, "审核通过"),
    AUDIT_NO(1114, "审核不通过"),
    PAUSE(1115,"暂停"),
    RESTART_APPLY(1116,"重新发布"),//重新申请
    PAUSE_RECOVER (1117,"暂停恢复"),
    INVALID_SET_RECOVER(1118,"无效恢复"),

    //    以下为借款人状态
    STATUS_CHECK_PENDING(0,"待审核"),

    STATUS_COLLECTION(1,"常规催收"),

    STATUS_JUDICIARY(2,"司法化解"),

    STATUS_BAZAAR(3,"市场处置"),

    STATUS_OTHER(99,"其他处置方式"),
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
