package com.dqys.auth.service.constant.entering;

/**
 * Created by Yvan on 16/6/7.
 */
public enum LenderTypeInfo {

    LENDER(1, "借款人"),
    LENDER_WITH(2, "共同借款人"),
    GUALANTEE(3, "担保方"),
    BANK_MANAGER(4, "银行客户经理"),
    OTHER(5, "其他");

    private Integer value;
    private String name;

    LenderTypeInfo(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public LenderTypeInfo getLenderTypeInfo(Integer value){
        for(LenderTypeInfo lenderTypeInfo : LenderTypeInfo.values()){
            if(lenderTypeInfo.getValue().equals(value)){
                return lenderTypeInfo;
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
