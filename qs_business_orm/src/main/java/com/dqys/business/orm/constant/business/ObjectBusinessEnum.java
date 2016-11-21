package com.dqys.business.orm.constant.business;

/**
 * Created by yan on 16-11-21.
 */
public enum ObjectBusinessEnum {
    PAWN_ON_BUSINESS(0, "抵押物业务正在进行"),
    PAWN_NOTON_BUSINESS(1, "抵押物业务没有在进行"),
    IOU_ON_BUSINESS(0, "借据业务正在进行"),
    IOU_NOTON_BUSINESS(1, "借据业务没有在进行");
    private Integer value;
    private String name;

    ObjectBusinessEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ObjectBusinessEnum getObjectBusinessEnum(Integer value) {
        if (value != null) {
            for (ObjectBusinessEnum objectBusinessEnum : ObjectBusinessEnum.values()) {
                if (objectBusinessEnum.getValue().equals(value)) {
                    return objectBusinessEnum;
                }
            }
        }
        return null;
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
