package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yan on 16-10-4.
 */
public class PermissionUtil {
    public static List<OperType> getDefaultOperTypeList(List<OperType> list) {
        List<OperType> newList = new LinkedList<>();
        for (OperType operType : list) {
            if (operType.getOperType() == LenderEnum.OPERATION_LOG.getValue().intValue()) {
                newList.add(operType);
                break;
            }
        }
        return newList;
    }

    /**
     * 求list1与list2中的交集
     * @param list1
     * @param list2
     * @param resultList
     */
    public static void intersection(List<OperType> list1, List<OperType> list2, List<OperType> resultList) {
        for (OperType operType1 : list1) {
            for (OperType operType2 : list2) {
                if (operType2.getOperType().intValue() == operType1.getOperType().intValue()) {
                    resultList.add(operType1);
                }
            }
        }
    }

    public static void addEditOperType(List<OperType> list, Integer value, String name) {
        OperType operType = new OperType();
        operType.setOperType(value);
        operType.setOperName(name);
        list.add(operType);
    }

    /**
     *  list中存在该type的OperType
     * @param list
     * @param type
     * @return
     */
    public static boolean isContian(List<OperType> list,int type) {
        for(OperType operType:list){
            if(operType.getOperType()==type){
                return true;
            }
        }
        return false;
    }

}
