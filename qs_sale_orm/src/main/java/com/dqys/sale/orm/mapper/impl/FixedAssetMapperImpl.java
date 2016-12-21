package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.FixedAssetMapper;
import com.dqys.sale.orm.pojo.FixedAsset;
import com.dqys.sale.orm.query.FixedAssetQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Repository
public class FixedAssetMapperImpl extends SaleBaseDao implements FixedAssetMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(FixedAssetMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(FixedAsset record) {
        return super.getSqlSession().getMapper(FixedAssetMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(FixedAsset record) {
        return super.getSqlSession().getMapper(FixedAssetMapper.class).insertSelective(record);
    }

    @Override
    public FixedAsset selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(FixedAssetMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(FixedAsset record) {
        return super.getSqlSession().getMapper(FixedAssetMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(FixedAsset record) {
        return super.getSqlSession().getMapper(FixedAssetMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(FixedAsset record) {
        return super.getSqlSession().getMapper(FixedAssetMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<FixedAsset> fixedList(FixedAssetQuery query) {
        return super.getSqlSession().getMapper(FixedAssetMapper.class).fixedList(query);
    }
}
