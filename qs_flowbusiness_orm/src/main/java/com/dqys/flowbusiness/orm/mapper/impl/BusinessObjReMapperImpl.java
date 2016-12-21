package com.dqys.flowbusiness.orm.mapper.impl;


import com.dqys.core.base.SaleBaseDao;
import com.dqys.flowbusiness.orm.mapper.BusinessObjReMapper;
import com.dqys.flowbusiness.orm.pojo.BusinessObjRe;
import com.dqys.flowbusiness.orm.query.BusinessObjReQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16/12/15.
 */
@Repository
@Primary
public class BusinessObjReMapperImpl extends SaleBaseDao implements BusinessObjReMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(BusinessObjRe record) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class).insert(record);
    }

    @Override
    public BusinessObjRe get(Integer id) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class).get(id);
    }

    @Override
    public Integer update(BusinessObjRe record) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class).update(record);
    }

    @Override
    public BusinessObjRe getByObject(@Param("type") Integer type, @Param("id") Integer id) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class).getByObject(type, id);
    }

    @Override
    public List<Integer> listIdByTypeIdStatusUser(@Param("type") Integer type, @Param("status") Integer status,
                                                  @Param("userId") Integer userId) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class)
                .listIdByTypeIdStatusUser(type, status, userId);
    }

    @Override
    public List<Integer> listIdByTypeIdStatus(@Param("type") Integer type, @Param("status") Integer status) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class)
                .listIdByTypeIdStatus(type, status);
    }

    @Override
    public List<Integer> auditObject(Integer objectType) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class)
                .auditObject(objectType);
    }

    @Override
    public List<BusinessObjRe> list(BusinessObjReQuery businessObjReQuery) {
        return super.getSqlSession().getMapper(BusinessObjReMapper.class).list(businessObjReQuery);
    }
}
