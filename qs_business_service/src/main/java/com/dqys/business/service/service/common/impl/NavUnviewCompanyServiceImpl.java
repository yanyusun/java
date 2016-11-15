package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.mapper.common.NavUnviewCompanyMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.NavUnviewCompany;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.AbstractNavUnview;
import com.dqys.business.service.service.common.NavUnviewCompanyService;
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
public class NavUnviewCompanyServiceImpl extends AbstractNavUnview implements NavUnviewCompanyService {
    @Autowired
    private NavUnviewCompanyMapper navUnviewCompanyMapper;
    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;

    protected void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId) {
        if (navIds != null && navIds.size() == 0) {
            return;
        }
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
    public void del(Integer id) {
        navUnviewCompanyMapper.deleteByPrimaryKey(id);
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

    @Override
    protected SourceNavigationMapper getSourceNavigationMapper() {
        return sourceNavigationMapper;
    }
}
