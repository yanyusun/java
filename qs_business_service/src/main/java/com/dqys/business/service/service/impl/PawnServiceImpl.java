package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.asset.PiRelationMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PiRelation;
import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.PawnService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/7/12.
 */
@Repository
@Primary
public class PawnServiceImpl implements PawnService {

    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private PiRelationMapper piRelationMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private BusinessService businessService;

    @Override
    public JsonResponse delete(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(pawnInfoMapper.deleteByPrimaryKey(id));
    }

    @Override
    public JsonResponse add(PawnDTO pawnDTO) {
        if (CommonUtil.checkParam(pawnDTO, pawnDTO.getLenderId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer pawnId = pawnInfoMapper.insert(AssetServiceUtils.toPawnInfo(pawnDTO));
        if(!CommonUtil.checkResult(pawnId)){
            // 添加关联关系
            if(pawnDTO.getIouIds().length() > 0){
                String[] idStr = pawnDTO.getIouIds().split(",");
                for(String id : idStr){
                    IOUInfo iouInfo = iouInfoMapper.get(Integer.valueOf(id));
                    if(iouInfo != null){
                        PiRelation piRelation = new PiRelation();
                        piRelation.setIouId(iouInfo.getId());
                        piRelation.setPawnId(pawnId);
                        piRelationMapper.insert(piRelation);
                    }
                }
            }
            // 添加业务
            businessService.addServiceObject(ObjectTypeEnum.LENDER.getValue(), pawnId,
                    ObjectTypeEnum.LENDER.getValue(), pawnDTO.getLenderId());
            return JsonResponseTool.success(pawnId);
        }else{
            return JsonResponseTool.failure("增加失败");
        }
    }

    @Override
    public JsonResponse update(PawnDTO pawnDTO) {
        if (CommonUtil.checkParam(pawnDTO, pawnDTO.getLenderId(), pawnDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer pawnId = pawnDTO.getId();
        // 修改借据
        pawnInfoMapper.update(AssetServiceUtils.toPawnInfo(pawnDTO));
        // 清除关联
        piRelationMapper.deleteByPawnId(pawnId);
        // 添加关联
        if(pawnDTO.getIouIds().length() > 0){
            String[] idStr = pawnDTO.getIouIds().split(",");
            for(String id : idStr){
                IOUInfo iouInfo = iouInfoMapper.get(Integer.valueOf(id));
                if(iouInfo != null){
                    PiRelation piRelation = new PiRelation();
                    piRelation.setIouId(iouInfo.getId());
                    piRelation.setPawnId(pawnId);
                    piRelationMapper.insert(piRelation);
                }
            }
        }
        return JsonResponseTool.success(pawnId);
    }

    @Override
    public JsonResponse get(Integer id) {
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(AssetServiceUtils.toPawnDTO(pawnInfoMapper.get(id)));
    }

    @Override
    public JsonResponse listPawnByLenderId(Integer lenderId) {
        if(CommonUtil.checkParam(lenderId)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(AssetServiceUtils.toPawnDTO(pawnInfoMapper.listByLenderId(lenderId)));
    }
}
