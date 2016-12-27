package com.dqys.sale.service.facade;

import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import com.dqys.sale.service.constant.UserBusTotalEnum;
import com.dqys.sale.service.exception.bean.UserBusTotalException;

import java.util.List;

/**
 * Created by yan on 16-12-21.
 */
public interface UserBusTotalService {
    UserBusTotal getByUserId(Integer userId) throws UserBusTotalException;

    List<UserBusTotal> list(UserBusTotalQuery query);

    void update(Integer userId, UserBusTotalEnum userBusTotalEnum, Integer assetType, Integer assetId);

    Integer createUserBusTotal(UserBusTotal userBusTotal);

}
