package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.CompanyRelationMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class CompanyRelationMapperImpl extends BaseDao implements CompanyRelationMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(CompanyRelation record) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(CompanyRelation record) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).insertSelective(record);
    }

    @Override
    public CompanyRelation selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(CompanyRelation record) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(CompanyRelation record) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).updateByPrimaryKey(record);
    }
}
