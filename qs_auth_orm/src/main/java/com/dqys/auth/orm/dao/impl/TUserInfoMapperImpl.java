package com.dqys.auth.orm.dao.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.UserDetail;
import com.dqys.auth.orm.query.TUserQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by pan on 16-4-6.
 */
@Repository
@Primary
public class TUserInfoMapperImpl extends BaseDao implements TUserInfoMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insertSelective(TUserInfo record) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).insertSelective(record);
    }

    @Override
    public TUserInfo selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(TUserInfo record) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public List<TUserInfo> verifyUser(@Param("account") String account, @Param("mobile") String mobile, @Param("email") String email) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).verifyUser(account, mobile, email);
    }

    @Override
    public Integer queryCount(TUserQuery tUserQuery) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).queryCount(tUserQuery);
    }

    @Override
    public List<TUserInfo> queryList(TUserQuery tUserQuery) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).queryList(tUserQuery);
    }

    @Override
    public Integer queryUpdateStatus(@Param("ids") List<Integer> ids, @Param("status") Integer status) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).queryUpdateStatus(ids, status);
    }

    @Override
    public List<Integer> listIdByUserName(String userName) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).listIdByUserName(userName);
    }

    @Override
    public List<TUserInfo> findAccountByStatus(@Param("userIds") List<Integer> userIds, @Param("status") Integer status) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).findAccountByStatus(userIds, status);
    }

    @Override
    public void updateAccountUse(@Param("userIds") List<Integer> userIds, @Param("useStatus") Integer useStatus) {
        super.getSqlSession().getMapper(TUserInfoMapper.class).updateAccountUse(userIds, useStatus);
    }

    @Override
    public Map getUserPart(Integer userId) {
        TUserInfo info = selectByPrimaryKey(userId);
        Map map = new HashMap<>();
        if (info != null) {
            map.put("realName", info.getRealName());
            map.put("userId", info.getId());
        }
        return map;
    }

    @Override
    public UserDetail getUserDetail(Integer userId) {
        return super.getSqlSession().getMapper(TUserInfoMapper.class).getUserDetail(userId);
    }
}
