package com.dqys.core.constant;

/**
 * Created by mkfeng on 2016/8/11.
 */
public enum SmsEnum {
    REGISTER(101, "验证码"),//注册
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
    SMS_DISTRIBUTION_PASS_CODE(114, "分配器接收邀请"),
    SMS_DISTRIBUTION_REFUSE_CODE(115, "分配器拒绝邀请"),
    SMS_OUT_CODE(116, "移除分配器工作"),
    SMS_DISTRIBUTION_JOIN_CODE(117, "分配器主动申请加入"),
    INITIATIVE_JOIN_YES(118, "主动加入同意"),
    INITIATIVE_JOIN_NO(119, "主动加入被拒绝"),
    DISTRIBUTION_JOIN_YES(120, "公司间主动加入同意"),
    DISTRIBUTION_JOIN_NO(121, "公司间主动加入被拒绝"),
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
    ADD_FLOW_COMPANY(133, "平台添加流转公司"),
    LEAVE_WORD(134, "留言操作"),
    REGISTER_AUDIT(135, "注册请求审核"),
    REGISTER_AUDIT_RESULT(136, "注册审核结果"),
    ACTIVATION_INFORM(137, "组织架构导入通知激活"),
    RESET_PAWW(138, "重置密码"),
    ACTIVATE_ACCOUNT(139, "帐号激活通知"),
    APPLY_AGAIN(140, "业务重新提交审核"),
    FLOW_ADD_COMPANY(141, "通知请求公司平台为他添加的单家合作机构"),
    FLOW_ADD_COMPANY2(142, "通知请求公司平台为他添加的两家合作机构"),
    FLOW_ADD_COMPANY3(143, "通知请求公司平台为他添加的三家合作机构"),
    ADD_FLOW_ONESELF(144, "自己添加流转公司"),
    ADD_COMPANY_RELATION(145, "合作伙伴请求"),
    COMPANY_RELATION_RESULT(146, "合作伙伴的同意或拒绝或终止"),
    //B端通知短信模版
    PUBLISH_BUSINESS(1001, "发布或重新发布的通知"),
    INVALID_BUSINESS(1002, "无效"),
    RESULT_BUSINESS(1003, "审核通过或驳回"),
    FB_BUSINESS(1004, "平台发布"),
    WX_BUSINESS(1005, "平台无效");

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
