package com.dqys.business.orm.mapper.asset;


import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.query.asset.PawnQuery;

import java.util.List;

public interface PawnInfoMapper {
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
    Integer insert(PawnInfo record);

    /**
     * 获取单个实例
     * @param id
     * @return
     */
    PawnInfo get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(PawnInfo record);

    /**
     * 统计
     * @return
     */
    Integer count();

    /**
     * 根据借款人查看抵押物
     * @param lenderId
     * @return
     */
    List<PawnInfo> listByLenderId(Integer lenderId);

    /**
     * 多条件查询抵押物
     * @param pawnQuery
     * @return
     */
    List<PawnInfo> queryList(PawnQuery pawnQuery);
}