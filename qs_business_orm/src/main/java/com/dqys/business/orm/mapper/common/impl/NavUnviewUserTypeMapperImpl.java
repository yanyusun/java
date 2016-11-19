package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewUserTypeMapper;
import com.dqys.business.orm.pojo.common.NavUnviewUserType;
import com.dqys.business.orm.query.common.NavUnviewUserTypeQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 2016/11/1.
 */
@Repository
@Primary
public class NavUnviewUserTypeMapperImpl extends BaseDao implements NavUnviewUserTypeMapper {
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).deleteByPrimaryKey(id);
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
    public Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).delByNavId(navId, userId, object, objectId);
    }

    @Override
    public Integer insertSelectiveByUserType(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).insertSelectiveByUserType(navId, unviewList, object, objectId);
    }

    @Override
    public List<Map> findNavNameByNavId(@Param("navIds") List<Integer> navIds, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).findNavNameByNavId(navIds, object, objectId);
    }

    @Override
    public int queryCount(NavUnviewUserTypeQuery query) {
        return super.getSqlSession().getMapper(NavUnviewUserTypeMapper.class).queryCount(query);
    }

}
