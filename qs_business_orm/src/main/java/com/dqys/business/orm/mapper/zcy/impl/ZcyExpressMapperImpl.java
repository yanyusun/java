package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyExpressMapper;
import com.dqys.business.orm.pojo.zcy.ZcyExpress;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyExpressMapperImpl extends BaseDao implements ZcyExpressMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyExpressMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyExpress record) {
        return super.getSqlSession().getMapper(ZcyExpressMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyExpress record) {
        return super.getSqlSession().getMapper(ZcyExpressMapper.class).insertSelective(record);
    }

    @Override
    public ZcyExpress selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyExpressMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyExpress record) {
        return super.getSqlSession().getMapper(ZcyExpressMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyExpress record) {
        return super.getSqlSession().getMapper(ZcyExpressMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyExpress> selectBySelective(ZcyExpress record) {
        return super.getSqlSession().getMapper(ZcyExpressMapper.class).selectBySelective(record);
    }
}
