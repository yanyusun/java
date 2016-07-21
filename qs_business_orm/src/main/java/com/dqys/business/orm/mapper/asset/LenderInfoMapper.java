package com.dqys.business.orm.mapper.asset;

import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.query.asset.LenderQuery;

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

    /**
     * 根据资产包Id查询借款人
     * @param id
     * @return
     */
    List<LenderInfo> listByAssetId(Integer id);

    /**
     * 条件搜索借款人
     * @param lenderQuery
     * @return
     */
    List<LenderInfo> queryList(LenderQuery lenderQuery);

    /**
     * 条件统计
     * @param lenderQuery
     * @return
     */
    Integer queryCount(LenderQuery lenderQuery);


}