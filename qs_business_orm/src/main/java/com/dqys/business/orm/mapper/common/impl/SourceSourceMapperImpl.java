package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.SourceSourceMapper;
import com.dqys.business.orm.pojo.common.SourceSource;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/7/29.
 */
@Repository
@Primary
public class SourceSourceMapperImpl extends BaseDao implements SourceSourceMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(SourceSourceMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(SourceSource record) {
        return super.getSqlSession().getMapper(SourceSourceMapper.class).insert(record);
    }

    @Override
    public SourceSource get(Integer id) {
        return super.getSqlSession().getMapper(SourceSourceMapper.class).get(id);
    }

    @Override
    public Integer update(SourceSource record) {
        return super.getSqlSession().getMapper(SourceSourceMapper.class).update(record);
    }

    @Override
    public List<SourceSource> listBySourceId(Integer id) {
        return super.getSqlSession().getMapper(SourceSourceMapper.class).listBySourceId(id);
    }
}
