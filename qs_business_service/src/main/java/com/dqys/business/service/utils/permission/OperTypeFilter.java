package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * Created by yan on 16-10-1.
 */
public abstract class OperTypeFilter {

    protected OperTypeFilter operTypePermissionFilter;


    public void decorate(OperTypeFilter permission) {
        operTypePermissionFilter = permission;
    }

    public abstract List<OperType> getPermission();



}
