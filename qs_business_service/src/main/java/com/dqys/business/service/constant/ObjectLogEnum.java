package com.dqys.business.service.constant;

/**
 * Created by Yvan on 16/7/18.
 */
public enum ObjectLogEnum {

    add(1, "新增一条记录"),
    update(2, "修改记录"),
    delete(3, "删除记录"),
    join(4, "加入"),
    exit(5, "退出")
    ;

    private Integer value;
    private String name;

    ObjectLogEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

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
}
