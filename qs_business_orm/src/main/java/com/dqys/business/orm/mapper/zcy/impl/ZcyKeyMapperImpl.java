package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyKeyMapper;
import com.dqys.business.orm.pojo.zcy.ZcyKey;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyKeyMapperImpl extends BaseDao implements ZcyKeyMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyKeyMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyKey record) {
        return super.getSqlSession().getMapper(ZcyKeyMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyKey record) {
        return super.getSqlSession().getMapper(ZcyKeyMapper.class).insertSelective(record);
    }

    @Override
    public ZcyKey selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyKeyMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyKey record) {
        return super.getSqlSession().getMapper(ZcyKeyMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyKey record) {
        return super.getSqlSession().getMapper(ZcyKeyMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyKey> selectBySelective(ZcyKey record) {
        return super.getSqlSession().getMapper(ZcyKeyMapper.class).selectBySelective(record);
    }
}
