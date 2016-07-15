package com.dqys.business.orm.constant.business;

/**
 * Created by Yvan on 16/7/15.
 */
public enum BusinessTypeEnum {

    handing(0, "进行中"),
    end(1, "已完成");

    private Integer value;
    private String name;

    BusinessTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static BusinessTypeEnum getBusinessTypeEnum(Integer value){
        if(value != null){
            for(BusinessTypeEnum businessTypeEnum :BusinessTypeEnum.values()){
                if(businessTypeEnum.getValue().equals(value)){
                    return businessTypeEnum;
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
