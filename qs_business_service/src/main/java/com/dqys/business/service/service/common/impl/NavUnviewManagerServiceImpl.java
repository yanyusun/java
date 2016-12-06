package com.dqys.business.service.service.common.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.common.NavUnviewCompanyMapper;
import com.dqys.business.orm.mapper.common.NavUnviewUserInfoMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.zcy.ZcyEstatesMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.zcy.ZcyEstates;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.dto.sourceAuth.UnviewReIdMap;
import com.dqys.business.service.service.common.*;
import com.dqys.business.service.utils.common.NavUnviewServerAgent;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TUserTagMapper tUserTagMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private ZcyEstatesMapper zcyEstatesMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;

    /**
     * 所有可以选择的用户类型
     */
    private List<SelectDto> userTypeInitList = null;
    /**
     * 所有可以选择的角色类型
     */
    private List<SelectDto> roleInitList = null;


    @Override
    public SelectDtoMap getAll(Integer navId, Integer object, Integer objectId) {
        return resetAndGetNewALL(navId, object, objectId, null);
    }

    @Override
    public SelectDtoMap resetAndGetNewALL(Integer navId, Integer object, Integer objectId, UnviewReIdMap unviewReIdMap) {
        SelectDtoMap selectDtoMap = new SelectDtoMap();
        //用户类型列表
        List<SelectDto> userTypeInitList = getUserTypeInitList();
        NavUnviewServerAgent userTypeAgent = new NavUnviewServerAgent(navUnviewUserTypeService, userTypeInitList);
        if (unviewReIdMap != null && unviewReIdMap.getUserType() != null) {//判断是否要更新
            userTypeAgent.initCacheReset(navId, object, objectId, unviewReIdMap.getUserType());
        }
        selectDtoMap.setUserTypeList(userTypeAgent.getSelectOptions(navId, object, objectId));
        //公司列表
        List<SelectDto> companyInitList = getCompanyInitList(userTypeAgent.getSelectedDtoList(navId, object, objectId));
        if (unviewReIdMap != null && unviewReIdMap.getCompanySearchKey() != null) {
            filter(companyInitList, unviewReIdMap.getCompanySearchKey());
        }
        NavUnviewServerAgent companyAgent = new NavUnviewServerAgent(navUnviewCompanyService, companyInitList);
        if (unviewReIdMap != null && unviewReIdMap.getCompanyId() != null) {
            companyAgent.reset(navId, object, objectId, unviewReIdMap.getCompanyId());
        }
        selectDtoMap.setCompanyList(companyAgent.getSelectOptions(navId, object, objectId));
        //角色列表
        List<SelectDto> roleInitList = getRoleInitList();
        NavUnviewServerAgent roleAgent = new NavUnviewServerAgent(navUnviewRoleService, roleInitList);
        if (unviewReIdMap != null && unviewReIdMap.getRoleType() != null) {
            roleAgent.reset(navId, object, objectId, unviewReIdMap.getRoleType());
        }
        selectDtoMap.setRoleList(roleAgent.getSelectOptions(navId, object, objectId));
        //用户列表
        List<SelectDto> userInfoInitList = getUserInitList(
                roleAgent.getSelectedDtoList(navId, object, objectId), companyAgent.getSelectedDtoList(navId, object, objectId)
                , object, objectId);
        if (unviewReIdMap != null && unviewReIdMap.getUserSearchKey() != null) {
            filter(userInfoInitList, unviewReIdMap.getUserSearchKey());
        }
        NavUnviewServerAgent userInfoAgent = new NavUnviewServerAgent(navUnviewUserInfoService, userInfoInitList);
        if (unviewReIdMap != null && unviewReIdMap.getUserId() != null) {
            userInfoAgent.reset(navId, object, objectId, unviewReIdMap.getUserId());
        }

        selectDtoMap.setUserList(userInfoAgent.getSelectOptions(navId, object, objectId));
        return selectDtoMap;
    }

    @Override
    public boolean hasSourceSourceAuth(Integer navId, Integer object, Integer objectId, Integer userId) {
        NavUnviewServerAgent userTypeAgent = new NavUnviewServerAgent(navUnviewUserTypeService);
        UserSession userSession = UserSession.getCurrent();
        int userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        if (hasSourceSourceAuth(userTypeAgent, userType, navId, object, objectId)) {//如果有人员类型权限
            NavUnviewServerAgent roleAgent = new NavUnviewServerAgent(navUnviewRoleService);
            int userRole = UserServiceUtils.headerStringToInt(userSession.getRoleId());
            if (hasSourceSourceAuth(roleAgent, userRole, navId, object, objectId)) {//如果有角色权限
                NavUnviewServerAgent companyAgent = new NavUnviewServerAgent(navUnviewCompanyService);
                TUserInfo userInfo = tUserInfoMapper.selectByPrimaryKey(userId);
                if (hasSourceSourceAuth(companyAgent, userInfo.getCompanyId(), navId, object, objectId)) {//如果有公司权限
                    NavUnviewServerAgent userInfoAgent = new NavUnviewServerAgent(navUnviewUserInfoService);
                    if (hasSourceSourceAuth(userInfoAgent, userInfo.getId(), navId, object, objectId)) {//有人员权限
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasSourceSourceAuth(NavUnviewServerAgent navUnviewServerAgent, int reId, Integer navId, Integer object, Integer objectId) {
        List<SelectDto> list = navUnviewServerAgent.getUnview(navId, object, objectId);
        for (SelectDto selectDto : list) {
            if (selectDto.getReId().intValue() == reId) {
                return false;
            }
        }
        return true;
    }

    //// TODO: 16-11-9 mkf 1.判断是否与录入是同一家公司的人员 2.如果为管理者或者管理员放回true 3.是不是协作器中的所属人
    @Override
    public boolean hasNavUnviewOperAuth(Integer navId, Integer object, Integer objectId, Integer userId) {
        if (userId == null || objectId == null || object == null) {
            return false;
        }
        List<TUserTag> operators = null;
        List<TUserTag> tags = tUserTagMapper.selectByUserId(userId);
        TUserTag user = tags.size() > 0 ? tags.get(0) : null;//当前用户
        if (user == null) {
            return false;
        }
        //当前用户为对象的录入人员
        if (ObjectTypeEnum.LENDER.getValue().intValue() == object) {
            LenderInfo info = lenderInfoMapper.get(objectId);
            if (info != null) {
                operators = tUserTagMapper.selectByUserId(info.getOperator());
            }
        } else if (ObjectTypeEnum.ASSETSOURCE.getValue().intValue() == object) {
            ZcyEstates info = zcyEstatesMapper.selectByPrimaryKey(objectId);
            if (info != null) {
                operators = tUserTagMapper.selectByUserId(MessageUtils.transStringToInt(info.getOperator()));
            }
        }
        TUserTag operator = operators.size() > 0 ? operators.get(0) : null;//录入人
        if (operator == null) {
            return false;
        }
        //录入人所在机构和当前用户的所在机构不是相同的就返回false
        if (operator.getUserType().intValue() == UserInfoEnum.USER_TYPE_ADMIN.getValue() ||
                operator.getUserType().intValue() == UserInfoEnum.USER_TYPE_ENTRUST.getValue()) {//判断录入人用户类型为平台或委托
            if (user.getUserType().intValue() != UserInfoEnum.USER_TYPE_ADMIN.getValue() &&
                    operator.getUserType().intValue() != UserInfoEnum.USER_TYPE_ENTRUST.getValue()) {//判断当前用户的用户类型
                return false;
            }
        }
        if (operator.getUserType().intValue() == UserInfoEnum.USER_TYPE_COLLECTION.getValue() ||
                operator.getUserType().intValue() == UserInfoEnum.USER_TYPE_JUDICIARY.getValue() ||
                operator.getUserType().intValue() == UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue()) {//判断录入人用户类型为处置机构
            if (operator.getUserType().intValue() != UserInfoEnum.USER_TYPE_COLLECTION.getValue() &&
                    operator.getUserType().intValue() != UserInfoEnum.USER_TYPE_JUDICIARY.getValue() &&
                    operator.getUserType().intValue() != UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue()) {//判断当前用户的用户类型
                return false;
            }
        }
        if (user.getRoleId().intValue() == RoleTypeEnum.ADMIN.getValue().intValue() || user.getRoleId().intValue() == RoleTypeEnum.REGULATOR.getValue().intValue()) {
            return true;
        }
        //当前用户参与对象协作器，并且是管理员或是管理者或是所属人的具有权限返回true
        Map coor = coordinatorMapper.getCoordMessage(userId, objectId, object);//查询当前用户是否参与到协作器了
        if (coor != null) {
            if (userId.toString().equals(MessageUtils.transMapToString(coor, "mangerId"))) {//判断是否为协作器管理员
                return true;
            }
            Integer type = MessageUtils.transMapToInt(coor, "type");
            if (type != null && (type == 0 || type == 1)) {//在协作器是否为管理者或所属人
                return true;
            }
        }
        return false;
    }



    private List<SelectDto> getUserTypeInitList() {
            userTypeInitList = new ArrayList<>();
            //普通人员
//            SelectDto commonSelectDto = new SelectDto();
//            commonSelectDto.setReId(UserInfoEnum.USER_TYPE_COMMON.getValue());
//            commonSelectDto.setShowName("普通人员");
//            userTypeInitList.add(commonSelectDto);
            //平台
//            SelectDto adminSelectDto = new SelectDto();
//            adminSelectDto.setReId(UserInfoEnum.USER_TYPE_ADMIN.getValue());
//            adminSelectDto.setShowName("平台");
//            userTypeInitList.add(adminSelectDto);
            //委托
//            SelectDto entrustSelectDto = new SelectDto();
//            entrustSelectDto.setReId(UserInfoEnum.USER_TYPE_ENTRUST.getValue());
//            entrustSelectDto.setShowName("委托");
//            userTypeInitList.add(entrustSelectDto);
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
        List<Integer> userTypes = new ArrayList<>();//用户类型
        List<SelectDto> dtos = new ArrayList<>();
        for (SelectDto dto : selectUserTypeList) {
            if (!userTypes.contains(dto.getReId())) {
                userTypes.add(dto.getReId());
            }
        }
        if (userTypes.size() != 0) {
            List<Map> list = navUnviewCompanyMapper.selectCompanyByUserType(userTypes);//查询对应类型的所有公司
            for (Map m : list) {
                SelectDto dto = new SelectDto(null, MessageUtils.transMapToInt(m, "reId"), MessageUtils.transMapToString(m, "showName"));
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public List<SelectDto> getRoleInitList() {
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
        List<Integer> roles = new ArrayList<>();//角色
        List<Integer> companyIds = new ArrayList<>();//公司
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
        Map their = new HashMap<>();
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

    /**
     * 根据key过滤掉不包含key信息的对象
     *
     * @param key
     * @return
     */
    private void filter(List<SelectDto> selectDtoList, String key) {
        if (key != null && !key.equalsIgnoreCase("") && selectDtoList != null) {
            Iterator<SelectDto> iter = selectDtoList.iterator();
            while (iter.hasNext()) {
                if (!iter.next().getShowName().contains(key)) {
                    iter.remove();
                }
            }
        }

    }

}
