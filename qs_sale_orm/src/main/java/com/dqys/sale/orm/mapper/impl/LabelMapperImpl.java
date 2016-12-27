package com.dqys.sale.orm.mapper.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.FixedAssetMapper;
import com.dqys.sale.orm.mapper.LabelMapper;
import com.dqys.sale.orm.pojo.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Repository
public class LabelMapperImpl extends SaleBaseDao implements LabelMapper {
    @Override
    public List<Label> selectByAssetId(@Param("assetId") Integer assetId, @Param("assetType") Integer assetType) {
        return super.getSqlSession().getMapper(LabelMapper.class).selectByAssetId(assetId, assetType);
    }

    @Override
    public List<Label> getLableList(String name) {
        return super.getSqlSession().getMapper(LabelMapper.class).getLableList(name);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LabelMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(Label record) {
        return super.getSqlSession().getMapper(LabelMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(Label record) {
        return super.getSqlSession().getMapper(LabelMapper.class).insertSelective(record);
    }

    @Override
    public Label selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LabelMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Label record) {
        return super.getSqlSession().getMapper(LabelMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(Label record) {
        return super.getSqlSession().getMapper(LabelMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(Label record) {
        return super.getSqlSession().getMapper(LabelMapper.class).updateByPrimaryKey(record);
    }
}
