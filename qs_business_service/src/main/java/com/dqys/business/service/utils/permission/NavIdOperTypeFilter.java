package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.utils.operType.OperTypeUtile;

import java.util.*;

/**
 * Created by yan on 16-10-1.
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
    public List<OperType> getPermission(List<OperType> list) {
        List<OperType> operTypes2= OperTypeUtile.getOperType(navId,objectType);
        for(OperType operType1:list){
            for(OperType operType2:operTypes2){
                if(operType2.getOperType()==operType1.getOperType()){
                    operTypes.add(operType1);
                }
            }
        }
        return getNextPermission();
    }

}
