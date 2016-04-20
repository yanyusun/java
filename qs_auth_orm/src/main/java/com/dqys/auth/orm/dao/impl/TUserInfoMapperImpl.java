package com.dqys.auth.orm.dao.impl;

import com.dqys.core.base.BaseDao;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<TUserInfo> verifyUser(@Param("userName") String userName, @Param("mobile") String mobile, @Param("email") String email) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).verifyUser(userName, mobile, email);
    }
}
