package com.dqys.sale.service.impl;

import com.dqys.flowbusiness.service.service.BusinessService;
import com.dqys.sale.orm.mapper.UserBusTotalMapper;
import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import com.dqys.sale.service.facade.UserBusTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yan on 16-12-21.
 */
@Service
@Primary
public class UserBusTotalServiceImpl implements UserBusTotalService {
    @Autowired
    private UserBusTotalMapper userBusTotalMapper;
    @Autowired
    @Qualifier("saleBusinessService")
    private BusinessService businessService;


    @Override
    public List<UserBusTotal> list(UserBusTotalQuery query) {
       // businessService.getBusiness(1);
        return userBusTotalMapper.list(query);
    }
}
