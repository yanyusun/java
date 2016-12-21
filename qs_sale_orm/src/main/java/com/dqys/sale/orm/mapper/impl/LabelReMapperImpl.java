package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.LabelReMapper;
import com.dqys.sale.orm.pojo.LabelRe;

/**
 * Created by mkfeng on 2016/12/21.
 */
public class LabelReMapperImpl extends SaleBaseDao implements LabelReMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LabelReMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).insertSelective(record);
    }

    @Override
    public LabelRe selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LabelReMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(LabelRe record) {
        return null;
    }
}
