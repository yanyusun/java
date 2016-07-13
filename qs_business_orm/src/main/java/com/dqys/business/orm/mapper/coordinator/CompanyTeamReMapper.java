package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;

public interface CompanyTeamReMapper {
    /**
     * 删除协作器与邀请人员的关系
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加协作器与邀请人员的关系
     *
     * @param record
     * @return
     */
    Integer insert(CompanyTeamRe record);

    Integer insertSelective(CompanyTeamRe record);

    CompanyTeamRe selectByPrimaryKey(Integer id);

    /**
     * 修改协作器与邀请人员的关系
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(CompanyTeamRe record);

    Integer updateByPrimaryKey(CompanyTeamRe record);
}