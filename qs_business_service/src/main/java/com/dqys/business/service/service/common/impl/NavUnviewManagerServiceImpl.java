package com.dqys.business.service.service.common.impl;

import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.dto.sourceAuth.UnviewReIdMap;
import com.dqys.business.service.service.common.*;
import com.dqys.business.service.utils.common.NavUnviewServerAgent;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.RoleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 资料实勘权限管理者
 * Created by yan on 16-11-1.
 */
@Repository
@Primary
public class NavUnviewManagerServiceImpl implements NavUnviewManagerService {
    @Autowired
    private NavUnviewUserTypeService navUnviewUserTypeService;
    @Autowired
    private NavUnviewCompanyService navUnviewCompanyService;
    @Autowired
    private NavUnviewRoleService navUnviewRoleService;
    @Autowired
    private NavUnviewUserInfoService navUnviewUserInfoService;
    @Autowired
    private NavUnviewCompanyMapper navUnviewCompanyMapper;
    @Autowired
    private NavUnviewUserInfoMapper navUnviewUserInfoMapper;

    /**
     * 所有可以选择的用户类型
     */
    private List<SelectDto> userTypeInitList = null;
    /**
     * 所有可以选择的角色类型
     */
    private List<SelectDto> roleInitList=null;


    @Override
    public SelectDtoMap getAll(Integer navId, Integer object, Integer objectId) {
        return getAll(navId, object, objectId, null);
    }


    @Override
    public SelectDtoMap getNewALL(Integer navId, Integer object, Integer objectId, UnviewReIdMap unviewReIdMap) {
        return getAll(navId, object, objectId, unviewReIdMap);
    }

    /**
     * 根据unviewReIdMap的值判断是否需要更新,并返回最新结果
     *
     * @param navId
     * @param object
     * @param objectId
     * @param unviewReIdMap
     * @return
     */
    private SelectDtoMap getAll(Integer navId, Integer object, Integer objectId, UnviewReIdMap unviewReIdMap) {
        SelectDtoMap selectDtoMap = new SelectDtoMap();
        //角色列表
        List<SelectDto> userTypeInitList = getUserTypeInitList();
        NavUnviewServerAgent userTypeAgent = new NavUnviewServerAgent(navUnviewUserTypeService, userTypeInitList);
        if (unviewReIdMap.getUserTypeList() != null && unviewReIdMap.getUserTypeList().size() == 0) {//判断是否要更新
            userTypeAgent.reset(navId, object, objectId, unviewReIdMap.getUserTypeList());
        }
        selectDtoMap.setUserTypeList(userTypeAgent.getSelectOptions(navId, object, objectId));
        //公司列表
        List<SelectDto> companyInitList = getCompanyInitList(userTypeAgent.getSelectedDtoList(navId, object, objectId));
        NavUnviewServerAgent companyAgent = new NavUnviewServerAgent(navUnviewCompanyService, companyInitList);
        if (unviewReIdMap.getCompanyList() != null && unviewReIdMap.getCompanyList().size() == 0) {
            companyAgent.reset(navId, object, objectId, unviewReIdMap.getCompanyList());
        }
        selectDtoMap.setCompanyList(companyAgent.getSelectOptions(navId, object, objectId));
        //角色列表
        List<SelectDto> roleInitList = getRoleInitList();
        NavUnviewServerAgent roleAgent = new NavUnviewServerAgent(navUnviewRoleService, roleInitList);
        if (unviewReIdMap.getRoleList() != null && unviewReIdMap.getRoleList().size() == 0) {
            roleAgent.reset(navId, object, objectId, unviewReIdMap.getRoleList());
        }
        selectDtoMap.setRoleList(roleAgent.getSelectOptions(navId, object, objectId));
        //用户列表
        List<SelectDto> userInfoInitList = getUserInitList(
                roleAgent.getSelectedDtoList(navId, object, objectId), companyAgent.getSelectedDtoList(navId, object, objectId)
                , object, objectId);
        NavUnviewServerAgent userInfoAgent = new NavUnviewServerAgent(navUnviewUserInfoService, userInfoInitList);
        if (unviewReIdMap.getUserList() != null && unviewReIdMap.getUserList().size() == 0) {
            userInfoAgent.reset(navId, object, objectId, unviewReIdMap.getUserList());
        }
        selectDtoMap.setUserList(userInfoAgent.getSelectOptions(navId, object, objectId));
        return selectDtoMap;
    }

