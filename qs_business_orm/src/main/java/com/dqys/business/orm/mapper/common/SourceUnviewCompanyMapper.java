package com.dqys.business.orm.mapper.common;


import com.dqys.business.orm.pojo.common.SourceUnviewCompany;

/**
 * 资源公司不可见关系
 * Created by yan on 16/10/27.
 */
public interface SourceUnviewCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SourceUnviewCompany record);

    int insertSelective(SourceUnviewCompany record);

    SourceUnviewCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SourceUnviewCompany record);

    int updateByPrimaryKey(SourceUnviewCompany record);
}