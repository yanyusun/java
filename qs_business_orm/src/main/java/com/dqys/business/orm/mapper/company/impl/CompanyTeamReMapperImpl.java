package com.dqys.business.orm.mapper.company.impl;

import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public CompanyTeamRe get(Integer id) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).get(id);
    }

    @Override
    public List<CompanyTeamRe> queryList(CompanyTeamReQuery companyTeamReQuery) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).queryList(companyTeamReQuery);
    }

    @Override
    public Integer update(CompanyTeamRe record) {
        return super.getSqlSession().getMapper(CompanyTeamReMapper.class).update(record);
    }
}
