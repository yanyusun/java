package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.utils.AssetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yvan on 16/6/6.
 */
@Service
@Primary
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetInfoMapper assetInfoMapper;

    @Override
    public Integer add(AssetInfo assetInfo) {
        assetInfo.setAssetNo(AssetUtil.createAssetCode());
        Integer addResult = assetInfoMapper.insert(assetInfo);
        if(addResult.equals(1)) {
            return assetInfo.getId();
        }else{
            return addResult;
        }
    }

    @Override
    public Integer updateById(AssetInfo assetInfo) {
        return assetInfoMapper.update(assetInfo);
    }

    @Override
    public Integer delete(Integer id) {
        return assetInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AssetInfo getById(Integer id) {
        return assetInfoMapper.get(id);
    }

    @Override
    public List<AssetInfo> listAll() {
        return assetInfoMapper.listAll();
    }

    @Override
    public Integer count() {
        return assetInfoMapper.count();
    }

    @Override
    public Integer queryCount(AssetQuery assetQuery) {
        return assetInfoMapper.queryCount(assetQuery);
    }

    @Override
    public List<AssetInfo> pageList(AssetQuery assetQuery) {
        return assetInfoMapper.pageList(assetQuery);
    }
}
