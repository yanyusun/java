package com.dqys.sale.orm.mapper;

import com.dqys.sale.orm.pojo.UserBond;
import com.dqys.sale.orm.query.UserBondQuery;

import java.util.List;


public interface UserBondMapper extends BaseMapper<UserBond> {

    List<UserBond> list(UserBondQuery query);

    Integer listCount(UserBondQuery query);
}