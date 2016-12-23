package com.dqys.sale.orm.mapper;

import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.FixedAsset;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetFileMapper extends BaseMapper<AssetFile> {

    List<AssetFile> selectByAssetId(@Param("assetId") Integer assetId, @Param("objectType") Integer objectType);
}