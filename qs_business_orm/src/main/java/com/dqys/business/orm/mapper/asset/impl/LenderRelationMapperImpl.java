package com.dqys.business.orm.mapper.asset.impl;

import com.dqys.business.orm.mapper.asset.LenderRelationMapper;
import com.dqys.business.orm.pojo.asset.LenderRelation;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
@Repository
@Primary
public class LenderRelationMapperImpl extends BaseDao implements LenderRelationMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LenderRelationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(LenderRelation lenderRelation) {
        return super.getSqlSession().getMapper(LenderRelationMapper.class).update(lenderRelation);
    }

    @Override
    public Integer insert(LenderRelation record) {
        return super.getSqlSession().getMapper(LenderRelationMapper.class).insert(record);
    }

    @Override
    public LenderRelation get(Integer id) {
        return super.getSqlSession().getMapper(LenderRelationMapper.class).get(id);
    }

    @Override
    public List<LenderRelation> listByLenderId(Integer lenderId) {
        return super.getSqlSession().getMapper(LenderRelationMapper.class).listByLenderId(lenderId);
    }
}
