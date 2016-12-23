package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.AssetFileMapper;
import com.dqys.sale.orm.pojo.AssetFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Repository
public class AssetFileMapperImpl extends SaleBaseDao implements AssetFileMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(AssetFileMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(AssetFile record) {
        return super.getSqlSession().getMapper(AssetFileMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(AssetFile record) {
        return super.getSqlSession().getMapper(AssetFileMapper.class).insertSelective(record);
    }

    @Override
    public AssetFile selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(AssetFileMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(AssetFile record) {
        return super.getSqlSession().getMapper(AssetFileMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(AssetFile record) {
        return super.getSqlSession().getMapper(AssetFileMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(AssetFile record) {
        return super.getSqlSession().getMapper(AssetFileMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<AssetFile> selectByAssetId(@Param("assetId") Integer assetId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(AssetFileMapper.class).selectByAssetId(assetId, objectType);
    }
}
