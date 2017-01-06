package com.dqys.sale.orm.mapper;

import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DisposeMapper extends BaseMapper<Dispose> {
    List<Dispose> selectByAssetId(@Param("assetId") Integer assetId, @Param("objectType") Integer objectType);

    Integer deleteByPrimaryKeyObject(@Param("assetId") Integer assetId, @Param("objectType") Integer objectType);
}