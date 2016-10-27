package com.dqys.business.orm.mapper.common;


import com.dqys.business.orm.pojo.common.SourceUnviewUserInfo;
/**
 * 资源用户不可见关系
 * Created by yan on 16/10/27.
 */
public interface SourceUnviewUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SourceUnviewUserInfo record);

    int insertSelective(SourceUnviewUserInfo record);

    SourceUnviewUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SourceUnviewUserInfo record);

    int updateByPrimaryKey(SourceUnviewUserInfo record);
}