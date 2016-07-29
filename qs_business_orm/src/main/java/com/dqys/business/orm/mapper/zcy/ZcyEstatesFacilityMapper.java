package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyEstatesFacility;

import java.util.List;

public interface ZcyEstatesFacilityMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyEstatesFacility record);

    Integer insertSelective(ZcyEstatesFacility record);

    ZcyEstatesFacility selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyEstatesFacility record);

    Integer updateByPrimaryKey(ZcyEstatesFacility record);

    List<ZcyEstatesFacility> selectBySelective(ZcyEstatesFacility record);
}