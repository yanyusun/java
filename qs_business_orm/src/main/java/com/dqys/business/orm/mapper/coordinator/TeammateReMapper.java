package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param teammateRe
     * @return
     */
    List<TeammateRe> selectSelective(TeammateRe teammateRe);

    /**
     * 查询出当前用户创建的所有的特定参与的特定类型对象id
     * @param type
     * @param userId
     * @return
     */
    List<Integer> listObjectIdByJoinType(@Param("type")Integer type, @Param("userId")Integer userId,
                                     @Param("joinType")Integer joinType);
}