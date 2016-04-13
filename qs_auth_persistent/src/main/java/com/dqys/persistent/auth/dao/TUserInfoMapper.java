package com.dqys.persistent.auth.dao;

import com.dqys.persistent.auth.pojo.TUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TUserInfo record);

    TUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserInfo record);

    List<TUserInfo> verifyUser(@Param("userName") String userName, @Param("mobile") String mobile, @Param("email") String email);
}