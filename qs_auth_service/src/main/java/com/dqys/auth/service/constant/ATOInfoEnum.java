package com.dqys.auth.service.constant;

/**
 * Created by Yvan on 16/5/26.
 */
public enum ATOInfoEnum {

    apartment(1, "部门"),
    team(2, "团队"),
    occupation(3 , "职业");

    private Integer type;
    private String name;

    ATOInfoEnum(Integer typeValue, String typeName){
        this.type = typeValue;
        this.name = typeName;
    }

    /**
     * 获取枚举类
     * @param type
     * @return
     */
    public static ATOInfoEnum getATOInfoEnum(Integer type){
        if(type == null || type.equals("")){
            return null;
        }
        for(ATOInfoEnum atoInfoEnum : ATOInfoEnum.values()) {
            if(atoInfoEnum.getType().equals(type)){
                return atoInfoEnum;
            }
        }
        return null;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
