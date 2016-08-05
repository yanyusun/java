package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyEstatesMapper;
import com.dqys.business.orm.pojo.zcy.ZcyEstates;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyEstatesMapperImpl extends BaseDao implements ZcyEstatesMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyEstatesMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyEstates record) {
        return super.getSqlSession().getMapper(ZcyEstatesMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyEstates record) {
        return super.getSqlSession().getMapper(ZcyEstatesMapper.class).insertSelective(record);
    }

    @Override
    public ZcyEstates selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyEstatesMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyEstates record) {
        return super.getSqlSession().getMapper(ZcyEstatesMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(ZcyEstates record) {
        return super.getSqlSession().getMapper(ZcyEstatesMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyEstates record) {
        return super.getSqlSession().getMapper(ZcyEstatesMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyEstates> selectBySelective(ZcyEstates record) {
        return super.getSqlSession().getMapper(ZcyEstatesMapper.class).selectBySelective(record);
    }
}
