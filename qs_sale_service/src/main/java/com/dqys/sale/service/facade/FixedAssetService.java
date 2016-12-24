package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.service.dto.FixedAssetDTO;
import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.Label;
import com.dqys.sale.orm.query.FixedAssetQuery;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
public interface FixedAssetService {
    /**
     * 查询固定资产列表
     *
     * @param fixedAssetQuery
     * @return
     */
    JsonResponse fixedList(FixedAssetQuery fixedAssetQuery);

    /**
     * 固定资产详情
     *
     * @param fixedAssetId
     * @return
     */
    JsonResponse getDetail(Integer fixedAssetId);

    /**
     * 添加固定资产
     *
     * @param fixedAssetDTO
     * @return
     */
    JsonResponse addFixed_tx(FixedAssetDTO fixedAssetDTO);

    void addOtherEntity_tx(List<Label> labels, List<Dispose> disposes, List<AssetFile> assetFiles, Integer id, Integer objectType);

    JsonResponse updateFixed(FixedAssetDTO fixedAssetDTO);
}
