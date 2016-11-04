package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewUserTypeMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.NavUnviewUserTypeService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.NavUnviewEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/1.
 */
@Repository
@Primary
public class NavUnviewUserTypeServiceImpl implements NavUnviewUserTypeService {

    @Autowired
    private NavUnviewUserTypeMapper navUnviewUserTypeMapper;

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
            }
        }
        setSelectDtoList(dtos, navIds, object, objectId);
        return dtos;
    }

    @Override
    public List<SelectDto> getList(Integer navId, Integer object, Integer objectId) {
        List<SelectDto> dtos = new ArrayList<>();
        List<Integer> navIds = new ArrayList<>();
        navIds.add(navId);
        setSelectDtoList(dtos, navIds, object, objectId);
        NoSQLWithRedisTool.removeValueObject(navId + "_" + NavUnviewEnum.USER_TYPE);
        NoSQLWithRedisTool.setValueObject(navId + "_" + NavUnviewEnum.USER_TYPE, dtos, 31L, TimeUnit.DAYS);
        return dtos;
    }

    private void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId) {
        List<Map> list = navUnviewUserTypeMapper.findNavNameByNavId(navIds, object, objectId);
        for (Map m : list) {
            SelectDto dto = new SelectDto(MessageUtils.transMapToInt(m, "id"), MessageUtils.transMapToInt(m, "reId"), MessageUtils.transMapToString(m, "showName"));
            dtos.add(dto);
        }
    }


    @Override
    public void del(Integer navId, Integer object, Integer objectId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        navUnviewUserTypeMapper.delByNavId(navId, userId, object, objectId);
    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, List<Integer> unviewList) {
        navUnviewUserTypeMapper.insertSelectiveByUserType(navId, unviewList, object, objectId);
    }

    @Override
    public SelectDto get(Integer navId, Integer object, Integer objectId, Integer reId) {
        return null;
    }

    @Override
    public void del(Integer navId, Integer object, Integer objectId, Integer reId) {

    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, Integer reId) {

    }
}
