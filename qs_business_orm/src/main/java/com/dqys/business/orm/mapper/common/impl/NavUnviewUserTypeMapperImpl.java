package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewUserInfoMapper;
import com.dqys.business.orm.mapper.common.NavUnviewUserTypeMapper;
import com.dqys.business.orm.pojo.common.NavUnviewUserType;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/1.
 */
public class NavUnviewUserTypeMapperImpl extends BaseDao implements NavUnviewUserTypeMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(NavUnviewUserType record) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).insert(record);
    }

    @Override
    public int insertSelective(NavUnviewUserType record) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).insertSelective(record);
    }

    @Override
    public NavUnviewUserType selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(NavUnviewUserType record) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NavUnviewUserType record) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public Integer delByNavId(Integer navId, Integer userId) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).delByNavId(navId, userId);
    }

    @Override
    public Integer insertSelectiveByUserType(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).insertSelectiveByUserType(navId, unviewList);
    }

    @Override
    public List<Map> findNavNameByNavId(List<Integer> navIds) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).findNavNameByNavId(navIds);
    }
}
