package com.dqys.business.orm.mapper.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewCompanyMapper;
import com.dqys.business.orm.pojo.common.NavUnviewCompany;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-10-27.
 */
@Repository
@Primary
public class NavUnviewCompanyMapperImpl extends BaseDao implements NavUnviewCompanyMapper {

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public int insert(NavUnviewCompany record) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).insert(record);
    }

    @Override
    public int insertSelective(NavUnviewCompany record) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).insertSelective(record);
    }

    @Override
    public NavUnviewCompany selectByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(NavUnviewCompany record) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NavUnviewCompany record) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).updateByPrimaryKey(record);
    }

    @Override
    public Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).delByNavId(navId, userId, object, objectId);
    }

    @Override
    public Integer insertSelectiveByCompanyId(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).insertSelectiveByCompanyId(navId, unviewList, object, objectId);
    }

    @Override
    public List<Map> findNavNameByNavId(@Param("navIds") List<Integer> navIds, @Param("object") Integer object, @Param("objectId") Integer objectId) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).findNavNameByNavId(navIds, object, objectId);
    }

    @Override
    public List<Map> selectCompanyByUserType(List<Integer> userTypes) {
        return super.getSqlSession().getMapper(NavUnviewCompanyMapper.class).selectCompanyByUserType(userTypes);
    }
}
