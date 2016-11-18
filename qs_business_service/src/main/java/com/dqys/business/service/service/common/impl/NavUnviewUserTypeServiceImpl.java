package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewUserTypeMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.NavUnviewUserType;
import com.dqys.business.orm.query.common.NavUnviewUserTypeQuery;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.AbstractNavUnview;
import com.dqys.business.service.service.common.NavUnviewUserTypeService;
import com.dqys.business.service.utils.common.NavUtil;
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
 * Created by yan on 2016/11/1.
 */
@Repository
@Primary
public class NavUnviewUserTypeServiceImpl extends AbstractNavUnview implements NavUnviewUserTypeService {

    @Autowired
    private NavUnviewUserTypeMapper navUnviewUserTypeMapper;
    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;


    protected void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId) {
        if (navIds != null && navIds.size() == 0) {
            return;
        }
        List<Map> list = navUnviewUserTypeMapper.findNavNameByNavId(navIds, object, objectId);
        if (list != null && list.size() > 0) {
            for (Map m : list) {
                SelectDto dto = new SelectDto(MessageUtils.transMapToInt(m, "id"), MessageUtils.transMapToInt(m, "reId"), MessageUtils.transMapToString(m, "showName"));
                dtos.add(dto);
            }
        } else {
            for (Integer navId : navIds) {
                List<SelectDto> selectDtos = NavUtil.getSelectDtoList(navId, NavUnviewEnum.USER_TYPE.getValue());
                if (selectDtos != null) {
                    dtos.addAll(selectDtos);
                }
            }
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
    public void del(Integer id) {
        navUnviewUserTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, Integer reId) {
        NavUnviewUserType nav = new NavUnviewUserType();
        nav.setUserType(reId);
        nav.setNavId(navId);
        nav.setObject(object);
        nav.setObjectId(objectId);
        navUnviewUserTypeMapper.insertSelective(nav);
    }

    @Override
    public boolean hasDiy(Integer navId, Integer object, Integer objectId) {
        NavUnviewUserTypeQuery navUnviewUserTypeQuery = new NavUnviewUserTypeQuery();
        navUnviewUserTypeQuery.setNavId(navId);
        navUnviewUserTypeQuery.setObject(object);
        navUnviewUserTypeQuery.setObjectId(objectId);
        if (navUnviewUserTypeMapper.queryCount(navUnviewUserTypeQuery) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<SelectDto> getInit(Integer navId) {
        List<SelectDto> dtos = new ArrayList<>();
        dtos.addAll(getALLParentList(navId, 0, 0));
        dtos.addAll(getList(navId, 0, 0));
        return dtos;
    }

    @Override
    public int getType() {
        return NavUnviewEnum.USER_TYPE.getValue();
    }

    @Override
    protected SourceNavigationMapper getSourceNavigationMapper() {
        return sourceNavigationMapper;
    }
}
