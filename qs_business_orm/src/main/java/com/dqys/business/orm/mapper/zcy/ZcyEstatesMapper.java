package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyEstates;

import java.util.List;
import java.util.Map;

public interface ZcyEstatesMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyEstates record);

    Integer insertSelective(ZcyEstates record);

    ZcyEstates selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyEstates record);

    Integer updateByPrimaryKeyWithBLOBs(ZcyEstates record);

    Integer updateByPrimaryKey(ZcyEstates record);

    List<ZcyEstates> selectBySelective(ZcyEstates record);

    /**
     * 查询资产源详情
     * @param estatesId
     * @return
     */
    Map<String,Object> selectDetailByZcy(Integer estatesId);
}