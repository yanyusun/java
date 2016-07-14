package com.dqys.business.orm.mapper.company;


import com.dqys.business.orm.pojo.coordinator.CompanyTeam;

import java.util.List;

public interface CompanyTeamMapper {
    /**
     * 删除公司协作器
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加公司协作器
     *
     * @param record
     * @return
     */
    Integer insert(CompanyTeam record);

    /**
     * 根据Id获取
     * @param id
     * @return
     */
    CompanyTeam get(Integer id);

    /**
     * 根据邀请人Id
     * @param id
     * @return
     */
    List<CompanyTeam> listBySendId(Integer id);

}