    private List<SelectDto> getUserTypeInitList() {
        if (userTypeInitList == null) {
            userTypeInitList = new ArrayList<>();
            //普通人员
            SelectDto commonSelectDto = new SelectDto();
            commonSelectDto.setReId(UserInfoEnum.USER_TYPE_COMMON.getValue());
            commonSelectDto.setShowName("普通人员");
            userTypeInitList.add(commonSelectDto);
            //平台
            SelectDto adminSelectDto = new SelectDto();
            adminSelectDto.setReId(UserInfoEnum.USER_TYPE_ADMIN.getValue());
            adminSelectDto.setShowName("平台");
            userTypeInitList.add(adminSelectDto);
            //委托
            SelectDto entrustSelectDto = new SelectDto();
            entrustSelectDto.setReId(UserInfoEnum.USER_TYPE_ENTRUST.getValue());
            entrustSelectDto.setShowName("委托");
            userTypeInitList.add(entrustSelectDto);
            //催收
            SelectDto collectionSelectDto = new SelectDto();
            collectionSelectDto.setReId(UserInfoEnum.USER_TYPE_COLLECTION.getValue());
            collectionSelectDto.setShowName("催收");
            userTypeInitList.add(collectionSelectDto);
            //司法
            SelectDto judiciarySelectDto = new SelectDto();
            judiciarySelectDto.setReId(UserInfoEnum.USER_TYPE_JUDICIARY.getValue());
            judiciarySelectDto.setShowName("司法");
            userTypeInitList.add(judiciarySelectDto);
            //中介
            SelectDto intermediarySelectDto = new SelectDto();
            intermediarySelectDto.setReId(UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue());
            intermediarySelectDto.setShowName("中介");
            userTypeInitList.add(intermediarySelectDto);
        }
        return userTypeInitList;
    }

    /**
     * 根据用户类型list查找对应的所有公司
     *
     * @param selectUserTypeList
     * @return
     */
    private List<SelectDto> getCompanyInitList(List<SelectDto> selectUserTypeList) {
        // TODO: 16-11-3 mkf
        List<Integer> userTypes = null;//用户类型
        List<SelectDto> dtos = new ArrayList<>();
        for (SelectDto dto : selectUserTypeList) {
            if (!userTypes.contains(dto.getReId())) {
                userTypes.add(dto.getReId());
            }
        }
        List<Map> list = navUnviewCompanyMapper.selectCompanyByUserType(userTypes);//查询对应类型的所有公司
        for (Map m : list) {
            SelectDto dto = new SelectDto(null, MessageUtils.transMapToInt(m, "reId"), MessageUtils.transMapToString(m, "showName"));
            dtos.add(dto);
        }
        return dtos;
    }

    public List<SelectDto> getRoleInitList() {
        if (roleInitList == null) {
            roleInitList = new ArrayList<>();
            //管理员
            SelectDto adminSelectDto = new SelectDto();
            adminSelectDto.setReId(RoleTypeEnum.ADMIN.getValue());
            adminSelectDto.setShowName("管理员");
            roleInitList.add(adminSelectDto);
            //管理者
            SelectDto regulatorSelectDto = new SelectDto();
            regulatorSelectDto.setReId(RoleTypeEnum.REGULATOR.getValue());
            regulatorSelectDto.setShowName("管理者");
            roleInitList.add(regulatorSelectDto);
            //普通员工
            SelectDto generalSelectDto = new SelectDto();
            generalSelectDto.setReId(RoleTypeEnum.GENERAL.getValue());
            generalSelectDto.setShowName("普通员工");
            roleInitList.add(generalSelectDto);
            //所属人
            SelectDto theirSelectDto = new SelectDto();
            theirSelectDto.setReId(RoleTypeEnum.THEIR.getValue());
            theirSelectDto.setShowName("所属人");
            roleInitList.add(theirSelectDto);
        }
        return roleInitList;
    }

    /**
     * 得到对应公司的对应角色的所有用户
     *
     * @param selectRoleList    用户角色list
     * @param selectCompanyList 公司list
     * @param objectType        对象类型:借款人,资料源
     * @param objectId          对象id
     * @return
     */
    public List<SelectDto> getUserInitList(List<SelectDto> selectRoleList, List<SelectDto> selectCompanyList, Integer objectType, Integer objectId) {
        //// TODO: 16-11-3 mkf
        List<Integer> roles = null;//角色
        List<Integer> companyIds = null;//公司
        List<SelectDto> dtos = new ArrayList<>();
        for (SelectDto dto : selectRoleList) {
            if (!roles.contains(dto.getReId())) {
                roles.add(dto.getReId());
            }
        }
        for (SelectDto dto : selectCompanyList) {
            if (!companyIds.contains(dto.getReId())) {
                companyIds.add(dto.getReId());
            }
        }
        Map their = null;
        if (roles.contains(RoleTypeEnum.THEIR.getValue())) {
            their.put("objectId", objectId);
            their.put("objectType", objectType);
        }
        List<Map> list = navUnviewUserInfoMapper.selectUserInfoByRoleAndCompanyId(roles, companyIds, their);//根据角色和公司获取相应用户信息，角色中存在所属人就去查询协作器
        for (Map m : list) {
            SelectDto dto = new SelectDto(null, MessageUtils.transMapToInt(m, "reId"), MessageUtils.transMapToString(m, "showName"));
            dtos.add(dto);
        }
        // TODO: 16-11-4 mkf 查询对象的所属人,建议所属人单独查询,如果需要先查询到分配器,那就再协作器service增加 list<协作器>f(objectType,objectId的方法)
        return dtos;
    }

}
