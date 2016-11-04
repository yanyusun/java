package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewRoleMapper;
import com.dqys.business.orm.mapper.common.NavUnviewUserInfoMapper;
import com.dqys.business.orm.pojo.common.NavUnviewUserInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/31.
 */
public class NavUnviewUserInfoMapperImpl extends BaseDao implements NavUnviewUserInfoMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(NavUnviewUserInfo record) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).insert(record);
    }

    @Override
    public int insertSelective(NavUnviewUserInfo record) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).insertSelective(record);
    }

    @Override
    public NavUnviewUserInfo selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(NavUnviewUserInfo record) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NavUnviewUserInfo record) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).delByNavId(navId, userId, object, objectId);
    }

    @Override
    public Integer insertSelectiveByUserInfo(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).insertSelectiveByUserInfo(navId, unviewList, object, objectId);
    }

    @Override
    public List<Map> findNavNameByNavId(@Param("navIds") List<Integer> navIds, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).findNavNameByNavId(navIds, object, objectId);
    }

    @Override
    public TeammateRe findUserTeamReByObject(@Param("userId") Integer userId, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).findUserTeamReByObject(userId, object, objectId);
    }

    @Override
    public List<Map> selectUserInfoByRoleAndCompanyId(@Param("roles") List<Integer> roles, @Param("companyIds") List<Integer> companyIds, @Param("their") Map their) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).selectUserInfoByRoleAndCompanyId(roles, companyIds, their);
    }
}
