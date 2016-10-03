package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yan on 16-10-1.
 */
public abstract class OperTypeFilter {

    protected  List<OperType> operTypes= new LinkedList();

    protected OperTypeFilter operTypePermissionFilter;


    public void decorate(OperTypeFilter filter) {
        operTypePermissionFilter = filter;
    }

    public abstract List<OperType> getPermission(List<OperType> list);

    public List<OperType> getNextPermission(){
        if(operTypePermissionFilter!=null){
            return operTypePermissionFilter.getPermission(operTypes);
        }
        return operTypes;
    };

}
