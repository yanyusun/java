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
    POSTPONE_AUDIT_NO(111, "延期申请审核失败"),
    INVITE_COORDINATOR_YES(112, "协作器邀请同意"),
    INVITE_COORDINATOR_NO(113, "协作器邀请拒绝"),
    INITIATIVE_JOIN_YES(118, "主动加入同意"),
    INITIATIVE_JOIN_NO(119, "主动加入被拒绝"),
    REPLACE_CONTACTS(122, "联系人替换"),
    REPLACE(123, "被替换"),
    FLOW(124, "业务流转请求操作,通知平台管理员短信"),
    FLOW_RESULT_YES(125, "业务流转请求接受,通知请求公司短信"),
    FLOW_RESULT_NO(126, "业务流转请求拒绝,通知请求公司短信"),
    ADMIN_INVITE_RESULT_YES(127, "被邀请公司接受,通知平台管理员短信"),
    ADMIN_INVITE_RESULT_NO(128, "被邀请公司拒绝,通知平台管理员短信"),
    RESPOND_INVITE_RESULT_YES(129, "被邀请公司接受,通知请求公司短信"),
    RESPOND_INVITE_RESULT_NO(130, "被邀请公司拒绝,通知请求公司短信"),
    FlOW_OPER(131, "流转通知"),
    BUSINESS_INVALID(132, "平台业务无效"),
    ADD_FLOW_COMPANY(133, "添加流转公司"),
    LEAVE_WORD(134, "留言操作"),
    REGISTER_AUDIT(135, "注册请求审核"),
    REGISTER_AUDIT_RESULT(136, "注册审核结果");

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
