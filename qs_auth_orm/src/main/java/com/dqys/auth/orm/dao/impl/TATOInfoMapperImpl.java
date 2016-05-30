package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TATOInfoMapper;
import com.dqys.auth.orm.pojo.TATOInfo;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/5/26.
 */
@Repository
@Primary
public class TATOInfoMapperImpl extends BaseDao implements TATOInfoMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TATOInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insertSelective(TATOInfo record) {
        return super.getSqlSession().getMapper(TATOInfoMapper.class).insertSelective(record);
    }

    @Override
    public TATOInfo selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TATOInfoMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKey(TATOInfo record) {
        return super.getSqlSession().getMapper(TATOInfoMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<TATOInfo> selectListByType(Integer type) {
        return super.getSqlSession().getMapper(TATOInfoMapper.class).selectListByType(type);
    }
}
