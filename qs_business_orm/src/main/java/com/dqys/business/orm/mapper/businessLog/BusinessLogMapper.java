package com.dqys.business.orm.mapper.businessLog;


import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;

import java.util.List;

public interface BusinessLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessLog record);

    int insertSelective(BusinessLog record);

    BusinessLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessLog record);

    int updateByPrimaryKey(BusinessLog record);

    List<BusinessLog> list(BusinessLogQuery query);

}