package com.dqys.business.service.service.common.impl;

import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.service.common.*;
import com.dqys.business.service.utils.common.NavUnviewServerAgent;
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
    private NavUnviewUserTypeService navUnviewUserTypeService;
    @Autowired
    private NavUnviewCompanyService navUnviewCompanyService;
    @Autowired
    private NavUnviewRoleService navUnviewRoleService;
    @Autowired
    private NavUnviewUserInfoService navUnviewUserInfoService;


    @Override
    public SelectDtoMap getAll(Integer navId, Integer object, Integer objectId) {
        List<SelectDto> userTypeIntList = null;
        NavUnviewServerAgent UserTypeAgent = new NavUnviewServerAgent(navUnviewUserTypeService,userTypeIntList);
        UserTypeAgent.getSelectOptions(navId,object,objectId);
        return null;
    }

    @Override
    public SelectDtoMap getNewALL(Integer navId, Integer object, Integer objectId, SelectDtoMap selectDtoMap) {
        List<SelectDto> userTypeIntList = null;
        NavUnviewServerAgent UserTypeAgent = new NavUnviewServerAgent(navUnviewUserTypeService,userTypeIntList);
        UserTypeAgent.reset(navId, object, objectId,getUnvisableList(selectDtoMap.getUserTypeList()));
        return null;
    }
    private List<Integer> getUnvisableList(List<SelectDto> selectDtoList){
        return  null;
    }
}
