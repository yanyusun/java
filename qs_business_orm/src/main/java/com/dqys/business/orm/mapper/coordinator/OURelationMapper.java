package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.OURelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OURelationMapper {
    /**
     * 删除 操作人员被操作事物关系
     *
     * @return
     */
    Integer deleteByPrimaryKey(OURelation record);

    /**
     * 添加操作人员被操作事物关系
     *
     * @param record
     * @return
     */
    Integer insert(OURelation record);

    Integer insertSelective(OURelation record);

    OURelation selectByPrimaryKey(Integer id);

    //    <!--条件查询操作人与事物关系-->
    List<OURelation> selectBySelective(OURelation record);

    /**
     * 修改操作人员被操作事物关系
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(OURelation record);

    Integer updateByPrimaryKey(OURelation record);

    /**
     * 物理删除业务表
     *
     * @param userTeamsIds
     * @return
     */
    Integer deleteByUserTeamId(@Param("userTeamIds") List<Integer> userTeamIds);

    //    查询公司业务流转关系-
    List<OURelation> findByOURelation(@Param("userId") Integer userId, @Param("objectType") Integer objectType);

    /**
     * 关联查询事物关系表
     * @param userId 用户id
     * @param lenderId 借款人id
     * @param objectType 借款人对象类型
     * @return
     */
    List<OURelation> selectOURelationByUserTeam(@Param("userId")Integer userId, @Param("lenderId")Integer lenderId,@Param("objectType") Integer objectType);
}