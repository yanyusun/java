package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.SourceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SourceInfoMapper {

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Integer insert(SourceInfo record);

    /**
     * 根据ID单取
     *
     * @param id
     * @return
     */
    SourceInfo get(Integer id);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    Integer update(SourceInfo record);

    /**
     * 根据借款人ID查询当前分类的资源数据
     *
     * @param navId
     * @param lenderId
     * @return
     */
    SourceInfo getByNavIdAndLenderId(@Param("navId") Integer navId, @Param("lenderId") Integer lenderId, @Param("estatesId") Integer estatesId);

    List<SourceInfo> selectByNavId(Integer navId);

    Integer deleteByPrimaryKeyByNavId(Integer navId);
}