package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * 
 * Created by yan on 16-10-2.
 */
//// TODO: 16-10-2 后期权限控制用户自定义过滤类 
public class DiyOpertypeFilter extends OperTypeFilter {
    @Override
    public List<OperType> getPermission() {
        return null;
    }
}
