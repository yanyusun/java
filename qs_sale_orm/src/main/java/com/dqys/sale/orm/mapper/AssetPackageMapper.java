package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.AssetPackage;
import com.dqys.sale.orm.pojo.UserBond;
import com.dqys.sale.orm.query.AssetPackageQuery;
import com.dqys.sale.orm.query.UserBondQuery;

import java.util.List;
import java.util.Map;

public interface AssetPackageMapper extends BaseMapper<AssetPackage> {
    List<AssetPackage> list(AssetPackageQuery query);

    Integer listCount(AssetPackageQuery query);

    //统计数据
    Map getAssetPackageCount();
}