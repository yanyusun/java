package com.dqys.business.orm.mapper.common;


import com.dqys.business.orm.pojo.common.NavUnviewCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 分类公司不可见关系
 * Created by yan on 16/10/27.
 */
public interface NavUnviewCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NavUnviewCompany record);

    int insertSelective(NavUnviewCompany record);

    NavUnviewCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NavUnviewCompany record);

    int updateByPrimaryKey(NavUnviewCompany record);

    Integer delByNavId(@Param("navId") Integer navId, @Param("userId") Integer userId);

    Integer insertSelectiveByCompanyId(@Param("navId") Integer navId, @Param("unviewList") List<Integer> unviewList);

    /**
     * @param navId
     * @return bt_source_nav表的name和id
     */
    List<Map> findNavNameByNavId(List<Integer> navIds);
}