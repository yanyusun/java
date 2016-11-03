package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.asset.PiRelationMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.asset.PiRelation;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.IouService;
import com.dqys.business.service.utils.asset.IouServiceUtils;
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

    @Override
    public JsonResponse delete_tx(Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = iouInfoMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("删除失败");
        }
        // 删除借据关联
        piRelationMapper.deleteByIouId(id);
        // 添加操作记录
        businessLogService.add(id, ObjectTypeEnum.IOU.getValue(), IouEnum.DELETE.getValue(),
                "", "", 0, 0);
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse add_tx(IouDTO iouDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(iouDTO, iouDTO.getLenderId(), iouDTO.getIouName())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        IOUInfo iouInfo = IouServiceUtils.toIouInfo(iouDTO);
        iouInfo.setIouNo(RandomUtil.getCode(RandomUtil.IOU_CODE));
//        String typeStr = UserSession.getCurrent().getUserType();
//        UserInfoEnum infoEnum = UserInfoEnum.getUserInfoEnum(Integer.valueOf(typeStr.substring(0, typeStr.indexOf(","))));
//        if(infoEnum != null){
//            if(UserInfoEnum.USER_TYPE_COLLECTION.getValue().equals(infoEnum.getValue())){
//                iouInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
//            }else if(UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().equals(infoEnum.getValue())){
//                iouInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
//            }else if(UserInfoEnum.USER_TYPE_JUDICIARY.getValue().equals(infoEnum.getValue())){
//                iouInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
//            }
//        }

        Integer iouResult = iouInfoMapper.insert(iouInfo);
        if (!CommonUtil.checkResult(iouResult)) {
            Integer iouId = iouInfo.getId();
            // 添加关联关系
            if (iouDTO.getPawnIds() != null && iouDTO.getPawnIds().length() > 0) {
                // id 关联关系
                String[] idStr = iouDTO.getPawnIds().split(",");
                for (String id : idStr) {
                    addRelation(iouId, Integer.valueOf(id), null, iouDTO.getLenderId());
                }
            } else if (iouDTO.getPawnNames() != null && iouDTO.getPawnNames().length() > 0) {
                // 名称关联
                String nameStr[] = iouDTO.getPawnNames().split(",");
                for (String name : nameStr) {
                    addRelation(iouId, null, name, iouDTO.getLenderId());
                }
            }
            // 添加业务
            businessService.addServiceObject(ObjectTypeEnum.IOU.getValue(), iouId,
                    ObjectTypeEnum.LENDER.getValue(), iouDTO.getLenderId());
            // 添加操作记录
            businessLogService.add(iouId, ObjectTypeEnum.IOU.getValue(), IouEnum.ADD.getValue(),
                    "", iouDTO.getMemo(), 0, 0);
            return JsonResponseTool.success(iouId);
        } else {
            return JsonResponseTool.failure("增加失败");
        }
    }

    private void addRelation(Integer iouId, Integer pawnId, String name, Integer lenderId) {
        if (iouId != null && (pawnId != null || name != null)) {
            if (pawnId == null) {
                List<PawnInfo> pawnInfoList = pawnInfoMapper.listByName(lenderId, name);
                if (pawnInfoList != null && pawnInfoList.size() > 0) {
                    RelationQuery relationQuery = new RelationQuery();
                    relationQuery.setIouId(iouId);
                    relationQuery.setPawnId(pawnInfoList.get(0).getId());
                    List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
                    if (piRelationList == null || piRelationList.size() == 0) {
                        PiRelation piRelation = new PiRelation();
                        piRelation.setIouId(iouId);
                        piRelation.setPawnId(pawnInfoList.get(0).getId());
                        piRelationMapper.insert(piRelation);
                    }
                }
            } else {
                PawnInfo pawnInfo = pawnInfoMapper.get(pawnId);
                if (pawnInfo != null) {
                    RelationQuery relationQuery = new RelationQuery();
                    relationQuery.setIouId(iouId);
                    relationQuery.setPawnId(pawnInfo.getId());
                    List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
                    if (piRelationList == null || piRelationList.size() == 0) {
                        // 防止重复添加关系
                        PiRelation piRelation = new PiRelation();
                        piRelation.setIouId(iouId);
                        piRelation.setPawnId(pawnInfo.getId());
                        piRelationMapper.insert(piRelation);
                    }
                }
            }
        }
    }


    @Override
    public JsonResponse listAdd(List<IouDTO> iouDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(iouDTOList) || iouDTOList.size() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        for (IouDTO iouDTO : iouDTOList) {
            JsonResponse response = null;
            if (iouDTO.getId() != null) {
                response = update_tx(iouDTO);
            } else {
                response = add_tx(iouDTO);
            }
            if (!response.getCode().equals(ResponseCodeEnum.SUCCESS.getValue())) {
                return JsonResponseTool.failure("新增失败");
            }
        }
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse update_tx(IouDTO iouDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(iouDTO, iouDTO.getLenderId(), iouDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer iouId = iouDTO.getId();
        // 修改借据
        iouInfoMapper.update(IouServiceUtils.toIouInfo(iouDTO));
        // 清除关联
        piRelationMapper.deleteByIouId(iouId);
        // 添加关联(如果ids&names同时存在，以IDS为准)
        if (iouDTO.getPawnIds() != null && iouDTO.getPawnIds().length() > 0) {
            String[] idStr = iouDTO.getPawnIds().split(",");
            for (String id : idStr) {
                addRelation(iouDTO.getId(), Integer.valueOf(id), null, iouDTO.getLenderId());
            }
        } else if (iouDTO.getPawnNames() != null && iouDTO.getPawnNames().length() > 0) {
            String[] nameStr = iouDTO.getPawnNames().split(",");
            for (String s : nameStr) {
                addRelation(iouDTO.getId(), null, s, iouDTO.getLenderId());
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
        IOUInfo iouInfo = iouInfoMapper.get(id);
        if (iouInfo == null) {
            return JsonResponseTool.failure("找不到信息");
        }
        return JsonResponseTool.success(changeToDTO(iouInfo));
    }

    @Override
    public JsonResponse listIouByLenderId(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        List<IOUInfo> iouList = iouInfoMapper.listByLenderId(id);
        List<IouDTO> iouDTOList = new ArrayList<>();
        for (IOUInfo iouInfo : iouList) {
            iouDTOList.add(changeToDTO(iouInfo));
        }
        return CommonUtil.responseBack(iouDTOList);
    }

    /**
     * 将DAO转化为DTO
     *
     * @param iouInfo
     * @return
     */
    private IouDTO changeToDTO(IOUInfo iouInfo) {
        if (iouInfo != null) {
            IouDTO iouDTO = IouServiceUtils.toIouDTO(iouInfo);
            RelationQuery relationQuery = new RelationQuery();
            relationQuery.setIouId(iouInfo.getId());
            List<PiRelation> piRelationList = piRelationMapper.queryList(relationQuery);
            piRelationList.forEach(piRelation -> {
                PawnInfo pawnInfo = pawnInfoMapper.get(piRelation.getPawnId());
                if (pawnInfo != null) {
                    if (iouDTO.getPawnNames() == null) {
                        iouDTO.setPawnNames(pawnInfo.getName());
                        iouDTO.setPawnIds("" + pawnInfo.getId());
                    } else {
                        iouDTO.setPawnIds("," + pawnInfo.getId());
                        iouDTO.setPawnNames("," + pawnInfo.getName());
                    }
                }
            });
            return iouDTO;
        }
        return null;
    }
}
