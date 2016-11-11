package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.asset.PiRelationMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.asset.PiRelation;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.service.constant.ObjectEnum.PawnEnum;
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

import java.util.ArrayList;
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

    @Override
    public JsonResponse delete_tx(Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = pawnInfoMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("删除失败");
        }
        // 删除关联
        piRelationMapper.deleteByPawnId(id);
        // 增加操作记录
        businessLogService.add(id, ObjectTypeEnum.PAWN.getValue(), PawnEnum.DELETE.getValue(),
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
        if (pawnInfo.getPawnNo() == null) {
            pawnInfo.setPawnNo(RandomUtil.getCode(RandomUtil.PAWN_CODE));
        }
//        String typeStr = UserSession.getCurrent().getUserType();
//        UserInfoEnum infoEnum = UserInfoEnum.getUserInfoEnum(Integer.valueOf(typeStr.substring(0, typeStr.indexOf(","))));
//        if(infoEnum != null){
//            if(UserInfoEnum.USER_TYPE_COLLECTION.getValue().equals(infoEnum.getValue())){
//                pawnInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
//            }else if(UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().equals(infoEnum.getValue())){
//                pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
//            }else if(UserInfoEnum.USER_TYPE_JUDICIARY.getValue().equals(infoEnum.getValue())){
//                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
//            }
//        }
        Integer addResult = pawnInfoMapper.insert(pawnInfo);
        if (!CommonUtil.checkResult(addResult)) {
            Integer pawnId = pawnInfo.getId();
            // 添加关联关系
            if (pawnDTO.getIouIds() != null && pawnDTO.getIouIds().length() > 0) {
                // 以Id为关联
                String[] idStr = pawnDTO.getIouIds().split(",");
                for (String id : idStr) {
                    addRelation(pawnId, Integer.valueOf(id), pawnDTO.getLenderId(), null);
                }
            } else if (pawnDTO.getIouNames() != null && pawnDTO.getIouNames().length() > 0) {
                // 以名称为关联
                String[] nameStr = pawnDTO.getIouNames().split(",");
                for (String name : nameStr) {
                    addRelation(pawnId, null, pawnDTO.getLenderId(), name);
                }
            }
            // 添加业务
            businessService.addServiceObject(ObjectTypeEnum.PAWN.getValue(), pawnId,
                    ObjectTypeEnum.LENDER.getValue(), pawnDTO.getLenderId());
            // 增加操作记录
            businessLogService.add(pawnId, ObjectTypeEnum.PAWN.getValue(), PawnEnum.ADD.getValue(),
                    "", pawnDTO.getMemo(), 0, 0);
            return JsonResponseTool.success(pawnId);
        } else {
            return JsonResponseTool.failure("增加失败");
        }
    }

    private void addRelation(Integer pawnId, Integer iouId, Integer lenderId, String name) {
        if (pawnId != null && (iouId != null || name != null)) {
            piRelationMapper.deleteByPawnId(pawnId);  // 清除关联
            if (iouId == null) {
                List<IOUInfo> iouInfoList = iouInfoMapper.listByName(lenderId, name);
                if (iouInfoList != null && iouInfoList.size() > 0) {
                    RelationQuery relationQuery = new RelationQuery();
                    relationQuery.setIouId(iouInfoList.get(0).getId());
                    relationQuery.setPawnId(pawnId);
                    List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
                    if (piRelationList == null || piRelationList.size() == 0) {
                        PiRelation piRelation = new PiRelation();
                        piRelation.setIouId(iouInfoList.get(0).getId());
                        piRelation.setPawnId(pawnId);
                        piRelationMapper.insert(piRelation);
                    }
                }
            } else {
                IOUInfo iouInfo = iouInfoMapper.get(iouId);
                if (iouInfo != null) {
                    RelationQuery relationQuery = new RelationQuery();
                    relationQuery.setIouId(iouId);
                    relationQuery.setPawnId(pawnId);
                    List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
                    if (piRelationList == null || piRelationList.size() == 0) {
                        PiRelation piRelation = new PiRelation();
                        piRelation.setIouId(iouId);
                        piRelation.setPawnId(pawnId);
                        piRelationMapper.insert(piRelation);
                    }
                }
            }
        }
    }


    @Override
    public JsonResponse listAdd(List<PawnDTO> pawnDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(pawnDTOList)) {
            return JsonResponseTool.failure("参数错误");
        }
        for (PawnDTO pawnDTO : pawnDTOList) {
            JsonResponse response = null;
            if (pawnDTO.getId() != null) {
                response = update_tx(pawnDTO);
            } else {
                response = add_tx(pawnDTO);
            }
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

        // 添加关联(如果ids&names同时存在，以IDS为准)
        if (pawnDTO.getIouIds() != null && pawnDTO.getIouIds().length() > 0) {
            String[] idStr = pawnDTO.getIouIds().split(",");
            for (String id : idStr) {
                addRelation(pawnId, Integer.valueOf(id), pawnDTO.getLenderId(), null);
            }
        } else if (pawnDTO.getIouNames() != null && pawnDTO.getIouNames().length() > 0) {
            String[] nameStr = pawnDTO.getIouNames().split(",");
            for (String name : nameStr) {
                addRelation(pawnId, null, pawnDTO.getLenderId(), name);
            }
        }
        // 增加操作记录
        businessLogService.add(pawnId, ObjectTypeEnum.PAWN.getValue(), PawnEnum.UPDATE.getValue(),
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
        return JsonResponseTool.success(changeToDTO(pawnInfo));
    }

    @Override
    public JsonResponse listPawnByLenderId(Integer lenderId) {
        if (CommonUtil.checkParam(lenderId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        List<PawnDTO> result = new ArrayList<>();
        List<PawnInfo> pawnInfoList = pawnInfoMapper.listByLenderId(lenderId);
        pawnInfoList.forEach(pawnInfo -> {
            result.add(changeToDTO(pawnInfo));
        });
        return JsonResponseTool.success(result);
    }

    private PawnDTO changeToDTO(PawnInfo pawnInfo) {
        if (pawnInfo != null) {
            PawnDTO pawnDTO = PawnServiceUtils.toPawnDTO(pawnInfo);
            RelationQuery query = new RelationQuery();
            query.setPawnId(pawnInfo.getId());
            List<PiRelation> relationList = piRelationMapper.queryList(query);
            relationList.forEach(relation -> {
                IOUInfo iouInfo = iouInfoMapper.get(relation.getIouId());
                if (iouInfo != null) {
                    if (pawnDTO.getIouNames() == null) {
                        pawnDTO.setIouIds("" + iouInfo.getId());
                        pawnDTO.setIouNames(iouInfo.getName());
                    } else {
                        pawnDTO.setIouIds("," + iouInfo.getId());
                        pawnDTO.setIouNames("," + iouInfo.getName());
                    }
                }
            });
            return pawnDTO;
        }
        return null;
    }

}
