package com.dqys.business.orm.mapper.organization;

import com.dqys.business.orm.pojo.organization.OrganizationCompanyDto;
import com.dqys.business.orm.pojo.organization.OrganizationCompanyQuery;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/13.
 */
public interface OrganizationCompanyMapper {


    List<OrganizationCompanyDto> selectCompanyByOrganiz(OrganizationCompanyQuery query);

    Integer selectCount(OrganizationCompanyQuery query);
}
