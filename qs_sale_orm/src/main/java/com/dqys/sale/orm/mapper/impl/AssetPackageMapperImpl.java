package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.AssetPackageMapper;
import com.dqys.sale.orm.mapper.UserBondMapper;
import com.dqys.sale.orm.pojo.AssetPackage;
import com.dqys.sale.orm.pojo.UserBond;
import com.dqys.sale.orm.query.AssetPackageQuery;
import com.dqys.sale.orm.query.UserBondQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/23.
 */
@Repository
public class AssetPackageMapperImpl extends SaleBaseDao implements AssetPackageMapper {

    @Override
    public List<AssetPackage> list(AssetPackageQuery query) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).list(query);
    }

    @Override
    public Integer listCount(AssetPackageQuery query) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).listCount(query);
    }

    @Override
    public Map getAssetPackageCount() {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).getAssetPackageCount();
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(AssetPackage record) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(AssetPackage record) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).insertSelective(record);
    }

    @Override
    public AssetPackage selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(AssetPackage record) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(AssetPackage record) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(AssetPackage record) {
        return super.getSqlSession().getMapper(AssetPackageMapper.class).updateByPrimaryKey(record);
    }
}
