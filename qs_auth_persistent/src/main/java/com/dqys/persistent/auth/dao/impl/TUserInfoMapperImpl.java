package com.dqys.persistent.auth.dao.impl;

import com.dqys.core.base.BaseDao;
import com.dqys.persistent.auth.dao.TUserInfoMapper;
import com.dqys.persistent.auth.pojo.TUserInfo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author by pan on 16-4-6.
 */
@Repository
@Primary
public class TUserInfoMapperImpl extends BaseDao implements TUserInfoMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(TUserInfo record) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).insertSelective(record);
    }

    @Override
    public TUserInfo selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TUserInfo record) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).updateByPrimaryKeySelective(record);
    }
}
