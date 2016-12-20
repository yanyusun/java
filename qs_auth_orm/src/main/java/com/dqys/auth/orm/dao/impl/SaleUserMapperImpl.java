package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.SaleUserMapper;
import com.dqys.auth.orm.pojo.SaleUser;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mfeng on 2016/12/20.
 */
@Repository
public class SaleUserMapperImpl extends BaseDao implements SaleUserMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaleUser record) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).insert(record);
    }

    @Override
    public int insertSelective(SaleUser record) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).insertSelective(record);
    }

    @Override
    public SaleUser selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SaleUser record) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SaleUser record) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<SaleUser> verifyUser(@Param("account") String account, @Param("mobile") String mobile, @Param("email") String email) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).verifyUser(account, mobile, email);
    }
}
