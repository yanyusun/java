package com.dqys.sale.service.facade;

import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import com.dqys.sale.service.constant.UserBusTotalEnum;

import java.util.List;

/**
 * Created by yan on 16-12-21.
 */
public interface UserBusTotalService {
    UserBusTotal getByUserId(Integer userId);
    List<UserBusTotal> list(UserBusTotalQuery query);
    void update(Integer userId, UserBusTotalEnum userBusTotalEnum);
}
