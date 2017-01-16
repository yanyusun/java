package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.SourceSource;

import java.util.List;

public interface SourceSourceMapper {

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
    Integer insert(SourceSource record);

    /**
     * 根据ID单取
     *
     * @param id
     * @return
     */
    SourceSource get(Integer id);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    Integer update(SourceSource record);

    /**
     * 根据资源信息获取文件信息
     *
     * @param id
     * @return
     */
    List<SourceSource> listBySourceId(Integer id);

    Integer deleteByPrimaryKeyBySourceId(Integer id);

}