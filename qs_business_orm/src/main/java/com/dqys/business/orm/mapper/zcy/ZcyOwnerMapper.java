package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyOwner;

import java.util.List;

public interface ZcyOwnerMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyOwner record);

    Integer insertSelective(ZcyOwner record);

    ZcyOwner selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyOwner record);

    Integer updateByPrimaryKey(ZcyOwner record);

    List<ZcyOwner> selectBySelective(ZcyOwner record);
}