package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * Created by yan on 16-10-4.
 */
public class OriginOperTypeFiler extends OperTypeFilter {
    @Override
    public List<OperType> getPermission(List<OperType> list) {
        return operTypePermissionFilter.getPermission(list);
    }

    @Override
    public void decorate(OperTypeFilter filter) {
        getLastFiler(operTypePermissionFilter).decorate(filter);
    }

    private OperTypeFilter getLastFiler(OperTypeFilter filter) {
        if (filter != null) {
            getLastFiler(filter.operTypePermissionFilter);
        }
        return filter;
    }

}
