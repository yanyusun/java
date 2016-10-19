package com.dqys.business.orm.mapper.operType;

import com.dqys.business.orm.pojo.operType.OperType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/1.
 */
public interface OperTypeMapper {
    /**
     * 查询对应用户类型的角色权限
     *
     * @param roleId
     * @param userType
     * @return
     */
    List<OperType> selectByRoleToOperType(@Param("roleId") Integer roleId, @Param("userType") Integer userType, @Param("objectType") Integer objectType);

    /**
     * 查询用户类型
     *
     * @return
     */
    List<Integer> selectByUserIds();

    /**
     * 查询用户角色id
     *
     * @return
     */
    List<Integer> selectByRoleIds();

    /**
     * 查询对象类型
     *
     * @return
     */
    List<Integer> selectByObjectIds();

    /**
     * 获取全部操作权限
     * @param operType
     * @return
     */
    List<OperType> getAll(OperType operType);
}
