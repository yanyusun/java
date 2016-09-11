package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.pojo.coordinator.OURelation;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class OURelationMapperImpl extends BaseDao implements OURelationMapper {
    @Override
    public Integer deleteByPrimaryKey(OURelation record) {
        return super.getSqlSession().getMapper(OURelationMapper.class).deleteByPrimaryKey(record);
    }

    @Override
    public Integer insert(OURelation record) {
        return super.getSqlSession().getMapper(OURelationMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(OURelation record) {
        return super.getSqlSession().getMapper(OURelationMapper.class).insertSelective(record);
    }

    @Override
    public OURelation selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(OURelationMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public List<OURelation> selectBySelective(OURelation record) {
        return super.getSqlSession().getMapper(OURelationMapper.class).selectBySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeySelective(OURelation record) {
        return super.getSqlSession().getMapper(OURelationMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKey(OURelation record) {
        return super.getSqlSession().getMapper(OURelationMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public Integer deleteByUserTeamId(@Param("userTeamIds") List<Integer> userTeamIds) {
        return super.getSqlSession().getMapper(OURelationMapper.class).deleteByUserTeamId(userTeamIds);
    }

    @Override
    public List<OURelation> findByOURelation(@Param("userId") Integer userId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(OURelationMapper.class).findByOURelation(userId, objectType);
    }
}
