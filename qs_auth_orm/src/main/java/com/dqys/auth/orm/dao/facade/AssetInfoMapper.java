package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.AssetInfo;

public interface AssetInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AssetInfo record);

    int insertSelective(AssetInfo record);

    AssetInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AssetInfo record);

    int updateByPrimaryKey(AssetInfo record);
}