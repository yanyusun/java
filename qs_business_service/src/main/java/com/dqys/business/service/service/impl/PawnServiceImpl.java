package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.asset.PiRelationMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.asset.PiRelation;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.service.constant.ObjectLogEnum;
import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.PawnService;
import com.dqys.business.service.utils.asset.PawnServiceUtils;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;

    @Override
    public JsonResponse delete_tx(Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = pawnInfoMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("删除失败");
        }
        // 增加操作记录
        businessLogService.add(id, ObjectTypeEnum.PAWN.getValue(), ObjectLogEnum.delete.getValue(),
                "", "", 0, 0);
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse add_tx(PawnDTO pawnDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(pawnDTO, pawnDTO.getLenderId(), pawnDTO.getPawnName())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        PawnInfo pawnInfo = PawnServiceUtils.toPawnInfo(pawnDTO);
        // 统计当前借款人已经具有的抵押物数量
        pawnInfo.setPawnNo(RandomUtil.getCode(RandomUtil.PAWN_CODE));
        Integer addResult = pawnInfoMapper.insert(pawnInfo);
        if (!CommonUtil.checkResult(addResult)) {
            Integer pawnId = pawnInfo.getId();
            // 添加关联关系
            if (pawnDTO.getIouIds() != null && pawnDTO.getIouIds().length() > 0) {
                // 以Id为关联
                String[] idStr = pawnDTO.getIouIds().split(",");
                for (String id : idStr) {
                    IOUInfo iouInfo = iouInfoMapper.get(Integer.valueOf(id));
                    if (iouInfo != null) {
                        RelationQuery relationQuery = new RelationQuery();
                        relationQuery.setIouId(Integer.valueOf(id));
                        relationQuery.setPawnId(pawnId);
                        List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
                        if (piRelationList == null || piRelationList.size() == 0) {
                            PiRelation piRelation = new PiRelation();
                            piRelation.setIouId(iouInfo.getId());
                            piRelation.setPawnId(pawnId);
                            piRelationMapper.insert(piRelation);
                        }
                    }
                }
            } else if (pawnDTO.getIouNames() != null && pawnDTO.getIouNames().length() > 0) {
                // 以名称为关联
                String[] nameStr = pawnDTO.getIouNames().split(",");
                for (String name : nameStr) {
                    IOUInfo iouInfo = iouInfoMapper.getByName(pawnDTO.getLenderId(), name);
                    if (iouInfo != null) {
                        RelationQuery relationQuery = new RelationQuery();
                        relationQuery.setIouId(iouInfo.getId());
                        relationQuery.setPawnId(pawnId);
                        List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
                        if (piRelationList == null || piRelationList.size() == 0) {
                            PiRelation piRelation = new PiRelation();
                            piRelation.setIouId(iouInfo.getId());
                            piRelation.setPawnId(pawnId);
                            piRelationMapper.insert(piRelation);
                        }
                    }
                }
            }
            // 添加业务
            businessService.addServiceObject(ObjectTypeEnum.PAWN.getValue(), pawnId,
                    ObjectTypeEnum.LENDER.getValue(), pawnDTO.getLenderId());
            // 增加操作记录
            businessLogService.add(pawnId, ObjectTypeEnum.PAWN.getValue(), ObjectLogEnum.add.getValue(),
                    "", pawnDTO.getMemo(), 0, 0);
            return JsonResponseTool.success(pawnId);
        } else {
            return JsonResponseTool.failure("增加失败");
        }
    }

    @Override
    public JsonResponse listAdd(List<PawnDTO> pawnDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(pawnDTOList)) {
            return JsonResponseTool.failure("参数错误");
        }
        for (PawnDTO pawnDTO : pawnDTOList) {
            JsonResponse response = add_tx(pawnDTO);
            if (!response.getCode().equals(ResponseCodeEnum.SUCCESS.getValue())) {
                return JsonResponseTool.failure("添加失败");
            }
        }
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse update_tx(PawnDTO pawnDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(pawnDTO, pawnDTO.getLenderId(), pawnDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer pawnId = pawnDTO.getId();
        // 修改借据
        pawnInfoMapper.update(PawnServiceUtils.toPawnInfo(pawnDTO));
        // 清除关联
        piRelationMapper.deleteByPawnId(pawnId);
        // 添加关联
        if (pawnDTO.getIouIds().length() > 0) {
            String[] idStr = pawnDTO.getIouIds().split(",");
            for (String id : idStr) {
                IOUInfo iouInfo = iouInfoMapper.get(Integer.valueOf(id));
                if (iouInfo != null) {
                    PiRelation piRelation = new PiRelation();
                    piRelation.setIouId(iouInfo.getId());
                    piRelation.setPawnId(pawnId);
                    piRelationMapper.insert(piRelation);
                }
            }
        }
        // 增加操作记录
        businessLogService.add(pawnId, ObjectTypeEnum.PAWN.getValue(), ObjectLogEnum.update.getValue(),
                "", pawnDTO.getMemo(), 0, 0);
        return JsonResponseTool.success(pawnId);
    }

    @Override
    public JsonResponse get(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        PawnInfo pawnInfo = pawnInfoMapper.get(id);
        if (pawnInfo == null) {
            return JsonResponseTool.failure("找不到信息");
        }
        PawnDTO pawnDTO = PawnServiceUtils.toPawnDTO(pawnInfo);
        RelationQuery relationQuery = new RelationQuery();
        relationQuery.setPawnId(pawnDTO.getId());
        List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
        piRelationList.forEach(piRelation -> {
            if(piRelation.getIouId() != null){
                IOUInfo iouInfo = iouInfoMapper.get(piRelation.getIouId());
                if(pawnDTO.getIouNames() == null || pawnDTO.getIouNames().equals("")){
                    pawnDTO.setIouNames(iouInfo.getName());
                }else{
                    pawnDTO.setIouNames(pawnDTO.getIouNames() + "," + iouInfo.getName());
                }
            }
        });
        return CommonUtil.responseBack(PawnServiceUtils.toPawnDTO(pawnInfoMapper.get(id)));
    }

    @Override
    public JsonResponse listPawnByLenderId(Integer lenderId) {
        if (CommonUtil.checkParam(lenderId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(PawnServiceUtils.toPawnDTO(pawnInfoMapper.listByLenderId(lenderId)));
    }

}
