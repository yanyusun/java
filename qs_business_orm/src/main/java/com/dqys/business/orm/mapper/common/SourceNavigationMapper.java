package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.SourceNavigation;

public interface SourceNavigationMapper {
    /**
     * 数据删除
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Integer insert(SourceNavigation record);

    /**
     * 根据ID单取
     *
     * @param id
     * @return
     */
    SourceNavigation get(Integer id);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    Integer update(SourceNavigation record);

}