package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.CompanyTeamMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class CompanyTeamMapperImpl extends BaseDao implements CompanyTeamMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(CompanyTeam record) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(CompanyTeam record) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).insertSelective(record);
    }

    @Override
    public CompanyTeam selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CompanyTeam record) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(CompanyTeam record) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).updateByPrimaryKey(record);
    }
}
