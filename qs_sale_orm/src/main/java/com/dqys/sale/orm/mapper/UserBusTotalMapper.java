package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;

import java.util.List;

public interface UserBusTotalMapper {
    int deleteByPrimaryKey(Integer hasPublish);

    int insert(UserBusTotal record);

    int insertSelective(UserBusTotal record);

    UserBusTotal selectByPrimaryKey(Integer hasPublish);

    int updateByPrimaryKeySelective(UserBusTotal record);

    int updateByPrimaryKey(UserBusTotal record);

    List<UserBusTotal> list(UserBusTotalQuery query);
}