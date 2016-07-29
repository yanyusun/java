package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyExpress;

import java.util.List;

public interface ZcyExpressMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyExpress record);

    Integer insertSelective(ZcyExpress record);

    ZcyExpress selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyExpress record);

    Integer updateByPrimaryKey(ZcyExpress record);

    List<ZcyExpress> selectBySelective(ZcyExpress record);
}