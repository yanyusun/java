package com.dqys.business.service.service.common.impl;

import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.service.common.NavUnviewCompanyService;
import com.dqys.business.service.service.common.NavUnviewManagerService;
import com.dqys.business.service.service.common.NavUnviewRoleService;
import com.dqys.business.service.service.common.NavUnviewUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资料实勘权限管理者
 * Created by yan on 16-11-1.
 */
@Repository
@Primary
public class NavUnviewManagerServiceImpl implements NavUnviewManagerService{
    @Autowired
    private NavUnviewCompanyService navUnviewCompanyService;
    @Autowired
    private NavUnviewRoleService navUnviewRoleService;
    @Autowired
    private NavUnviewUserInfoService navUnviewUserInfoService;
    @Autowired
    private NavUnviewManagerService navUnviewManagerService;


    @Override
    public SelectDtoMap getAll(String navId) {
        return null;
    }

    @Override
    public List<SelectDto> getUserList(List<Integer> companyList, List<Integer> roleList) {
        return null;
    }

    @Override
    public void setALL(Integer navId, SelectDtoMap selectDtoMap) {

    }
}
