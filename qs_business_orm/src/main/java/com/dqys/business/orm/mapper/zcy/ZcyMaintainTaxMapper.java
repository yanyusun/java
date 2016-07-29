package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyMaintainTax;

import java.util.List;

public interface ZcyMaintainTaxMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyMaintainTax record);

    Integer insertSelective(ZcyMaintainTax record);

    ZcyMaintainTax selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyMaintainTax record);

    Integer updateByPrimaryKey(ZcyMaintainTax record);

    List<ZcyMaintainTax> selectBySelective(ZcyMaintainTax record);
}