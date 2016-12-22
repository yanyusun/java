package com.dqys.sale.orm.mapper.business.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.business.BusinessMapper;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.pojo.BusinessORelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Repository
public class BusinessORelationMapperImpl extends SaleBaseDao implements BusinessORelationMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(BusinessORelationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(BusinessORelation record) {
        return super.getSqlSession().getMapper(BusinessORelationMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(BusinessORelation record) {
        return super.getSqlSession().getMapper(BusinessORelationMapper.class).insertSelective(record);
    }

    @Override
    public BusinessORelation selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(BusinessORelationMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(BusinessORelation record) {
        return super.getSqlSession().getMapper(BusinessORelationMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(BusinessORelation record) {
        return super.getSqlSession().getMapper(BusinessORelationMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(BusinessORelation record) {
        return super.getSqlSession().getMapper(BusinessORelationMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<Integer> selectObjectIdByObjectType(@Param("objectType") Integer objectType, @Param("status") Integer... status) {
        return super.getSqlSession().getMapper(BusinessORelationMapper.class).selectObjectIdByObjectType(objectType, status);
    }
}
