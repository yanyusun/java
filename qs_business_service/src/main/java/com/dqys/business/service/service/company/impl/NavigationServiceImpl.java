package com.dqys.business.service.service.company.impl;

import com.dqys.business.orm.mapper.company.NavigationMapper;
import com.dqys.business.orm.pojo.company.Navigation;
import com.dqys.business.orm.query.company.NavigationQuery;
import com.dqys.business.service.dto.company.NavigationDTO;
import com.dqys.business.service.service.company.NavigationService;
import com.dqys.business.service.utils.company.NavigationServiceUtils;
import com.dqys.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/7/25.
 */
@Repository
@Primary
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        if(CommonUtil.checkParam(id)){
            return null;
        }
        return navigationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(Navigation record) {
        if(CommonUtil.checkParam(record, record.getName(), record.getValue())){
            return null;
        }
        Integer result = navigationMapper.insert(record);
        if(CommonUtil.checkResult(result)){
            return null;
        }else{
            return record.getId();
        }
    }

    @Override
    public NavigationDTO get(Integer id) {
        if(CommonUtil.checkParam(id)){
            return null;
        }
        return NavigationServiceUtils.toNavigationDTO(navigationMapper.get(id));
    }

    @Override
    public Integer update(Navigation record) {
        if(CommonUtil.checkParam(record, record.getId())){
            return null;
        }
        return navigationMapper.update(record);
    }

    @Override
    public List<NavigationDTO> queryList(NavigationQuery query) {
        if(CommonUtil.checkParam(query, query.getPid())){
            return null;
        }
        List<Navigation> navigationList = navigationMapper.queryList(query);
        if(CommonUtil.checkParam(navigationList)){
            return null;
        }
        return NavigationServiceUtils.toNavigationDTO(navigationList);
    }

    @Override
    public NavigationDTO getParent(Integer id) {
        if(CommonUtil.checkParam(id)){
            return null;
        }
        return NavigationServiceUtils.toNavigationDTO(navigationMapper.getParent(id));
    }
}
