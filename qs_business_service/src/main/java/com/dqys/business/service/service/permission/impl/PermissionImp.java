package com.dqys.business.service.service.permission.impl;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.business.service.service.permission.Permission;
import com.dqys.business.service.utils.permission.NavIdOperTypeFilter;
import com.dqys.business.service.utils.permission.UserObjOperTypeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-10-3.
 */
@Service
@Primary
public class PermissionImp implements Permission{
    @Autowired
    private OperTypeService operTypeService;

    public List<OperType> getUserObjectNavidOperType( Integer objectType, Integer objectId, Integer navId){
        UserObjOperTypeFilter userObjOperTypeFilter =new UserObjOperTypeFilter(objectType,objectId,operTypeService);
        NavIdOperTypeFilter navIdOperTypeFilter = new NavIdOperTypeFilter(navId,objectType);
        navIdOperTypeFilter.decorate(userObjOperTypeFilter);
        return navIdOperTypeFilter.getPermission();
    }
}
