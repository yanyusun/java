package com.dqys.auth.orm.dao.facade.asset;


import com.dqys.auth.orm.pojo.entering.PawnInfo;

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

}