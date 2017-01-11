package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.common.NavUnviewUserInfoMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.NavUnviewUserInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.AbstractNavUnview;
import com.dqys.business.service.service.common.NavUnviewUserInfoService;
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
public class NavUnviewUserInfoServiceImpl  extends AbstractNavUnview implements NavUnviewUserInfoService{
    @Autowired
    private NavUnviewUserInfoMapper navUnviewUserInfoMapper;

    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;

    protected void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId) {
        if (navIds != null && navIds.size() == 0) {
            return;
        }
        List<Map> list = navUnviewUserInfoMapper.findNavNameByNavId(navIds, object, objectId);
        for (Map m : list) {
            SelectDto dto = new SelectDto(MessageUtils.transMapToInt(m, "id"), MessageUtils.transMapToInt(m, "reId"), MessageUtils.transMapToString(m, "showName"));
            //在showName后面补加级别（是否为所属人）
            TeammateRe re = navUnviewUserInfoMapper.findUserTeamReByObject(dto.getReId(), object, objectId);
            String typeName = "";
            if (re != null && re.getType() != null) {
                if (re.getType() == TeammateReEnum.TYPE_ADMIN.getValue().intValue()) {
                    typeName = TeammateReEnum.TYPE_ADMIN.getName();
                } else if (re.getType() == TeammateReEnum.TYPE_AUXILIARY.getValue().intValue()) {
                    typeName = TeammateReEnum.TYPE_AUXILIARY.getName();
                } else if (re.getType() == TeammateReEnum.TYPE_PARTICIPATION.getValue().intValue()) {
                    typeName = TeammateReEnum.TYPE_PARTICIPATION.getName();
                }
            }
            dto.setShowName(dto.getShowName() + "|" + typeName);
            dtos.add(dto);
        }
    }


    @Override
    public void del(Integer navId, Integer object, Integer objectId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        navUnviewUserInfoMapper.delByNavId(navId, userId, object, objectId);
    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, List<Integer> unviewList) {
        navUnviewUserInfoMapper.insertSelectiveByUserInfo(navId, unviewList, object, objectId);
    }

    @Override
    public void del(Integer id) {
        navUnviewUserInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Integer navId, Integer object, Integer objectId, Integer reId) {
        Integer userId = UserSession.getCurrent().getUserId();
        NavUnviewUserInfo nav = new NavUnviewUserInfo();
        nav.setUserId(reId);
        nav.setNavId(navId);
        nav.setObject(object);
        nav.setObjectId(objectId);
        nav.setOperUser(userId);
        navUnviewUserInfoMapper.insertSelective(nav);
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
        return NavUnviewEnum.USER_INFO.getValue();
    }

    @Override
    protected SourceNavigationMapper getSourceNavigationMapper() {
        return sourceNavigationMapper;
    }
}
