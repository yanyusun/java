package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.CompanyTeamReMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class CompanyTeamReMapperImpl extends BaseDao implements CompanyTeamReMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(CompanyTeamRe record) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(CompanyTeamRe record) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).insertSelective(record);
    }

    @Override
    public CompanyTeamRe selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CompanyTeamRe record) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(CompanyTeamRe record) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).updateByPrimaryKey(record);
    }
}
