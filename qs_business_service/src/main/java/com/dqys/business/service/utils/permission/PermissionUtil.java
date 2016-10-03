package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yan on 16-10-4.
 */
public class PermissionUtil {
    public static List<OperType> getDefaultOperTypeList(List<OperType> list){
        List<OperType> newList = new LinkedList<>();
        for(OperType operType:list){
            if(operType.getOperType()== LenderEnum.OPERATION_LOG.getValue()){
                newList.add(operType);
                break;
            }
        }
        return newList;
    };
}
