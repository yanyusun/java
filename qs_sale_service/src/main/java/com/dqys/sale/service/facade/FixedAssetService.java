package com.dqys.sale.service.facade;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.orm.pojo.AssetUserRe;
import com.dqys.sale.service.dto.FixedAssetDTO;
import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.Label;
import com.dqys.sale.orm.query.FixedAssetQuery;

import java.util.List;
import java.util.Map;

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

    AssetUserRe getAssetUserRe(Integer objectId, Integer objectType);

    /**
     * 固定资产详情
     *
     * @param fixedAssetId
     * @return
     */
    JsonResponse getDetail(Integer fixedAssetId);

    void getDisposeAndCollect(Integer id, Integer type, Map map);

    /**
     * 添加固定资产
     *
     * @param fixedAssetDTO
     * @return
     */
    JsonResponse addFixed_tx(FixedAssetDTO fixedAssetDTO);

    /**
     * 创建初始业务状态
     *
     * @param id
     * @param objectType
     * @return
     */
    Integer createBusiness(Integer id, Integer objectType);

    void addOtherEntity_tx(List<Label> labels, List<Dispose> disposes, List<AssetFile> assetFiles, Integer id, Integer objectType);

    JsonResponse updateFixed(FixedAssetDTO fixedAssetDTO);

    JsonResponse list(FixedAssetQuery fixedAssetQuery);

    //标签库
    JsonResponse getLable(String name);
}
