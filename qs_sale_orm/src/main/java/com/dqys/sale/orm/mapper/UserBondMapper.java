package com.dqys.sale.orm.mapper;

import com.dqys.sale.orm.pojo.UserBond;
import com.dqys.sale.orm.query.UserBondQuery;

import java.util.List;
import java.util.Map;


public interface UserBondMapper extends BaseMapper<UserBond> {

    List<UserBond> list(UserBondQuery query);

    Integer listCount(UserBondQuery query);

    /**
     * 统计新增和总记录数
     *
     * @return
     */
    Map getUserBondCount(Integer bondType);

}