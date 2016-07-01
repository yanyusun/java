package com.dqys.business.orm.mapper.operType;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/1.
 */
public interface OperTypeMapper {
    /**
     * 初始化权限
     * @param roleId
     * @param userType
     * @return
     */
    List<OperType> selectByRoleToOperType(Integer roleId, Integer userType);

}
