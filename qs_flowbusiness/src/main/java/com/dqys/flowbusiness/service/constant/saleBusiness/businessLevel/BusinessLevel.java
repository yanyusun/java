package com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel;


/**
 * Created by yan on 16-12-26.
 */
public  abstract class BusinessLevel {
    public  Integer level;
    public  String name;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
