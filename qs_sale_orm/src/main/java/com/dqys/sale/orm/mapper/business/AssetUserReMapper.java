package com.dqys.sale.orm.mapper.business;


import com.dqys.sale.orm.mapper.BaseMapper;
import com.dqys.sale.orm.pojo.AssetUserRe;

import java.util.List;

public interface AssetUserReMapper extends BaseMapper<AssetUserRe> {
    /**
     * 条件查询用户与对象的关系是否收藏和处置
     *
     * @param userRe
     * @return
     */
    List<AssetUserRe> selectByUserRe(AssetUserRe userRe);
}