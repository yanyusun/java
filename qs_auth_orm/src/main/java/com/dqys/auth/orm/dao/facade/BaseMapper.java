package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.SaleUser;

/**
 * Created by mkfeng on 2016/12/20.
 */
public interface BaseMapper<T> {
    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
