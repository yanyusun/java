package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyKey;

import java.util.List;

public interface ZcyKeyMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyKey record);

    Integer insertSelective(ZcyKey record);

    ZcyKey selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyKey record);

    Integer updateByPrimaryKey(ZcyKey record);

    List<ZcyKey> selectBySelective(ZcyKey record);
}