package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TTeamInfo;

public interface TTeamInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TTeamInfo record);

    TTeamInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTeamInfo record);
}