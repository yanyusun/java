package com.dqys.business.orm.mapper.zcy.impl;

import com.dqys.business.orm.mapper.zcy.ZcyEstatesFacilityMapper;
import com.dqys.business.orm.pojo.zcy.ZcyEstatesFacility;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Repository
public class ZcyEstatesFacilityMapperImpl extends BaseDao implements ZcyEstatesFacilityMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyEstatesFacilityMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ZcyEstatesFacility record) {
        return super.getSqlSession().getMapper(ZcyEstatesFacilityMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(ZcyEstatesFacility record) {
        return super.getSqlSession().getMapper(ZcyEstatesFacilityMapper.class).insertSelective(record);
    }

    @Override
    public ZcyEstatesFacility selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ZcyEstatesFacilityMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(ZcyEstatesFacility record) {
        return super.getSqlSession().getMapper(ZcyEstatesFacilityMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(ZcyEstatesFacility record) {
        return super.getSqlSession().getMapper(ZcyEstatesFacilityMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<ZcyEstatesFacility> selectBySelective(ZcyEstatesFacility record) {
        return super.getSqlSession().getMapper(ZcyEstatesFacilityMapper.class).selectBySelective(record);
    }
}
