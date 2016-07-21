package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class CoordinatorMapperImpl extends BaseDao implements CoordinatorMapper {
    @Override
    public List<TeamDTO> getLenderOrAsset(Integer companyId, Integer objectId, Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getLenderOrAsset(companyId, objectId, objectType);
    }

    @Override
    public Map<String, Object> getTaskOngoing(@Param("companyId") Integer companyId, @Param("userId") Integer userId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getTaskOngoing(companyId, userId, objectType);
    }

    @Override
    public Map<String, Object> getTaskRatio(@Param("companyId") Integer companyId, @Param("userId") Integer userId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getTaskRatio(companyId, userId, objectType);
    }

    @Override
    public List<Map<String, Object>> getPeopleNum(@Param("companyId") Integer companyId, @Param("objectId") Integer objectId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getPeopleNum(companyId, objectId, objectType);
    }

    @Override
    public List<Map<String, Object>> companyList(Integer objectId, Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).companyList(objectId, objectType);
    }

    @Override
    public Map<String, Object> getAdminUser(Integer companyId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getAdminUser(companyId);
    }

    @Override
    public List<Map<String, Object>> getCompanyUserList(String realName, Integer userId, Integer companyId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getCompanyUserList(realName, userId, companyId);
    }

    @Override
    public Map selectByUserTeamAndMateRe(Integer teammateId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).selectByUserTeamAndMateRe(teammateId);
    }

    @Override
    public Map getLastLeaveWord(Integer userId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getLastLeaveWord(userId);
    }

    @Override
    public Map selectByBusinessId(Integer objectType, Integer objectId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).selectByBusinessId(objectType, objectId);
    }

    @Override
    public List<LenderInfo> selectByLender(LenderInfo lenderInfo) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).selectByLender(lenderInfo);
    }

    @Override
    public LenderInfo getLenderInfo(Integer objectId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getLenderInfo(objectId);
    }

    @Override
    public AssetInfo getAssetInfo(Integer objectId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getAssetInfo(objectId);
    }
}
