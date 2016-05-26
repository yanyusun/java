package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.TATOInfo;

import java.util.List;

public interface TATOInfoMapper {

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     *  新增
     * @param record
     * @return
     */
    Integer insertSelective(TATOInfo record);

    /**
     * 主键查询
     * @param id
     * @return
     */
    TATOInfo selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer updateByPrimaryKey(TATOInfo record);

    /**
     * 分类查询全部
     * @param type
     * @return
     */
    List<TATOInfo> selectListByType(Integer type);


}