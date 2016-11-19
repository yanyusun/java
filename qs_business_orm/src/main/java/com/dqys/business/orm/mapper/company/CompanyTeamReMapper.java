package com.dqys.business.orm.mapper.company;


import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;
import org.apache.ibatis.annotations.Param;

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
     *
     * @param id
     * @return
     */
    CompanyTeamRe get(Integer id);

    /**
     * 条件统计
     *
     * @param companyTeamReQuery
     * @return
     */
    Integer queryCount(CompanyTeamReQuery companyTeamReQuery);

    /**
     * 条件获取分配器详情
     *
     * @param companyTeamReQuery
     * @return
     */
    List<CompanyTeamRe> queryList(CompanyTeamReQuery companyTeamReQuery);

    /**
     * 修改协作器与邀请人员的关系
     *
     * @param record
     */
    Integer update(CompanyTeamRe record);

    /**
     * 根据对象类型和被邀请公司Id以及受邀情况搜索对象ID
     *
     * @param type   对象类型
     * @param status 受邀状态
     * @param id     管理员ID
     */
    List<Integer> listObjectIdByTypeAndManager(@Param("type") Integer type, @Param("status") Integer status,
                                               @Param("id") Integer id);

    /**
     * 遍历所有已分配的对象ID
     *
     * @param type 对象类型
     */
    List<Integer> listAssigned(@Param("type") Integer type);

    List<CompanyTeamRe> teamReListByLenderIdAndUserid(@Param("objectId")Integer objectId, @Param("objectType")Integer objectType,
                                                      @Param("userId") Integer userId, @Param("status")Integer status);

}