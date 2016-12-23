package com.dqys.auth.orm.dao.facade;


import com.dqys.auth.orm.pojo.SaleUser;
import com.dqys.auth.orm.pojo.TUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleUserMapper extends BaseMapper<SaleUser> {

    List<SaleUser> verifyUser(@Param("account") String account,
                               @Param("mobile") String mobile,
                               @Param("email") String email);

}