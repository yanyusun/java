package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.*;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.synthLender.SynthLenderMapper;
import com.dqys.business.orm.pojo.asset.*;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.dto.asset.LenderSynDTO;
import com.dqys.business.service.service.SynthLenderService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private ContactInfoMapper contactInfoMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;


    @Override
    public Map pawnList(Integer lenderId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        String userType = UserSession.getCurrent() == null ? "0" : UserSession.getCurrent().getUserType();
        if (userType.indexOf(",") > 0) {
            userType = userType.substring(0, userType.indexOf(","));
        }
//        userId = 12;
//        userType = "31";
        Map<String, Object> map = new HashMap<String, Object>();
        LenderInfo lenderInfo = lenderInfoMapper.get(lenderId);//借款人信息
        ContactInfo contactInfo = contactInfoMapper.getByModel(ObjectTypeEnum.LENDER.getValue().toString(), ContactTypeEnum.LENDER.getValue(), lenderId);
        LenderSynDTO lenderSynDTO = transform(lenderInfo, contactInfo);//数据转换
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setObjectType(ObjectTypeEnum.LENDER.getValue());
        query.setObjectId(lenderId);
        query.setUserId(userId);
        List<ObjectUserRelation> list = objectUserRelationMapper.list(query);
        boolean flag = false;
        if (list.size() > 0) {
            ObjectUserRelation our = list.get(0);
            if (our.getVisibleType() != null && our.getVisibleType() == OURelationEnum.VISIBLE_TYPE_PORTION.getValue()) {
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
        map.put("lenderSynDTO", lenderSynDTO);//借款人信息
        map.put("pawnList", pawnInfoList);//抵押物信息列表
        map.put("iouList", iouInfoList);//借据信息列表
        return map;
    }

    private LenderSynDTO transform(LenderInfo lf, ContactInfo cf) {
        LenderSynDTO dto = new LenderSynDTO();
        if (cf != null) {
            dto.setType(cf.getType());
            dto.setAddress(cf.getAddress());
            dto.setAvg(cf.getAvg());
            dto.setName(cf.getName());
            dto.setGender(cf.getGender().toString());
        }
        if (lf != null) {
            dto.setLenderNo(lf.getLenderNo());
            dto.setTags(lf.getTags());
            dto.setAccrual(lf.getAccrual().toString());
            dto.setLoan(lf.getLoan().toString());
            dto.setAppraisal(lf.getAppraisal().toString());
            dto.setLoanType(lf.getLoanType());
            dto.setLoanMode(lf.getLoanMode());
            dto.setLoanName(lf.getLoanName());
            dto.setRealUrgeTime(lf.getRealUrgeTime());
            dto.setPhoneUrgeTime(lf.getPhoneUrgeTime());
            dto.setEntrustUrgeTime(lf.getEntrustUrgeTime());
            dto.setFollowUpDate(lf.getFollowUpDate());
            dto.setEnteringDate(lf.getCreateAt());
            dto.setStartAt(lf.getStartAt());
            dto.setEndAt(lf.getEndAt());
            AssetInfo assetInfo = assetInfoMapper.get(lf.getAssetId());//获取资产包信息
            if (assetInfo != null) {
                dto.setAssetNo(assetInfo.getAssetNo());
            }
            long nowTime = lf.getEndAt().getTime() - new Date().getTime();
            Integer day = (int) nowTime / (1000 * 3600 * 24);
            dto.setSurplusDay(day.toString());
            Map map = synthLenderMapper.getRealNameByOURelation(ObjectTypeEnum.LENDER.getValue(), lf.getId(),
                    TeammateReEnum.STATUS_ACCEPT.getValue(), TeammateReEnum.TYPE_AUXILIARY.getValue(), OURelationEnum.TYPE_ALLOCATION_TEAM.getValue());
            if (map != null) {
                dto.setBelongId(MessageUtils.transMapToInt(map, "user_id"));
                dto.setBelongName(MessageUtils.transMapToString(map, "real_name"));
            }
        }

        return dto;
    }
}
