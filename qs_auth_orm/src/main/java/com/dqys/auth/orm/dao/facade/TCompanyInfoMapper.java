package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.query.CompanyQuery;

import java.util.List;

public interface TCompanyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TCompanyInfo record);

    TCompanyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCompanyInfo record);

    int countByQuery(CompanyQuery query);

    List<TCompanyInfo> selectByQuery(CompanyQuery query);
}