package com.dqys.auth.service.constant.asset;

/**
 * Created by Yvan on 16/6/7.
 */
public enum LenderTypeEnum {

    LENDER(1, "借款人"),
    LENDER_WITH(2, "共同借款人"),
    GUALANTEE(3, "担保方"),
    BANK_MANAGER(4, "银行客户经理"),
    OTHER(5, "其他"),
    JUDGE(6, "法官");

    private Integer value;
    private String name;

    LenderTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static LenderTypeEnum getLenderTypeInfo(Integer value){
        for(LenderTypeEnum lenderTypeEnum : LenderTypeEnum.values()){
            if(lenderTypeEnum.getValue().equals(value)){
                return lenderTypeEnum;
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
