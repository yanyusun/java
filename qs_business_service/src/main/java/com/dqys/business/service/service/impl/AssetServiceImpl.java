package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.*;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.mapper.followUp.FollowUpReadstatusMapper;
import com.dqys.business.orm.pojo.asset.*;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.orm.query.coordinator.UserTeamQuery;
import com.dqys.business.service.constant.ObjectEnum.*;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.dto.asset.*;
import com.dqys.business.service.dto.excel.ExcelMessage;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.exception.bean.LenderException;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.business.service.utils.asset.IouServiceUtils;
import com.dqys.business.service.utils.asset.LenderServiceUtils;
import com.dqys.business.service.utils.asset.PawnServiceUtils;
import com.dqys.business.service.utils.excel.ExcelUtilAsset;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.RandomUtil;
import com.dqys.core.utils.SysPropertyTool;
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
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
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
    private PiRelationMapper piRelationMapper;
    @Autowired
    private TCompanyInfoMapper companyInfoMapper;
    @Autowired
    private FollowUpReadstatusMapper followUpReadstatusMapper;

    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private CoordinatorService coordinatorService;

    @Override
    public JsonResponse add_tx(AssetDTO assetDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(assetDTO, assetDTO.getStartAt(), assetDTO.getEndAt())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        AssetInfo assetInfo = AssetServiceUtils.toAssetInfo(assetDTO);
        assetInfo.setOperator(userId);
        String typeStr = UserSession.getCurrent().getUserType();
        UserInfoEnum infoEnum = UserInfoEnum.getUserInfoEnum(Integer.valueOf(typeStr.substring(0, typeStr.indexOf(","))));
        if (infoEnum != null) {
            if (UserInfoEnum.USER_TYPE_COLLECTION.getValue().equals(infoEnum.getValue())) {
                assetInfo.setIsCollection(SysProperty.BOOLEAN_TRUE);
            } else if (UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().equals(infoEnum.getValue())) {
                assetInfo.setIsAgent(SysProperty.BOOLEAN_TRUE);
            } else if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue().equals(infoEnum.getValue())) {
                assetInfo.setIsLawyer(SysProperty.BOOLEAN_TRUE);
            }
        }
        if (assetInfo.getAssetNo() == null) {
            assetInfo.setAssetNo(RandomUtil.getCode(RandomUtil.ASSET_CODE));
        }
        Integer addResult = assetInfoMapper.insert(assetInfo);
        if (addResult.equals(1)) {
            Integer id = assetInfo.getId();
            // 增加业务数据
            Integer serviceObjectId = businessService.addServiceObject(ObjectTypeEnum.ASSETPACKAGE.getValue(), id, null, null);
            if (CommonUtil.checkResult(serviceObjectId)) {
                // todo 增加业务数据失败,记录

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
        return JsonResponseTool.success(assetDTO.getId());
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
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (CommonUtil.checkParam(type) || ObjectTabEnum.getObjectTabEnum(type) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        AssetQuery assetQuery = createAssetQuery(type);
        if (assetQuery == null || (assetQuery.getId() != null && assetQuery.getId().equals(SysProperty.NULL_DATA_ID))) {
            return JsonResponseTool.successNullList();
        }
        if (assetListQuery != null) {
            assetQuery = AssetServiceUtils.toAssetQuery(assetListQuery, assetQuery);
            if (assetListQuery.isOwn()) {
                assetQuery.setOperator(userId);
            }
        }

        Map<String, Object> map = new HashMap<>();
        if (!CommonUtil.checkParam(assetQuery.getIds(), assetQuery.getExceptIds())) {
            List<Integer> ids = CommonUtil.exceptList(assetQuery.getIds(), assetQuery.getExceptIds());
            if (CommonUtil.checkParam(ids) || ids.size() == 0) {
                return JsonResponseTool.successNullList();
            } else {
                assetQuery.setIds(ids);
            }
        }
        List<AssetInfo> assetInfoList = assetInfoMapper.pageList(assetQuery);
        if (assetInfoList == null || assetInfoList.size() == 0) {
            return JsonResponseTool.successNullList();
        } else {
            List<AssetListDTO> listDTOList = new ArrayList<>();
            assetInfoList.forEach(assetInfo -> {
                // todo 完成率
                listDTOList.add(AssetServiceUtils.toAssetListDTO(assetInfo,
                        userInfoMapper.selectByPrimaryKey(assetInfo.getOperator()),
                        null,
                        getBelong(assetInfo),
                        followUpReadstatusMapper.countByTypeIdUser(assetInfo.getId(),
                                ObjectTypeEnum.ASSETPACKAGE.getValue(),
                                userId)));
            });
            map.put("data", listDTOList);
            map.put("total", assetInfoMapper.queryCount(assetQuery));
            return JsonResponseTool.success(map);
        }
    }

    /**
     * 获取资产包在当前操作人公司的所属人
     *
     * @param assetInfo
     * @return
     */
    private String getBelong(AssetInfo assetInfo) {
        // 当前操作用户
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        TUserInfo currentUser = userInfoMapper.selectByPrimaryKey(userId);
        UserTeam userTeam = userTeamMapper.getByObject(
                assetInfo.getId(), ObjectTypeEnum.ASSETPACKAGE.getValue(), currentUser.getCompanyId());
        if (userTeam != null) {
            List<TeamDTO> teamDTOList = coordinatorService.getLenderOrAsset(userTeam.getCompanyId(),
                    assetInfo.getId(), ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (teamDTOList != null && teamDTOList.size() > 0) {
                for (TeamDTO teamDTO : teamDTOList) {
                    if (teamDTO.getRoleType().equals(TeammateReEnum.TYPE_AUXILIARY.getValue())) {
                        // 获取所属人身份信息
                        return userInfoMapper.selectByPrimaryKey(teamDTO.getUserId()).getUserName();
                    }
                }
            }
        }
        return null;
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
            for (int i = 0; i < assetLenderDTOList.size(); i++) {
                assetLenderDTOList.get(i).setAssetName(assetInfo.getName());
                ContactInfo contactInfo = contactInfoMapper.getByModel(
                        ObjectTypeEnum.LENDER.getValue().toString(),
                        ContactTypeEnum.LENDER.getValue(),
                        assetLenderDTOList.get(i).getId());
                if (contactInfo != null) {
                    assetLenderDTOList.get(i).setName(contactInfo.getName()); // 设置资产包名称
                }
            }
        }
        return JsonResponseTool.success(assetLenderDTOList);
    }

    /**
     * 将参数type转化成相应的搜索条件
     *
     * @param type
     * @return
     */
    private AssetQuery createAssetQuery(Integer type) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (type == null || ObjectTabEnum.getObjectTabEnum(type) == null) {
            return null;
        }

        AssetQuery assetQuery = new AssetQuery();
        boolean flag = CommonUtil.isManage(); // 是否是总管理员
        Boolean isPlatformOrEntrust = false; // 平台或者委托方
        Boolean isUrgeOrLawyer = false; // 催收或者律所
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        CompanyDetailInfo detailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId());
        if (detailInfo.getType().toString().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_ENTRUST).getPropertyValue())) {
            isPlatformOrEntrust = true;
        } else if (detailInfo.getType().toString().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM).getPropertyValue())) {
            isPlatformOrEntrust = true;
        } else if (detailInfo.getType().toString().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW).getPropertyValue())) {
            isUrgeOrLawyer = true;
        } else if (detailInfo.getType().toString().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE).getPropertyValue())) {
            isUrgeOrLawyer = true;
        }
        // 为当前操作人且类型为借款人,状态为通过的数据ID
        List<Integer> passIds = businessObjReMapper.listIdByTypeIdStatusUser(
                ObjectTypeEnum.ASSETPACKAGE.getValue(), BusinessStatusEnum.platform_pass.getValue(), userInfo.getId());
        List<Integer> disposeIds = businessObjReMapper.listIdByTypeIdStatusUser(
                ObjectTypeEnum.ASSETPACKAGE.getValue(), BusinessStatusEnum.dispose.getValue(), userInfo.getId());
        List<Integer> businessIds = CommonUtil.pickList(passIds, disposeIds);
        List<Integer> managerPassIds = businessObjReMapper.listIdByTypeIdStatus(
                ObjectTypeEnum.ASSETPACKAGE.getValue(), BusinessStatusEnum.platform_pass.getValue()
        );
        List<Integer> managerDisposeIds = businessObjReMapper.listIdByTypeIdStatus(
                ObjectTypeEnum.ASSETPACKAGE.getValue(), BusinessStatusEnum.dispose.getValue()
        );
        List<Integer> managerBusinessIds = CommonUtil.pickList(managerDisposeIds, managerPassIds);

        if (ObjectTabEnum.accept.getValue().equals(type)) {
            // 待接收 -- 其他机构发起邀请未处理&协作器内没有接收的
            // 分配器中的待接收
            List<Integer> distributionIds = companyTeamReMapper.listObjectIdByTypeAndManager(
                    ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    ObjectAcceptTypeEnum.init.getValue(),
                    userId
            );
            // 协作器中的待接收
            List<Integer> coordinatorIds = userTeamMapper.selectByOperatorAndStatus(userId,
                    TeammateReEnum.STATUS_INIT.getValue(), ObjectTypeEnum.ASSETPACKAGE.getValue());
            // 对象中的待接收对象集合
//            ObjectUserRelationQuery query = new ObjectUserRelationQuery();
//            query.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
//            query.setStatus(ObjectUserStatusEnum.accept.getValue());
//            List<ObjectUserRelation> objectList = objectUserRelationMapper.list(query);
//            List<Integer> objectIds = new ArrayList<>();
//            objectList.forEach(object -> {
//                objectIds.add(object.getObjectId());
//            });
//            List<Integer> result = CommonUtil.pickAll(distributionIds, coordinatorIds, objectIds);
            List<Integer> result = CommonUtil.pickAll(distributionIds, coordinatorIds);
            if (CommonUtil.checkParam(result) || result.size() == 0) {
                // 找不到数据,填充0数据限制
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setIds(result);
            }
        } else if (ObjectTabEnum.apply.getValue().equals(type)) {
            // 待申请 -- 暂未其他公司参与
            assetQuery.setNoTakePart(true);
            //还需要审核通过的对象
            List<Integer> objectIds = businessObjReMapper.auditObject(ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (objectIds != null && objectIds.size() > 0) {
                assetQuery.setIds(objectIds);
            } else {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
//            List<Integer> ids = companyTeamReMapper.listAssigned(ObjectTypeEnum.ASSETPACKAGE.getValue());
//            if (ids != null && ids.size() > 0) {
//                assetQuery.setExceptIds(ids);
//            }

//            if(isUrgeOrLawyer){
//                if(businessIds == null || businessIds.size() == 0){
//                    assetQuery.setId(SysProperty.NULL_DATA_ID);
//                }else{
//                    assetQuery.setIds(businessIds);
//                }
//            }
//            assetQuery.setOperator(userId);
        } else if (ObjectTabEnum.gongingOn.getValue().equals(type)) {
            // 正在进行
//            List<Integer> ids = companyTeamReMapper.listObjectIdByTypeAndManager(
//                    ObjectTypeEnum.LENDER.getValue(),
//                    ObjectAcceptTypeEnum.accept.getValue(),
//                    userId
//            );
//            if (ids == null || ids.size() == 0) {
//                //  找不到数据,填充0数据限制
//                assetQuery.setId(SysProperty.NULL_DATA_ID);
//            } else {
//                assetQuery.setIds(ids);
//            }
            // 自己分配
            ObjectUserRelationQuery query = new ObjectUserRelationQuery();
            query.setType(BusinessRelationEnum.own.getValue()); // 自己分配
            List<ObjectUserRelation> mine = objectUserRelationMapper.list(query);
            List<Integer> mineIds = new ArrayList<>();
            mine.forEach(objectUserRelation -> {
                mineIds.add(objectUserRelation.getObjectId());
            });
            // 公司分配
            query.setType(BusinessRelationEnum.company.getValue()); // 公司分配
            List<ObjectUserRelation> company = objectUserRelationMapper.list(query);
            List<Integer> companyIds = new ArrayList<>();
            company.forEach(objectUserRelation -> {
                companyIds.add(objectUserRelation.getObjectId());
            });
            // 团队分配
            query.setType(BusinessRelationEnum.team.getValue());
            List<ObjectUserRelation> team = objectUserRelationMapper.list(query);
            List<Integer> teamIds = new ArrayList<>();
            team.forEach(objectUserRelation -> {
                teamIds.add(objectUserRelation.getObjectId());
            });
            TeammateRe teammateRe = new TeammateRe();
            teammateRe.setType(TeammateReEnum.TYPE_ADMIN.getValue()); // 管理员创建的
            List<TeammateRe> adminList = teammateReMapper.selectSelective(teammateRe);
            List<Integer> adminIds = new ArrayList<>();
            adminList.forEach(teammateRe1 -> {
                UserTeam userTeam = userTeamMapper.get(teammateRe1.getUserTeamId());
                if (userTeam != null) {
                    adminIds.add(userTeam.getObjectId());
                }
            });
            teammateRe.setType(TeammateReEnum.TYPE_ADMIN.getValue()); // 管理员创建的
            List<TeammateRe> managerList = teammateReMapper.selectSelective(teammateRe);
            List<Integer> managerIds = new ArrayList<>();
            managerList.forEach(teammateRe1 -> {
                UserTeam userTeam = userTeamMapper.get(teammateRe1.getUserTeamId());
                if (userTeam != null) {
                    managerIds.add(userTeam.getObjectId());
                }
            });
            List<Integer> result = CommonUtil.pickList(mineIds, companyIds);
            List<Integer> teamateIds = CommonUtil.pickList(adminIds, managerIds);
            result = CommonUtil.pickList(result, CommonUtil.unionList(teamateIds, teamateIds));
            if (isUrgeOrLawyer) {
                assetQuery.setIds(CommonUtil.unionList(result, businessIds));
            } else {
                assetQuery.setIds(result);
            }
            if (CommonUtil.checkParam(assetQuery.getIds()) || assetQuery.getIds().size() == 0) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
            assetQuery.setRepayStatus(SysProperty.BOOLEAN_FALSE);
        } else if (ObjectTabEnum.focus.getValue().equals(type)) {
            // 聚焦
            assetQuery.setId(SysProperty.NULL_DATA_ID); // 暂时不显示数据
        } else if (ObjectTabEnum.month.getValue().equals(type)) {
            // 当月
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(userId);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            objectUserRelationQuery.setStartAt(calendar.getTime());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (isUrgeOrLawyer) {
                assetQuery.setIds(CommonUtil.unionList(ids, businessIds));
            } else {
                assetQuery.setIds(ids);
            }
            if (CommonUtil.checkParam(assetQuery.getIds()) || assetQuery.getIds().size() == 0) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.stock.getValue().equals(type)) {
            // 存量
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(userId);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            objectUserRelationQuery.setEndAt(calendar.getTime());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (isUrgeOrLawyer) {
                assetQuery.setIds(CommonUtil.unionList(ids, businessIds));
            } else {
                assetQuery.setIds(ids);
            }
            if (CommonUtil.checkParam(assetQuery.getIds()) || assetQuery.getIds().size() == 0) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.over.getValue().equals(type)) {
            // 完成
            List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    BusinessStatusEnum.end.getValue(), userId);
            assetQuery.setIds(ids);
            if (CommonUtil.checkParam(assetQuery.getIds()) || assetQuery.getIds().size() == 0) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.outTime.getValue().equals(type)) {
            // 超时
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(userId);
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (isPlatformOrEntrust || isUrgeOrLawyer) {
                assetQuery.setIds(CommonUtil.unionList(ids, businessIds));
            } else {
                assetQuery.setIds(ids);
            }
            assetQuery.setOutTime(true);
        } else if (ObjectTabEnum.invalid.getValue().equals(type)) {
            // 无效
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(userId);
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            assetQuery.setIds(ids);
            assetQuery.setStopStatus(2);//这里表示无效， 暂停状态为1
        } else if (ObjectTabEnum.join.getValue().equals(type)) {
            // 待参与
            UserTeamQuery query = new UserTeamQuery();
            if (!flag) {
                query.setCompanyId(userInfo.getCompanyId());
            }
            query.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            List<UserTeam> list = userTeamMapper.queryList(query); // 获取该公司下所有的协作器
            List<Integer> result = new ArrayList<>();
            for (UserTeam userTeam : list) {
                TeammateRe teammateRe = new TeammateRe();
                teammateRe.setUserTeamId(userTeam.getId());
                List<TeammateRe> reList = teammateReMapper.selectSelective(teammateRe);
                if (reList == null) {
                    result.add(userTeam.getObjectId());
                } else if (reList.size() < 6) {
                    boolean isExist = false;
                    for (TeammateRe re : reList) {
                        if (re.getUserId().equals(userInfo.getId())) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        result.add(userTeam.getObjectId());
                    }
                }
            }
            if (result == null || result.size() == 0) {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setIds(result);
            }
        } else if (ObjectTabEnum.joined.getValue().equals(type)) {
            // 已参与
            List<Integer> ids = userTeamMapper.selectByOperatorAndStatus(userId,
                    TeammateReEnum.STATUS_ACCEPT.getValue(), ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (ids != null && ids.size() > 0) {
                assetQuery.setIds(ids);
            } else {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.check.getValue().equals(type)) {
            // 待审核
            if (flag) {
                List<Integer> ids = businessObjReMapper.listIdByTypeIdStatus(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                        BusinessStatusEnum.init.getValue());
                assetQuery.setIds(ids);
            } else {
                List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                        BusinessStatusEnum.init.getValue(), userId);
                assetQuery.setIds(ids);
            }
            if (CommonUtil.checkParam(assetQuery.getIds()) || assetQuery.getIds().size() == 0) {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
            if (!flag) {
                assetQuery.setOperator(userInfo.getId());
            }
        } else if (ObjectTabEnum.refuse.getValue().equals(type)) {
            // 已驳回
            List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    BusinessStatusEnum.platform_refuse.getValue(), userId);
            if (!CommonUtil.checkParam(ids) && ids.size() > 0) {
                assetQuery.setIds(ids);
            } else {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
            if (!flag) {
                assetQuery.setOperator(userInfo.getId());
            }
        } else if (ObjectTabEnum.handle.getValue().equals(type)) {
            // 待处置
            if (flag) {
                assetQuery.setIds(managerPassIds); // 通过审核还未处置
            } else {
                assetQuery.setIds(passIds); // 通过审核还未处置
                assetQuery.setOperator(userInfo.getId());
            }
            if (CommonUtil.checkParam(assetQuery.getIds()) || assetQuery.getIds().size() == 0) {
                // 找不到数据
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.assign.getValue().equals(type)) {
            // 待分配
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(userId);
            }
            objectUserRelationQuery.setType(BusinessRelationEnum.team.getValue());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (!CommonUtil.checkParam(ids) && ids.size() > 0) {
                assetQuery.setExceptIds(ids);
            }
            assetQuery.setOperator(userInfo.getId());
            if (!flag) {
//                if (isPlatformOrEntrust) { // 修改于10.11
                if (businessIds != null && businessIds.size() > 0) {
                    assetQuery.setIds(businessIds);
                } else {
                    assetQuery.setId(SysProperty.NULL_DATA_ID);
                }
//                }
            }
        } else if (ObjectTabEnum.new48h.getValue().equals(type)) {
            // 48h 新
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(userId);
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (isUrgeOrLawyer) {
                assetQuery.setIds(CommonUtil.unionList(ids, businessIds));
            } else {
                assetQuery.setIds(ids);
            }
            if (CommonUtil.checkParam(assetQuery.getIds()) || assetQuery.getIds().size() == 0) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            assetQuery.setStartAt(calendar.getTime());
        } else if (ObjectTabEnum.task.getValue().equals(type)) {
            // 我的任务
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            objectUserRelationQuery.setUserId(userId);
            objectUserRelationQuery.setType(BusinessRelationEnum.team.getValue());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> relationIds = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                relationIds.add(objectUserRelation.getObjectId());
            });
            List<Integer> teammateIds = teammateReMapper.listObjectIdByType(ObjectTypeEnum.ASSETPACKAGE.getValue(),
                    userId, TeammateReEnum.TYPE_AUXILIARY.getValue());
            List<Integer> ids = CommonUtil.unionList(relationIds, teammateIds);
            if (CommonUtil.checkParam(ids) || ids.size() == 0) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                assetQuery.setIds(ids);
            }
        } else if (ObjectTabEnum.handling_urge.getValue().equals(type)
                || ObjectTabEnum.handling_entrust.getValue().equals(type)) {
            // 委托的处置中
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(userId);
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (CommonUtil.checkParam(ids) || ids.size() == 0) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                // 这里引入业务的处置中状态
                assetQuery.setIds(CommonUtil.unionList(ids, managerDisposeIds));
            }
            assetQuery.setTakePart(true);
            assetQuery.setStop(false);
            assetQuery.setStopStatus(0);//0正常1暂停2无效
        } else if (ObjectTabEnum.stop.getValue().equals(type)) {
            // 暂停
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(userId);
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            assetQuery.setIds(ids);
            assetQuery.setIsStop(true);
        } else if (ObjectTabEnum.all.getValue().equals(type)) {
            //全部
            if (CommonUtil.isManage()) {
                userId = null;
            }
            assetQuery.setIds(lenderInfoMapper.lenderAllByObjectUserRelation(userId, ObjectTypeEnum.ASSETPACKAGE.getValue()));
            if (assetQuery.getIds() == null) {
                assetQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else {
            return null;
        }
        return assetQuery;
    }

    @Override
    public JsonResponse addLender_tx(Integer id, List<ContactDTO> contactDTOList, LenderDTO lenderDTO,
                                     List<PawnDTO> pawnDTOList, List<IouDTO> iouDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(id, contactDTOList, lenderDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        // 借款人信息
        lenderDTO.setAssetId(id);
        LenderInfo lenderInfo = LenderServiceUtils.toLenderInfo(lenderDTO);
        if (lenderInfo.getLenderNo() == null) {
            lenderInfo.setLenderNo(RandomUtil.getCode(RandomUtil.LENDER_CODE));
        }
        lenderInfo.setOperator(userId);
        String typeStr = UserSession.getCurrent().getUserType();
        UserInfoEnum infoEnum = UserInfoEnum.getUserInfoEnum(Integer.valueOf(typeStr.substring(0, typeStr.indexOf(","))));
        if (infoEnum != null) {
            if (UserInfoEnum.USER_TYPE_COLLECTION.getValue().equals(infoEnum.getValue())) {
                lenderInfo.setIsCollection(SysProperty.BOOLEAN_TRUE);
            } else if (UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().equals(infoEnum.getValue())) {
                lenderInfo.setIsAgent(SysProperty.BOOLEAN_TRUE);
            } else if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue().equals(infoEnum.getValue())) {
                lenderInfo.setIsLawyer(SysProperty.BOOLEAN_TRUE);
            }
        }
        Integer result = lenderInfoMapper.insert(lenderInfo);
        if (CommonUtil.checkResult(result)) {
            // 添加失败
            return JsonResponseTool.failure("添加借款人失败");
        }
        Integer lenderId = lenderInfo.getId();

        // 添加借款人的联系人信息
        for (ContactDTO contactDTO : contactDTOList) {
            contactDTO.setMode(ObjectTypeEnum.LENDER.getValue().toString());
            contactDTO.setModeId(lenderId);
            result = contactInfoMapper.insert(LenderServiceUtils.toContactInfo(contactDTO));
            if (CommonUtil.checkResult(result)) {
                // todo 联系人增加失败,请处理

            }
        }
        // 添加业务
        businessService.addServiceObject(ObjectTypeEnum.LENDER.getValue(), lenderId,
                ObjectTypeEnum.ASSETPACKAGE.getValue(), lenderDTO.getAssetId());
        // 添加历史记录
        businessLogService.add(lenderId, ObjectTypeEnum.LENDER.getValue(), LenderEnum.ADD.getValue(),
                "", "", 0, 0);

        Map<Integer, String> pawnRelation = new HashMap<>();
        Map<Integer, String> iouRelation = new HashMap<>();
        Map<String, Integer> pawnIdMap = new HashMap<>();
        Map<String, Integer> iouIdMap = new HashMap<>();

        // 抵押物信息
        if (pawnDTOList != null && pawnDTOList.size() != 0) {
            for (PawnDTO pawnDTO : pawnDTOList) {
                pawnDTO.setLenderId(lenderId);
                PawnInfo pawnInfo = PawnServiceUtils.toPawnInfo(pawnDTO);
                if (pawnInfo.getPawnNo() == null) {
                    pawnInfo.setPawnNo(RandomUtil.getCode(RandomUtil.PAWN_CODE));
                }
                result = pawnInfoMapper.insert(pawnInfo);
                if (CommonUtil.checkResult(result)) {
                    // 添加失败
                    return JsonResponseTool.failure("添加抵押物失败");
                }
                businessService.addServiceObject(ObjectTypeEnum.PAWN.getValue(), pawnInfo.getId(),
                        ObjectTypeEnum.LENDER.getValue(), pawnDTO.getLenderId());
                // 增加操作记录
                businessLogService.add(pawnInfo.getId(), ObjectTypeEnum.PAWN.getValue(), PawnEnum.ADD.getValue(),
                        "", pawnDTO.getMemo(), 0, 0);
                if (pawnDTO.getIouNames() != null && pawnDTO.getIouNames().trim().length() > 0) {
                    pawnRelation.put(pawnInfo.getId(), pawnDTO.getIouNames().trim());
                }
                pawnIdMap.put(pawnDTO.getPawnName(), pawnInfo.getId());
            }
        }
        // 借据信息
        if (iouDTOList != null && iouDTOList.size() != 0) {
            for (IouDTO iouDTO : iouDTOList) {
                iouDTO.setLenderId(lenderId);
                IOUInfo iouInfo = IouServiceUtils.toIouInfo(iouDTO);
                if (iouInfo.getIouNo() == null) {
                    iouInfo.setIouNo(RandomUtil.getCode(RandomUtil.IOU_CODE));
                }
                result = iouInfoMapper.insert(iouInfo);
                if (CommonUtil.checkResult(result)) {
                    // 添加失败
                    return JsonResponseTool.failure("添加借据失败");
                }
                // 添加业务
                businessService.addServiceObject(ObjectTypeEnum.IOU.getValue(), iouInfo.getId(),
                        ObjectTypeEnum.LENDER.getValue(), iouDTO.getLenderId());
                // 添加操作记录
                businessLogService.add(iouInfo.getId(), ObjectTypeEnum.IOU.getValue(), IouEnum.ADD.getValue(),
                        "", iouDTO.getMemo(), 0, 0);
                if (iouDTO.getPawnNames() != null && iouDTO.getPawnNames().trim().length() > 0) {
                    iouRelation.put(iouInfo.getId(), iouDTO.getPawnNames().trim());
                }
                iouIdMap.put(iouDTO.getIouName(), iouInfo.getId());
            }
        }
        // 抵押物与借据之间的关联关系
        if (pawnRelation.size() > 0) {
            for (Integer pawnId : pawnRelation.keySet()) {
//                if (pawnRelation.get(pawnId) != null) {
                String nameArr[] = pawnRelation.get(pawnId).split(",");
                for (String name : nameArr) {
                    if (iouIdMap.get(name) != null) {
                        RelationQuery relationQuery = new RelationQuery();
                        relationQuery.setPawnId(pawnId);
                        relationQuery.setIouId(iouIdMap.get(name));
                        List<PiRelation> list = piRelationMapper.queryList(relationQuery);
                        if (list == null || list.size() == 0) {
                            // 防止重复数据
                            PiRelation piRelation = new PiRelation();
                            piRelation.setPawnId(pawnId);
                            piRelation.setIouId(iouIdMap.get(name));
                            piRelationMapper.insert(piRelation);
                        }
                    }
                }
//                }
            }
        }
        if (iouRelation.size() > 0) {
            for (Integer iouId : iouRelation.keySet()) {
                String nameStr = iouRelation.get(iouId);
                String nameArr[] = nameStr.split(",");
                for (String name : nameArr) {
                    if (pawnIdMap.get(name) != null) {
                        RelationQuery relationQuery = new RelationQuery();
                        relationQuery.setPawnId(pawnIdMap.get(name));
                        relationQuery.setIouId(iouId);
                        List<PiRelation> list = piRelationMapper.queryList(relationQuery);
                        if (list == null || list.size() == 0) {
                            // 防止重复数据
                            PiRelation piRelation = new PiRelation();
                            piRelation.setPawnId(pawnIdMap.get(name));
                            piRelation.setIouId(iouId);
                            piRelationMapper.insert(piRelation);
                        }
                    }
                }
            }
        }

        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse excelImport_tx(Integer id, String file) throws BusinessLogException, LenderException {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (CommonUtil.checkParam(id, file)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Map<String, Object> map = ExcelUtilAsset.uploadExcel(file);
        if (map.get("result").equals("error")) {
            List<ExcelMessage> error = (List<ExcelMessage>) map.get("data");
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setCode(ResponseCodeEnum.FAILURE.getValue());
            jsonResponse.setMsg("格式内容出错");
            jsonResponse.setData(error);
            return jsonResponse;
        }
        List<ContactDTO> contactDTOList = (List<ContactDTO>) map.get("contactDTOs");
        List<LenderDTO> lenderDTOList = (List<LenderDTO>) map.get("lenderDTOs");
        List<PawnDTO> pawnDTOList = (List<PawnDTO>) map.get("pawnDTOs");
        List<IouDTO> iouDTOList = (List<IouDTO>) map.get("iouDTOs");
        if (CommonUtil.checkParam(lenderDTOList, contactDTOList)) {
            return JsonResponseTool.paramErr("参数错误");
        }

        // 资产包信息
        AssetInfo assetInfo = assetInfoMapper.get(id);
        if (assetInfo == null) {
            return JsonResponseTool.paramErr("资产包信息错误，请重新操作");
        }

        Map<Integer, Integer> idMap = new HashMap<>();
        // 增加借款人基础信息
        for (LenderDTO lenderDTO : lenderDTOList) {
            // 增加继承信息
            lenderDTO.setAssetId(id);
            LenderInfo lenderInfo = LenderServiceUtils.toLenderInfo(lenderDTO);
            lenderInfo.setAttribute(assetInfo.getAttribute()); // 借款人公私有继承自资产包
            lenderInfo.setEntrustName(assetInfo.getEntrustName()); // 借款人委托方继承自资产包
            lenderInfo.setOperator(userId);
            if (lenderInfo.getLenderNo() == null) {
                lenderInfo.setLenderNo(RandomUtil.getCode(RandomUtil.LENDER_CODE));
            }
            lenderInfo.setOperator(userId);
            String typeStr = UserSession.getCurrent().getUserType();
            UserInfoEnum infoEnum = UserInfoEnum.getUserInfoEnum(Integer.valueOf(typeStr.substring(0, typeStr.indexOf(","))));
            if (infoEnum != null) {
                if (UserInfoEnum.USER_TYPE_COLLECTION.getValue().equals(infoEnum.getValue())) {
                    lenderInfo.setIsCollection(SysProperty.BOOLEAN_TRUE);
                } else if (UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().equals(infoEnum.getValue())) {
                    lenderInfo.setIsAgent(SysProperty.BOOLEAN_TRUE);
                } else if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue().equals(infoEnum.getValue())) {
                    lenderInfo.setIsLawyer(SysProperty.BOOLEAN_TRUE);
                }
            }
            Integer result = lenderInfoMapper.insert(lenderInfo);
            if (CommonUtil.checkResult(result)) {
                return JsonResponseTool.failure("增加借款人基础信息失败");
            }
            // 添加业务
            businessService.addServiceObject(ObjectTypeEnum.LENDER.getValue(), lenderInfo.getId(),
                    ObjectTypeEnum.ASSETPACKAGE.getValue(), lenderDTO.getAssetId());
            // 添加历史记录
            businessLogService.add(lenderInfo.getId(), ObjectTypeEnum.LENDER.getValue(), LenderEnum.ADD.getValue(),
                    "", "", 0, 0);
            idMap.put(lenderDTO.getId(), lenderInfo.getId());
        }
        // 增加借款人的相关联系人
        for (ContactDTO contactDTO : contactDTOList) {
            if (idMap.get(contactDTO.getId()) == null) {
                throw new LenderException("借款人的相关联系人序号关联不对", LenderException.EXCEPTION_CODE);
            }
            contactDTO.setMode(ObjectTypeEnum.LENDER.getValue().toString());
            contactDTO.setModeId(idMap.get(contactDTO.getId()));
            ContactInfo contactInfo = LenderServiceUtils.toContactInfo(contactDTO);
            Integer result = contactInfoMapper.insert(contactInfo);
            if (CommonUtil.checkResult(result)) {
                throw new LenderException("增加借款人相关联系人基础信息失败", LenderException.EXCEPTION_CODE);
            }
        }
        // 增加抵押物
        Map<String, Integer> keyMap = new HashMap<>(); // 增加关联关系映射，这里的以抵押物名称，借据中的的映射为准
        for (PawnDTO pawnDTO : pawnDTOList) {
            if (idMap.get(pawnDTO.getId()) == null) {
                throw new LenderException("借款人的相关联系人序号关联不对", LenderException.EXCEPTION_CODE);
            }
            pawnDTO.setLenderId(idMap.get(pawnDTO.getId()));
            PawnInfo pawnInfo = PawnServiceUtils.toPawnInfo(pawnDTO);
            if (pawnInfo.getPawnNo() == null) {
                pawnInfo.setPawnNo(RandomUtil.getCode(RandomUtil.PAWN_CODE));
            }
//            String typeStr = UserSession.getCurrent().getUserType();
//            UserInfoEnum infoEnum = UserInfoEnum.getUserInfoEnum(Integer.valueOf(typeStr.substring(0, typeStr.indexOf(","))));
//            if (infoEnum != null) {
//                if (UserInfoEnum.USER_TYPE_COLLECTION.getValue().equals(infoEnum.getValue())) {
//                    pawnInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
//                } else if (UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().equals(infoEnum.getValue())) {
//                    pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
//                } else if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue().equals(infoEnum.getValue())) {
//                    pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
//                }
//            }
            Integer result = pawnInfoMapper.insert(pawnInfo);
            if (CommonUtil.checkResult(result)) {
                throw new LenderException("增加抵押物信息失败", LenderException.EXCEPTION_CODE);
            }
            businessService.addServiceObject(ObjectTypeEnum.PAWN.getValue(), pawnInfo.getId(),
                    ObjectTypeEnum.LENDER.getValue(), pawnDTO.getLenderId());
            // 增加操作记录
            businessLogService.add(pawnInfo.getId(), ObjectTypeEnum.PAWN.getValue(), PawnEnum.ADD.getValue(),
                    "", pawnDTO.getMemo(), 0, 0);
            map.put(pawnDTO.getLenderId() + pawnDTO.getPawnName(), pawnInfo.getId());
        }
        // 增加借据
        for (IouDTO iouDTO : iouDTOList) {
            if (idMap.get(iouDTO.getId()) == null) {
                throw new LenderException("借款人的相关联系人序号关联不对", LenderException.EXCEPTION_CODE);
            }
            iouDTO.setLenderId(idMap.get(iouDTO.getId()));
            IOUInfo iouInfo = IouServiceUtils.toIouInfo(iouDTO);
            if (iouInfo.getIouNo() == null) {
                iouInfo.setIouNo(RandomUtil.getCode(RandomUtil.IOU_CODE));
            }
//            String typeStr = UserSession.getCurrent().getUserType();
//            UserInfoEnum infoEnum = UserInfoEnum.getUserInfoEnum(Integer.valueOf(typeStr.substring(0, typeStr.indexOf(","))));
//            if (infoEnum != null) {
//                if (UserInfoEnum.USER_TYPE_COLLECTION.getValue().equals(infoEnum.getValue())) {
//                    iouInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
//                } else if (UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().equals(infoEnum.getValue())) {
//                    iouInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
//                } else if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue().equals(infoEnum.getValue())) {
//                    iouInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
//                }
//            }
            Integer result = iouInfoMapper.insert(iouInfo);
            if (CommonUtil.checkResult(result)) {
                throw new LenderException("增加借据信息失败", LenderException.EXCEPTION_CODE);
            }
            // 添加业务
            businessService.addServiceObject(ObjectTypeEnum.IOU.getValue(), iouInfo.getId(),
                    ObjectTypeEnum.LENDER.getValue(), iouDTO.getLenderId());
            // 添加操作记录
            businessLogService.add(iouInfo.getId(), ObjectTypeEnum.IOU.getValue(), IouEnum.ADD.getValue(),
                    "", iouDTO.getMemo(), 0, 0);
            // 增加关联关系
            if (iouDTO.getPawnNames() != null && iouDTO.getPawnNames().length() > 0) {
                String[] nameStr = iouDTO.getPawnNames().split(",");
                for (String s : nameStr) {
                    if (s.length() > 0 && keyMap.get(s) != null) {
                        RelationQuery query = new RelationQuery();
                        query.setPawnId(keyMap.get(s));
                        query.setIouId(iouInfo.getId());
                        List<PiRelation> relation = piRelationMapper.queryList(query);
                        if (relation == null || relation.size() == 0) {
                            PiRelation piRelation = new PiRelation();
                            piRelation.setIouId(iouInfo.getId());
                            piRelation.setPawnId(keyMap.get(s));
                            piRelationMapper.insert(piRelation);
                        }
                    }
                }
            }
        }
        return JsonResponseTool.success(null);
    }
}
