package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TATLInfo;

public interface TATLInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TATLInfo record);

    int insertSelective(TATLInfo record);

    TATLInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(TATLInfo record);

}