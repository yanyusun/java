package com.dqys.sale.service.impl;

import com.dqys.core.base.SysProperty;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.constant.ObjectTypeEnum;
import com.dqys.sale.orm.dto.FixedAssetDTO;
import com.dqys.sale.orm.mapper.*;
import com.dqys.sale.orm.mapper.business.BusinessMapper;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.pojo.*;
import com.dqys.sale.orm.query.FixedAssetQuery;
import com.dqys.sale.service.facade.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Service
public class FixedAssetServiceImpl implements FixedAssetService {
    @Autowired
    private FixedAssetMapper fixedAssetMapper;
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
    public JsonResponse fixedList(FixedAssetQuery fixedAssetQuery) {
        List<Integer> objectIds = businessORelationMapper.selectObjectIdByObjectType(ObjectTypeEnum.fixed_asset.getValue(), 1);//查询业务状态符合的对象id
        if (objectIds == null || objectIds.size() == 0) {
            objectIds.add(SysProperty.NULL_DATA_ID);
        }
        fixedAssetQuery.setIds(objectIds);
        List<FixedAsset> fixedAssetList = fixedAssetMapper.fixedList(fixedAssetQuery);
        Integer count = fixedAssetMapper.fixedListCount(fixedAssetQuery);
        fixedAssetQuery.setTotalCount(count);
        List<FixedAssetDTO> dtos = new ArrayList<>();
        for (FixedAsset asset : fixedAssetList) {
            FixedAssetDTO dto = new FixedAssetDTO();
            dto.setLabels(labelMapper.selectByAssetId(asset.getId(), ObjectTypeEnum.fixed_asset.getValue()));
            dto.setFixedAsset(asset);
            dto.setAssetFiles(assetFileMapper.selectByAssetId(asset.getId(), ObjectTypeEnum.fixed_asset.getValue()));
            dto.setDisposes(disposeMapper.selectByAssetId(asset.getId(), ObjectTypeEnum.fixed_asset.getValue()));
            dtos.add(dto);
        }
        Map map = new HashMap<>();
        map.put("fixedAssetList", dtos);
        map.put("query", fixedAssetQuery);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse getDetail(Integer fixedAssetId) {
        FixedAsset fixedAsset = fixedAssetMapper.selectByPrimaryKey(fixedAssetId);
        List<AssetFile> assetFile = assetFileMapper.selectByAssetId(fixedAssetId, ObjectTypeEnum.fixed_asset.getValue());
        List<Dispose> disposes = disposeMapper.selectByAssetId(fixedAssetId, ObjectTypeEnum.fixed_asset.getValue());
        List<Label> labels = labelMapper.selectByAssetId(fixedAssetId, ObjectTypeEnum.fixed_asset.getValue());
        Map map = new HashMap<>();
        map.put("fixedAsset", fixedAsset);
        map.put("assetFile", assetFile);
        map.put("disposes", disposes);
        map.put("labels", labels);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse addFixed(FixedAssetDTO fixedAssetDTO) {
        if (CommonUtil.checkParam(fixedAssetDTO) || CommonUtil.checkParam(fixedAssetDTO.getFixedAsset())) {
            return JsonResponseTool.failure("参数错误");
        }
        FixedAsset fixedAsset = fixedAssetDTO.getFixedAsset();
        setEntity(fixedAsset);
        Integer num = fixedAssetMapper.insertSelective(fixedAsset);
        if (num == 0) {
            return JsonResponseTool.failure("添加失败");
        }
        addOtherEntity(fixedAssetDTO.getLabels(), fixedAssetDTO.getDisposes(), fixedAssetDTO.getAssetFiles(), fixedAsset.getId(), ObjectTypeEnum.fixed_asset.getValue());
        return JsonResponseTool.success(null);
    }

    private void setEntity(FixedAsset entity) {
        if (entity.getNo() == null) {

        }

    }

    @Override
    public void addOtherEntity(List<Label> labels, List<Dispose> disposes, List<AssetFile> assetFiles, Integer id, Integer objectType) {

        //文件
        if (assetFiles != null && assetFiles.size() > 0) {
            List<AssetFile> list = assetFileMapper.selectByAssetId(id, objectType);
            if (list != null && list.size() > 0) {
                for (AssetFile file : list) {
                    assetFileMapper.deleteByPrimaryKey(file.getId());
                }
            }
            for (AssetFile file : assetFiles) {
                file.setAssetId(id);
                file.setAssetType(objectType);
                assetFileMapper.insertSelective(file);
            }
        }
        //处置方式
        if (disposes != null && disposes.size() > 0) {
            List<Dispose> list = disposeMapper.selectByAssetId(id, objectType);
            if (list != null && list.size() > 0) {
                for (Dispose file : list) {
                    disposeMapper.deleteByPrimaryKey(file.getId());
                }
            }
            for (Dispose dis : disposes) {
                dis.setAssetType(objectType);
                dis.setAssetId(id);
                disposeMapper.insertSelective(dis);
            }
        }
        //标签
        if (labels != null && labels.size() > 0) {
            LabelRe labelRe = new LabelRe();
            labelRe.setAssetType(objectType);
            List<Dispose> list = labelReMapper.selectByAssetId(id, objectType);
            if (list != null && list.size() > 0) {
                for (Dispose file : list) {
                    labelReMapper.deleteByPrimaryKey(file.getId());
                }
            }
            for (Label label : labels) {
                if (label.getId() != null) {
                    labelRe.setAsssetId(id);
                    labelRe.setLabelId(label.getId());
                    labelReMapper.insertSelective(labelRe);
                }
            }
        }
    }

}
