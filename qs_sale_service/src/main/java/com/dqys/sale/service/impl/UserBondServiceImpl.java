package com.dqys.sale.service.impl;

import com.dqys.core.base.SysProperty;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.RandomUtil;
import com.dqys.sale.orm.mapper.*;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.Label;
import com.dqys.sale.orm.pojo.UserBond;
import com.dqys.sale.orm.query.UserBondQuery;
import com.dqys.sale.service.constant.ObjectTypeEnum;
import com.dqys.sale.service.dto.UserBondDTO;
import com.dqys.sale.service.facade.FixedAssetService;
import com.dqys.sale.service.facade.UserBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/22.
 */
@Service
public class UserBondServiceImpl implements UserBondService {
    @Autowired
    private FixedAssetService fixedAssetService;
    @Autowired
    private UserBondMapper userBondMapper;
    @Autowired
    private AssetFileMapper assetFileMapper;
    @Autowired
    private DisposeMapper disposeMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private BusinessORelationMapper businessORelationMapper;
    @Autowired
    private LabelReMapper labelReMapper;

    @Override
    public JsonResponse bondList(UserBondQuery query) {
        if (query != null && query.getBondType() != null) {
            List<Integer> objectIds = businessORelationMapper.selectObjectIdByObjectType(query.getBondType(), 1);//查询业务状态符合的对象id
            if (objectIds == null || objectIds.size() == 0) {
                objectIds.add(SysProperty.NULL_DATA_ID);
            }
            query.setIds(objectIds);
        }
        List<UserBondDTO> dtos = getUserBondDTOs(query);
        Map map = new HashMap<>();
        map.put("userBondList", dtos);
        map.put("query", query);
        return JsonResponseTool.success(map);
    }

    private List<UserBondDTO> getUserBondDTOs(UserBondQuery query) {
        List<UserBond> userBonds = userBondMapper.list(query);
        Integer count = userBondMapper.listCount(query);
        query.setTotalCount(count);
        List<UserBondDTO> dtos = new ArrayList<>();
        for (UserBond entity : userBonds) {
            UserBondDTO dto = new UserBondDTO();
            dto.setLabels(labelMapper.selectByAssetId(entity.getId(), entity.getBondType().intValue()));
            dto.setUserBond(entity);
            dto.setAssetFiles(assetFileMapper.selectByAssetId(entity.getId(), entity.getBondType().intValue()));
            dto.setDisposes(disposeMapper.selectByAssetId(entity.getId(), entity.getBondType().intValue()));
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public JsonResponse addBond_tx(UserBondDTO userBondDTO) {
        if (CommonUtil.checkParam(userBondDTO) || CommonUtil.checkParam(userBondDTO.getUserBond())) {
            return JsonResponseTool.failure("参数错误");
        }
        UserBond entity = userBondDTO.getUserBond();
        setEntity(entity);
        Integer num = userBondMapper.insertSelective(entity);
        if (num == 0) {
            return JsonResponseTool.failure("添加失败");
        }
        fixedAssetService.addOtherEntity_tx(userBondDTO.getLabels(), userBondDTO.getDisposes(), userBondDTO.getAssetFiles(), entity.getId(), ObjectTypeEnum.user_bond.getValue());
        return JsonResponseTool.success(null);
    }

    private void setEntity(UserBond entity) {
        if (entity.getBondNo() == null) {
            if (entity.getBondType() == ObjectTypeEnum.user_bond.getValue().intValue()) {
                entity.setBondNo(RandomUtil.getCode(RandomUtil.INDIVIDUAL_CREDITOR_CODE));
            } else if (entity.getBondType() == ObjectTypeEnum.overdue_asset.getValue().intValue()) {
                entity.setBondNo(RandomUtil.getCode(RandomUtil.INDIVIDUAL_CREDITOR_CODE));
            } else if (entity.getBondType() == ObjectTypeEnum.company_bond.getValue().intValue()) {
                entity.setBondNo(RandomUtil.getCode(RandomUtil.INDIVIDUAL_CREDITOR_CODE));
            }
        }

    }

    @Override
    public JsonResponse getDetail(Integer bondId) {
        UserBond userBond = userBondMapper.selectByPrimaryKey(bondId);
        List<AssetFile> assetFile = assetFileMapper.selectByAssetId(bondId, userBond.getBondType().intValue());
        List<Dispose> disposes = disposeMapper.selectByAssetId(bondId, userBond.getBondType().intValue());
        List<Label> labels = labelMapper.selectByAssetId(bondId, userBond.getBondType().intValue());
        Map map = new HashMap<>();
        map.put("userBond", userBond);
        map.put("assetFile", assetFile);
        map.put("disposes", disposes);
        map.put("labels", labels);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse updateBond_tx(UserBondDTO userBondDTO) {
        if (CommonUtil.checkParam(userBondDTO) || CommonUtil.checkParam(userBondDTO.getUserBond())) {
            return JsonResponseTool.failure("请把信息填写完整");
        }
        UserBond entity = userBondDTO.getUserBond();
        if (entity == null && entity.getId() == null) {
            return JsonResponseTool.failure("缺少必要数值");
        }
        setEntity(entity);
        Integer num = userBondMapper.updateByPrimaryKeySelective(entity);
        if (num == 0) {
            return JsonResponseTool.failure("添加失败");
        }
        fixedAssetService.addOtherEntity_tx(userBondDTO.getLabels(), userBondDTO.getDisposes(), userBondDTO.getAssetFiles(), entity.getId(), ObjectTypeEnum.user_bond.getValue());
        return JsonResponseTool.success(null);
    }

}
