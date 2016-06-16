package com.dqys.business.orm.mapper.asset;

import com.dqys.business.orm.pojo.asset.LenderInfo;

import java.util.List;

public interface LenderInfoMapper {
    /**
     * 逻辑删除关系
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     *  新增借款人关系
     * @param record
     * @return
     */
    Integer insert(LenderInfo record);

    /**
     * 修改联系关系
     * @param lenderInfo
     * @return
     */
    Integer update(LenderInfo lenderInfo);

    /**
     * 获取借款人联系信息
     * @param id
     * @return
     */
    LenderInfo get(Integer id);



}