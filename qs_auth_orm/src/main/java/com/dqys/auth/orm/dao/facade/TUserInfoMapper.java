package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.query.TUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserInfoMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insertSelective(TUserInfo record);

    TUserInfo selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(TUserInfo record);

    List<TUserInfo> verifyUser(@Param("account") String account,
                               @Param("mobile") String mobile,
                               @Param("email") String email);

    Integer queryCount(TUserQuery tUserQuery);

    List<TUserInfo> queryList(TUserQuery tUserQuery);

    Integer queryUpdateStatus(@Param("ids")List<Integer> ids, @Param("status")Integer status);
}