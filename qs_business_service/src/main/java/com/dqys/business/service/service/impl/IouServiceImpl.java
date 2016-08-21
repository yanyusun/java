package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.asset.PiRelationMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.asset.PiRelation;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.query.asset.IOUQuery;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.constant.ObjectLogEnum;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.IouService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.business.service.utils.asset.IouServiceUtils;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;

    @Override
    public JsonResponse delete_tx(Integer id) throws BusinessLogException{
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = iouInfoMapper.deleteByPrimaryKey(id);
        if(CommonUtil.checkResult(result)){
            return JsonResponseTool.failure("删除失败");
        }
        // 添加操作记录
        businessLogService.add(id, ObjectTypeEnum.IOU.getValue(), IouEnum.DELETE.getValue(),
                "", "", 0, 0);
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse add_tx(IouDTO iouDTO) throws BusinessLogException{
        if (CommonUtil.checkParam(iouDTO, iouDTO.getLenderId(), iouDTO.getIouName())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        IOUInfo iouInfo = IouServiceUtils.toIouInfo(iouDTO);
        iouInfo.setIouNo(IouServiceUtils.createIouCode());

        Integer iouResult = iouInfoMapper.insert(iouInfo);
        if(!CommonUtil.checkResult(iouResult)){
            Integer iouId = iouInfo.getId();
            // 添加关联关系
            if(iouDTO.getPawnIds() != null && iouDTO.getPawnIds().length() > 0){
                // id 关联关系
                String[] idStr = iouDTO.getPawnIds().split(",");
                for(String id : idStr){
                    PawnInfo pawnInfo = pawnInfoMapper.get(Integer.valueOf(id));
                    if(pawnInfo != null){
                        RelationQuery relationQuery = new RelationQuery();
                        relationQuery.setIouId(iouId);
                        relationQuery.setPawnId(pawnInfo.getId());
                        List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
                        if(piRelationList == null || piRelationList.size() == 0){
                            // 防止重复添加关系
                            PiRelation piRelation = new PiRelation();
                            piRelation.setIouId(iouInfo.getId());
                            piRelation.setPawnId(pawnInfo.getId());
                            piRelationMapper.insert(piRelation);
                        }
                    }
                }
            }else if(iouDTO.getPawnNames() != null && iouDTO.getPawnNames().length() > 0){
                // 名称关联
                String nameStr[] = iouDTO.getPawnNames().split(",");
                for(String name : nameStr){
                    PawnInfo pawnInfo = pawnInfoMapper.getByName(iouDTO.getLenderId(), name);
                    if(pawnInfo != null){
                        RelationQuery relationQuery = new RelationQuery();
                        relationQuery.setIouId(iouId);
                        relationQuery.setPawnId(pawnInfo.getId());
                        List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
                        if(piRelationList == null || piRelationList.size() == 0){
                            PiRelation piRelation = new PiRelation();
                            piRelation.setIouId(iouId);
                            piRelation.setPawnId(pawnInfo.getId());
                            piRelationMapper.insert(piRelation);
                        }
                    }
                }
            }
            // 添加业务
            businessService.addServiceObject(ObjectTypeEnum.IOU.getValue(), iouId,
                    ObjectTypeEnum.LENDER.getValue(), iouDTO.getLenderId());
            // 添加操作记录
            businessLogService.add(iouId, ObjectTypeEnum.IOU.getValue(), ObjectLogEnum.add.getValue(),
                    "", iouDTO.getMemo(), 0, 0);
            return JsonResponseTool.success(iouId);
        }else{
            return JsonResponseTool.failure("增加失败");
        }
    }

    @Override
    public JsonResponse listAdd(List<IouDTO> iouDTOList) throws BusinessLogException {
        if(CommonUtil.checkParam(iouDTOList) || iouDTOList.size() == 0){
            return JsonResponseTool.paramErr("参数错误");
        }
        for (IouDTO iouDTO : iouDTOList) {
            JsonResponse response = add_tx(iouDTO);
            if(!response.getCode().equals(ResponseCodeEnum.SUCCESS.getValue())){
                return JsonResponseTool.failure("新增失败");
            }
        }
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse update_tx(IouDTO iouDTO) throws BusinessLogException{
        if (CommonUtil.checkParam(iouDTO, iouDTO.getLenderId(), iouDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer iouId = iouDTO.getId();
        // 修改借据
        iouInfoMapper.update(IouServiceUtils.toIouInfo(iouDTO));
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
        // 添加操作记录
        businessLogService.add(iouId, ObjectTypeEnum.IOU.getValue(), IouEnum.UPDATE.getValue(),
                "", "", 0, 0);
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
        return CommonUtil.responseBack(IouServiceUtils.toIouDTO(iouInfoMapper.listByLenderId(id)));
    }
}
