package com.dqys.persistent.auth.dao;

import com.dqys.persistent.auth.pojo.TUserInfo;

public interface TUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TUserInfo record);

    TUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserInfo record);
}