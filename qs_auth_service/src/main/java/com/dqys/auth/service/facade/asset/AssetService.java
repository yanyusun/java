package com.dqys.auth.service.facade.asset;

import com.dqys.auth.orm.pojo.asset.AssetInfo;
import com.dqys.auth.orm.query.asset.AssetQuery;

import java.util.List;

/**
 * Created by Yvan on 16/6/1.
 */
public interface AssetService {

    /**
     * 增加一个资产包
     * @param assetInfo
     * @return
     */
    Integer add(AssetInfo assetInfo);

    /**
     * 修改资产包
     * @param assetInfo
     * @return
     */
    Integer updateById(AssetInfo assetInfo);

    /**
     * 逻辑删除资产包
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据ID获取单个资产包
     * @param id
     * @return
     */
    AssetInfo getById(Integer id);

    /**
     * 获取所有的资产包
     * @return
     */
    List<AssetInfo> listAll();

    /**
     * 统计资产包(*)
     * @return
     */
    Integer count();

    /**
     * 条件查询总条数
     * @param assetQuery
     * @return
     */
    Integer queryCount(AssetQuery assetQuery);

    /**
     * 分页获取资产包
     * @return
     */
    List<AssetInfo> pageList(AssetQuery assetQuery);

}
