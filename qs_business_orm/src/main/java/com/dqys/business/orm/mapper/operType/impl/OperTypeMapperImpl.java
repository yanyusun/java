package com.dqys.business.orm.mapper.operType.impl;

import com.dqys.business.orm.mapper.operType.OperTypeMapper;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/1.
 */
@Repository
public class OperTypeMapperImpl extends BaseDao implements OperTypeMapper {
    @Override
    public List<OperType> selectByRoleToOperType(Integer roleId, Integer userType, Integer objectType) {
        return super.getSqlSession().getMapper(OperTypeMapper.class).selectByRoleToOperType(roleId, userType, objectType);
    }

    @Override
    public List<Integer> selectByUserIds() {
        return super.getSqlSession().getMapper(OperTypeMapper.class).selectByUserIds();
    }

    @Override
    public List<Integer> selectByRoleIds() {
        return super.getSqlSession().getMapper(OperTypeMapper.class).selectByRoleIds();
    }

    @Override
    public List<Integer> selectByObjectIds() {
        return super.getSqlSession().getMapper(OperTypeMapper.class).selectByObjectIds();
    }
}
