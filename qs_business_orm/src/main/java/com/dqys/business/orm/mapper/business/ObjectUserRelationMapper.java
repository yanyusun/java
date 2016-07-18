package com.dqys.business.orm.mapper.business;

import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;

import java.util.List;

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

    /**
     * 分页或者不分也查询ObjectUserRelation 列表
     * @param query
     * @return ObjectUserRelation 列表
     */
    List<ObjectUserRelation> list(ObjectUserRelationQuery query);

}