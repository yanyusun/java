package com.dqys.business.orm.mapper.coordinator.impl;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.UserDetail;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.orm.pojo.zcy.dto.ZcyPawnDTO;
import com.dqys.business.orm.query.coordinator.ZcyListQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.misc.MessageUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Repository
public class CoordinatorMapperImpl extends BaseDao implements CoordinatorMapper {
    @Override
    public List<TeamDTO> getLenderOrAsset(Integer companyId, Integer objectId, Integer objectType, List<Integer> statusList) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getLenderOrAsset(companyId, objectId, objectType, statusList);
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

    @Override
    public Integer updateLender(LenderInfo lenderInfo) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).updateLender(lenderInfo);
    }

    @Override
    public List<Map<String, Object>> getUserIdByObjUserRelToLender(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getUserIdByObjUserRelToLender(objectType, objectId);
    }

    @Override
    public List<Map<String, Object>> getUserIdByObjUserRelToAsset(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getUserIdByObjUserRelToAsset(objectType, objectId);
    }

    @Override
    public Map<String, Object> getUserAndCompanyByUserId(Integer userId) {
        Map<String, Object> map = super.getSqlSession().getMapper(CoordinatorMapper.class).getUserAndCompanyByUserId(userId);
        return map;
    }

    @Override
    public List<Integer> getObjectIdList(@Param("objectType") Integer objectType, @Param("userId") Integer userId, @Param("status") Integer status) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getObjectIdList(objectType, userId, status);
    }

    @Override
    public Map<String, Object> getRealName(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId, @Param("type") Integer type) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getRealName(objectType, objectId, type);
    }

    @Override
    public List<ZcyPawnDTO> selectByZCYListPage(@Param("zcyListQuery") ZcyListQuery zcyListQuery) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).selectByZCYListPage(zcyListQuery);
    }

    @Override
    public Integer selectByZCYListPageCount(@Param("zcyListQuery") ZcyListQuery zcyListQuery) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).selectByZCYListPageCount(zcyListQuery);
    }

    @Override
    public List<Map<String, Object>> findLabel(Integer estatesId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).findLabel(estatesId);
    }

    @Override
    public List<Integer> findObjectIdByOURelation(@Param("userId") Integer userId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).findObjectIdByOURelation(userId, objectType);
    }

    @Override
    public List<Integer> findObjectIdByOURelationAndBusiness(Integer userId, Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).findObjectIdByOURelationAndBusiness(userId, objectType);
    }

    @Override
    public Map getCompanyAndUser(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType, @Param("userType") Integer userType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getCompanyAndUser(objectId, objectType, userType);
    }

    @Override
    public List<Map> findUserTypeByCompanyTeam(@Param("objectId") Integer objectId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).findUserTypeByCompanyTeam(objectId, objectType);
    }

    @Override
    public List<Integer> findObjectIdByAssetSourceAll(Integer userId, Integer value) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).findObjectIdByAssetSourceAll(userId, value);
    }

    @Override
    public UserDetail getUserDetail(Integer userId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getUserDetail(userId);
    }

    @Override
    public Integer getShiKanCount(Integer estatesId) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getShiKanCount(estatesId);
    }

    @Override
    public Map getCoordMessage(@Param("userId") Integer userId, @Param("objectId") Integer objectId, @Param("objectType") Integer objectType) {
        return super.getSqlSession().getMapper(CoordinatorMapper.class).getCoordMessage(userId, objectId, objectType);
    }


}
