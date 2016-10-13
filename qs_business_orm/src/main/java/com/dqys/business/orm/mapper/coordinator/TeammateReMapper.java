package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeammateReMapper {
    /**
     * 删除参与人关联表
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 添加参与人关联表
     *
     * @param record
     * @return
     */
    Integer insert(TeammateRe record);

    Integer insertSelective(TeammateRe record);

    TeammateRe selectByPrimaryKey(Integer id);

    /**
     * 修改参与人关联表
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(TeammateRe record);

    Integer updateByPrimaryKey(TeammateRe record);

    /**
     * 条件查询
     *
     * @param teammateRe
     * @return
     */
    List<TeammateRe> selectSelective(TeammateRe teammateRe);

    /**
     * 查询出当前用户创建的所有的特定身份类型对象id
     *
     * @param objectType
     * @param userId
     * @param type
     * @return
     */
    List<Integer> listObjectIdByType(@Param("objectType") Integer objectType, @Param("userId") Integer userId,
                                         @Param("type") Integer type);

    /**
     * @param objectType
     * @param userId
     * @param objectId
     * @return
     */
    TeammateRe selectByObjectAndUser(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId, @Param("userId") Integer userId);

    Integer deleteByUserTeamId(@Param("userTeamIds") List<Integer> userTeamIds);
//根据userId 、objectId、objectType、type四个参数查询协作器团队信息
    List<TeammateRe> selectSelectiveByUserTeam(Map teamMap);
}