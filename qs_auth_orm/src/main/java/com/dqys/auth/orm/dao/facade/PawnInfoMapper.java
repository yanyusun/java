package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.PawnInfo;

public interface PawnInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PawnInfo record);

    int insertSelective(PawnInfo record);

    PawnInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PawnInfo record);

    int updateByPrimaryKey(PawnInfo record);
}