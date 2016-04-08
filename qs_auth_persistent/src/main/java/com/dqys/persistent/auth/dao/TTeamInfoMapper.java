package com.dqys.persistent.auth.dao;

import com.dqys.persistent.auth.pojo.TTeamInfo;

public interface TTeamInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TTeamInfo record);

    TTeamInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTeamInfo record);
}