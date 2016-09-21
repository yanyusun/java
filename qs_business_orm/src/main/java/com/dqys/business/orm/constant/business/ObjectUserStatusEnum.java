package com.dqys.business.orm.constant.business;

/**
 * Created by Yvan on 16/7/15.
 */
public enum ObjectUserStatusEnum {

    accept(1, "待接收"),
    accepted(0, "已接受"),
    refuse(2, "已拒绝"),
    handled(3, "已跟进");

    private Integer value;
    private String name;

    ObjectUserStatusEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static ObjectUserStatusEnum getObjectUserStatusEnum(Integer value){
        if(value != null){
            for(ObjectUserStatusEnum objectUserStatusEnum :ObjectUserStatusEnum.values()){
                if(objectUserStatusEnum.getValue().equals(value)){
                    return objectUserStatusEnum;
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
