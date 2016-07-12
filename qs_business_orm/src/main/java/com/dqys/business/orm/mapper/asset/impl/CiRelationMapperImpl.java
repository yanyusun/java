package com.dqys.business.orm.mapper.asset.impl;

import com.dqys.business.orm.mapper.asset.CiRelationMapper;
import com.dqys.business.orm.pojo.asset.CiRelation;
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
public class CiRelationMapperImpl extends BaseDao implements CiRelationMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(CiRelationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByIouId(Integer id) {
        return super.getSqlSession().getMapper(CiRelationMapper.class).deleteByIouId(id);
    }

    @Override
    public Integer deleteByCaseId(Integer id) {
        return super.getSqlSession().getMapper(CiRelationMapper.class).deleteByCaseId(id);
    }

    @Override
    public Integer insert(CiRelation record) {
        return super.getSqlSession().getMapper(CiRelationMapper.class).insert(record);
    }

    @Override
    public CiRelation get(Integer id) {
        return super.getSqlSession().getMapper(CiRelationMapper.class).get(id);
    }

    @Override
    public Integer update(CiRelation record) {
        return super.getSqlSession().getMapper(CiRelationMapper.class).update(record);
    }

    @Override
    public List<CiRelation> queryList(RelationQuery record) {
        return super.getSqlSession().getMapper(CiRelationMapper.class).queryList(record);
    }
}
