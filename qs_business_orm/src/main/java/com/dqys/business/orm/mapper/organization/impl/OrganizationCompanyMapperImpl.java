package com.dqys.business.orm.mapper.organization.impl;

import com.dqys.business.orm.mapper.organization.OrganizationCompanyMapper;
import com.dqys.business.orm.pojo.organization.OrganizationCompanyDto;
import com.dqys.business.orm.pojo.organization.OrganizationCompanyQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
@Repository
public class OrganizationCompanyMapperImpl extends BaseDao implements OrganizationCompanyMapper {
    @Override
    public List<OrganizationCompanyDto> selectCompanyByOrganiz(OrganizationCompanyQuery query) {
        return super.getSqlSession().getMapper(OrganizationCompanyMapper.class).selectCompanyByOrganiz(query);
    }

    @Override
    public Integer selectCount(OrganizationCompanyQuery query) {
        return super.getSqlSession().getMapper(OrganizationCompanyMapper.class).selectCount(query);
    }
}
