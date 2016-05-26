package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TATLInfoMapper;
import com.dqys.auth.orm.pojo.TATLInfo;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/5/26.
 */
@Repository
@Primary
public class TATLInfoMapperImpl extends BaseDao implements TATLInfoMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TATLInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TATLInfo record) {
        return super.getSqlSession().getMapper(TATLInfoMapper.class).insert(record);
    }

    @Override
    public int insertSelective(TATLInfo record) {
        return super.getSqlSession().getMapper(TATLInfoMapper.class).insertSelective(record);
    }

    @Override
    public TATLInfo selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TATLInfoMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(TATLInfo record) {
        return super.getSqlSession().getMapper(TATLInfoMapper.class).updateByPrimaryKey(record);
    }
}
