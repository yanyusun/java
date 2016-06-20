package com.dqys.business.service.constant;

/**
 * Created by Yvan on 16/6/14.
 */
public enum ContactTypeEnum {

    LENDER(1, "借款人"),
    LENDER_WITH(2, "共同借款人"),
    GUARANTEE(3, "担保方"),
    BANK_MANAGER(4, "委托客户经理"),
    OTHER(5, "其他"),
    JUDGE(6, "法官");

    private Integer value;
    private String name;

    ContactTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static ContactTypeEnum getContactTypeEnum(Integer value){
        if(value != null){
            for(ContactTypeEnum contactTypeEnum : ContactTypeEnum.values()){
                if(value.equals(contactTypeEnum.getValue())){
                    return contactTypeEnum;
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
