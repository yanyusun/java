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
public class UserBusTotalMapperImpl extends SaleBaseDao implements UserBusTotalMapper {
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(UserBusTotalMapper.class).deleteByPrimaryKey(id);
    }

    public Integer insert(UserBusTotal record) {
        return super.getSqlSession().getMapper(UserBusTotalMapper.class).insert(record);
    }

    public Integer insertSelective(UserBusTotal record) {
        return super.getSqlSession().getMapper(UserBusTotalMapper.class).insertSelective(record);
    }

    public UserBusTotal selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(UserBusTotalMapper.class).selectByPrimaryKey(id);
    }

    public Integer updateByPrimaryKeySelective(UserBusTotal record) {
        return super.getSqlSession().getMapper(UserBusTotalMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(UserBusTotal record) {
        return null;
    }

    public Integer updateByPrimaryKey(UserBusTotal record) {
        return super.getSqlSession().getMapper(UserBusTotalMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<UserBusTotal> list(UserBusTotalQuery query) {
        return super.getSqlSession().getMapper(UserBusTotalMapper.class).list(query);
    }
}
