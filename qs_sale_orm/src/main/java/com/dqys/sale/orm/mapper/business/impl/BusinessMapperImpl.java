package com.dqys.sale.orm.mapper.business.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.business.BusinessMapper;
import com.dqys.sale.orm.pojo.Business;
import org.springframework.stereotype.Repository;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Repository
public class BusinessMapperImpl extends SaleBaseDao implements BusinessMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(BusinessMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(Business record) {
        return super.getSqlSession().getMapper(BusinessMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(Business record) {
        return super.getSqlSession().getMapper(BusinessMapper.class).insertSelective(record);
    }

    @Override
    public Business selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(BusinessMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Business record) {
        return super.getSqlSession().getMapper(BusinessMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(Business record) {
        return super.getSqlSession().getMapper(BusinessMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(Business record) {
        return super.getSqlSession().getMapper(BusinessMapper.class).updateByPrimaryKey(record);
    }
}
