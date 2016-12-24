package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.SaleUserTagMapper;
import com.dqys.auth.orm.pojo.saleUser.SaleUserTag;
import com.dqys.core.base.SaleBaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/20.
 */
@Repository
public class SaleUserTagMapperImpl extends SaleBaseDao implements SaleUserTagMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(SaleUserTagMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaleUserTag record) {
        return super.getSqlSession().getMapper(SaleUserTagMapper.class).insert(record);
    }

    @Override
    public int insertSelective(SaleUserTag record) {
        return super.getSqlSession().getMapper(SaleUserTagMapper.class).insertSelective(record);
    }

    @Override
    public SaleUserTag selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(SaleUserTagMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaleUserTag record) {
        return super.getSqlSession().getMapper(SaleUserTagMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaleUserTag record) {
        return super.getSqlSession().getMapper(SaleUserTagMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public SaleUserTag selectTagByUserId(Integer uid) {
        return super.getSqlSession().getMapper(SaleUserTagMapper.class).selectTagByUserId(uid);
    }
}
