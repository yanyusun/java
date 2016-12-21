package com.dqys.sale.service.impl;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.mapper.FixedAssetMapper;
import com.dqys.sale.orm.pojo.FixedAsset;
import com.dqys.sale.orm.query.FixedAssetQuery;
import com.dqys.sale.service.facade.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Service
public class FixedAssetServiceImpl implements FixedAssetService {
    @Autowired
    private FixedAssetMapper fixedAssetMapper;

    @Override
    public JsonResponse fixedList(FixedAssetQuery fixedAssetQuery) {
        List<FixedAsset> fixedAssetList = fixedAssetMapper.fixedList(fixedAssetQuery);
        return JsonResponseTool.success(fixedAssetList);
    }

    @Override
    public JsonResponse getDetail(Integer fixedAssetId) {
        FixedAsset fixedAsset = fixedAssetMapper.selectByPrimaryKey(fixedAssetId);
        if (fixedAsset != null) {

        }
        return JsonResponseTool.success(null);
    }

}
