package com.dqys.business.orm.constant.company;

/**
 * Created by Yvan on 16/7/15.
 */
public enum ObjectAcceptTypeEnum {

    init(0,"待处理"),
    accept(1,"接收"),
    refuse(2,"拒绝接收");

    private Integer value;
    private String name;

    ObjectAcceptTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static ObjectAcceptTypeEnum getObjectAcceptTypeEnum(Integer value){
        if(value!= null){
            for(ObjectAcceptTypeEnum o: ObjectAcceptTypeEnum.values()){
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
