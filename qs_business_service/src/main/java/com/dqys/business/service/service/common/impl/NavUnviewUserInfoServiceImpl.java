package com.dqys.business.service.service.common.impl;

import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.common.NavUnviewUserInfoMapper;
import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.common.NavUnviewRoleService;
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
 * Created by yan on 16-10-28.
 */
//// TODO: 16-10-28  mkf
@Primary
@Repository
public class NavUnviewUserInfoServiceImpl implements NavUnviewRoleService {
    @Autowired
    private NavUnviewUserInfoMapper navUnviewUserInfoMapper;

    @Autowired
    private SourceNavigationMapper sourceNavigationMapper;

    @Autowired
    private CoordinatorService coordinatorService;

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
        NoSQLWithRedisTool.removeValueObject(navId + "_" + NavUnviewEnum.USER_INFO);
        NoSQLWithRedisTool.setValueObject(navId + "_" + NavUnviewEnum.USER_INFO, dtos, 31L, TimeUnit.DAYS);
        return dtos;
    }

    private void setSelectDtoList(List<SelectDto> dtos, List<Integer> navIds, Integer object, Integer objectId) {
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
}
