package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyOwnerMapper;
import com.dqys.business.orm.pojo.zcy.ZcyOwner;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyOwnerMapperImpl extends BaseDao implements ZcyOwnerMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyOwnerMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyOwner record) {
        return super.getSqlSession().getMapper(ZcyOwnerMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyOwner record) {
        return super.getSqlSession().getMapper(ZcyOwnerMapper.class).insertSelective(record);
    }

    @Override
    public ZcyOwner selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyOwnerMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyOwner record) {
        return super.getSqlSession().getMapper(ZcyOwnerMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyOwner record) {
        return super.getSqlSession().getMapper(ZcyOwnerMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyOwner> selectBySelective(ZcyOwner record) {
        return super.getSqlSession().getMapper(ZcyOwnerMapper.class).selectBySelective(record);
    }
}
