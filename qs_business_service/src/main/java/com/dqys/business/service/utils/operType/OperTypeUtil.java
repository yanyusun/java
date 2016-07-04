package com.dqys.business.service.utils.operType;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/1.
 */
public class OperTypeUtil {
    @Autowired
    private static OperTypeService operTypeService;

    public List<OperType> getOperType(String userId_roleId){
        Map<String, Object> maps=new HashMap<String, Object>();
        if(maps.get(userId_roleId)!=null){
            return  (List<OperType>)maps.get(userId_roleId);
        }else{
            List<Integer> userIds=operTypeService.selectByUserIds();
            List<Integer> roleIds=operTypeService.selectByRoleIds();
            for(Integer use:userIds){
                for(Integer rol:roleIds){
                    List<OperType> operTypes=operTypeService.selectByRoleToOperType(rol,use);
                    maps.put(use+"_"+rol,operTypes);
                }
            }
        }
        return  (List<OperType>)maps.get(userId_roleId);
    }

}
