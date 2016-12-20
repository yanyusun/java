package com.dqys.sale.orm.impl;

import com.dqys.sale.orm.base.BaseTest;
import com.dqys.sale.orm.mapper.UserBusTotalMapper;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yan on 16-12-19.
 */


public class UserSaleTotalTest extends BaseTest {
    @Autowired
    public UserBusTotalMapper userBusTotalMapper;

    //@Transactional(transactionManager = "transactionManagerSale")
    @Test
    public void test(){
        UserBusTotalQuery query = new UserBusTotalQuery();
        query.setId(1);
        userBusTotalMapper.list(query);
        System.out.println();
    }

}
