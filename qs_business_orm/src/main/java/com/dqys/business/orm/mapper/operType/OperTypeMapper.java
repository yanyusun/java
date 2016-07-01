package com.dqys.business.orm.mapper.operType;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/1.
 */
public interface OperTypeMapper {
    /**
     * 查询对应用户类型的角色权限
     * @param roleId
     * @param userType
     * @return
     */
    List<OperType> selectByRoleToOperType(Integer roleId, Integer userType);

    /**
     * 查询用户类型
     * @return
     */
    List<Integer> selectByUserIds();

    /**
     * 查询用户角色id
     * @return
     */
    List<Integer> selectByRoleIds();
}
