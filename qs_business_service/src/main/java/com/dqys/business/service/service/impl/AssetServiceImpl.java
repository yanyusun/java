package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/6/6.
 */
@Service
@Primary
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetInfoMapper assetInfoMapper;

    @Override
    public JsonResponse add(AssetDTO assetDTO) {
        if(CommonUtil.checkParam(assetDTO, assetDTO.getStartAt(), assetDTO.getEndAt())){
            return JsonResponseTool.paramErr("参数错误");
        }
        AssetInfo assetInfo = AssetServiceUtils.toAssetInfo(assetDTO);
        assetInfo.setAssetNo(AssetServiceUtils.createAssetCode());
        Integer addResult = assetInfoMapper.insert(assetInfo);
        if (addResult.equals(1)) {
            return JsonResponseTool.success(assetInfo.getId());
        } else {
            return JsonResponseTool.failure(null);
        }
    }

    @Override
    public JsonResponse updateById(AssetDTO assetDTO) {
        if(CommonUtil.checkParam(assetDTO)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(assetInfoMapper.update(AssetServiceUtils.toAssetInfo(assetDTO)));
    }

    @Override
    public JsonResponse delete(Integer id) {
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(assetInfoMapper.deleteByPrimaryKey(id));
    }

    @Override
    public JsonResponse getById(Integer id) {
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        AssetInfo assetInfo = assetInfoMapper.get(id);
        if(assetInfo == null){
            return JsonResponseTool.failure("获取失败");
        }else{
            return JsonResponseTool.success(AssetServiceUtils.toAssetDTO(assetInfo));
        }
    }

    @Override
    public JsonResponse queryCount(AssetQuery assetQuery) {
        return CommonUtil.responseBack(assetInfoMapper.queryCount(assetQuery));
    }

    @Override
    public JsonResponse pageList(AssetListQuery assetListQuery) {
        AssetQuery assetQuery = new AssetQuery();
        if(assetListQuery != null){
            assetQuery = AssetServiceUtils.toAssetQuery(assetListQuery);
        }
        List<AssetInfo> assetInfoList = assetInfoMapper.pageList(assetQuery);
        if(assetInfoList == null || assetInfoList.size() == 0){
            return JsonResponseTool.success(null);
        }else{
            return JsonResponseTool.success(AssetServiceUtils.toAssetDTO(assetInfoList));
        }
    }

    @Override
    public JsonResponse assignedBatch(String ids, Integer id) {
        if(ids == null || ids.length() == 0){
            return JsonResponseTool.paramErr("参数错误");
        }
        String[] idArr = ids.split(",");
        List idList = new ArrayList<>();
        for(String s : idArr){
            idList.add(Integer.valueOf(s));
        }
        return CommonUtil.responseBack(assetInfoMapper.assignedBatch(idList, id));
    }
}
