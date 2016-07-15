package com.dqys.business.orm.mapper.business.impl;

import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/7/15.
 */
@Repository
@Primary
public class BusinessObjReMapperImpl extends BaseDao implements BusinessObjReMapper {
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
}
