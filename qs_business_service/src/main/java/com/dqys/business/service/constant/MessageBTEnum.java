package com.dqys.business.service.constant;

/**
 * Created by mkfeng on 2016/8/24.
 */
public enum MessageBTEnum {
    INSIDE(0, "公司内部邀请"),
    INITIATIVE(1, "主动加入"),
    COMPANY_BETWEEN(2, "公司间邀请"),
    POSTPONE(3, "延期申请操作"),
    BUSINESS(4, "平台业务审核结果"),
    BUSINESS_PAUSE(5, "业务暂停操作"),
    POSTPONE_AUDIT(7, "延期申请审核结果"),;

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
