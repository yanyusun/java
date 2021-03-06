package com.dqys.auth.orm.dao.facade;


import com.dqys.auth.orm.pojo.LoginLog;
import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.pojo.saleUser.dto.UserDetailDTO;
import com.dqys.auth.orm.query.SaleUserQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleUserMapper extends BaseMapper<SaleUser> {

    List<SaleUser> verifyUser(@Param("account") String account,
                              @Param("mobile") String mobile,
                              @Param("email") String email);

    void addLoginLog(LoginLog log);

    UserDetailDTO getUserDetail(Integer userId);

    // t_user_bus_total添加记录
    void insetUserBusTotal(Integer userId);

    List<SaleUser> list(SaleUserQuery query);

    Integer listCount(SaleUserQuery query);

    SaleUser getAdmin();

    Integer updateStatusByIds(@Param("ids") List<Integer> ids, @Param("status") Integer status);
}