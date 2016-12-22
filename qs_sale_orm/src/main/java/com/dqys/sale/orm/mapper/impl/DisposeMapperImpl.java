package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.DisposeMapper;
import com.dqys.sale.orm.pojo.Dispose;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Repository
public class DisposeMapperImpl extends SaleBaseDao implements DisposeMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(DisposeMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(Dispose record) {
        return super.getSqlSession().getMapper(DisposeMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(Dispose record) {
        return super.getSqlSession().getMapper(DisposeMapper.class).insertSelective(record);
    }

    @Override
    public Dispose selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(DisposeMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Dispose record) {
        return super.getSqlSession().getMapper(DisposeMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(Dispose record) {
        return super.getSqlSession().getMapper(DisposeMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(Dispose record) {
        return super.getSqlSession().getMapper(DisposeMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<Dispose> selectByAssetId(@Param("assetId") Integer assetId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(DisposeMapper.class).selectByAssetId(assetId, objectType);
    }
}
