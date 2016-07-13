package com.dqys.business.orm.mapper.coordinator;


import com.dqys.business.orm.pojo.coordinator.TeammateRe;

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
}