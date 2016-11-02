package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewCompanyMapper;
import com.dqys.business.orm.mapper.common.NavUnviewRoleMapper;
import com.dqys.business.orm.pojo.common.NavUnviewRole;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/31.
 */
public class NavUnviewRoleMapperImpl extends BaseDao implements NavUnviewRoleMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insert(NavUnviewRole record) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).insert(record);
    }

    @Override
    public int insertSelective(NavUnviewRole record) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).insertSelective(record);
    }

    @Override
    public NavUnviewRole selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(NavUnviewRole record) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NavUnviewRole record) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).delByNavId(navId, userId, object, objectId);
    }

    @Override
    public Integer insertSelectiveByRoleType(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).insertSelectiveByRoleType(navId, unviewList, object, objectId);
    }

    @Override
    public List<Map> findNavNameByNavId(@Param("navIds") List<Integer> navIds, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewRoleMapper.class).findNavNameByNavId(navIds, object, objectId);
    }
}
