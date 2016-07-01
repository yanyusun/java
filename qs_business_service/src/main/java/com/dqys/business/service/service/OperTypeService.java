package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/1.
 */
public interface OperTypeService {
    /**
     * 初始化权限
     * @param roleId
     * @param userType
     * @return
     */
    public List<OperType> selectByRoleToOperType(Integer roleId,Integer userType );

}
