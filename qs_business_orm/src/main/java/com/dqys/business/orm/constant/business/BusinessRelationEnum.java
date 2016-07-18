package com.dqys.business.orm.constant.business;

/**
 * Created by Yvan on 16/7/15.
 */
public enum  BusinessRelationEnum {

    team(1, "团队分配"),
    company(2, "公司分配"),
    own(0, "自己分配");

    private Integer value;
    private String name;

    BusinessRelationEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static BusinessRelationEnum getBusinessRelationEnum(Integer value){
        if(value != null){
            for(BusinessRelationEnum businessRelationEnum :BusinessRelationEnum.values()){
                if(businessRelationEnum.getValue().equals(value)){
                    return businessRelationEnum;
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
