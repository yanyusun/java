package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;

import java.util.List;

public interface UserBusTotalMapper extends BaseMapper<UserBusTotal> {
    List<UserBusTotal> list(UserBusTotalQuery query);
}