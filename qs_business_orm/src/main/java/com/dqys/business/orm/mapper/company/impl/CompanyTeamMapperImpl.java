package com.dqys.business.orm.mapper.company.impl;

import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public CompanyTeam get(Integer id) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).get(id);
    }

    @Override
    public List<CompanyTeam> listBySendId(Integer id) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).listBySendId(id);
    }

    @Override
    public CompanyTeam getByTypeId(@Param("type") Integer type, @Param("id") Integer id) {
        return super.getSqlSession().getMapper(CompanyTeamMapper.class).getByTypeId(type, id);
    }
}
