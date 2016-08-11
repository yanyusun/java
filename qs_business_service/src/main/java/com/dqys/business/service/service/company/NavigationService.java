package com.dqys.business.service.service.company;

import com.dqys.business.orm.pojo.company.Navigation;
import com.dqys.business.orm.query.company.NavigationQuery;
import com.dqys.business.service.dto.company.NavigationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yvan on 16/7/25.
 */
@Service
public interface NavigationService {

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
    NavigationDTO get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(Navigation record);

    /**
     * 根据父级Id查询子项
     * @param query
     * @return
     */
    List<NavigationDTO> queryList(NavigationQuery query);

    /**
     * 获取上级
     * @param id
     * @return
     */
    NavigationDTO getParent(Integer id);
}
