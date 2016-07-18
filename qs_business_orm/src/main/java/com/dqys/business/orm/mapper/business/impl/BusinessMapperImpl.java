package com.dqys.business.orm.mapper.business.impl;

import com.dqys.business.orm.mapper.business.BusinessMapper;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/7/15.
 */
@Repository
@Primary
public class BusinessMapperImpl extends BaseDao implements BusinessMapper {


    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(BusinessMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(Business record) {
        return super.getSqlSession().getMapper(BusinessMapper.class).insert(record);
    }

    @Override
    public Business get(Integer id) {
        return super.getSqlSession().getMapper(BusinessMapper.class).get(id);
    }

    @Override
    public Integer update(Business record) {
        return super.getSqlSession().getMapper(BusinessMapper.class).update(record);
    }
}
