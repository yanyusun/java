package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.NavUnviewUserType;

public interface NavUnviewUserTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NavUnviewUserType record);

    int insertSelective(NavUnviewUserType record);

    NavUnviewUserType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavUnviewUserType record);

    int updateByPrimaryKey(NavUnviewUserType record);
}