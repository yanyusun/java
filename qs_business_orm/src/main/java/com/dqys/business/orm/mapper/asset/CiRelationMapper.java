package com.dqys.business.orm.mapper.asset;

import com.dqys.business.orm.pojo.asset.CiRelation;
import com.dqys.business.orm.query.asset.RelationQuery;

import java.util.List;

public interface CiRelationMapper {
    /**
     * 逻辑删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 删除借据的关联案件
     * @param id
     * @return
     */
    Integer deleteByIouId(Integer id);

    /**
     * 删除案件的关联借据
     * @param id
     * @return
     */
    Integer deleteByCaseId(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    Integer insert(CiRelation record);

    /**
     * 单取
     * @param id
     * @return
     */
    CiRelation get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(CiRelation record);

    /**
     * 条件获取
     * @param record
     * @return
     */
    List<CiRelation> queryList(RelationQuery record);
}