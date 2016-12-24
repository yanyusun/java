package com.dqys.sale.orm.mapper;

import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.Label;
import com.dqys.sale.orm.pojo.LabelRe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelReMapper extends BaseMapper<LabelRe> {


    List<Dispose> selectByAssetId(@Param("assetId") Integer assetId, @Param("objectType") Integer objectType);
}