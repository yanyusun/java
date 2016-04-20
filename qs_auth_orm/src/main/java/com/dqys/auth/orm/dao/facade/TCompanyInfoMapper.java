package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TCompanyInfo;

public interface TCompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TCompanyInfo record);

    TCompanyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCompanyInfo record);

}