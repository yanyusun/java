package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.FixedAsset;
import com.dqys.sale.orm.query.FixedAssetQuery;

import java.util.List;

public interface FixedAssetMapper extends BaseMapper<FixedAsset> {

    List<FixedAsset> fixedList(FixedAssetQuery query);

    Integer fixedListCount(FixedAssetQuery query);
}