package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.auth.orm.query.TUserTagQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TUserTag record);

    TUserTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserTag record);

    List<TUserTag> selectByUserId(@Param("userId") Integer uid);

    List<TUserTag> selectByQuery(TUserTagQuery query);
}