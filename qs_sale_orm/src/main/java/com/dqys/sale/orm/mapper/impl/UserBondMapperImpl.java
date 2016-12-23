package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.UserBondMapper;
import com.dqys.sale.orm.pojo.UserBond;
import com.dqys.sale.orm.query.UserBondQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/22.
 */
@Repository
public class UserBondMapperImpl extends SaleBaseDao implements UserBondMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(UserBondMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(UserBond record) {
        return super.getSqlSession().getMapper(UserBondMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(UserBond record) {
        return super.getSqlSession().getMapper(UserBondMapper.class).insertSelective(record);
    }

    @Override
    public UserBond selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(UserBondMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(UserBond record) {
        return super.getSqlSession().getMapper(UserBondMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(UserBond record) {
        return super.getSqlSession().getMapper(UserBondMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(UserBond record) {
        return super.getSqlSession().getMapper(UserBondMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<UserBond> list(UserBondQuery query) {
        return super.getSqlSession().getMapper(UserBondMapper.class).list(query);
    }

    @Override
    public Integer listCount(UserBondQuery query) {
        return super.getSqlSession().getMapper(UserBondMapper.class).listCount(query);
    }
}
