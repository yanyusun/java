package com.dqys.business.service.service.common;

import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yan on 16-11-15.
 */
abstract class AbstractNavUnview implements NavUnviewService {
    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;
    @Override
    public List<SelectDto> getALLParentList(Integer navId, Integer object, Integer objectId) {
        List<SelectDto> dtos = new ArrayList<>();
        List<Integer> navIds = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            SourceNavigation nav = sourceNavigationMapper.get(navId);
            if (nav != null) {
                navIds.add(navId);
                if (nav.getPid() != 0) {
                    navId = nav.getPid();
                } else {
                    flag = false;
                }
            }else {
                flag = false;
            }
        }
        if (navIds.size() > 0) {
            setSelectDtoList(dtos, navIds, object, objectId);
        }
        return dtos;
    }

    @Override
    public List<SelectDto> getList(Integer navId, Integer object, Integer objectId) {
        List<SelectDto> dtos = new ArrayList<>();
        List<Integer> navIds = new ArrayList<>();
        navIds.add(navId);
        setSelectDtoList(dtos, navIds, object, objectId);
        return dtos;
    }

    protected abstract void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId);
}
