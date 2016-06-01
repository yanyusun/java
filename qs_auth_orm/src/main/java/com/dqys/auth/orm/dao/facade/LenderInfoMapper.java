package com.dqys.auth.orm.dao.facade;

import com.chebaiyong.biz.asset.domain.LenderInfo;

public interface LenderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LenderInfo record);

    int insertSelective(LenderInfo record);

    LenderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LenderInfo record);

    int updateByPrimaryKey(LenderInfo record);
}