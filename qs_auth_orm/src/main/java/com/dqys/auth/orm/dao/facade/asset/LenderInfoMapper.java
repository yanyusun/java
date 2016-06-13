package com.dqys.auth.orm.dao.facade.asset;


import com.dqys.auth.orm.pojo.asset.LenderInfo;
import com.dqys.auth.orm.query.asset.LenderQuery;

import java.util.List;

public interface LenderInfoMapper {
    /**
     * 逻辑删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     *  新增
     * @param record
     * @return
     */
    Integer insert(LenderInfo record);

    /**
     * 获取单个实例
     * @param id
     * @return
     */
    LenderInfo get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(LenderInfo record);

    /**
     * 条件查询
     * @param lenderQuery
     * @return
     */
    List<LenderInfo> queryList(LenderQuery lenderQuery);
}