package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.asset.ObjectTabEnum;

import java.util.List;

/**
 * Created by pan on 16-10-1.
 */
public class NavIdOperTypeFilter extends OperTypeFilter {
    //// TODO: 16-10-2 后期改成从数据库中得到
    static {

    }

    
    private Integer navId;

    public NavIdOperTypeFilter(Integer navId) {
        this.navId = navId;
    }

    public NavIdOperTypeFilter() {
    }

    @Override
    public List<OperType> getPermission() {
        operTypePermissionFilter.getPermission();
        return null;
    }


}
