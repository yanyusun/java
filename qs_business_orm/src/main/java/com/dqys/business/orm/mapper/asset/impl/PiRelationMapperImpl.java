package com.dqys.business.orm.mapper.asset.impl;

import com.dqys.business.orm.mapper.asset.PiRelationMapper;
import com.dqys.business.orm.pojo.asset.PiRelation;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/6/16.
 */
@Repository
@Primary
public class PiRelationMapperImpl extends BaseDao implements PiRelationMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(PiRelationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByPawnId(Integer id) {
        return super.getSqlSession().getMapper(PiRelationMapper.class).deleteByPawnId(id);
    }

    @Override
    public Integer deleteByIouId(Integer id) {
        return super.getSqlSession().getMapper(PiRelationMapper.class).deleteByIouId(id);
    }

    @Override
    public Integer insert(PiRelation record) {
        return super.getSqlSession().getMapper(PiRelationMapper.class).insert(record);
    }

    @Override
    public PiRelation get(Integer id) {
        return super.getSqlSession().getMapper(PiRelationMapper.class).get(id);
    }

    @Override
    public Integer update(PiRelation record) {
        return super.getSqlSession().getMapper(PiRelationMapper.class).update(record);
    }

    @Override
    public List<PiRelation> queryList(RelationQuery record) {
        return super.getSqlSession().getMapper(PiRelationMapper.class).queryList(record);
    }
}
