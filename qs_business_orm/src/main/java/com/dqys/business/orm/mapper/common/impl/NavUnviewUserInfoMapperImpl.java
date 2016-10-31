package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewRoleMapper;
import com.dqys.business.orm.mapper.common.NavUnviewUserInfoMapper;
import com.dqys.business.orm.pojo.common.NavUnviewUserInfo;
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
    public Integer delByNavId(Integer navId, Integer userId) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).delByNavId(navId, userId);
    }

    @Override
    public Integer insertSelectiveByUserInfo(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).insertSelectiveByUserInfo(navId, unviewList);
    }

    @Override
    public List<Map> findNavNameByNavId(List<Integer> navIds) {
        return super.getSqlSession().getMapper(NavUnviewUserInfoMapper.class).findNavNameByNavId(navIds);
    }
}
