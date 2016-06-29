package com.dqys.business.service.constant;

import com.dqys.business.orm.pojo.company.Organization;

/**
 * Created by Yvan on 16/6/27.
 */
public enum  OrganizationTypeEnum {

    tag, // 标签
    apartment, // 部门
    team, // 团队
    occupation; // 职业

    private String name;

    public static OrganizationTypeEnum getOrganizationTypeEnum(String name){
        if(name != null){
            for(OrganizationTypeEnum o: OrganizationTypeEnum.values()){
                if(o.getName().equals(name)){
                    return o;
                }
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
