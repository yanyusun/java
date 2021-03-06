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
     * @param objectType 对象类型
     * @param objectId
     * @return
     */
    public List<OperType> getOperType(Integer objectType, Integer objectId);

    /**
     * 根据用户类型，角色id，操作类型获取对应权限;用户类型_角色id_操作类型
     *
     * @param roleType   角色类型
     * @param userType   用户类型
     * @param objectType 对象类型
     * @return
     */
    public List<OperType> getOperType(Integer roleType, Integer userType, Integer objectType);


    /**
     * 判断是否拥有权限（true有权限，false无权限）
     *
     * @param roleType
     * @param userType
     * @param objectType
     * @param operType
     * @return
     */
    public boolean checkOperType(Integer roleType, Integer userType, Integer objectType, Integer operType);

    /**
     * 查询全部操作权限
     *
     * @return
     */
    public List<OperType> getAll(OperType operType);

    /**
     * 根据用户所录入的处置方式，组合成操作权限
     *
     * @param objectType
     * @param objectId
     * @return
     */
    List<OperType> getInitBuisnesOperTypeList(Integer objectType, Integer objectId, Integer flowType);
}
