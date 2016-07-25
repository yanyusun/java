package com.dqys.business.orm.mapper.business;

import com.dqys.business.orm.pojo.business.BusinessObjRe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 通过对象类型,id,以及业务中的审核状态查询对象ID
     * @param type 对象类型
     * @param status 业务状态
     * @param userId 操作人
     * @return
     */
    List<Integer> listIdByTypeIdStatusUser(@Param("type")Integer type, @Param("status")Integer status, @Param("userId") Integer userId);

}