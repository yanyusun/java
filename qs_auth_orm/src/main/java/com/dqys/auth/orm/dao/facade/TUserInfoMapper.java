package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.UserDetail;
import com.dqys.auth.orm.query.TUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    Integer queryUpdateStatus(@Param("ids") List<Integer> ids, @Param("status") Integer status);

    /**
     * 根据用户昵称查询用户ID
     *
     * @param userName
     * @return
     */
    List<Integer> listIdByUserName(String userName);

    /**
     * 查询相应状态的用户信息
     *
     * @param userIds
     * @return
     */
    List<TUserInfo> findAccountByStatus(@Param("userIds") List<Integer> userIds, @Param("status") Integer status);

    void updateAccountUse(@Param("userIds") List<Integer> userIds, @Param("useStatus") Integer useStatus);

    /**
     * 获取用户的部分信息
     *
     * @param userId
     * @return
     */
    Map getUserPart(Integer userId);

    /**
     * 获取用户的相关信息
     *
     * @param userId
     * @return
     */
    UserDetail getUserDetail(Integer userId);

    List<TUserInfo> queryLikeAccount(String account);
}