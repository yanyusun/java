package com.dqys.auth.orm.dao.facade.asset;

import com.dqys.auth.orm.pojo.entering.SourceInfo;

public interface SourceInfoMapper {
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
    Integer insert(SourceInfo record);

    /**
     * 获取单个实例
     * @param id
     * @return
     */
    SourceInfo get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(SourceInfo record);

}