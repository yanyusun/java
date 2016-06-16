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