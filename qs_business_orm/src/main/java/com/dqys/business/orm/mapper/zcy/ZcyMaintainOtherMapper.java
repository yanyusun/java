package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyMaintainOther;

import java.util.List;

public interface ZcyMaintainOtherMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyMaintainOther record);

    Integer insertSelective(ZcyMaintainOther record);

    ZcyMaintainOther selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyMaintainOther record);

    Integer updateByPrimaryKey(ZcyMaintainOther record);

    List<ZcyMaintainOther> selectBySelective(ZcyMaintainOther record);
}