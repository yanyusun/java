package com.dqys.business.orm.mapper.business;

import com.dqys.business.orm.pojo.business.BusinessObjRe;
import org.apache.ibatis.annotations.Param;

public interface BusinessObjReMapper {
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
    Integer insert(BusinessObjRe record);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    BusinessObjRe get(Integer id);

    BusinessObjRe getByObject(@Param("type")Integer type, @Param("id") Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(BusinessObjRe record);

}