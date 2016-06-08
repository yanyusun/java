package com.dqys.auth.service.impl;

import com.dqys.auth.orm.pojo.entering.AssetInfo;
import com.dqys.auth.orm.query.asset.AssetQuery;
import com.dqys.auth.service.facade.asset.AssetService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yvan on 16/6/6.
 */
@Service
@Primary
public class AssetServiceImpl implements AssetService {

    @Override
    public Integer add(AssetInfo assetInfo) {
        return null;
    }

    @Override
    public Integer updateById(AssetInfo assetInfo) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public AssetInfo getById(Integer id) {
        return null;
    }

    @Override
    public List<AssetInfo> listAll() {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Integer queryCount(AssetQuery assetQuery) {
        return null;
    }

    @Override
    public List<AssetInfo> pageList(AssetQuery assetQuery) {
        return null;
    }
}
