package com.dqys.flowbusiness.orm.mapper;


import com.dqys.flowbusiness.orm.pojo.BusinesslevelUserRe;
import com.dqys.flowbusiness.orm.query.BusinesslevelUserReQuery;

import java.util.List;

public interface BusinesslevelUserReMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusinesslevelUserRe record);

    int insertSelective(BusinesslevelUserRe record);

    BusinesslevelUserRe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusinesslevelUserRe record);

    int updateByPrimaryKey(BusinesslevelUserRe record);

    List<BusinesslevelUserRe> list(BusinesslevelUserReQuery query);
}