package com.dqys.business.service.service;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/1.
 */
public interface OperTypeService {
    /**
     * 初始化权限
     *
     * @param roleType   角色类型
     * @param userType   用户类型
     * @param objectType 对象类型
     * @return
     */
    public List<OperType> selectByRoleToOperType(Integer roleType, Integer userType, Integer objectType);

    /**
     * 查询用户类型
     *
     * @return
     */
    public List<Integer> selectByUserIds();

    /**
     * 查询用户角色id
     *
     * @return
     */
    public List<Integer> selectByRoleIds();

    /**
     * 查询对象类型
     *
     * @return
     */
    List<Integer> selectByObjectIds();

    /**
     * 根据用户类型，角色id，操作类型获取对应权限;用户类型_角色id_操作类型
     *
     * @param roleType   角色类型
     * @param userType   用户类型
     * @param objectType 对象类型
     * @return
     */
    public List<OperType> getOperType(Integer roleType, Integer userType, Integer objectType);
}
