package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.UserTeam;

public interface UserTeamMapper {
    /**
     * 删除公司内成员协作器表数据
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加公司内成员协作器表数据
     *
     * @param record
     * @return
     */
    Integer insert(UserTeam record);

    Integer insertSelective(UserTeam record);

    UserTeam selectByPrimaryKeySelective(UserTeam userTeam);

    /**
     * 修改公司内成员协作器表数据
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(UserTeam record);

    Integer updateByPrimaryKey(UserTeam record);
}