package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.service.dto.AssetPackageDTO;
import com.dqys.sale.orm.query.AssetPackageQuery;

/**
 * Created by mkfeng on 2016/12/23.
 */
public interface AssetPackageService {
    JsonResponse assetList(AssetPackageQuery query);

    JsonResponse addAsset_tx(AssetPackageDTO assetPackageDTO);

    JsonResponse getDetail(Integer assetId);

    JsonResponse updateAsset_tx(AssetPackageDTO assetPackageDTO);

    JsonResponse list(AssetPackageQuery query);
}
