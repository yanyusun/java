package com.dqys.business.service.service.impl;

import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.asset.PiRelationMapper;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.asset.PiRelation;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.service.IouService;
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
public class IouServiceImpl implements IouService {

    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private PiRelationMapper piRelationMapper;

    @Override
    public JsonResponse delete(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(iouInfoMapper.deleteByPrimaryKey(id));
    }

    @Override
    public JsonResponse add(IouDTO iouDTO) {
        if (CommonUtil.checkParam(iouDTO, iouDTO.getLenderId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer iouId = iouInfoMapper.insert(AssetServiceUtils.toIouInfo(iouDTO));
        if(!CommonUtil.checkResult(iouId)){
            // 添加关联关系
            if(iouDTO.getPawnIds().length() > 0){
                String[] idStr = iouDTO.getPawnIds().split(",");
                for(String id : idStr){
                    PawnInfo pawnInfo = pawnInfoMapper.get(Integer.valueOf(id));
                    if(pawnInfo != null){
                        PiRelation piRelation = new PiRelation();
                        piRelation.setIouId(iouId);
                        piRelation.setPawnId(pawnInfo.getId());
                        piRelationMapper.insert(piRelation);
                    }
                }
            }
            return JsonResponseTool.success(iouId);
        }else{
            return JsonResponseTool.failure("增加失败");
        }
    }

    @Override
    public JsonResponse update(IouDTO iouDTO) {
        if (CommonUtil.checkParam(iouDTO, iouDTO.getLenderId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer iouId = iouDTO.getId();
        // 修改借据
        iouInfoMapper.update(AssetServiceUtils.toIouInfo(iouDTO));
        // 清除关联
        piRelationMapper.deleteByPawnId(iouId);
        // 添加关联
        if(iouDTO.getPawnIds().length() > 0){
            String[] idStr = iouDTO.getPawnIds().split(",");
            for(String id : idStr){
                PawnInfo pawnInfo = pawnInfoMapper.get(Integer.valueOf(id));
                if(pawnInfo != null){
                    PiRelation piRelation = new PiRelation();
                    piRelation.setIouId(iouId);
                    piRelation.setPawnId(pawnInfo.getId());
                    piRelationMapper.insert(piRelation);
                }
            }
        }
        return JsonResponseTool.success(iouId);
    }

    @Override
    public JsonResponse get(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(iouInfoMapper.get(id));
    }

    @Override
    public JsonResponse listIouByLenderId(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(AssetServiceUtils.toIouDTO(iouInfoMapper.listByLenderId(id)));
    }
}
