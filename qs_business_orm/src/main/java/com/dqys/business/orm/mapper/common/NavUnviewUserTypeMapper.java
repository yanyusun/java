package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.NavUnviewUserType;
import com.dqys.business.orm.query.common.NavUnviewUserTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NavUnviewUserTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NavUnviewUserType record);

    int insertSelective(NavUnviewUserType record);

    NavUnviewUserType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavUnviewUserType record);

    int updateByPrimaryKey(NavUnviewUserType record);

    Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId, @Param("object") Integer object, @Param("objectId") Integer objectId);

    Integer insertSelectiveByUserType(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList, @Param("object") Integer object, @Param("objectId") Integer objectId);

    List<Map> findNavNameByNavId(@Param("navIds") List<Integer> navIds, @Param("object") Integer object, @Param("objectId") Integer objectId);

    int queryCount(NavUnviewUserTypeQuery query);
}