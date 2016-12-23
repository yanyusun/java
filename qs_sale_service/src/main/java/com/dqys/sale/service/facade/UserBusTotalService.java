package com.dqys.sale.service.facade;

import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;

import java.util.List;

/**
 * Created by yan on 16-12-21.
 */
public interface UserBusTotalService {
    List<UserBusTotal> list(UserBusTotalQuery query);
}
