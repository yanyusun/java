package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.NavUnviewUserInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 分类用户不可见关系
 * Created by yan on 16/10/27.
 */
public interface NavUnviewUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NavUnviewUserInfo record);

    int insertSelective(NavUnviewUserInfo record);

    NavUnviewUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavUnviewUserInfo record);

    int updateByPrimaryKey(NavUnviewUserInfo record);

    Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId, @Param("object") Integer object, @Param("objectId") Integer objectId);

    Integer insertSelectiveByUserInfo(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList, @Param("object") Integer object, @Param("objectId") Integer objectId);

    List<Map> findNavNameByNavId(@Param("navIds") List<Integer> navIds, @Param("object") Integer object, @Param("objectId") Integer objectId);

    TeammateRe findUserTeamReByObject(@Param("userId") Integer userId, @Param("object") Integer object, @Param("objectId") Integer objectId);
}