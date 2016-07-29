package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyMaintain;

import java.util.List;

public interface ZcyMaintainMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyMaintain record);

    Integer insertSelective(ZcyMaintain record);

    ZcyMaintain selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyMaintain record);

    Integer updateByPrimaryKey(ZcyMaintain record);

    List<ZcyMaintain> selectBySelective(ZcyMaintain record);
}