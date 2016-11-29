package com.dqys.business.orm.mapper.asset.impl;

import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.query.asset.LenderQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
@Repository
@Primary
public class LenderInfoMapperImpl extends BaseDao implements LenderInfoMapper {

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(LenderInfo lenderInfo) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).update(lenderInfo);
    }

    @Override
    public Integer insert(LenderInfo record) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).insert(record);
    }

    @Override
    public LenderInfo get(Integer id) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).get(id);
    }

    @Override
    public List<LenderInfo> listByAssetId(Integer id) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).listByAssetId(id);
    }

    @Override
    public List<LenderInfo> queryList(LenderQuery lenderQuery) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).queryList(lenderQuery);
    }

    @Override
    public Integer queryCount(LenderQuery lenderQuery) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).queryCount(lenderQuery);
    }

    @Override
    public List<Integer> likeList(String str) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).likeList(str);
    }

    @Override
    public List<Integer> lenderAllByObjectUserRelation(@Param("userId") Integer userId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).lenderAllByObjectUserRelation(userId, objectType);
    }

    @Override
    public LenderInfo getLenderBySumMoney(Integer assetId) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).getLenderBySumMoney(assetId);
    }

    @Override
    public List<Integer> findObjectIdByLender(@Param("userId") Integer userId, @Param("objectType") Integer objectType, @Param("roleType") Integer roleType) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).findObjectIdByLender(userId, objectType, roleType);
    }

    @Override
    public List<Integer> selectByAssetId(Integer assetId) {
        return super.getSqlSession().getMapper(LenderInfoMapper.class).selectByAssetId(assetId);
    }

}
