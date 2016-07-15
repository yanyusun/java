package com.dqys.business.orm.mapper.business.impl;

import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/7/15.
 */
@Repository
@Primary
public class ObjectUserRelationMapperImpl extends BaseDao implements ObjectUserRelationMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(ObjectUserRelationMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(ObjectUserRelation record) {
        return super.getSqlSession().getMapper(ObjectUserRelationMapper.class).insert(record);
    }

    @Override
    public ObjectUserRelation get(Integer id) {
        return super.getSqlSession().getMapper(ObjectUserRelationMapper.class).get(id);
    }

    @Override
    public Integer update(ObjectUserRelation record) {
        return super.getSqlSession().getMapper(ObjectUserRelationMapper.class).update(record);
    }
}
