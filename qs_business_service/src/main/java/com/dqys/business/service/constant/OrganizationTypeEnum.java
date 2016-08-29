package com.dqys.business.service.constant;

/**
 * Created by Yvan on 16/6/27.
 */
public enum  OrganizationTypeEnum {

    tag, // 标签
    apartment, // 部门
    team, // 团队
    occupation; // 职业

    public static OrganizationTypeEnum getOrganizationTypeEnum(String name){
        if(name != null){
            for(OrganizationTypeEnum o: OrganizationTypeEnum.values()){
                if(o.name().equals(name)){
                    return o;
                }
            }
        }
        return null;
    }

}
