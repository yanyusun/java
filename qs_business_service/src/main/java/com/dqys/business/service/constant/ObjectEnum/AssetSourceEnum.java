package com.dqys.business.service.constant.ObjectEnum;

/**
 * 资产源
 * Created by mkfeng on 2016/7/7.
 */
public enum AssetSourceEnum {
    UPDATE_EDIT(161, "修改/编辑"),
    ADD_ATTENTION(162, "添加关注"),
    INTERNAL_RATING(163, "评优/内部评级"),
    LAWYER_LETTER(164, "申请律师函"),
    ADJOURNMENT(165, "申请延期"),
    INVALID_SET(166, "设置为无效"),
    FOLLOW_UP(167, "录跟进"),
    ADD_COMMENT(168, "增加注释"),

    AUDIT_YES(169, "审核通过"),
    AUDIT_NO(1610, "审核不通过"),
    PAUSE(1611, "暂停"),
    RESTART_APPLY(1612, "重新申请"),
    INVALID_SET_RECOVER(1613, "无效恢复"),
    PAUSE_RECOVER(1614, "暂停恢复"),
    OPERATION_LOG(1615, "操作日志");

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

    AssetSourceEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
