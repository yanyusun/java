package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewCompanyMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.NavUnviewCompany;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.NavUnviewCompanyService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.NavUnviewEnum;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-10-28.
 */
//// TODO: 16-10-28  mkf
@Repository
@Primary
public class NavUnviewCompanyServiceImpl implements NavUnviewCompanyService {
    @Autowired
    private NavUnviewCompanyMapper navUnviewCompanyMapper;

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
//        NoSQLWithRedisTool.removeValueObject(navId + "_" + NavUnviewEnum.COMPANY);
//        NoSQLWithRedisTool.setValueObject(navId + "_" + NavUnviewEnum.COMPANY, dtos, 31L, TimeUnit.DAYS);
        return dtos;
    }

    private void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId) {
        List<Map> list = navUnviewCompanyMapper.findNavNameByNavId(navIds, object, objectId);
        for (Map m : list) {
            SelectDto dto = new SelectDto(MessageUtils.transMapToInt(m, "id"), MessageUtils.transMapToInt(m, "reId"), MessageUtils.transMapToString(m, "showName"));
            dtos.add(dto);
        }
    }


    @Override
    public void del(Integer navId, Integer object, Integer objectId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        navUnviewCompanyMapper.delByNavId(navId, userId, object, objectId);
    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, List<Integer> unviewList) {
        navUnviewCompanyMapper.insertSelectiveByCompanyId(navId, unviewList, object, objectId);
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

    @Override
    public void del(Integer navId, Integer object, Integer objectId, Integer reId) {
        SelectDto dto = get(navId, object, objectId, reId);
        if (dto != null) {
            navUnviewCompanyMapper.deleteByPrimaryKey(dto.getId());
        }
    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, Integer reId) {
        NavUnviewCompany nav = new NavUnviewCompany();
        nav.setCompanyId(reId);
        nav.setNavId(navId);
        nav.setObject(object);
        nav.setObjectId(objectId);
        navUnviewCompanyMapper.insertSelective(nav);
    }

    @Override
    public boolean hasDiy(Integer navId, Integer object, Integer objectId) {
        return true;
    }

    @Override
    public List<SelectDto> getInit(Integer navId) {
        return null;
    }

    @Override
    public int getType() {
        return NavUnviewEnum.COMPANY.getValue();
    }
}
