package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.asset.ObjectTabEnum;

import java.util.*;

/**
 * Created by pan on 16-10-1.
 */
public class NavIdOperTypeFilter extends OperTypeFilter {
    //// TODO: 16-10-2 后期改成从数据库中得到

    private Integer navId;

    private Integer objectType;

    public NavIdOperTypeFilter(Integer navId, Integer objectType) {
        this.navId = navId;
        this.objectType = objectType;
    }

    @Override
    public List<OperType> getPermission() {
        List<OperType> operTypes1=operTypePermissionFilter.getPermission();
        //// TODO: 16-10-3 mkfList
        Map<String,List<OperType>> map = new HashMap<>();
        List<OperType> operTypes2=map.get(getKey());
        List<OperType> operTypes= new LinkedList();
        for(OperType operType1:operTypes1){
            for(OperType operType2:operTypes2){
                if(operType2.getOperType()==operType1.getOperType()){
                    operTypes.add(operType1);
                }
            }
        }
        return operTypes;
    }

    private String getKey(){
        return navId+"_"+objectType;
    };
}
