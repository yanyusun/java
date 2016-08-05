package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.OURelation;

import java.util.List;

public interface OURelationMapper {
    /**
     * 删除 操作人员被操作事物关系
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加操作人员被操作事物关系
     *
     * @param record
     * @return
     */
    Integer insert(OURelation record);

    Integer insertSelective(OURelation record);

    OURelation selectByPrimaryKey(Integer id);
//    <!--条件查询操作人与事物关系-->
    List<OURelation> selectBySelective(OURelation record);

    /**
     * 修改操作人员被操作事物关系
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(OURelation record);

    Integer updateByPrimaryKey(OURelation record);
}