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

    List<TUserInfo> verifyUser2(@Param("account") String account,
                                @Param("mobile") String mobile,
                                @Param("email") String email, @Param("userType") Integer userType);


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

    List<TUserInfo> queryLikeAccount(@Param("account") String account, @Param("userId") Integer userId);

    /**
     * 根据公司id获取运营者用户id
     *
     * @param companyId
     * @return
     */
    Integer getUserByCompanyAdmin(Integer companyId);

    /**
     * 根据id得到用户信息
     * @param id
     * @return
     */
    TUserInfo get(int id);

    /**
     * Map中的属性(id用户id,userName用户名,avg头像地址)
     *
     * @param companyId
     * @return
     */
    List<Map> getUserByCompanyId(Integer companyId);
}