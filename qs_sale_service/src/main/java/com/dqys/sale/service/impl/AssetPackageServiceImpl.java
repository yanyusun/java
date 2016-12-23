package com.dqys.sale.service.impl;

import com.dqys.core.base.SysProperty;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.constant.ObjectTypeEnum;
import com.dqys.sale.orm.dto.AssetPackageDTO;
import com.dqys.sale.orm.dto.UserBondDTO;
import com.dqys.sale.orm.mapper.*;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.pojo.*;
import com.dqys.sale.orm.query.AssetPackageQuery;
import com.dqys.sale.service.facade.AssetPackageService;
import com.dqys.sale.service.facade.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/23.
 */
@Repository
public class AssetPackageServiceImpl implements AssetPackageService {
    @Autowired
    private FixedAssetService fixedAssetService;
    @Autowired
    private AssetPackageMapper assetPackageMapper;
    @Autowired
    private AssetFileMapper assetFileMapper;
    @Autowired
    private DisposeMapper disposeMapper;
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private BusinessORelationMapper businessORelationMapper;

    @Override
    public JsonResponse assetList(AssetPackageQuery query) {
        List<Integer> objectIds = businessORelationMapper.selectObjectIdByObjectType(ObjectTypeEnum.user_bond.getValue(), 1);//查询业务状态符合的对象id
        if (objectIds == null || objectIds.size() == 0) {
            objectIds.add(SysProperty.NULL_DATA_ID);
        }
        query.setIds(objectIds);
        List<AssetPackage> list = assetPackageMapper.list(query);
        Integer count = assetPackageMapper.listCount(query);
        query.setTotalCount(count);
        List<AssetPackageDTO> dtos = new ArrayList<>();
        for (AssetPackage entity : list) {
            AssetPackageDTO dto = new AssetPackageDTO();
            dto.setLabels(labelMapper.selectByAssetId(entity.getId(), ObjectTypeEnum.user_bond.getValue()));
            dto.setAssetPackage(entity);
            dto.setAssetFiles(assetFileMapper.selectByAssetId(entity.getId(), ObjectTypeEnum.user_bond.getValue()));
            dto.setDisposes(disposeMapper.selectByAssetId(entity.getId(), ObjectTypeEnum.user_bond.getValue()));
            dtos.add(dto);
        }
        Map map = new HashMap<>();
        map.put("assetPackageList", dtos);
        map.put("query", query);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse addAsset(AssetPackageDTO assetPackageDTO) {
        if (CommonUtil.checkParam(assetPackageDTO) || CommonUtil.checkParam(assetPackageDTO.getAssetPackage())) {
            return JsonResponseTool.failure("参数错误");
        }
        AssetPackage entity = assetPackageDTO.getAssetPackage();
        Integer num = assetPackageMapper.insertSelective(entity);
        if (num == 0) {
            return JsonResponseTool.failure("添加失败");
        }
        fixedAssetService.addOtherEntity(assetPackageDTO.getLabels(), assetPackageDTO.getDisposes(), assetPackageDTO.getAssetFiles(), entity.getId(), ObjectTypeEnum.asset_package.getValue());
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse getDetail(Integer assetId) {
        AssetPackage assetPackage = assetPackageMapper.selectByPrimaryKey(assetId);
        List<AssetFile> assetFile = assetFileMapper.selectByAssetId(assetId, ObjectTypeEnum.asset_package.getValue());
        List<Dispose> disposes = disposeMapper.selectByAssetId(assetId, ObjectTypeEnum.asset_package.getValue());
        List<Label> labels = labelMapper.selectByAssetId(assetId, ObjectTypeEnum.asset_package.getValue());
        Map map = new HashMap<>();
        map.put("assetPackage", assetPackage);
        map.put("assetFile", assetFile);
        map.put("disposes", disposes);
        map.put("labels", labels);
        return JsonResponseTool.success(map);
    }
}
