package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.CompanyTeam;

public interface CompanyTeamMapper {
    /**
     * 删除公司协作器
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加公司协作器
     *
     * @param record
     * @return
     */
    Integer insert(CompanyTeam record);

    Integer insertSelective(CompanyTeam record);

    CompanyTeam selectByPrimaryKey(Integer id);

    /**
     * 修改公司协作器
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(CompanyTeam record);

    Integer updateByPrimaryKey(CompanyTeam record);
}