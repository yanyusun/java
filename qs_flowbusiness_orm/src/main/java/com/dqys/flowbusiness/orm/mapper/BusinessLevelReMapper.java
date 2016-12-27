package com.dqys.flowbusiness.orm.mapper;


import com.dqys.flowbusiness.orm.pojo.BusinessLevelRe;
import com.dqys.flowbusiness.orm.query.BusinessLevelReQuery;

import java.util.List;

public interface BusinessLevelReMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinessLevelRe record);

    int insertSelective(BusinessLevelRe record);

    BusinessLevelRe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinessLevelRe record);

    int updateByPrimaryKey(BusinessLevelRe record);

    List<BusinessLevelRe> list(BusinessLevelReQuery query);
}