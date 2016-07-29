package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.ContactInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.constant.ObjectEnum.AssetPackageEnum;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.dto.asset.AssetLenderDTO;
import com.dqys.business.service.dto.asset.AssetListDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.core.base.SysProperty;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
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
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;
    @Autowired
    private CompanyTeamReMapper companyTeamReMapper;
    @Autowired
    private TeammateReMapper teammateReMapper;
    @Autowired
    private UserTeamMapper userTeamMapper;
    @Autowired
    private BusinessObjReMapper businessObjReMapper;
    @Autowired
    private TUserInfoMapper userInfoMapper;

    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private BusinessService businessService;

    @Override
    public JsonResponse add_tx(AssetDTO assetDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(assetDTO, assetDTO.getStartAt(), assetDTO.getEndAt())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        AssetInfo assetInfo = AssetServiceUtils.toAssetInfo(assetDTO);
        assetInfo.setAssetNo(AssetServiceUtils.createAssetCode());
        Integer addResult = assetInfoMapper.insert(assetInfo);
        if (addResult.equals(1)) {
            Integer id = assetInfo.getId();
            // 增加业务数据
            Integer serviceObjectId = businessService.addServiceObject(ObjectTypeEnum.ASSETPACKAGE.getValue(), id, null, null);
            if (CommonUtil.checkResult(serviceObjectId)) {
                // 增加业务数据失败,记录

            }
            // 增加操作记录
            businessLogService.add(id, ObjectTypeEnum.ASSETPACKAGE.getValue(), AssetPackageEnum.add.getValue(),
                    "", assetDTO.getMemo(), 0, 0);
            return JsonResponseTool.success(id);
        } else {
            return JsonResponseTool.failure(null);
        }
    }

    @Override
    public JsonResponse updateById_tx(AssetDTO assetDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(assetDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = assetInfoMapper.update(AssetServiceUtils.toAssetInfo(assetDTO));
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("修改失败");
        }
        businessLogService.add(assetDTO.getId(), ObjectTypeEnum.ASSETPACKAGE.getValue(), AssetPackageEnum.update.getValue(),
                "", assetDTO.getMemo(), 0, 0);
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse delete_tx(Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = assetInfoMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("删除失败");
        }
        businessLogService.add(id, ObjectTypeEnum.ASSETPACKAGE.getValue(), AssetPackageEnum.delete.getValue(),
                "", "", 0, 0);
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse getById(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        AssetInfo assetInfo = assetInfoMapper.get(id);
        if (assetInfo == null) {
            return JsonResponseTool.failure("获取失败");
        } else {
            return JsonResponseTool.success(AssetServiceUtils.toAssetDTO(assetInfo));
        }
    }

    @Override
    public JsonResponse queryCount(AssetQuery assetQuery) {
        return CommonUtil.responseBack(assetInfoMapper.queryCount(assetQuery));
    }

    @Override
    public JsonResponse pageList(AssetListQuery assetListQuery, Integer type) {
        if(CommonUtil.checkParam(type) || ObjectTabEnum.getObjectTabEnum(type) == null){
            return null;
        }
        AssetQuery assetQuery = createAssetQuery(type);
        if(assetQuery.getId() == null || assetQuery.getId().equals(SysProperty.NULL_DATA_ID)){
            return JsonResponseTool.successNullList();
        }
        if (assetListQuery != null) {
            assetQuery = AssetServiceUtils.toAssetQuery(assetListQuery, assetQuery);
        }

        Map<String, Object> map = new HashMap<>();
        if(!CommonUtil.checkParam(assetQuery.getIds(), assetQuery.getExceptIds())){
            List<Integer> ids = CommonUtil.exceptList(assetQuery.getIds(), assetQuery.getExceptIds());
            if(CommonUtil.checkParam(ids)){
                return JsonResponseTool.successNullList();
            }else{
                assetQuery.setIds(ids);
            }
        }
        List<AssetInfo> assetInfoList = assetInfoMapper.pageList(assetQuery);
        List<AssetListDTO> listDTOList = new ArrayList<>();
        assetInfoList.forEach(assetInfo -> {
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(assetInfo.getOperator());

            // TODO 需要增加完成率
            listDTOList.add(AssetServiceUtils.toAssetListDTO(assetInfo, userInfo, null));
        });
        if (CommonUtil.checkParam(listDTOList)) {
            return JsonResponseTool.successNullList();
        } else {
            map.put("data", listDTOList);
            map.put("total", assetInfoMapper.queryCount(assetQuery));
            return JsonResponseTool.success(map);
        }
    }

    @Override
    public JsonResponse assignedBatch(String ids, Integer id) throws BusinessLogException {
        if (ids == null || ids.length() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String[] idArr = ids.split(",");
        List idList = new ArrayList<>();
        for (String s : idArr) {
            idList.add(Integer.valueOf(s));
        }
        Integer result = assetInfoMapper.assignedBatch(idList, id);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("分配失败");
        }
        businessLogService.add(id, ObjectTypeEnum.ASSETPACKAGE.getValue(), AssetPackageEnum.update.getValue(),
                "", "", 0, 0);
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse listLender(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        AssetInfo assetInfo = assetInfoMapper.get(id);
        if (assetInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        List<LenderInfo> lenderInfoList = lenderInfoMapper.listByAssetId(id);
        List<AssetLenderDTO> assetLenderDTOList = AssetServiceUtils.toAssetLenderDTO(lenderInfoList);
        if (assetLenderDTOList != null) {
            Iterator<AssetLenderDTO> iterator = assetLenderDTOList.iterator();
            while (iterator.hasNext()) {
                iterator.next().setAssetName(assetInfo.getName()); // 设置资产包名称
                ContactInfo contactInfo = contactInfoMapper.getByModel(
                        ObjectTypeEnum.LENDER.getValue().toString(),
                        ContactTypeEnum.LENDER.getValue(),
                        iterator.next().getId());
                if (contactInfo != null) {
                    iterator.next().setName(contactInfo.getName()); // 设置资产包名称
                }
            }
        }
        return JsonResponseTool.success(assetLenderDTOList);
    }

    /**
     * 将参数type转化成相应的搜索条件
     * @param type
     * @return
     */
    private AssetQuery createAssetQuery(Integer type){
        if (type == null || ObjectTabEnum.getObjectTabEnum(type) == null) {
            return null;
        }
        AssetQuery assetQuery = new AssetQuery();
        if (ObjectTabEnum.accept.getValue().equals(type)) {
            // 待接收 -- 其他机构发起邀请未处理&协作器内没有接收的
            // 分配器中的待接收
            List<Integer> distributionIds = companyTeamReMapper.listObjectIdByTypeAndManager(
                    ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    ObjectAcceptTypeEnum.init.getValue(),
                    UserSession.getCurrent().getUserId()
            );
            // 协作器中的待接收
            List<Integer> coordinatorIds = userTeamMapper.selectByOperatorAndStatus(UserSession.getCurrent().getUserId(),
                    TeammateReEnum.STATUS_INIT.getValue(), ObjectTypeEnum.LENDER.getValue());
            List<Integer> result = CommonUtil.unionList(distributionIds, coordinatorIds);
            if (CommonUtil.checkParam(result)) {
                // 找不到数据,填充0数据限制
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setIds(result);
            }
        } else if (ObjectTabEnum.apply.getValue().equals(type)) {
            // 待申请 -- 暂未其他公司参与
            List<Integer> ids = companyTeamReMapper.listAssigned(ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (ids == null || ids.size() == 0) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setExceptIds(ids);
            }
            assetQuery.setOperator(UserSession.getCurrent().getUserId());
        } else if (ObjectTabEnum.handling_urge.getValue().equals(type)) {
            // 催收的正在处置
            List<Integer> ids = companyTeamReMapper.listObjectIdByTypeAndManager(
                    ObjectTypeEnum.LENDER.getValue(),
                    ObjectAcceptTypeEnum.accept.getValue(),
                    UserSession.getCurrent().getUserId()
            );
            if (ids == null || ids.size() == 0) {
                //  找不到数据,填充0数据限制
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setIds(ids);
            }
        } else if (ObjectTabEnum.focus.getValue().equals(type)) {
            // 聚焦

        } else if (ObjectTabEnum.month.getValue().equals(type)) {
            // 当月
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            objectUserRelationQuery.setStartAt(calendar.getTime());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            assetQuery.setIds(ids);
        } else if (ObjectTabEnum.stock.getValue().equals(type)) {
            // 存量
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            objectUserRelationQuery.setEndAt(calendar.getTime());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            assetQuery.setIds(ids);
        } else if (ObjectTabEnum.over.getValue().equals(type)) {
            // 完成
            List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    BusinessStatusEnum.end.getValue(), UserSession.getCurrent().getUserId());
            assetQuery.setIds(ids);
        } else if (ObjectTabEnum.outTime.getValue().equals(type)) {
            // 超时
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            assetQuery.setIds(ids);
            assetQuery.setOutTime(true);
        } else if (ObjectTabEnum.invalid.getValue().equals(type)) {
            // 无效
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            assetQuery.setIds(ids);
            assetQuery.setStateflag(1);
        } else if (ObjectTabEnum.join.getValue().equals(type)) {
            // 待参与
            List<Integer> ids = userTeamMapper.selectByOperatorAndStatus(UserSession.getCurrent().getUserId(),
                    TeammateReEnum.STATUS_INIT.getValue(), ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (ids != null && ids.size() > 0) {
                assetQuery.setIds(ids);
            } else {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.joined.getValue().equals(type)) {
            // 已参与
            List<Integer> ids = userTeamMapper.selectByOperatorAndStatus(UserSession.getCurrent().getUserId(),
                    TeammateReEnum.STATUS_ACCEPT.getValue(), ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (ids != null && ids.size() > 0) {
                assetQuery.setIds(ids);
            } else {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.check.getValue().equals(type)) {
            // 待审核
            List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    BusinessStatusEnum.init.getValue(), UserSession.getCurrent().getUserId());
            if (!CommonUtil.checkParam(ids)) {
                assetQuery.setIds(ids);
            } else {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.refuse.getValue().equals(type)) {
            // 已驳回
            List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    BusinessStatusEnum.platform_refuse.getValue(), UserSession.getCurrent().getUserId());
            if (!CommonUtil.checkParam(ids)) {
                assetQuery.setIds(ids);
            } else {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.handle.getValue().equals(type)) {
            // 待处置
            List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    BusinessStatusEnum.platform_pass.getValue(), UserSession.getCurrent().getUserId());
            if (!CommonUtil.checkParam(ids)) {
                assetQuery.setIds(ids);
            } else {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.assign.getValue().equals(type)) {
            // 待分配
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            objectUserRelationQuery.setType(BusinessRelationEnum.team.getValue());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (!CommonUtil.checkParam(ids)) {
                assetQuery.setExceptIds(ids);
            } else {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.new48h.getValue().equals(type)) {
            // 48h 新
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (CommonUtil.checkParam(ids)) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setIds(ids);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            assetQuery.setStartAt(calendar.getTime());
        } else if (ObjectTabEnum.task.getValue().equals(type)) {
            // 我的任务
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> relationIds = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                relationIds.add(objectUserRelation.getObjectId());
            });
            List<Integer> teammateIds = teammateReMapper.listObjectIdByJoinType(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    UserSession.getCurrent().getUserId(), TeammateReEnum.TYPE_PARTICIPATION.getValue());
            List<Integer> ids = CommonUtil.unionList(relationIds, teammateIds);
            if (CommonUtil.checkParam(ids)) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setIds(ids);
            }
        } else if (ObjectTabEnum.handling_entrust.getValue().equals(type)) {
            // 委托的处置中
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (CommonUtil.checkParam(ids)) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setIds(ids);
            }
            assetQuery.setTakePart(true);
        } else {
            return null;
        }
        return assetQuery;
    }
}
