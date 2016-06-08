package com.dqys.auth.orm.dao.facade.asset;

import com.dqys.auth.orm.pojo.entering.LenderRelation;

public interface LenderRelationMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(LenderRelation record);

    Integer insertSelective(LenderRelation record);

    LenderRelation selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(LenderRelation record);

    Integer updateByPrimaryKey(LenderRelation record);
}