package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.synthLender.SynthLenderMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.service.SynthLenderService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/9/9.
 */
@Service
public class SynthLenderServiceImpl implements SynthLenderService {

    @Autowired
    private SynthLenderMapper synthLenderMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;

    @Override
    public Map pawnList(Integer lenderId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        String userType = UserSession.getCurrent() == null ? "0" : UserSession.getCurrent().getUserType();
        Map<String, Object> map = new HashMap<String, Object>();
        LenderInfo lenderInfo = lenderInfoMapper.get(lenderId);//借款人信息
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setObjectType(ObjectTypeEnum.LENDER.getValue());
        query.setObjectId(lenderId);
        query.setUserId(userId);
        List<ObjectUserRelation> list = objectUserRelationMapper.list(query);
        boolean flag = false;
        if (list.size() > 0) {
            ObjectUserRelation our = list.get(0);
            if (our.getVisibleType()==1) {
                flag = true;
            }
        }
        List<PawnInfo> pawnInfoList = null;//抵押物
        List<IOUInfo> iouInfoList = null;//借据
        if (flag) {
            pawnInfoList = pawnInfoMapper.pawnListByLenderId(lenderId, userId, ObjectTypeEnum.PAWN.getValue(), MessageUtils.transStringToInt(userType));
            iouInfoList = iouInfoMapper.iouListByLenderId(lenderId, userId, ObjectTypeEnum.IOU.getValue(), MessageUtils.transStringToInt(userType));
        } else {
            pawnInfoList = pawnInfoMapper.listByLenderId(lenderId);
            iouInfoList = iouInfoMapper.listByLenderId(lenderId);
        }
        map.put("result", "yes");
        map.put("lenderInfo", lenderInfo);//借款人信息
        map.put("pawnList", pawnInfoList);//抵押物信息列表
        map.put("iouList", iouInfoList);//借据信息列表
        return map;
    }
}
