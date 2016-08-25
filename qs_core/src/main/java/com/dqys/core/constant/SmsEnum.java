package com.dqys.core.constant;

/**
 * Created by mkfeng on 2016/8/11.
 */
public enum SmsEnum {
    REGISTER(101, "注册验证码"),//注册
    INVITE_COORDINATOR(102, "任务邀请（协作器）"),//任务邀请（协作器）
    INVITE_DISTRIBUTOR(103, "任务邀请（分配器）"),//任务邀请（分配器）
    INITIATIVE_JOIN(104, "主动加入"),//主动加入
    BUSINESS_AUDIT_YES(105, "平台业务审核成功"),//平台业务审核通过
    BUSINESS_AUDIT_NO(106, "平台业务审核失败"),//平台业务审核不通过
    BUSINESS_PAUSE(107, "平台业务暂停"),//平台业务暂停
    BUSINESS_OPEN(108, "平台业务开启"),//平台业务开启
    POSTPONE_APPLY(109, "延期申请"),//延期申请
    POSTPONE_AUDIT_YES(110, "延期申请审核成功"),
    POSTPONE_AUDIT_NO(111, "延期申请审核失败"),;
    private Integer value;
    private String name;

    SmsEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    SmsEnum(Integer value) {
        this.value = value;
    }
}
