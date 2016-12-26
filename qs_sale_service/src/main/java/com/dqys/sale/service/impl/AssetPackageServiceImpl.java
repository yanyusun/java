package com.dqys.sale.service.impl;

import com.dqys.core.base.SysProperty;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.service.dto.AssetPackageDTO;
import com.dqys.sale.orm.mapper.AssetFileMapper;
import com.dqys.sale.orm.mapper.AssetPackageMapper;
import com.dqys.sale.orm.mapper.DisposeMapper;
import com.dqys.sale.orm.mapper.LabelMapper;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.pojo.AssetFile;
import com.dqys.sale.orm.pojo.AssetPackage;
import com.dqys.sale.orm.pojo.Dispose;
import com.dqys.sale.orm.pojo.Label;
import com.dqys.sale.orm.query.AssetPackageQuery;
import com.dqys.sale.service.constant.ObjectTypeEnum;
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
//        List<Integer> objectIds = businessORelationMapper.selectObjectIdByObjectType(ObjectTypeEnum.user_bond.getValue(), 1);//查询业务状态符合的对象id
//        if (objectIds == null || objectIds.size() == 0) {
//            objectIds.add(SysProperty.NULL_DATA_ID);
//        }
//        query.setIds(objectIds);
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
    public JsonResponse addAsset_tx(AssetPackageDTO assetPackageDTO) {
        if (CommonUtil.checkParam(assetPackageDTO) || CommonUtil.checkParam(assetPackageDTO.getAssetPackage())) {
            return JsonResponseTool.failure("参数错误");
        }
        AssetPackage entity = assetPackageDTO.getAssetPackage();
        setEntity(entity);
        Integer num = assetPackageMapper.insertSelective(entity);
        if (num == 0) {
            return JsonResponseTool.failure("添加失败");
        }
        fixedAssetService.addOtherEntity_tx(assetPackageDTO.getLabels(), assetPackageDTO.getDisposes(), assetPackageDTO.getAssetFiles(), entity.getId(), ObjectTypeEnum.asset_package.getValue());
        return JsonResponseTool.success(null);
    }

    private void setEntity(AssetPackage entity) {
        if (entity.getOperUser() == null) {
            entity.setOperUser(UserSession.getCurrent().getUserId());
        }
        if (entity.getAssetNo() == null) {

        }

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

    @Override
    public JsonResponse updateAsset_tx(AssetPackageDTO assetPackageDTO) {
        if (CommonUtil.checkParam(assetPackageDTO) || CommonUtil.checkParam(assetPackageDTO.getAssetPackage())) {
            return JsonResponseTool.failure("请把信息填写完整");
        }
        AssetPackage entity = assetPackageDTO.getAssetPackage();
        if (entity == null && entity.getId() == null) {
            return JsonResponseTool.failure("缺少必要数值");
        }
        setEntity(entity);
        Integer num = assetPackageMapper.updateByPrimaryKeySelective(entity);
        if (num == 0) {
            return JsonResponseTool.failure("修改失败");
        }
        fixedAssetService.addOtherEntity_tx(assetPackageDTO.getLabels(), assetPackageDTO.getDisposes(), assetPackageDTO.getAssetFiles(), entity.getId(), ObjectTypeEnum.asset_package.getValue());
        return JsonResponseTool.success(null);
    }

}
