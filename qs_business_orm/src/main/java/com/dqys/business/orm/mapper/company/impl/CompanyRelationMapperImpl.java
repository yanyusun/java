package com.dqys.business.orm.mapper.company.impl;

import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
@Primary
public class CompanyRelationMapperImpl extends BaseDao implements CompanyRelationMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByCompanyId(@Param("aId") Integer aId, @Param("bId") Integer bId) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).deleteByCompanyId(aId, bId);
    }

    @Override
    public Integer clearByCompanyId(Integer id) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).clearByCompanyId(id);
    }

    @Override
    public Integer insert(CompanyRelation record) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).insert(record);
    }

    @Override
    public List<CompanyRelation> listByCompanyId(Integer id) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).listByCompanyId(id);
    }

    @Override
    public List<CompanyRelation> listByCompanyIdAndType(@Param("id") Integer id, @Param("type") Integer type) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).listByCompanyIdAndType(id, type);
    }

    @Override
    public CompanyRelation getByCompanyId(@Param("aId") Integer aId, @Param("bId") Integer bId) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).getByCompanyId(aId, bId);
    }

    @Override
    public CompanyRelation get(Integer id) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).get(id);
    }

    @Override
    public Integer update(CompanyRelation companyRelation) {
        return super.getSqlSession().getMapper(CompanyRelationMapper.class).update(companyRelation);
    }
}
