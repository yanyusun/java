package com.dqys.persistent.auth.dao.impl;

import com.dqys.core.base.BaseDao;
import com.dqys.persistent.auth.dao.TUserTagMapper;
import com.dqys.persistent.auth.pojo.TUserTag;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author by pan on 16-4-6.
 */
@Repository
@Primary
public class TUserTagMapperImpl extends BaseDao implements TUserTagMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(TUserTag record) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).insertSelective(record);
    }

    @Override
    public TUserTag selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TUserTag record) {
        return super.getSqlSession().getMapper(TUserTagMapper.class).updateByPrimaryKeySelective(record);
    }
}
