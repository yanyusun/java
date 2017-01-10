package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.LabelReMapper;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.LabelRe;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Repository
public class LabelReMapperImpl extends SaleBaseDao implements LabelReMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LabelReMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).insertSelective(record);
    }

    @Override
    public LabelRe selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LabelReMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(LabelRe record) {
        return super.getSqlSession().getMapper(LabelReMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<Dispose> selectByAssetId(@Param("assetId") Integer assetId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(LabelReMapper.class).selectByAssetId(assetId, objectType);
    }

    @Override
    public Integer deleteByPrimaryKeyObject(@Param("assetId") Integer assetId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(LabelReMapper.class).deleteByPrimaryKeyObject(assetId, objectType);
    }
}
