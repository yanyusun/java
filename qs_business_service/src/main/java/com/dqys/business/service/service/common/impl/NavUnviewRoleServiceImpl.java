package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewRoleMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.NavUnviewRole;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.AbstractNavUnview;
import com.dqys.business.service.service.common.NavUnviewRoleService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.NavUnviewEnum;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-10-28.
 */
//// TODO: 16-10-28  mkf
@Repository
@Primary
public class NavUnviewRoleServiceImpl extends AbstractNavUnview implements NavUnviewRoleService {
    @Autowired
    private NavUnviewRoleMapper navUnviewRoleMapper;

    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;

    protected void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId) {
        if (navIds != null && navIds.size() == 0) {
            return;
        }
        List<Map> list = navUnviewRoleMapper.findNavNameByNavId(navIds, object, objectId);
        for (Map m : list) {
            SelectDto dto = new SelectDto(MessageUtils.transMapToInt(m, "id"), MessageUtils.transMapToInt(m, "reId"), MessageUtils.transMapToString(m, "showName"));
            dtos.add(dto);
        }
    }

    @Override
    public void del(Integer navId, Integer object, Integer objectId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        navUnviewRoleMapper.delByNavId(navId, userId, object, objectId);
    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, List<Integer> unviewList) {
        navUnviewRoleMapper.insertSelectiveByRoleType(navId, unviewList, object, objectId);
    }


    @Override
    public void del(Integer id) {
        navUnviewRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, Integer reId) {
        Integer userId = UserSession.getCurrent().getUserId();
        NavUnviewRole nav = new NavUnviewRole();
        nav.setRoleType(reId);
        nav.setNavId(navId);
        nav.setObject(object);
        nav.setObjectId(objectId);
        nav.setOperUser(userId);
        navUnviewRoleMapper.insertSelective(nav);
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
        return NavUnviewEnum.ROLE.getValue();
    }

    @Override
    protected SourceNavigationMapper getSourceNavigationMapper() {
        return sourceNavigationMapper;
    }
}
