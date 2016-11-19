package com.dqys.business.orm.mapper.asset.impl;


import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/6/7.
 */
@Repository
@Primary
public class AssetInfoMapperImpl extends BaseDao implements AssetInfoMapper {
    @Override
    public Integer count() {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).count();
    }

    @Override
    public Integer queryCount(AssetQuery assetQuery) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).queryCount(assetQuery);
    }

    @Override
    public List<AssetInfo> listAll() {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).listAll();
    }

    @Override
    public List<AssetInfo> pageList(AssetQuery assetQuery) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).pageList(assetQuery);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(AssetInfo assetInfo) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).insert(assetInfo);
    }

    @Override
    public AssetInfo get(Integer id) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).get(id);
    }

    @Override
    public Integer update(AssetInfo record) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).update(record);
    }

    @Override
    public Integer assignedBatch(@Param("ids") List<Integer> ids, @Param("id") Integer id) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).assignedBatch(ids, id);
    }

    @Override
    public List<Integer> selectIdbyAssetNo(String assetNo) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).selectIdbyAssetNo(assetNo);
    }

    @Override
    public List<Integer> findObjectIdByAsset(@Param("userId") Integer userId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(AssetInfoMapper.class).findObjectIdByAsset(userId, objectType);
    }
}
