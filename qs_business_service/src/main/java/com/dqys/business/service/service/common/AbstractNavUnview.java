package com.dqys.business.service.service.common;

import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.sourceAuth.SelectDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yan on 16-11-15.
 */
public abstract class AbstractNavUnview implements NavUnviewService {
    @Override
    public List<SelectDto> getALLParentList(Integer navId, Integer object, Integer objectId) {
        List<SelectDto> dtos = new ArrayList<>();
        List<Integer> navIds = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            SourceNavigation nav = getSourceNavigationMapper().get(navId);
            if (nav != null) {
                if (nav.getPid() != 0) {
                    navId = nav.getPid();
                    navIds.add(navId);
                } else {
                    flag = false;
                }
            } else {
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

    @Override
    public SelectDto get(Integer navId, Integer object, Integer objectId, Integer reId) {
        List<SelectDto> dtos = new ArrayList<>();
        List<Integer> navIds = new ArrayList<>();
        navIds.add(navId);
        setSelectDtoList(dtos, navIds, object, objectId);
        for (SelectDto dto : dtos) {
            if (dto != null && dto.getReId().intValue() == reId) {
                return dto;
            }
        }
        return null;
    }

    protected abstract SourceNavigationMapper getSourceNavigationMapper();


    protected abstract void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId);
}
