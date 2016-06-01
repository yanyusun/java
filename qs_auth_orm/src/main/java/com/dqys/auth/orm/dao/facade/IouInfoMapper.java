package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.IouInfo;

public interface IouInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IouInfo record);

    int insertSelective(IouInfo record);

    IouInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IouInfo record);

    int updateByPrimaryKey(IouInfo record);
}