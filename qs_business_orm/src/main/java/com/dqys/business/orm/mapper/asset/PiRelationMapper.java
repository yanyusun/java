package com.dqys.business.orm.mapper.asset;

import com.dqys.business.orm.pojo.asset.PiRelation;
import com.dqys.business.orm.query.asset.RelationQuery;

import java.util.List;

public interface PiRelationMapper {
    /**
     * 逻辑删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 逻辑删除抵押物的关联关系
     * @param id
     * @return
     */
    Integer deleteByPawnId(Integer id);

    /**
     * 逻辑删除借据的关联关系
     * @param id
     * @return
     */
    Integer deleteByIouId(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    Integer insert(PiRelation record);

    /**
     * 单取
     * @param id
     * @return
     */
    PiRelation get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(PiRelation record);

    /**
     * 条件获取
     * @param record
     * @return
     */
    List<PiRelation> queryList(RelationQuery record);
}