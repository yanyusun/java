package com.dqys.sale.service.impl;

import com.dqys.core.base.SysProperty;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.RandomUtil;
import com.dqys.sale.orm.pojo.*;
import com.dqys.sale.service.dto.APDto;
import com.dqys.sale.service.dto.AssetPackageDTO;
import com.dqys.sale.orm.mapper.AssetFileMapper;
import com.dqys.sale.orm.mapper.AssetPackageMapper;
import com.dqys.sale.orm.mapper.DisposeMapper;
import com.dqys.sale.orm.mapper.LabelMapper;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.query.AssetPackageQuery;
import com.dqys.sale.service.constant.ObjectTypeEnum;
import com.dqys.sale.service.dto.FADto;
import com.dqys.sale.service.dto.FixedAssetDTO;
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
    public JsonResponse list(AssetPackageQuery query) {
        List<AssetPackageDTO> dtos = getAssetPackageDTOs(query);
        Map map = new HashMap<>();
        map.put("assetPackageList", dtos);
        map.put("query", query);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse assetList(AssetPackageQuery query) {
        List<AssetPackageDTO> dtos = getAssetPackageDTOs(query);
        for (AssetPackageDTO dto : dtos) {
            APDto apDto = new APDto();
            AssetPackage asset = dto.getAssetPackage();
            if (asset != null) {
                transformToAPDto(apDto, asset);
            }
            dto.setApDto(apDto);
            dto.setAssetPackage(null);
        }
        Map map = new HashMap<>();
        map.put("assetPackageList", dtos);
        map.put("query", query);
        return JsonResponseTool.success(map);
    }

    private void transformToAPDto(APDto apDto, AssetPackage asset) {
        apDto.setAssetType(asset.getAssetType());
        String address = "";
        if (asset.getProvince() != null && AreaTool.getAreaById(asset.getProvince()) != null) {
            address += AreaTool.getAreaById(asset.getProvince()).getLabel();
        }
        if (asset.getCity() != null && AreaTool.getAreaById(asset.getCity()) != null) {
            address += AreaTool.getAreaById(asset.getCity()).getLabel();
        }
        if (asset.getArea() != null && AreaTool.getAreaById(asset.getArea()) != null) {
            AreaTool.getAreaById(asset.getArea()).getLabel();
        }
        if (asset.getAddress() != null) {
            address += asset.getAddress();
        }
        apDto.setAddress(address);
        apDto.setCollectionNum(asset.getCollectionNum());
        apDto.setDisposeNum(asset.getDisposeNum());
        apDto.setDisposeStatus(asset.getDisposeStatus());
        apDto.setId(asset.getId());
        apDto.setAssetNo(asset.getAssetNo());
        apDto.setIsSpecial(asset.getIsSpecial());
        apDto.setTitle(asset.getTitle());
        apDto.setEndTime(asset.getEndTime());
        apDto.setStartTime(asset.getStartTime());
        apDto.setTotalMoney(asset.getTotalMoney());
        apDto.setGrade(asset.getGrade());
        apDto.setEntrustType(asset.getEntrustType());
    }

    private List<AssetPackageDTO> getAssetPackageDTOs(AssetPackageQuery query) {
        query.setObjectType(ObjectTypeEnum.asset_package.getValue());
        List<AssetPackage> list = assetPackageMapper.list(query);
        Integer count = assetPackageMapper.listCount(query);
        query.setTotalCount(count);
        List<AssetPackageDTO> dtos = new ArrayList<>();
        for (AssetPackage entity : list) {
            AssetPackageDTO dto = new AssetPackageDTO();
            dto.setLabels(labelMapper.selectByAssetId(entity.getId(), ObjectTypeEnum.asset_package.getValue()));
            dto.setAssetPackage(entity);
            dto.setAssetFiles(assetFileMapper.selectByAssetId(entity.getId(), ObjectTypeEnum.asset_package.getValue()));
            dto.setDisposes(disposeMapper.selectByAssetId(entity.getId(), ObjectTypeEnum.asset_package.getValue()));
            dto.setoRelation(businessORelationMapper.getORelation(entity.getId(), ObjectTypeEnum.asset_package.getValue()));
            dtos.add(dto);
        }
        return dtos;
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
        return JsonResponseTool.success(entity.getId());
    }

    private void setEntity(AssetPackage entity) {
        if (entity.getOperUser() == null) {
            entity.setOperUser(UserSession.getCurrent().getUserId());
        }
        if (entity.getAssetNo() == null) {
            entity.setAssetNo(RandomUtil.getCode(RandomUtil.ASSET_PACKAGE_CODE));
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
