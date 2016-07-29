package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyMaintainOtherMapper;
import com.dqys.business.orm.pojo.zcy.ZcyMaintainOther;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyMaintainOtherMapperImpl extends BaseDao implements ZcyMaintainOtherMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyMaintainOtherMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyMaintainOther record) {
        return super.getSqlSession().getMapper(ZcyMaintainOtherMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyMaintainOther record) {
        return super.getSqlSession().getMapper(ZcyMaintainOtherMapper.class).insertSelective(record);
    }

    @Override
    public ZcyMaintainOther selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyMaintainOtherMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyMaintainOther record) {
        return super.getSqlSession().getMapper(ZcyMaintainOtherMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyMaintainOther record) {
        return super.getSqlSession().getMapper(ZcyMaintainOtherMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyMaintainOther> selectBySelective(ZcyMaintainOther record) {
        return super.getSqlSession().getMapper(ZcyMaintainOtherMapper.class).selectBySelective(record);
    }
}
