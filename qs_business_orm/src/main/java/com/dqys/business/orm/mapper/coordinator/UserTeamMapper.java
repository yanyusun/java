package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.query.coordinator.UserTeamQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    UserTeam get(Integer id);

    /**
     * 修改公司内成员协作器表数据
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(UserTeam record);

    Integer updateByPrimaryKey(UserTeam record);

    /**
     * 根据协作器成员的操作人以及操作状态搜索所有的协作器对象ID
     * @param id
     * @param status
     * @param type
     * @return
     */
    List<Integer> selectByOperatorAndStatus(
            @Param("id") Integer id, @Param("status") Integer status, @Param("type")Integer type);

    /**
     * 根据对像类型和对象ID查询协作器
     * @param id
     * @param type
     * @return
     */
    UserTeam getByObject(@Param("id") Integer id, @Param("type") Integer type);

    /**
     * 条件遍历
     * @param userTeamQuery
     * @return
     */
    List<UserTeam> queryList(UserTeamQuery userTeamQuery);
}