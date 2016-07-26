package com.dqys.business.orm.mapper.company;

import com.dqys.business.orm.pojo.company.Navigation;
import com.dqys.business.orm.query.company.NavigationQuery;

import java.util.List;

/**
 * 导航栏配置
 */
public interface NavigationMapper {
    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    Integer insert(Navigation record);

    /**
     * 根据Id获取
     * @param id
     * @return
     */
    Navigation get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(Navigation record);

    /**
     * 根据父级Id查询自相
     * @param navigationQuery
     * @return
     */
    List<Navigation> queryList(NavigationQuery navigationQuery);

    /**
     * 获取上级
     * @param id
     * @return
     */
    Navigation getParent(Integer id);
}