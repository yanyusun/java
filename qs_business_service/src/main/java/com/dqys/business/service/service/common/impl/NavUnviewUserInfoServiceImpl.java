package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewUserInfoMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.NavUnviewRoleService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.NavUnviewEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by yan on 16-10-28.
 */
//// TODO: 16-10-28  mkf
public class NavUnviewUserInfoServiceImpl implements NavUnviewRoleService {
    @Autowired
    private NavUnviewUserInfoMapper navUnviewUserInfoMapper;

    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;

    @Override
    public List<SelectDto> getALLParentList(Integer navId,Integer object,Integer objectId) {
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
    public List<SelectDto> getList(Integer navId,Integer object,Integer objectId) {
        List<SelectDto> dtos = new ArrayList<>();
        List<Integer> navIds = new ArrayList<>();
        navIds.add(navId);
        setSelectDtoList(dtos, navIds);
        NoSQLWithRedisTool.removeValueObject(navId + "_" + NavUnviewEnum.USER_INFO);
        NoSQLWithRedisTool.setValueObject(navId + "_" + NavUnviewEnum.USER_INFO, dtos, 31L, TimeUnit.DAYS);
        return dtos;
    }

    private void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds) {
        List<Map> list = navUnviewUserInfoMapper.findNavNameByNavId(navIds);
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
    public void del(Integer navId,Integer object,Integer objectId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        navUnviewUserInfoMapper.delByNavId(navId, userId);
    }

    @Override
    public void add(Integer navId,Integer object,Integer objectId,List<Integer> unviewList) {
        navUnviewUserInfoMapper.insertSelectiveByUserInfo(navId, unviewList);
    }
}
