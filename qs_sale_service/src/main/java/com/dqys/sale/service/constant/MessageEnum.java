package com.dqys.sale.service.constant;

/**
 * 消息类型
 * Created by mkfeeng on 2016/7/8.
 */
public enum MessageEnum {
    TASK(0, "任务消息"),
    PRODUCT(1, "产品消息"),
    SAFETY(2, "安全消息"),
    SERVE(3, "服务消息"),;
    private Integer value;
    private String name;

    public static String getEnumByValue(Integer value) {
        for (MessageEnum e : MessageEnum.values()) {
            if (e.getValue().equals(value)) {
                return e.getName();
            }
        }
        return "";
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

    MessageEnum(Integer value, String name) {

        this.value = value;
        this.name = name;
    }
}
