package com.dqys.flowbusiness.orm.mapper;


import com.dqys.flowbusiness.orm.pojo.Business;

public interface PublicBusinessMapper {
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
    Integer insert(Business record);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    Business get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(Business record);
}