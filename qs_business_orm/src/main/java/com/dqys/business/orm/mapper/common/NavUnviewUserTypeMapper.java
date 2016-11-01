package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.NavUnviewUserType;
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

    Integer insertSelectiveByUserType(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList);

    List<Map> findNavNameByNavId(List<Integer> navIds);

    Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId);
}