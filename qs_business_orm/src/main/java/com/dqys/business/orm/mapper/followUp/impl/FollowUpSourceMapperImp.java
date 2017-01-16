package com.dqys.business.orm.mapper.followUp.impl;

import com.dqys.business.orm.mapper.followUp.FollowUpSourceMapper;
import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.orm.query.followUp.FollowUpSourceQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16-9-22.
 */
@Repository
public class FollowUpSourceMapperImp extends BaseDao implements FollowUpSourceMapper{
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(FollowUpSource record) {
        return 0;
    }

    @Override
    public int insertSelective(FollowUpSource record) {
        return super.getSqlSession().getMapper(FollowUpSourceMapper.class).insertSelective(record);
    }

    @Override
    public FollowUpSource selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(FollowUpSource record) {
        return super.getSqlSession().getMapper(FollowUpSourceMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(FollowUpSource record) {
        return 0;
    }

    @Override
    public List<FollowUpSource> list(FollowUpSourceQuery query) {
        return super.getSqlSession().getMapper(FollowUpSourceMapper.class).list(query);
    }
}
