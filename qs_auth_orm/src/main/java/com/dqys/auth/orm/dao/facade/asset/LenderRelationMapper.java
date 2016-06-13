package com.dqys.auth.orm.dao.facade.asset;

import com.dqys.auth.orm.pojo.asset.LenderRelation;

import java.util.List;

public interface LenderRelationMapper {
    /**
     * 逻辑删除关系
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     *  新增借款人关系
     * @param record
     * @return
     */
    Integer insert(LenderRelation record);

    /**
     * 修改联系关系
     * @param lenderRelation
     * @return
     */
    Integer update(LenderRelation lenderRelation);

    /**
     * 获取借款人联系信息
     * @param id
     * @return
     */
    LenderRelation get(Integer id);

    /**
     * 获取当前借款人的所有借款信息
     * @param lenderId
     * @return
     */
    List<LenderRelation> listByLenderId(Integer lenderId);



}