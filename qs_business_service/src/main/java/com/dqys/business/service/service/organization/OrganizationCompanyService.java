package com.dqys.business.service.service.organization;

import com.dqys.business.orm.pojo.organization.OrganizationCompanyQuery;

import java.util.Map;

/**
 * Created by mkfeng on 2016/12/13.
 */
public interface OrganizationCompanyService {
    /**
     * 机构列表
     *
     * @param query
     * @return
     */
    Map organizList(OrganizationCompanyQuery query);
}
