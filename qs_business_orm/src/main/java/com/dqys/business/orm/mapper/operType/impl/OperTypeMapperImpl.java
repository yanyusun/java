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
    public List<OperType> selectByRoleToOperType(Integer roleId, Integer userType) {
        return super.getSqlSession().getMapper(OperTypeMapper.class).selectByRoleToOperType(roleId,userType);
    }
}
