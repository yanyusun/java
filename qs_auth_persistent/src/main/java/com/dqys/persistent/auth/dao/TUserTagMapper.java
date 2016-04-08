package com.dqys.persistent.auth.dao;

import com.dqys.persistent.auth.pojo.TUserTag;

public interface TUserTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TUserTag record);

    TUserTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserTag record);
}