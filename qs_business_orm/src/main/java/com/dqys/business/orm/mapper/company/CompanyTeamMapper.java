package com.dqys.business.orm.mapper.company;


import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyTeamMapper {
    /**
     * 删除分配器
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加分配器
     *
     * @param record
     * @return
     */
    Integer insert(CompanyTeam record);

    /**
     * 根据Id获取
     *
     * @param id
     * @return
     */
    CompanyTeam get(Integer id);

    /**
     * 根据类型以及ID联合查询分配器
     *
     * @param type
     * @param id
     * @return
     */
    CompanyTeam getByTypeId(@Param("type") Integer type, @Param("id") Integer id);

    /**
     * 根据邀请人Id获取分配器
     *
     * @param id   创建人ID
     * @param type 查询对象类型
     * @return
     */
    List<CompanyTeam> listBySendId(@Param("type") Integer type, @Param("id") Integer id);

    Integer update(CompanyTeam team);

}