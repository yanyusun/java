package com.dqys.business.service.service;

import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.core.model.JsonResponse;

/**
 * Created by Yvan on 16/6/1.
 */
public interface AssetService {

    /**
     * 增加一个资产包
     *
     * @param assetDTO
     * @return
     */
    JsonResponse add(AssetDTO assetDTO);

    /**
     * 修改资产包
     *
     * @param assetDTO
     * @return
     */
    JsonResponse updateById(AssetDTO assetDTO);

    /**
     * 逻辑删除资产包
     *
     * @param id
     * @return
     */
    JsonResponse delete(Integer id);

    /**
     * 根据ID获取单个资产包
     *
     * @param id
     * @return
     */
    JsonResponse getById(Integer id);

    /**
     * 条件查询总条数
     *
     * @param assetQuery
     * @return
     */
    JsonResponse queryCount(AssetQuery assetQuery);

    /**
     * 分页获取资产包
     *
     * @return
     */
    JsonResponse pageList(AssetListQuery assetQuery);

    /**
     * 批量分配
     *
     * @param ids
     * @return
     */
    JsonResponse assignedBatch(String ids, Integer id);

    /**
     * 根据资产包信息查询所属借款人
     *
     * @param id
     * @return
     */
    JsonResponse listLender(Integer id);

}
