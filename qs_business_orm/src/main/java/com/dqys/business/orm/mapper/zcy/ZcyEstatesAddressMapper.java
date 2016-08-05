package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyEstatesAddress;

import java.util.List;

public interface ZcyEstatesAddressMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyEstatesAddress record);

    Integer insertSelective(ZcyEstatesAddress record);

    ZcyEstatesAddress selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyEstatesAddress record);

    Integer updateByPrimaryKey(ZcyEstatesAddress record);

    /**
     *条件搜索
     *
     */
    List<ZcyEstatesAddress> selectBySelective(ZcyEstatesAddress record);
}