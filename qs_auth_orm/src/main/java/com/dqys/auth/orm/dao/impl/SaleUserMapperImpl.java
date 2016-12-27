package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.SaleUserMapper;
import com.dqys.auth.orm.pojo.LoginLog;
import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.pojo.saleUser.dto.UserDetailDTO;
import com.dqys.core.base.SaleBaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mfeng on 2016/12/20.
 */
@Repository
public class SaleUserMapperImpl extends SaleBaseDao implements SaleUserMapper {
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

    @Override
    public void addLoginLog(LoginLog log) {
        super.getSqlSession().getMapper(SaleUserMapper.class).addLoginLog(log);
    }

    @Override
    public UserDetailDTO getUserDetail(Integer userId) {
        return super.getSqlSession().getMapper(SaleUserMapper.class).getUserDetail(userId);
    }

    @Override
    public void insetUserBusTotal(Integer userId) {
        super.getSqlSession().getMapper(SaleUserMapper.class).insetUserBusTotal(userId);
    }
}
