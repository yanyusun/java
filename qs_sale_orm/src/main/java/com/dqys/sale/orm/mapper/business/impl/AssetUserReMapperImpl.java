package com.dqys.sale.orm.mapper.business.impl;

import com.dqys.core.base.SaleBaseDao;
import com.dqys.sale.orm.mapper.business.AssetUserReMapper;
import com.dqys.sale.orm.pojo.AssetUserRe;
import com.dqys.sale.orm.query.AssetUserReQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/26.
 */
@Repository
public class AssetUserReMapperImpl extends SaleBaseDao implements AssetUserReMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(AssetUserRe record) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).insert(record);
    }

    @Override
    public Integer insertSelective(AssetUserRe record) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).insertSelective(record);
    }

    @Override
    public AssetUserRe selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(AssetUserRe record) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByPrimaryKeyWithBLOBs(AssetUserRe record) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public Integer updateByPrimaryKey(AssetUserRe record) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public List<AssetUserRe> selectByUserRe(AssetUserRe userRe) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).selectByUserRe(userRe);
    }

    @Override
    public List<AssetUserRe> selectByUserReList(AssetUserReQuery query) {
        return super.getSqlSession().getMapper(AssetUserReMapper.class).selectByUserReList(query);
    }
}
