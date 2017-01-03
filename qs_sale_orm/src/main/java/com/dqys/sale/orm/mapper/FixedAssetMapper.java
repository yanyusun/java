package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.FixedAsset;
import com.dqys.sale.orm.query.FixedAssetQuery;

import java.util.List;
import java.util.Map;

public interface FixedAssetMapper extends BaseMapper<FixedAsset> {

    List<FixedAsset> fixedList(FixedAssetQuery query);

    Integer fixedListCount(FixedAssetQuery query);

    //    固定资产统计
    Map getFixedAssetCount();
}