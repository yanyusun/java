package com.dqys.business.orm.mapper.asset;


import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.query.asset.LenderQuery;

import java.util.List;

public interface ContactInfoMapper {
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
    Integer insert(ContactInfo record);

    /**
     * 获取单个实例
     * @param id
     * @return
     */
    ContactInfo get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(ContactInfo record);

    /**
     * 条件查询
     * @param lenderQuery
     * @return
     */
    List<ContactInfo> queryList(LenderQuery lenderQuery);
}