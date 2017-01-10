package com.dqys.sale.service.constant;

/**
 * Created by Administrator on 2017/1/3.
 */
public enum ObjectDisposeEnum {
    dispose_status_wait(0, "待处置"),
    dispose_status_yes(1, "正在处置"),
    dispose_status_over(2, "已处置"),
    enable_ye(0, "有效"),
    enable_no(1, "无效");
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

    ObjectDisposeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
