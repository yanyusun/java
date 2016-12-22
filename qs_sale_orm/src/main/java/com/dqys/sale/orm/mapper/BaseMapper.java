package com.dqys.sale.orm.mapper;

/**
 * Created by mkfeng on 2016/12/21.
 */
public interface BaseMapper<T> {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(T record);

    Integer insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(T record);

    Integer updateByPrimaryKeyWithBLOBs(T record);

    Integer updateByPrimaryKey(T record);

}
