package com.dqys.persistent.auth.dao;

import com.dqys.persistent.auth.pojo.TCompanyInfo;

public interface TCompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TCompanyInfo record);

    TCompanyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCompanyInfo record);

}