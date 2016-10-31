package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewCompanyMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.NavUnviewRoleService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-10-28.
 */
//// TODO: 16-10-28  mkf
public class NavUnviewUserInfoServiceImpl implements NavUnviewRoleService {
    @Autowired
    private NavUnviewCompanyMapper navUnviewCompanyMapper;

    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;

    @Override
    public List<SelectDto> getALLParentList(Integer navId) {
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
            }
        }
        setSelectDtoList(dtos, navIds);
        return dtos;
    }

    @Override
    public List<SelectDto> getList(Integer navId) {
        List<SelectDto> dtos = new ArrayList<>();
        List<Integer> navIds = new ArrayList<>();
        navIds.add(navId);
        setSelectDtoList(dtos, navIds);
        return dtos;
    }

    private void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds) {
        List<Map> list = navUnviewCompanyMapper.findNavNameByNavId(navIds);
        for (Map m : list) {
            SelectDto dto = new SelectDto(MessageUtils.transMapToInt(m, "id"), MessageUtils.transMapToString(m, "name"));
            dtos.add(dto);
        }
    }

    @Override
    public List<SelectDto> getIntList(Object o) {
        return null;
    }

    @Override
    public void del(Integer navId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        navUnviewCompanyMapper.delByNavId(navId, userId);
    }

    @Override
    public void add(Integer navId, List<Integer> unviewList) {
        navUnviewCompanyMapper.insertSelectiveByCompanyId(navId, unviewList);
    }
}
