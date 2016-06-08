package com.dqys.auth.orm.dao.impl.asset;

import com.dqys.auth.orm.dao.facade.asset.AssetInfoMapper;
import com.dqys.auth.orm.pojo.entering.AssetInfo;
import com.dqys.auth.orm.query.asset.AssetQuery;
import com.dqys.core.base.BaseDao;
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
}
