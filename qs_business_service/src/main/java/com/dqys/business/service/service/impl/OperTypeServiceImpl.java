package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.operType.OperTypeMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.constant.ObjectEnum.PawnEnum;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.business.service.utils.operType.OperTypeUtile;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.NoSQLWithRedisTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/1.
 */
@Service
public class OperTypeServiceImpl implements OperTypeService {

    @Autowired
    private OperTypeMapper operTypeMapper;

    @Autowired
    private TUserTagMapper tUserTagMapper;
    @Autowired
    private TeammateReMapper teammateReMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;

    @Override
    public List<OperType> selectByRoleToOperType(Integer roleId, Integer userType, Integer objectType) {
        return operTypeMapper.selectByRoleToOperType(roleId, userType, objectType);
    }

    @Override
    public List<Integer> selectByUserIds() {
        return operTypeMapper.selectByUserIds();
    }

    @Override
    public List<Integer> selectByRoleIds() {
        return operTypeMapper.selectByRoleIds();
    }

    @Override
    public List<Integer> selectByObjectIds() {
        return operTypeMapper.selectByObjectIds();
    }

    @Override
    public List<OperType> getOperType(Integer objectType, Integer objectId) {
        UserSession userSession = UserSession.getCurrent();
        Integer userId = UserSession.getCurrent().getUserId();
        Integer roleId = UserServiceUtils.headerStringToInt(userSession.getRoleId());
        Integer userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        Integer flowId = objectId;
        Integer flowType = objectType;
        //对象类型为抵押物或借据，设置借款人的对象类型，协作器只能查资产包或借款人
        if (ObjectTypeEnum.PAWN.getValue() == objectType) {
            flowId = pawnInfoMapper.get(objectId).getLenderId();
            flowType = ObjectTypeEnum.LENDER.getValue();
        } else if (ObjectTypeEnum.IOU.getValue() == objectType) {
            flowId = iouInfoMapper.get(objectId).getLenderId();
            flowType = ObjectTypeEnum.LENDER.getValue();
        }
        //不是管理者的就是查询是否在团队中是所属人角色
        if (roleId != RoleTypeEnum.ADMIN.getValue()) {
            Map teamMap = new HashMap<>();
            teamMap.put("objectId", flowId);
            teamMap.put("objectType", flowType);
            teamMap.put("userId", userId);
            teamMap.put("type", TeammateReEnum.TYPE_AUXILIARY.getValue());
            List<TeammateRe> teammateReList = teammateReMapper.selectSelectiveByUserTeam(teamMap);
            if (teammateReList.size() > 0) {
                roleId = RoleTypeEnum.THEIR.getValue();
            } else if (flowType == ObjectTypeEnum.LENDER.getValue()) {
                //借款人的所属人查不到再去查资产包的所属人
                LenderInfo info = lenderInfoMapper.get(flowId);
                teamMap.put("objectId", info.getAssetId());
                teamMap.put("objectType", ObjectTypeEnum.ASSETPACKAGE.getValue());
                teammateReList = teammateReMapper.selectSelectiveByUserTeam(teamMap);
                if (teammateReList.size() > 0) {
                    roleId = RoleTypeEnum.THEIR.getValue();
                }
            }
        }
        // }
//        String userId_roleId_objectId = userType + "_" + roleId + "_" + objectType;
//        List<OperType> list = NoSQLWithRedisTool.getValueObject(userId_roleId_objectId) == null ? new ArrayList<>() : NoSQLWithRedisTool.getValueObject(userId_roleId_objectId);
//        if (list != null && list.size() != 0) {
//            return list;
//        } else {
//            NoSQLWithRedisTool.getRedisTemplate().opsForValue().set(userId_roleId_objectId, selectByRoleToOperType(roleId, userType, objectType));
//        }
//        return (List<OperType>) NoSQLWithRedisTool.getValueObject(userId_roleId_objectId);
        return OperTypeUtile.operTypes(userType,roleId,objectType);
    }

    @Override
    public List<OperType> getOperType(Integer roleId, Integer userType, Integer objectType) {
        String userId_roleId_objectId = userType + "_" + roleId + "_" + objectType;
        List<OperType> list = NoSQLWithRedisTool.getValueObject(userId_roleId_objectId) == null ? new ArrayList<>() : NoSQLWithRedisTool.getValueObject(userId_roleId_objectId);
        if (list != null && list.size() != 0) {
            return list;
        } else {
            NoSQLWithRedisTool.getRedisTemplate().opsForValue().set(userId_roleId_objectId, selectByRoleToOperType(roleId, userType, objectType));
        }
        return (List<OperType>) NoSQLWithRedisTool.getValueObject(userId_roleId_objectId);
    }


