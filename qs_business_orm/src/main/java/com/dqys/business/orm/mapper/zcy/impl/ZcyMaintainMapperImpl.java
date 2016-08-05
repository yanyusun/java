package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyMaintainMapper;
import com.dqys.business.orm.pojo.zcy.ZcyMaintain;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyMaintainMapperImpl extends BaseDao implements ZcyMaintainMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyMaintainMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyMaintain record) {
        return super.getSqlSession().getMapper(ZcyMaintainMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyMaintain record) {
        return super.getSqlSession().getMapper(ZcyMaintainMapper.class).insertSelective(record);
    }

    @Override
    public ZcyMaintain selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyMaintainMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyMaintain record) {
        return super.getSqlSession().getMapper(ZcyMaintainMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyMaintain record) {
        return super.getSqlSession().getMapper(ZcyMaintainMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyMaintain> selectBySelective(ZcyMaintain record) {
        return super.getSqlSession().getMapper(ZcyMaintainMapper.class).selectBySelective(record);
    }
}
