package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.UserDetail;
import com.dqys.business.orm.constant.business.ObjectBusinessEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.mapper.asset.*;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.pojo.asset.*;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.IouService;
import com.dqys.business.service.utils.asset.IouServiceUtils;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private ContactInfoMapper contactInfoMapper;

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
        UserSession userSession = UserSession.getCurrent();
        int userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        //第一次添加时根据人员类型,设置抵押物的处置状态
        if (userType == UserInfoEnum.USER_TYPE_COLLECTION.getValue()) {//只有催收是在业务的
            iouDTO.setLawyer(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
            iouDTO.setAgent(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
        } else if (userType == UserInfoEnum.USER_TYPE_JUDICIARY.getValue()) {//只有司法时在业务的
            iouDTO.setUrge(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
            iouDTO.setAgent(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
        } else if (userType == UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue()) {//只有中介时在业务的
            iouDTO.setUrge(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
            iouDTO.setLawyer(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
        } else {//都不在业务
            iouDTO.setUrge(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
            iouDTO.setLawyer(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
            iouDTO.setAgent(ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
        }
        IOUInfo iouInfo = IouServiceUtils.toIouInfo(iouDTO);
        if (iouInfo.getIouNo() == null) {
            iouInfo.setIouNo(RandomUtil.getCode(RandomUtil.IOU_CODE));
        }
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
        int num = 0;
        List<Integer> lenderIds = new ArrayList<>();
        for (IouDTO iouDTO : iouDTOList) {
            JsonResponse response = null;
            if (iouDTO.getId() != null) {
                response = update_tx(iouDTO);
            } else {
                response = add_tx(iouDTO);
            }
            if (response.getCode().equals(ResponseCodeEnum.SUCCESS.getValue())) {
                num++;
            }
            if (!lenderIds.contains(iouDTO.getLenderId())) {
                lenderIds.add(iouDTO.getLenderId());
            }
        }
        setLenderAndAsset(lenderIds);//计算借款人和资产包的总评估价、总贷款、总利息金额
        if (num > 0) {
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("新增失败");
        }
    }

    @Override
    public void setLenderAndAsset(List<Integer> lenderIds) {
        //累加借据金额，用于填充借款人的金额
        List<Integer> assetIds = new ArrayList<>();
        if (lenderIds.size() > 0) {
            //对借款人的金额修改
            for (Integer lenderId : lenderIds) {
                LenderInfo info = lenderInfoMapper.get(lenderId);
                if (info != null) {
//                    防止所传入的借款人id集合不是同一个资产包的情况
                    if (info.getAssetId() != null && !assetIds.contains(info.getAssetId())) {
                        assetIds.add(info.getAssetId());
                    }
                    IOUInfo iouMoney = iouInfoMapper.getIouBySumMoney(lenderId);//根据借款人获取借款人下所有的借据
                    //计算金额
                    info.setAccrual((iouMoney.getPenalty() + iouMoney.getAccrualArrears()));//总利息
                    info.setLoan(iouMoney.getLessCorpus());//总贷款
                    info.setAppraisal(iouMoney.getWorth());//总评估
                    lenderInfoMapper.update(info);//修改借款人总贷款金额、总利息、总评估价格
                }
            }
            //对资产包的计算修改
            for (Integer assetId : assetIds) {
                AssetInfo assetInfo = assetInfoMapper.get(assetId);//查询出资产包，对数据修改
                if (assetInfo != null) {
                    LenderInfo lenderMoney = lenderInfoMapper.getLenderBySumMoney(assetId);//根据资产包获取资产包下所有的借据
                    assetInfo.setAccrual(lenderMoney.getAccrual());//总利息
                    assetInfo.setLoan(lenderMoney.getLoan());//总贷款
                    assetInfo.setAppraisal(lenderMoney.getAppraisal());//总评估
                    assetInfoMapper.update(assetInfo);//修改资产包总贷款金额、总利息、总评估价格
                }
            }
        }
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
        Integer userId = UserSession.getCurrent().getUserId();
        com.dqys.business.orm.pojo.coordinator.UserDetail detail = coordinatorMapper.getUserDetail(userId);
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setObjectType(ObjectTypeEnum.LENDER.getValue());
        query.setObjectId(id);
        query.setUserId(userId);
        query.setVisibleType(OURelationEnum.VISIBLE_TYPE_PORTION.getValue());
        List<ObjectUserRelation> list = objectUserRelationMapper.list(query);
        List<IOUInfo> iouList;
        if (list != null && list.size() > 0) {
            iouList = iouInfoMapper.iouListByLenderId(id, userId, ObjectTypeEnum.IOU.getValue(), MessageUtils.transStringToInt(detail.getUserType()));
        } else {
            iouList = iouInfoMapper.listByLenderId(id);
        }
        List<IouDTO> iouDTOList = new ArrayList<>();
        for (IOUInfo iouInfo : iouList) {
            iouDTOList.add(changeToDTO(iouInfo));
        }
        return CommonUtil.responseBack(iouDTOList);
    }

    @Override
    public JsonResponse listIouByLenderIdC(Integer id) {
        List<Map> list = new ArrayList<>();
        JsonResponse response = listIouByLenderId(id);
        if (response.getCode() == ResponseCodeEnum.SUCCESS.getValue().intValue()) {
            List<IouDTO> dtos = (List<IouDTO>) response.getData();
            for (IouDTO dto : dtos) {
                Map map = new HashMap<>();
                map.put("id", dto.getId());
                map.put("name", dto.getIouName());
                list.add(map);
            }
        } else {
            return response;
        }
        return JsonResponseTool.success(list);
    }

    @Override
    public JsonResponse getC(Integer id) {
        JsonResponse response = get(id);
        if (response.getCode() == ResponseCodeEnum.SUCCESS.getValue()) {
            IouDTO iouDTO = (IouDTO) response.getData();
            LenderInfo info = lenderInfoMapper.get(iouDTO.getLenderId());
            iouDTO.setOperTime(info.getCreateAt());
            iouDTO.setLenderNo(info.getLenderNo());
            UserDetail detail = tUserInfoMapper.getUserDetail(info.getOperator());
            iouDTO.setOperator(detail.getRealName());
            ContactInfo contactInfo = contactInfoMapper.getByModel(ObjectTypeEnum.LENDER.getValue().toString(), ContactTypeEnum.LENDER.getValue(), info.getId());
            iouDTO.setLenderName(contactInfo.getName());
            return JsonResponseTool.success(iouDTO);
        } else {
            return response;
        }
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