    @Override
    public boolean checkOperType(Integer roleType, Integer userType, Integer objectType, Integer operType) {
        List<OperType> list = getOperType(roleType, userType, objectType);
        for (OperType o : list) {
            if (o.getOperType() == operType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<OperType> getAll(OperType operType) {
        return operTypeMapper.getAll(operType);
    }

    @Override
    public List<OperType> getInitBuisnesOperTypeList(Integer objectType, Integer objectId, Integer flowType) {
        List<Integer> dis = new ArrayList<>();//处置方式中的操作
        if (objectType == ObjectTypeEnum.PAWN.getValue().intValue()) {
            PawnInfo info = pawnInfoMapper.get(objectId);
            if (info != null) {
                objectId = info.getLenderId();
                objectType = ObjectTypeEnum.LENDER.getValue().intValue();
            }
        }
        if (objectType == ObjectTypeEnum.IOU.getValue().intValue()) {
            IOUInfo info = iouInfoMapper.get(objectId);
            if (info != null) {
                objectId = info.getLenderId();
                objectType = ObjectTypeEnum.LENDER.getValue().intValue();
            }
        }
        if (objectType == ObjectTypeEnum.LENDER.getValue().intValue()) {//解析借款人的处置方式
            LenderInfo info = lenderInfoMapper.get(objectId);
            if (info != null) {
                //[{"disMethod":"1","disQuota":1,"disVal":"100"}] 借款人的json格式
                if (info.getDisposeMode() != null && !info.getDisposeMode().equals("")) {
                    getValueByJson(dis, info.getDisposeMode(), "disMethod");
                } else {
                    //借款人的操作没有，准备查询资产包的
                    if (info.getAssetId() != null) {
                        objectType = ObjectTypeEnum.ASSETPACKAGE.getValue().intValue();
                        objectId = info.getAssetId();
                    }
                }
            }
        }
        if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue().intValue()) {//解析资产包的处置方式
            AssetInfo info = assetInfoMapper.get(objectId);
            if (info != null) {
                //[{"dealWay":"1","payWay":"1","payValue":"11"}] 资产包的json格式
                getValueByJson(dis, info.getDisposeMode(), "dealWay");
            }
        }
        Object[] nav = getNav(dis, flowType);
        List<OperType> operTypes = null;
        if (flowType == null) {
            operTypes = OperTypeUtile.getOperTypeList(nav, ObjectTypeEnum.IOU.getValue(), ObjectTypeEnum.PAWN.getValue());
        } else {
            operTypes = OperTypeUtile.getOperTypeList(nav, flowType);
        }
        return operTypes;
    }

    /**
     * 获取对应的权限数组
     *
     * @param dis
     * @param flowType
     * @return
     */
    private Object[] getNav(List<Integer> dis, Integer flowType) {
        List<Integer> iouNavs = new ArrayList<>();
        List<Integer> pawnNavs = new ArrayList<>();
        if (dis.contains(LenderEnum.STATUS_COLLECTION.getValue())) {//维持常规催收
            iouNavs.add(IouEnum.MAINTAIN_REGULAR.getValue());
            pawnNavs.add(PawnEnum.MAINTAIN_REGULAR.getValue());
        }
        if (dis.contains(LenderEnum.STATUS_JUDICIARY.getValue())) {//司法化解
            iouNavs.add(IouEnum.EXECUTE_JUSTICE_RESOLVE.getValue());
            pawnNavs.add(PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue());
        }
        if (dis.contains(LenderEnum.STATUS_BAZAAR.getValue())) {//市场处置
            iouNavs.add(IouEnum.MARKET_DISPOSITION.getValue());
            pawnNavs.add(PawnEnum.MARKET_DISPOSITION.getValue());
        }
        if (dis.contains(LenderEnum.STATUS_COLLECTION.getValue()) && dis.contains(LenderEnum.STATUS_JUDICIARY.getValue())) {//催收/司法化解同时进行
            iouNavs.add(IouEnum.CJ_SIMULTANEOUS.getValue());
            pawnNavs.add(PawnEnum.CJ_SIMULTANEOUS.getValue());
        }
        if (dis.contains(LenderEnum.STATUS_COLLECTION.getValue()) && dis.contains(LenderEnum.STATUS_BAZAAR.getValue())) {//催收/市场同时进行
            iouNavs.add(IouEnum.CM_SIMULTANEOUS.getValue());
            pawnNavs.add(PawnEnum.CM_SIMULTANEOUS.getValue());
        }
        if (dis.contains(LenderEnum.STATUS_COLLECTION.getValue()) && dis.contains(LenderEnum.STATUS_JUDICIARY.getValue()) && dis.contains(LenderEnum.STATUS_BAZAAR.getValue())) {//催收、市场、司法同时进行
            iouNavs.add(IouEnum.CMJ_SIMULTANEOUS.getValue());
            pawnNavs.add(PawnEnum.CMJ_SIMULTANEOUS.getValue());
        }
        if (flowType != null) {
            if (flowType == ObjectTypeEnum.IOU.getValue().intValue()) {
                return iouNavs.toArray();
            } else if (flowType == ObjectTypeEnum.PAWN.getValue().intValue()) {
                return pawnNavs.toArray();
            }
        } else {
            List<Integer> sumNavs = new ArrayList<>();
            sumNavs.addAll(pawnNavs);
            sumNavs.addAll(iouNavs);
            return sumNavs.toArray();
        }
        return new Object[]{};
    }

    /**
     * 解析处置方式的json
     *
     * @param dis
     * @param json
     * @param key
     */
    private void getValueByJson(List<Integer> dis, String json, String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map> modes = objectMapper.readValue(json, List.class);
            for (Map m : modes) {
                dis.add(MessageUtils.transMapToInt(m, key));
            }
        } catch (IOException e) {
            return;
        }
    }


}
