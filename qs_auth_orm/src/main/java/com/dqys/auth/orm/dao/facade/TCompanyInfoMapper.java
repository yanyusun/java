package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.query.CompanyQuery;

import java.util.List;

public interface TCompanyInfoMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insertSelective(TCompanyInfo record);

    TCompanyInfo selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(TCompanyInfo record);

    Integer countByQuery(CompanyQuery query);

    List<TCompanyInfo> queryList(CompanyQuery query);
}