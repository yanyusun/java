package com.dqys.business.orm.mapper.asset;


import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.query.asset.AssetQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetInfoMapper {

    /**
     * 逻辑删除资产包
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 新增资产包
     *
     * @param assetInfo
     * @return
     */
    Integer insert(AssetInfo assetInfo);

    /**
     * 修改资产包
     *
     * @param record
     * @return
     */
    Integer update(AssetInfo record);

    /**
     * 获取单个资产包
     *
     * @param id
     * @return
     */
    AssetInfo get(Integer id);

    /**
     * 统计所有资产包数量
     *
     * @return
     */
    Integer count();

    /**
     * 条件统计资产包数量
     *
     * @param assetQuery
     * @return
     */
    Integer queryCount(AssetQuery assetQuery);

    /**
     * 查询所有资产包
     *
     * @return
     */
    List<AssetInfo> listAll();

    /**
     * 条件获取资产包
     *
     * @param assetQuery
     * @return
     */
    List<AssetInfo> pageList(AssetQuery assetQuery);

    /**
     * 批量分配
     *
     * @param ids
     * @param id
     * @return
     */
    Integer assignedBatch(@Param("ids") List<Integer> ids, @Param("id") Integer id);

    /**
     * 根据资产包编号查询资产包id
     *
     * @param assetNo
     * @return
     */
    List<Integer> selectIdbyAssetNo(String assetNo);
}