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

    /**
     * 查询用户类型
     * @return
     */
    public List<Integer> selectByUserIds();

    /**
     * 查询用户角色id
     * @return
     */
    public List<Integer> selectByRoleIds();

}
