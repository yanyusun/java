package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.ContactInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.constant.asset.AssetModelTypeEnum;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.constant.asset.LenderTypeEnum;
import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.dto.asset.AssetLenderDTO;
import com.dqys.business.service.dto.asset.LenderListDTO;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.apache.tools.ant.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Yvan on 16/6/6.
 */
@Service
@Primary
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private ContactInfoMapper contactInfoMapper;

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
        Map<String, Object> map = new HashMap<>();
        AssetQuery assetQuery = new AssetQuery();
        if(assetListQuery != null){
            assetQuery = AssetServiceUtils.toAssetQuery(assetListQuery);
        }
        List<AssetInfo> assetInfoList = assetInfoMapper.pageList(assetQuery);
        if(assetInfoList == null || assetInfoList.size() == 0){
            return JsonResponseTool.success(null);
        }else{
            map.put("data", AssetServiceUtils.toAssetListDTO(assetInfoList));
            map.put("total", assetInfoMapper.queryCount(assetQuery));
            return JsonResponseTool.success(map);
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

    @Override
    public JsonResponse listLender(Integer id) {
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        AssetInfo assetInfo = assetInfoMapper.get(id);
        if(assetInfo == null){
            return JsonResponseTool.paramErr("参数错误");
        }
        List<LenderInfo> lenderInfoList = lenderInfoMapper.listByAssetId(id);
        List<AssetLenderDTO> assetLenderDTOList = AssetServiceUtils.toAssetLenderDTO(lenderInfoList);
        if(assetLenderDTOList != null){
            Iterator<AssetLenderDTO> iterator = assetLenderDTOList.iterator();
            while (iterator.hasNext()){
                iterator.next().setAssetName(assetInfo.getName()); // 设置资产包名称
                ContactInfo contactInfo = contactInfoMapper.getByModel(
                        AssetModelTypeEnum.LENDER,
                        ContactTypeEnum.LENDER.getValue(),
                        iterator.next().getId());
                if(contactInfo != null){
                    iterator.next().setName(contactInfo.getName()); // 设置资产包名称
                }
            }
        }
        return JsonResponseTool.success(assetLenderDTOList);
    }
}
