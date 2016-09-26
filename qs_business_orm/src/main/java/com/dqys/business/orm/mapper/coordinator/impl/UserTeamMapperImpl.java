package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.query.coordinator.UserTeamQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Integer> selectByCompany(@Param("companyId") Integer companyId, @Param("objectId") Integer objectId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).selectByCompany(companyId, objectId, objectType);
    }

    @Override
    public Integer deleteByCompany(Integer companyId) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).deleteByCompany(companyId);
    }

    @Override
    public UserTeam get(Integer id) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).get(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(UserTeam record) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(UserTeam record) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<Integer> selectByOperatorAndStatus(@Param("id") Integer id, @Param("status") Integer status, @Param("type") Integer type) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).selectByOperatorAndStatus(id, status, type);
    }

    @Override
    public UserTeam getByObject(@Param("id") Integer id, @Param("type") Integer type,@Param("companyId") Integer companyId) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).getByObject(id, type,companyId);
    }

    @Override
    public List<UserTeam> queryList(UserTeamQuery userTeamQuery) {
        return super.getSqlSession().getMapper(UserTeamMapper.class).queryList(userTeamQuery);
    }
}
