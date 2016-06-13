package com.dqys.auth.service.constant.asset;

/**
 * Created by Yvan on 16/6/6.
 */
public enum QuanlityTypeEnum {


    GREAT(1, "非常好"),
    NICE(2, "很好"),
    GOOD(3, "好"),
    COMMONLY(4, "一般"),
    BAD(5, "差");

    private Integer value;
    private String name;

    QuanlityTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public QuanlityTypeEnum getQuanlityTypeEnum(Integer value){
        for(QuanlityTypeEnum quanlityTypeEnum : QuanlityTypeEnum.values()){
            if(quanlityTypeEnum.getValue().equals(value)){
                return quanlityTypeEnum;
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
