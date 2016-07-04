package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.operType.OperTypeMapper;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/1.
 */
@Service
public class OperTypeServiceImpl implements OperTypeService {

    @Autowired
    private OperTypeMapper operTypeMapper;

    @Override
    public List<OperType> selectByRoleToOperType(Integer roleId, Integer userType, Integer objectType) {
        return operTypeMapper.selectByRoleToOperType(roleId, userType, objectType);
    }

    @Override
    public List<Integer> selectByUserIds() {
        return operTypeMapper.selectByUserIds();
    }

    @Override
    public List<Integer> selectByRoleIds() {
        return operTypeMapper.selectByRoleIds();
    }

    @Override
    public List<Integer> selectByObjectIds() {
        return operTypeMapper.selectByObjectIds();
    }

    @Override
    public List<OperType> getOperType(String userId_roleId_objectId) {
        Map<String, Object> maps = new HashMap<String, Object>();
        if (maps.get(userId_roleId_objectId) != null) {
            return (List<OperType>) maps.get(userId_roleId_objectId);
        } else {
            List<Integer> userIds = selectByUserIds();
            List<Integer> roleIds = selectByRoleIds();
            List<Integer> objectIds = selectByObjectIds();
            for (Integer use : userIds) {
                for (Integer rol : roleIds) {
                    for (Integer obj : objectIds) {
                        List<OperType> operTypes = selectByRoleToOperType(rol, use, obj);
                        maps.put(use + "_" + rol + "_" + obj, operTypes);
                    }
                }
            }
        }
        return (List<OperType>) maps.get(userId_roleId_objectId);
    }
}
