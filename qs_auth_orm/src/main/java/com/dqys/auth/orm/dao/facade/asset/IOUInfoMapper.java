package com.dqys.auth.orm.dao.facade.asset;

import com.dqys.auth.orm.pojo.entering.IOUInfo;

public interface IOUInfoMapper {
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
    Integer insert(IOUInfo record);

    /**
     * 获取单个
     * @param id
     * @return
     */
    IOUInfo get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(IOUInfo record);

}