package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class UserTeamMapperImpl extends BaseDao implements UserTeamMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(UserTeam record) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(UserTeam record) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).insertSelective(record);
    }

    @Override
    public UserTeam selectByPrimaryKeySelective(UserTeam userTeam) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).selectByPrimaryKeySelective(userTeam);
    }

    @Override
    public Integer updateByPrimaryKeySelective(UserTeam record) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(UserTeam record) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).updateByPrimaryKey(record);
    }
}
