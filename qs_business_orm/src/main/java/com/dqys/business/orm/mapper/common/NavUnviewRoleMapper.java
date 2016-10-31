package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.NavUnviewRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *分类角色不可见关系
 * Created by yan on 16/10/27.
 */
public interface NavUnviewRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NavUnviewRole record);

    int insertSelective(NavUnviewRole record);

    NavUnviewRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavUnviewRole record);

    int updateByPrimaryKey(NavUnviewRole record);

    Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId);

    Integer insertSelectiveByRoleType(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList);

    List<Map> findNavNameByNavId(List<Integer> navIds);

}