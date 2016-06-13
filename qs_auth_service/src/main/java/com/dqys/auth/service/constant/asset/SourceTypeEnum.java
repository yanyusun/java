package com.dqys.auth.service.constant.asset;

/**
 * Created by Yvan on 16/6/7.
 */
public enum SourceTypeEnum {

    ASSET("asset", "资产包"),
    LENDER("lender", "借款人"),
    PAWN("pawn", "抵押物"),
    IOU("iou", "借据");

    private String value;
    private String name;

    SourceTypeEnum(String value, String name){
        this.value = value;
        this.name = name;
    }

    public SourceTypeEnum getSourceTypeEnum(String value){
        for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
            if(sourceTypeEnum.getValue().equals(value)){
                return sourceTypeEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
