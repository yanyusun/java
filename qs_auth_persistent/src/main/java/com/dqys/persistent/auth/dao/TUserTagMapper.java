package com.dqys.persistent.auth.dao;

import com.dqys.persistent.auth.pojo.TUserTag;
import org.apache.ibatis.annotations.Param;

public interface TUserTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TUserTag record);

    TUserTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserTag record);

    TUserTag selectByUserId(@Param("userId") Integer uid);
}