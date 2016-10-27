package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.NavUnviewUserInfo;


/**
 *分类用户不可见关系
 * Created by yan on 16/10/27.
 */
public interface NavUnviewUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NavUnviewUserInfo record);

    int insertSelective(NavUnviewUserInfo record);

    NavUnviewUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavUnviewUserInfo record);

    int updateByPrimaryKey(NavUnviewUserInfo record);
}