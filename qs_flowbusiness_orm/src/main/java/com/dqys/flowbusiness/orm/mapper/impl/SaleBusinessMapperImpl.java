package com.dqys.flowbusiness.orm.mapper.impl;


import com.dqys.core.base.SaleBaseDao;
import com.dqys.flowbusiness.orm.mapper.BusinessMapper;
import com.dqys.flowbusiness.orm.pojo.Business;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by yan on 16/12/15.
 */
@Repository("SaleBusinessMapper")
@Primary
public class SaleBusinessMapperImpl extends SaleBaseDao implements BusinessMapper {


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
