package com.dqys.business.orm.mapper.company.impl;

import com.dqys.business.orm.mapper.company.OrganizationMapper;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.query.company.OrganizationQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/6/27.
 */
@Repository
@Primary
public class OrganizationMapperImpl extends BaseDao implements OrganizationMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(OrganizationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(Organization record) {
        return super.getSqlSession().getMapper(OrganizationMapper.class).insert(record);
    }

    @Override
    public Organization get(Integer id) {
        return super.getSqlSession().getMapper(OrganizationMapper.class).get(id);
    }

    @Override
    public Integer update(Organization record) {
        return super.getSqlSession().getMapper(OrganizationMapper.class).update(record);
    }

    @Override
    public List<Organization> list(OrganizationQuery organizationQuery) {
        return super.getSqlSession().getMapper(OrganizationMapper.class).list(organizationQuery);
    }
}
