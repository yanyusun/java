package com.dqys.business.service.constant;

/**
 * Created by mkfeng on 2016/8/24.
 */
public enum MessageBTEnum {
    INSIDE(0, "公司内部邀请"),//调接口（coordinator/isAccept）
    INITIATIVE(1, "主动加入"),//调接口（coordinator/isAccept）
    COMPANY_BETWEEN(2, "公司间邀请"),//调接口
    POSTPONE(3, "延期申请操作"),//调接口（repay/auditPostpone）
    BUSINESS(4, "平台业务审核操作"),
    BUSINESS_PAUSE(5, "业务暂停操作"),
    COMPANY_JOIN(6, "主动加入分配器"),//调接口
    POSTPONE_AUDIT(7, "延期申请审核结果"),
    REPLACE_CONTACTS(8, "替换联系人"),
    REPLACE(9, "被替换联系人"),
    FLOW(10, "流转请求"),//调接口
    FLOW_RESULT(11, "流转请求结果"),
    INVITE_RESULT(12, "被邀请公司答复结果");

    private Integer value;
    private String name;

    public static String get(Integer value) {
        for (MessageBTEnum mess : MessageBTEnum.values()) {
            if (mess.getValue().equals(value)) {
                return mess.getName();
            }
        }
        return "";
    }

    MessageBTEnum(Integer value, String name) {
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
