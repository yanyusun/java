package com.dqys.business.service.service.userTeam.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.service.service.userTeam.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-11-29.
 */
@Service
@Primary
public class UserTeamServiceImpl implements UserTeamService {
    @Autowired
    private TeammateReMapper teammateReMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;

    /**
     *
     * @param objectType
     * @param objectId
     * @param userId
     * @return
     */
    @Override
    public boolean isTheir(int objectType, int objectId, int userId) {
        Map teamMap = new HashMap<>();
        teamMap.put("objectType", objectType);
        teamMap.put("objectId", objectId);
        teamMap.put("userId", userId);
        teamMap.put("type", TeammateReEnum.TYPE_AUXILIARY.getValue());
        List<TeammateRe> teammateReList = teammateReMapper.selectSelectiveByUserTeam(teamMap);
        if (teammateReList.size() > 0) {
            return true;
        } else if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            //借款人的所属人查不到再去查资产包的所属人
            LenderInfo info = lenderInfoMapper.get(objectId);
            teamMap.put("objectId", info.getAssetId());
            teamMap.put("objectType", ObjectTypeEnum.ASSETPACKAGE.getValue());
            teammateReList = teammateReMapper.selectSelectiveByUserTeam(teamMap);
            if (teammateReList.size() > 0) {
                return true;
            }
        }
        return false;
    }
}
