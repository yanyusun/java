package com.dqys.business.orm.mapper.company;


import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.query.company.OrganizationQuery;

import java.util.List;

public interface OrganizationMapper {

    Integer deleteByPrimaryKey(Integer id);

    Integer insert(Organization record);

    Organization get(Integer id);

    Integer update(Organization record);

    List<Organization> list(OrganizationQuery organizationQuery);

}