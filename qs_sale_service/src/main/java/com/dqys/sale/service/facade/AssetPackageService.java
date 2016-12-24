package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.orm.dto.AssetPackageDTO;
import com.dqys.sale.orm.query.AssetPackageQuery;

/**
 * Created by mkfeng on 2016/12/23.
 */
public interface AssetPackageService {
    JsonResponse assetList(AssetPackageQuery query);

    JsonResponse addAsset(AssetPackageDTO assetPackageDTO);

    JsonResponse getDetail(Integer assetId);
}
