package com.dqys.business.orm.mapper.common;


import com.dqys.business.orm.pojo.common.NavUnviewCompany;

/**
 * 分类公司不可见关系
 * Created by yan on 16/10/27.
 */
public interface NavUnviewCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NavUnviewCompany record);

    int insertSelective(NavUnviewCompany record);

    NavUnviewCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavUnviewCompany record);

    int updateByPrimaryKey(NavUnviewCompany record);
}