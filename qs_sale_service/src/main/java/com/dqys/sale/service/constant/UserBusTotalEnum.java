package com.dqys.sale.service.constant;

/**
 * Created by yan on 16-12-26.
 */
public enum  UserBusTotalEnum {
    add_collection(11, "增加收藏"),
    del_collection(12,"减少收藏"),
    add_dopise(21, "增加处置"),
    reduce_dopise(22, "减少处置") ;
    private Integer value;
    private String name;

    UserBusTotalEnum(Integer value, String name) {
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
