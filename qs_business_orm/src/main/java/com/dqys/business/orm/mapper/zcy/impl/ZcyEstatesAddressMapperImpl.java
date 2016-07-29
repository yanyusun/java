package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyEstatesAddressMapper;
import com.dqys.business.orm.pojo.zcy.ZcyEstatesAddress;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyEstatesAddressMapperImpl extends BaseDao implements ZcyEstatesAddressMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyEstatesAddressMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyEstatesAddress record) {
        return super.getSqlSession().getMapper(ZcyEstatesAddressMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyEstatesAddress record) {
        return super.getSqlSession().getMapper(ZcyEstatesAddressMapper.class).insertSelective(record);
    }

    @Override
    public ZcyEstatesAddress selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyEstatesAddressMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyEstatesAddress record) {
        return super.getSqlSession().getMapper(ZcyEstatesAddressMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyEstatesAddress record) {
        return super.getSqlSession().getMapper(ZcyEstatesAddressMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyEstatesAddress> selectBySelective(ZcyEstatesAddress record) {
        return super.getSqlSession().getMapper(ZcyEstatesAddressMapper.class).selectBySelective(record);
    }
}
