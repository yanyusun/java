package com.dqys.business.orm.mapper.common;


import com.dqys.business.orm.pojo.common.SourceUnviewRole;
/**
 * 资源角色不可见关系
 * Created by yan on 16/10/27.
 */
public interface SourceUnviewRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SourceUnviewRole record);

    int insertSelective(SourceUnviewRole record);

    SourceUnviewRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SourceUnviewRole record);

    int updateByPrimaryKey(SourceUnviewRole record);
}