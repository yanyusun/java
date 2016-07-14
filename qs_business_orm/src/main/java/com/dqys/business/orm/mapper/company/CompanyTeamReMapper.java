package com.dqys.business.orm.mapper.company;


import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;

import java.util.List;

public interface CompanyTeamReMapper {
    /**
     * 删除协作器与邀请人员的关系
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加协作器与邀请人员的关系
     *
     * @param record
     * @return
     */
    Integer insert(CompanyTeamRe record);

    /**
     * 根据Id获取
     * @param id
     * @return
     */
    CompanyTeamRe get(Integer id);

    /**
     * 条件获取分配器详情
     * @param companyTeamReQuery
     * @return
     */
    List<CompanyTeamRe> queryList(CompanyTeamReQuery companyTeamReQuery);

    /**
     * 修改协作器与邀请人员的关系
     *
     * @param record
     * @return
     */
    Integer update(CompanyTeamRe record);

}