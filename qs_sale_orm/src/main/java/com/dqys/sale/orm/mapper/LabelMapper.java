package com.dqys.sale.orm.mapper;


import com.dqys.sale.orm.pojo.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelMapper extends BaseMapper<Label> {

    List<Label> selectByAssetId(@Param("assetId") Integer assetId, @Param("assetType") Integer assetType);

}