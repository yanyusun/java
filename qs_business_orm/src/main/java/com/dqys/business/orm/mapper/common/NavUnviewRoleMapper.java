package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.NavUnviewRole;

/**
 *分类角色不可见关系
 * Created by yan on 16/10/27.
 */
public interface NavUnviewRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NavUnviewRole record);

    int insertSelective(NavUnviewRole record);

    NavUnviewRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavUnviewRole record);

    int updateByPrimaryKey(NavUnviewRole record);
}