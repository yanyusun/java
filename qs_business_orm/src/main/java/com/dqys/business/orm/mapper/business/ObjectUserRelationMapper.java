package com.dqys.business.orm.mapper.business;

import com.dqys.business.orm.pojo.business.ObjectUserRelation;

public interface ObjectUserRelationMapper {

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    Integer insert(ObjectUserRelation record);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    ObjectUserRelation get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(ObjectUserRelation record);

}