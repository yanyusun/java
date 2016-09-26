package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.operType.OperTypeMapper;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.core.cache.MybatisRedisCache;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private TUserTagMapper tUserTagMapper;
    @Autowired
    private TeammateReMapper teammateReMapper;

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
        Integer userId = UserSession.getCurrent().getUserId();
        List<TUserTag> tags = tUserTagMapper.selectByUserId(userId);
        if (tags.size() > 0) {
            TUserTag tag = tags.get(0);
            roleId = (int) tag.getRoleId();
            userType = (int) tag.getUserType();
            //不是管理者的就是查询是否在团队中是所属人角色
            if (roleId != RoleTypeEnum.ADMIN.getValue()) {
                TeammateRe teammateRe = new TeammateRe();
                teammateRe.setUserId(userId);
                teammateRe.setType(TeammateReEnum.TYPE_AUXILIARY.getValue());
                List<TeammateRe> teammateReList = teammateReMapper.selectSelective(teammateRe);
                if (teammateReList.size() > 0) {
                    roleId = RoleTypeEnum.THEIR.getValue();
                }
            }
        }
        String userId_roleId_objectId = userType + "_" + roleId + "_" + objectType;
        List<OperType> list = NoSQLWithRedisTool.getValueObject(userId_roleId_objectId) == null ? new ArrayList<>() : NoSQLWithRedisTool.getValueObject(userId_roleId_objectId);
        if (list != null && list.size() != 0) {
            return list;
        } else {
            NoSQLWithRedisTool.getRedisTemplate().opsForValue().set(userId_roleId_objectId, selectByRoleToOperType(roleId, userType, objectType));
        }
        return (List<OperType>) NoSQLWithRedisTool.getValueObject(userId_roleId_objectId);
    }

    @Override
    public boolean checkOperType(Integer roleType, Integer userType, Integer objectType, Integer operType) {
        List<OperType> list = getOperType(roleType, userType, objectType);
        for (OperType o : list) {
            if (o.getOperType() == operType) {
                return true;
            }
        }
        return false;
    }


}
