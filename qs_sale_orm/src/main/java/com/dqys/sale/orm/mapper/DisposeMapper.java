package com.dqys.sale.orm.mapper;

import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;

import java.util.List;

public interface DisposeMapper extends BaseMapper<Dispose> {
    List<Dispose> selectByAssetId(Integer assetId);
}