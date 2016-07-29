package com.dqys.business.orm.mapper.asset;


import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.query.asset.ContactQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContactInfoMapper {
    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 逻辑删除联系人
     *
     * @param mode
     * @param id
     * @return
     */
    Integer deleteByMode(@Param("mode") String mode, @Param("id") Integer id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Integer insert(ContactInfo record);

    /**
     * 获取单个实例
     *
     * @param id
     * @return
     */
    ContactInfo get(Integer id);

    /**
     * 根据类型ID获取特定类型联系人信息
     *
     * @param model
     * @param type
     * @param id
     * @return
     */
    ContactInfo getByModel(@Param("model") String model, @Param("type") Integer type, @Param("id") Integer id);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    Integer update(ContactInfo record);

    /**
     * 条件查询
     *
     * @param contactQuery
     * @return
     */
    List<ContactInfo> queryList(ContactQuery contactQuery);

    /**
     * 根据类型ID获取所有类型联系人信息
     *
     * @param model
     * @param id
     * @return
     */
    List<ContactInfo> listByMode(@Param("model") String model, @Param("id") Integer id);


}