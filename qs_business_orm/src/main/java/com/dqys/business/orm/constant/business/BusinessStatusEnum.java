package com.dqys.business.orm.constant.business;

/**
 * Created by Yvan on 16/7/15.
 */
public enum BusinessStatusEnum {

    asset(10, "资产包业务"),
    lender(11, "借款人业务"),
    law(15, "案件业务");

    private Integer value;
    private String name;

    BusinessStatusEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static BusinessStatusEnum getBusinessStatusEnum(Integer value){
        if(value != null){
            for(BusinessStatusEnum businessStatusEnum :BusinessStatusEnum.values()){
                if(businessStatusEnum.getValue().equals(value)){
                    return businessStatusEnum;
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
