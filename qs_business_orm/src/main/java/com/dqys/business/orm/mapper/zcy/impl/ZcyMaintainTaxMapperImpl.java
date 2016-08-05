package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyMaintainTaxMapper;
import com.dqys.business.orm.pojo.zcy.ZcyMaintainTax;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyMaintainTaxMapperImpl extends BaseDao implements ZcyMaintainTaxMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyMaintainTaxMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyMaintainTax record) {
        return super.getSqlSession().getMapper(ZcyMaintainTaxMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyMaintainTax record) {
        return super.getSqlSession().getMapper(ZcyMaintainTaxMapper.class).insertSelective(record);
    }

    @Override
    public ZcyMaintainTax selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyMaintainTaxMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyMaintainTax record) {
        return super.getSqlSession().getMapper(ZcyMaintainTaxMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyMaintainTax record) {
        return super.getSqlSession().getMapper(ZcyMaintainTaxMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyMaintainTax> selectBySelective(ZcyMaintainTax record) {
        return super.getSqlSession().getMapper(ZcyMaintainTaxMapper.class).selectBySelective(record);
    }
}
