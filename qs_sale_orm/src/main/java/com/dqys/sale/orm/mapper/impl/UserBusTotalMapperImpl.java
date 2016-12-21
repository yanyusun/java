package com.dqys.sale.orm.mapper.impl;


import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.UserBusTotalMapper;
import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yan on 16-12-19.
 */
@Repository
@Primary
public class UserBusTotalMapperImpl extends SaleBaseDao implements UserBusTotalMapper{
    public int deleteByPrimaryKey(Integer hasPublish) {
        return 0;
    }

    public int insert(UserBusTotal record) {
        return 0;
    }

    public int insertSelective(UserBusTotal record) {
        return 0;
    }

    public UserBusTotal selectByPrimaryKey(Integer hasPublish) {
        return null;
    }

    public int updateByPrimaryKeySelective(UserBusTotal record) {
        return 0;
    }

    public int updateByPrimaryKey(UserBusTotal record) {
        return 0;
    }

    @Override
    public List<UserBusTotal> list(UserBusTotalQuery query) {
        return super.getSqlSession().getMapper(UserBusTotalMapper.class).list(query);
    }
}
