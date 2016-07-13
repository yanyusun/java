package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.CompanyRelation;

public interface CompanyRelationMapper {
    /**
     * 删除公司之间关系
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加公司关系
     *
     * @param record
     * @return
     */
    Integer insert(CompanyRelation record);

    Integer insertSelective(CompanyRelation record);

    CompanyRelation selectByPrimaryKey(Integer id);

    /**
     * 修改公司关系
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(CompanyRelation record);

    Integer updateByPrimaryKey(CompanyRelation record);
}