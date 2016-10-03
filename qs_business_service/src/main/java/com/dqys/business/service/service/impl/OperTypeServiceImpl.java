package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.operType.OperTypeMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Service;

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
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;

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
    public List<OperType> getOperType(Integer roleId, Integer userType, Integer objectType, Integer objectId) {
        Integer userId = UserSession.getCurrent().getUserId();
        List<TUserTag> tags = tUserTagMapper.selectByUserId(userId);
        if (tags.size() > 0) {
            TUserTag tag = tags.get(0);
            roleId = (int) tag.getRoleId();
            userType = (int) tag.getUserType();
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
        }
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
    public boolean checkOperType(Integer roleType, Integer userType, Integer objectType, Integer operType, Integer objectId) {
        List<OperType> list = getOperType(roleType, userType, objectType, objectId);
        for (OperType o : list) {
            if (o.getOperType() == operType) {
                return true;
            }
        }
        return false;
    }


}
