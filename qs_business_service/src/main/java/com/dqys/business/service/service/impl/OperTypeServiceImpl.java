package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.operType.OperTypeMapper;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/1.
 */
@Service
public class OperTypeServiceImpl implements OperTypeService{

    @Autowired
    private OperTypeMapper operTypeMapper;

    @Override
    public List<OperType> selectByRoleToOperType(Integer roleId, Integer userType) {
        return operTypeMapper.selectByRoleToOperType(roleId,userType);
    }
}
