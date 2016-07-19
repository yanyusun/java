package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.operType.OperTypeMapper;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.core.cache.MybatisRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/1.
 */
@Service
public class OperTypeServiceImpl implements OperTypeService {

    @Autowired
    private OperTypeMapper operTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

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
    public List<OperType> getOperType(Integer roleId, Integer userType, Integer objectType) {
        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache();
        String userId_roleId_objectId = userType + "_" + roleId + "_" + objectType;
        List<OperType> list = (List<OperType>) mybatisRedisCache.getObject(userId_roleId_objectId);
        if (list != null) {
            return list;
        } else {
            mybatisRedisCache.putObject(userId_roleId_objectId, selectByRoleToOperType(roleId, userType, objectType));
        }
        return (List<OperType>) mybatisRedisCache.getObject(userId_roleId_objectId);
    }

    @Override
    public boolean checkOperType(Integer roleType, Integer userType, Integer objectType, Integer operType) {
        List<OperType> list=getOperType(roleType,userType,objectType);
        for(OperType o:list){
            if(o.getOperType()==operType){
               return true;
            }
        }
        return false;
    }


}
