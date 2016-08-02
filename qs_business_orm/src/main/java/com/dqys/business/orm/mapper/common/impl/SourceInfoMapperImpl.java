package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.SourceInfoMapper;
import com.dqys.business.orm.pojo.common.SourceInfo;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/7/29.
 */
@Repository
@Primary
public class SourceInfoMapperImpl extends BaseDao implements SourceInfoMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(SourceInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(SourceInfo record) {
        return super.getSqlSession().getMapper(SourceInfoMapper.class).insert(record);
    }

    @Override
    public SourceInfo get(Integer id) {
        return super.getSqlSession().getMapper(SourceInfoMapper.class).get(id);
    }

    @Override
    public Integer update(SourceInfo record) {
        return super.getSqlSession().getMapper(SourceInfoMapper.class).update(record);
    }
}
