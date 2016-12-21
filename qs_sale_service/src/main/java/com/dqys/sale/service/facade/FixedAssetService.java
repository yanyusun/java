package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.orm.query.FixedAssetQuery;

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

    JsonResponse getDetail(Integer fixedAssetId);
}
