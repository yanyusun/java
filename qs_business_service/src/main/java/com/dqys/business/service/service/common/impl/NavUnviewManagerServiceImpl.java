package com.dqys.business.service.service.common.impl;

import com.dqys.business.service.service.common.NavUnviewCompanyService;
import com.dqys.business.service.service.common.NavUnviewRoleService;
import com.dqys.business.service.service.common.NavUnviewUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by yan on 16-11-1.
 */
@Repository
@Primary
public class NavUnviewManagerServiceImpl {
    @Autowired
    private NavUnviewCompanyService navUnviewCompanyService;
    @Autowired
    private NavUnviewRoleService navUnviewRoleService;
    @Autowired
    private NavUnviewUserInfoService navUnviewUserInfoService;


}
