package com.dqys.business.orm.constant.company;

/**
 * Created by Yvan on 16/7/15.
 */
public enum ObjectBusinessTypeEnum {

    create(2, "创建者"),
    mechanism(3, "业务流转"),
    platform(0,"平台分配"),
    join(1,"主动加入");

    private Integer value;
    private String name;

    ObjectBusinessTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static ObjectBusinessTypeEnum getObjectBusinessTypeEnum(Integer value){
        if(value!= null){
            for(ObjectBusinessTypeEnum o: ObjectBusinessTypeEnum.values()){
                if(o.getValue().equals(value)){
                    return o;
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
