package com.dqys.business.orm.mapper.common;

import com.dqys.business.orm.pojo.common.SourceNavigation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SourceNavigationMapper {
    /**
     * 数据删除
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
    Integer insert(SourceNavigation record);

    /**
     * 根据ID单取
     *
     * @param id
     * @return
     */
    SourceNavigation get(Integer id);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    Integer updateByPrimaryKeySelective(SourceNavigation record);

    /**
     * 获取借款人的可视化资料实勘分类列表
     * @return
     */
    List<SourceNavigation> listByTypeAndLenderId(@Param("lenderId") Integer lenderId,@Param("estatesId") Integer estatesId, @Param("type")Integer type);

    /**
     * 获取借款人的可视化资料实勘分类列表
     * @return
     */
    List<SourceNavigation> listByTypeAndLenderIdSite(@Param("lenderId") Integer lenderId,@Param("estatesId") Integer estatesId, @Param("type")Integer type,@Param("site")Integer site);


    List<SourceNavigation> listByTypeAndLenderIdAndPid(@Param("lenderId") Integer lenderId,@Param("estatesId") Integer estatesId, @Param("type")Integer type,@Param("pid") Integer pid);

    /**
     * 查询自定义或者公共的导航栏
     * @return
     */
    List<SourceNavigation> ListByIsCustom(Integer isCustom);
    //// TODO: 17-1-17 mkf
    SourceNavigation selectDetail(Integer id);

}