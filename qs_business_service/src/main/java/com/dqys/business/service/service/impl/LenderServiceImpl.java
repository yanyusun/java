
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
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.pojo.asset.*;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.orm.query.asset.*;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.orm.query.coordinator.UserTeamQuery;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.constant.ObjectLogEnum;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.dto.asset.*;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.LenderListQuery;
import com.dqys.business.service.service.*;
import com.dqys.business.service.utils.asset.IouServiceUtils;
import com.dqys.business.service.utils.asset.LenderServiceUtils;
import com.dqys.business.service.utils.asset.PawnServiceUtils;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.base.BaseSelectonDTO;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Yvan on 16/6/12.
 */
@Service
@Primary
public class LenderServiceImpl implements LenderService {

    @Autowired
    private ContactInfoMapper contactInfoMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private CompanyTeamMapper companyTeamMapper;
    @Autowired
    private CompanyTeamReMapper companyTeamReMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;
    @Autowired
    private UserTeamMapper userTeamMapper;
    @Autowired
    private TeammateReMapper teammateReMapper;
    @Autowired
    private BusinessObjReMapper businessObjReMapper;
    @Autowired
    private TUserInfoMapper userInfoMapper;
    @Autowired
    private TCompanyInfoMapper companyInfoMapper;
    @Autowired
    private PiRelationMapper piRelationMapper;

    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private CoordinatorService coordinatorService;


    @Override
    public JsonResponse queryList(LenderListQuery lenderListQuery, Integer type) {
        LenderQuery lenderQuery = createQueryByTab(type);
        if (lenderQuery == null) {
            return null; // 类型不对,参数错误
        }
        if (SysProperty.NULL_DATA_ID.equals(lenderQuery.getId())) {
            // 搜索不到数据
            return JsonResponseTool.successNullList();
        }
        // 不同tag类型有不同的搜索条件
        lenderQuery.setIsPaging(true);
        lenderQuery.setPageSize(lenderListQuery.getPageCount());
        if (lenderListQuery.getPage() > 0) {
            lenderQuery.setStartPageNum((lenderListQuery.getPage() - 1) * lenderListQuery.getPageCount());
        } else {
            lenderQuery.setStartPageNum(0);
        }
        lenderQuery = LenderServiceUtils.toLenderQuery(lenderQuery, lenderListQuery);

        if (lenderListQuery.getAssetNo() != null) {
            AssetQuery assetQuery = new AssetQuery();
            assetQuery.setCode(lenderListQuery.getAssetNo());
            List<AssetInfo> assetInfoList = assetInfoMapper.pageList(assetQuery);
            if (assetInfoList == null) {
                // 搜索不到数据
                return JsonResponseTool.successNullList();
            } else {
                lenderQuery.setAssetId(assetInfoList.get(0).getId());
            }
        }
        if (lenderListQuery.getSearch() != null) {
            // 搜索内容(借款人编号,用户姓名,手机号,备注)
            ContactQuery contactQuery = new ContactQuery();
            contactQuery.setMode(ObjectTypeEnum.LENDER.getValue().toString());
            contactQuery.setType(ContactTypeEnum.LENDER.getValue());
            contactQuery.setListSearch(lenderListQuery.getSearch());
            List<ContactInfo> contactInfoList = contactInfoMapper.queryList(contactQuery);
            List<Integer> ids = new ArrayList<>();
            contactInfoList.forEach(contactInfo -> {
                ids.add(contactInfo.getModeId());
            });
//            lenderQuery.setListSearch(lenderListQuery.getSearch());
            // 這裡模糊查询是或结构，故需要先查询出借款人的符合数据
            List<Integer> listSearchIds = lenderInfoMapper.likeList(lenderListQuery.getSearch());
            if (!CommonUtil.checkParam(lenderQuery.getIds())) {
                List<Integer> result = CommonUtil.pickList(listSearchIds, ids);
                lenderQuery.setIds(CommonUtil.unionList(lenderQuery.getIds(), result));
            }
        }
        if (lenderListQuery.getBelong() != null) {
            // 所属人
            List<Integer> ids = teammateReMapper.listObjectIdByJoinType(ObjectTypeEnum.LENDER.getValue(),
                    UserSession.getCurrent().getUserId(), TeammateReEnum.TYPE_AUXILIARY.getValue());
            if (CommonUtil.checkParam(ids)) {
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
            if (lenderQuery.getIds() != null) {
                List<Integer> result = CommonUtil.unionList(ids, lenderQuery.getIds());
                if (CommonUtil.checkParam(result)) {
                    // 搜索不到数据,理论上不存在
                    return JsonResponseTool.successNullList();
                } else {
                    lenderQuery.setIds(result);
                }
            }
        }
        if (lenderListQuery.getUrgeType() != null) {
            // 催收阶段
            if (lenderListQuery.getUrgeType().equals(KeyEnum.U_TYPE_INTERMEDIARY)) {
                lenderQuery.setIsAgent(true);
            } else if (KeyEnum.U_TYPE_LAW.equals(lenderListQuery.getUrgeType())) {
                lenderQuery.setIsLawyer(true);
            } else if (KeyEnum.U_TYPE_URGE.equals(lenderListQuery.getUrgeType())) {
                lenderQuery.setIsCollection(true);
            } else if ("0".equals(lenderListQuery.getUrgeType())) {
                // 常规催收司法化解同时进行
                lenderQuery.setIsLawyer(true);
                lenderQuery.setIsCollection(true);
            } else {
                // 搜索不到数据,理论上不存在
                return JsonResponseTool.successNullList();
            }
        }
        if (lenderListQuery.getOutDays() != null) {
            // N天以上未催收借款人 > 受邀请时间
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 0 - lenderListQuery.getOutDays());
            lenderQuery.setBelongFollowDate(calendar.getTime());
        }
//        if ("1".equals(lenderListQuery.getIsAssigned())) {
        if (lenderListQuery.isAssigned()) {
            // 已分配
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
            if (CommonUtil.checkParam(userInfo, userInfo.getCompanyId())) {
                // 搜索不到数据,理论上不存在
                return JsonResponseTool.successNullList();
            }
            UserTeamQuery userTeamQuery = new UserTeamQuery();
            userTeamQuery.setCompanyId(userInfo.getCompanyId());
            userTeamQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            List<UserTeam> userTeamList = userTeamMapper.queryList(userTeamQuery);
            List<Integer> result = new ArrayList<>();
            userTeamList.forEach(userTeam -> {
                result.add(userTeam.getObjectId());
            });
            if (CommonUtil.checkParam(result)) {
                // 搜索不到数据,理论上不存在
                return JsonResponseTool.successNullList();
            }
            if (!CommonUtil.checkParam(lenderQuery.getIds())) {
                lenderQuery.setIds(CommonUtil.unionList(lenderQuery.getIds(), result));
            }
        }
//        if ("1".equals(lenderListQuery.getPassIn())) {
        if (lenderListQuery.isPassIn()) {
            // 转入 -- 被邀请中接收的
            List<Integer> ids = companyTeamReMapper.listObjectIdByTypeAndManager(ObjectTypeEnum.LENDER.getValue(),
                    ObjectAcceptTypeEnum.accept.getValue(), UserSession.getCurrent().getUserId()
            );
            if (CommonUtil.checkParam(ids)) {
                return JsonResponseTool.successNullList();
            }
            if (!CommonUtil.checkParam(lenderQuery.getIds())) {
                lenderQuery.setIds(CommonUtil.unionList(lenderQuery.getIds(), ids));
            }
        }
        if ("1".equals(lenderListQuery.getUrgeType())) {
            // 转出 -- 创建分配器
            List<CompanyTeam> companyTeamList = companyTeamMapper.listBySendId(ObjectTypeEnum.LENDER.getValue(),
                    UserSession.getCurrent().getUserId());
            List<Integer> result = new ArrayList<>();
            companyTeamList.forEach(companyTeam -> {
                result.add(companyTeam.getObjectId());
            });
            if (CommonUtil.checkParam(result)) {
                // 搜索不到数据,理论上不存在
                return JsonResponseTool.successNullList();
            }
            if (!CommonUtil.checkParam(lenderQuery.getIds())) {
                lenderQuery.setIds(CommonUtil.unionList(lenderQuery.getIds(), result));
            }
        }
//        if ("1".equals(lenderListQuery.getWaitAssist())) {
        if (lenderListQuery.isWaitAssist()) {
            // 待协助 -- 协作器里面状态为参与者
            List<Integer> ids = teammateReMapper.listObjectIdByJoinType(ObjectTypeEnum.LENDER.getValue(),
                    UserSession.getCurrent().getUserId(), TeammateReEnum.TYPE_PARTICIPATION.getValue());
            if (CommonUtil.checkParam(ids)) {
                return JsonResponseTool.successNullList();
            }
            if (!CommonUtil.checkParam(lenderQuery.getIds())) {
                lenderQuery.setIds(CommonUtil.unionList(lenderQuery.getIds(), ids));
            }
        }
//        if ("1".equals(lenderListQuery.getAssist())) {
        if (lenderListQuery.isAssist()) {
            // 正在协助的 -- 协作器里面状态为所有人
            List<Integer> ids = teammateReMapper.listObjectIdByJoinType(ObjectTypeEnum.LENDER.getValue(),
                    UserSession.getCurrent().getUserId(), TeammateReEnum.TYPE_AUXILIARY.getValue());
            if (CommonUtil.checkParam(ids)) {
                return JsonResponseTool.successNullList();
            }
            if (!CommonUtil.checkParam(lenderQuery.getIds())) {
                lenderQuery.setIds(CommonUtil.unionList(lenderQuery.getIds(), ids));
            }
        }
        if (!CommonUtil.checkParam(lenderQuery.getExceptIds())) {
            if (!CommonUtil.checkParam(lenderQuery.getIds())) {
                List<Integer> idList = CommonUtil.exceptList(lenderQuery.getIds(), lenderQuery.getExceptIds());
                if (idList != null || idList.size() == 0) {
                    // 搜索不到数据,理论上不存在
                    return JsonResponseTool.successNullList();
                } else {
                    lenderQuery.setIds(idList);
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        Integer count = lenderInfoMapper.queryCount(lenderQuery);
        map.put("total", count);
        List<LenderInfo> lenderInfoList = lenderInfoMapper.queryList(lenderQuery);
        List<LenderListDTO> lenderListDTOList = new ArrayList<>();
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        lenderInfoList.forEach(lenderInfo -> {
            // 借款人信息
            ContactInfo contactInfo = contactInfoMapper.getByModel(ObjectTypeEnum.LENDER.getValue().toString(),
                    ContactTypeEnum.LENDER.getValue(), lenderInfo.getId());
            // 协作器
            if (userInfo != null) {
                UserTeam userTeam = userTeamMapper.getByObject(
                        lenderInfo.getId(), ObjectTypeEnum.LENDER.getValue(), userInfo.getCompanyId());
                List<TeamDTO> teamDTOList = new ArrayList<>();
                if (userTeam != null) {
                    teamDTOList = coordinatorService.getLenderOrAsset(userTeam.getCompanyId(),
                            lenderInfo.getId(), ObjectTypeEnum.LENDER.getValue());
                }
                lenderListDTOList.add(LenderServiceUtils.toLenderListDTO(lenderInfo, contactInfo, teamDTOList));
            }
        });
        map.put("data", lenderListDTOList);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse add_tx(List<ContactDTO> contactDTOList, LenderDTO lenderDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(contactDTOList, lenderDTO) || contactDTOList.size() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        boolean flag = false;
        for (ContactDTO contactDTO : contactDTOList) {
            if (ContactTypeEnum.getContactTypeEnum(contactDTO.getType()) == null) {
                return JsonResponseTool.paramErr("联系人类型参数错误");
            }
            if (ContactTypeEnum.LENDER.getValue()
                    .equals(ContactTypeEnum.getContactTypeEnum(contactDTO.getType()).getValue())) {
                flag = true;
            }
        }
        if (!flag) {
            return JsonResponseTool.paramErr("缺少借款人信息");
        }
        // 添加借款人基础信息
        LenderInfo lenderInfo = LenderServiceUtils.toLenderInfo(lenderDTO);
        lenderInfo.setLenderNo(RandomUtil.getCode(RandomUtil.LENDER_CODE));
        lenderInfo.setOperator(UserSession.getCurrent().getUserId());
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
        Integer addResult = lenderInfoMapper.insert(lenderInfo);
        if (CommonUtil.checkResult(addResult)) {
            return JsonResponseTool.failure("添加失败");
        }
        Integer lenderId = lenderInfo.getId();
        // 增加借款人相关联系人的身份信息
        for (ContactDTO contactDTO : contactDTOList) {
            contactDTO.setMode(ObjectTypeEnum.LENDER.getValue().toString());
            contactDTO.setModeId(lenderId);
            Integer result = contactInfoMapper.insert(LenderServiceUtils.toContactInfo(contactDTO));
            if (CommonUtil.checkResult(result)) {
                // todo 联系人增加失败,请处理

            }
        }
        // 添加业务
        if (lenderDTO.getAssetId() == null) {
            businessService.addServiceObject(ObjectTypeEnum.LENDER.getValue(), lenderId, null, null);
        } else {
            businessService.addServiceObject(ObjectTypeEnum.LENDER.getValue(), lenderId,
                    ObjectTypeEnum.ASSETPACKAGE.getValue(), lenderDTO.getAssetId());
        }
        // 添加历史记录
        businessLogService.add(lenderId, ObjectTypeEnum.LENDER.getValue(), ObjectLogEnum.add.getValue(),
                "", "", 0, 0);
        return JsonResponseTool.success(lenderId);
    }

    @Override
    public JsonResponse delete_tx(Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer lender = lenderInfoMapper.deleteByPrimaryKey(id);
        Integer contact = contactInfoMapper.deleteByMode(ObjectTypeEnum.LENDER.getValue().toString(), id);

        if (CommonUtil.checkResult(lender) && CommonUtil.checkResult(contact)) {
            // 添加历史记录
            businessLogService.add(id, ObjectTypeEnum.LENDER.getValue(), LenderEnum.DELETE.getValue(),
                    "", "", 0, 0);
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("删除失败");
        }
    }


    @Override
    public JsonResponse update_tx(List<ContactDTO> contactDTOList, LenderDTO lenderDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(contactDTOList, lenderDTO, lenderDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        boolean flag = false;
        for (ContactDTO contactDTO : contactDTOList) {
            if (ContactTypeEnum.getContactTypeEnum(contactDTO.getType()) == null) {
                return JsonResponseTool.paramErr("联系人类型参数错误");
            }
            if (ContactTypeEnum.LENDER.getValue().equals(ContactTypeEnum.getContactTypeEnum(contactDTO.getType()).getValue())) {
                flag = true;
            }
        }
        if (!flag) {
            return JsonResponseTool.paramErr("缺少借款人信息");
        }
        Integer result = lenderInfoMapper.update(LenderServiceUtils.toLenderInfo(lenderDTO));
        if (!CommonUtil.checkResult(result)) {
            flag = true;
        }
        // 添加历史记录
        businessLogService.add(lenderDTO.getId(), ObjectTypeEnum.LENDER.getValue(), LenderEnum.UPDATE_EDIT.getValue(),
                "", "", 0, 0);
        // 流程:比较先有的联系人信息与数据库中的差异性,余删缺增.
        List<ContactInfo> contactInfoList = contactInfoMapper.listByMode(
                ObjectTypeEnum.LENDER.getValue().toString(), lenderDTO.getId());
        for (ContactInfo contactInfo : contactInfoList) {
            contactInfoMapper.deleteByPrimaryKey(contactInfo.getId());
        }
        // 补足新增联系人
        for (ContactDTO contactDTO : contactDTOList) {
            ContactInfo info = LenderServiceUtils.toContactInfo(contactDTO);
            info.setMode(ObjectTypeEnum.LENDER.getValue().toString());
            info.setModeId(lenderDTO.getId());
            contactInfoMapper.insert(info);
        }
        return JsonResponseTool.success(lenderDTO.getId());
    }

    @Override
    public JsonResponse get(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        LenderInfo lenderInfo = lenderInfoMapper.get(id);
        if (lenderInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        LenderDTO lenderDTO = LenderServiceUtils.toLenderDTO(lenderInfo);
        ContactInfo contactInfo = contactInfoMapper.getByModel(
                ObjectTypeEnum.LENDER.getValue().toString(), ContactTypeEnum.LENDER.getValue(), id);
        if (contactInfo != null) {
            lenderDTO.setName(contactInfo.getName());
            lenderDTO.setSex(contactInfo.getGender());
        }
        return JsonResponseTool.success(lenderDTO);
    }

    @Override
    public JsonResponse getLenderAll(Integer id) {
        Map<String, Object> resultMap = new HashMap<>();
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        // 借款人
        LenderInfo lenderInfo = lenderInfoMapper.get(id);
        if (lenderInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        LenderDTO lenderDTO = LenderServiceUtils.toLenderDTO(lenderInfo);
        if (lenderInfo.getAssetId() != null) {
            AssetInfo assetInfo = assetInfoMapper.get(lenderInfo.getAssetId());
            if (assetInfo != null) {
                lenderDTO.setAssetCode(assetInfo.getAssetNo());
            }
        }
        // 联系人
        List<ContactInfo> contactInfoList = contactInfoMapper.listByMode(ObjectTypeEnum.LENDER.getValue().toString(), lenderInfo.getId());
        resultMap.put("contactDTOs", LenderServiceUtils.toContactDTO(contactInfoList));
        for (ContactInfo contactInfo : contactInfoList) {
            if (contactInfo.getType().equals(ContactTypeEnum.LENDER.getValue())
                    ) {
                lenderDTO.setName(contactInfo.getName());
                lenderDTO.setCurrentAddress(AreaTool.getAreaById(contactInfo.getProvince()).getLabel()
                                + AreaTool.getAreaById(contactInfo.getCity()).getLabel()
                                + AreaTool.getAreaById(contactInfo.getDistrict()).getLabel()
                                + contactInfo.getAddress()
                );
                break;
            }
        }
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        String userType = UserSession.getCurrent() == null ? "0" : UserSession.getCurrent().getUserType();
        if (userType.indexOf(",") > 0) {
            userType = userType.substring(0, userType.indexOf(","));
        }
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setObjectType(ObjectTypeEnum.LENDER.getValue());
        query.setObjectId(id);
        query.setUserId(userId);
        List<ObjectUserRelation> list = objectUserRelationMapper.list(query);
        boolean flag = false; // 是否业务流转
        if (list.size() > 0) {
            ObjectUserRelation our = list.get(0);
            if (our.getVisibleType() != null && our.getVisibleType() == 1) {
                flag = true;
            }
        }
        List<PawnInfo> pawnInfoList; //抵押物
        List<IOUInfo> iouList; //借据
        if (flag) {
            pawnInfoList = pawnInfoMapper.pawnListByLenderId(id, userId, ObjectTypeEnum.PAWN.getValue(), MessageUtils.transStringToInt(userType));
            iouList = iouInfoMapper.iouListByLenderId(id, userId, ObjectTypeEnum.IOU.getValue(), MessageUtils.transStringToInt(userType));
        } else {
            pawnInfoList = pawnInfoMapper.listByLenderId(id);
            iouList = iouInfoMapper.listByLenderId(id);
        }
        // 借据
        List<IouDTO> iouDTOList = new ArrayList<>();
        Date date = null;
        for (IOUInfo iouInfo : iouList) {
            // 获取抵押物与借据的关联
            iouDTOList.add(changeToDTO(iouInfo));
            // 获取最大的结束时间
            if (date == null
                    || (iouInfo.getEndAt() != null && iouInfo.getEndAt().compareTo(date) > 0)) {
                date = iouInfo.getEndAt();
            }
        }
        if (date == null) {
            date = new Date();
        }
        resultMap.put("iouDTOs", iouDTOList);
        // 获取逾期天数
        Calendar calendar = Calendar.getInstance();
        if (calendar.getTime().compareTo(date) > 0) {
            long millis = (calendar.getTimeInMillis() - date.getTime()) / 24 / 3600 / 1000;
            lenderDTO.setOverdueDay(Integer.valueOf(String.valueOf(millis)));
        }
        // 抵押物
        List<PawnDTO> pawnDTOList = new ArrayList<>();
        for (PawnInfo pawnInfo : pawnInfoList) {
            pawnDTOList.add(changeToDTO(pawnInfo));
        }
        resultMap.put("pawnDTOs", pawnDTOList);
        // 协作器
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        UserTeam userTeam = userTeamMapper.getByObject(
                lenderInfo.getId(), ObjectTypeEnum.LENDER.getValue(), userInfo.getCompanyId());
        if (userTeam != null) {
            TeammateRe teammateRe = new TeammateRe();
            teammateRe.setUserTeamId(userTeam.getId());
            teammateRe.setType(TeammateReEnum.TYPE_AUXILIARY.getValue());
            List<TeammateRe> teammateReList = teammateReMapper.selectSelective(teammateRe);
            if (teammateReList != null && teammateReList.size() > 0 && teammateReList.get(0).getUserId() != null) {
                TUserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(teammateReList.get(0).getUserId());
                if (userInfo1 != null) {
                    lenderDTO.setBelong(userInfo1.getRealName());
                }
            }
        }
        resultMap.put("lenderDTO", lenderDTO);
        return JsonResponseTool.success(resultMap);
    }


    @Override
    public JsonResponse listLender(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        List<BaseSelectonDTO> selectonDTOList = new ArrayList<>();
        List<LenderInfo> lenderInfoList = lenderInfoMapper.listByAssetId(id);
        for (LenderInfo lenderInfo : lenderInfoList) {
            ContactInfo contactInfo = contactInfoMapper.getByModel(
                    ObjectTypeEnum.LENDER.getValue().toString(), ContactTypeEnum.LENDER.getValue(), lenderInfo.getId()
            );
            if (contactInfo != null) {
                BaseSelectonDTO selectonDTO = new BaseSelectonDTO();
                selectonDTO.setKey(lenderInfo.getId().toString());
                selectonDTO.setValue(contactInfo.getName());
                selectonDTOList.add(selectonDTO);
            }
        }
        return JsonResponseTool.success(selectonDTOList);
    }

    /**
     * 转化DAO为DTO
     *
     * @param pawnInfo
     * @return
     */
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

    /**
     * 将DAO转化为DTO
     *
     * @param iouInfo
     * @return
     */
    public IouDTO changeToDTO(IOUInfo iouInfo) {
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

    /**
     * 根据导航栏的标签,设置不同的搜索条件
     *
     * @param tab
     * @return
     */
    private LenderQuery createQueryByTab(Integer tab) {
        if (tab == null || ObjectTabEnum.getObjectTabEnum(tab) == null) {
            return null;
        }
        boolean flag = CommonUtil.isManage(); // 是否是总管理员
        Boolean isPlatformOrEntrust = false; // 平台或者委托方
        Boolean isUrgeOrLawyer = false; // 催收或者律所
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        CompanyDetailInfo detailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId());
        if (detailInfo.getType().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_ENTRUST).getPropertyValue())) {
            isPlatformOrEntrust = true;
        } else if (detailInfo.getType().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM).getPropertyValue())) {
            isPlatformOrEntrust = true;
        } else if (detailInfo.getType().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW).getPropertyValue())) {
            isUrgeOrLawyer = true;
        } else if (detailInfo.getType().equals(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE).getPropertyValue())) {
            isUrgeOrLawyer = true;
        }
        // 为当前操作人且类型为借款人,状态为通过的数据ID
        List<Integer> passIds = businessObjReMapper.listIdByTypeIdStatusUser(
                ObjectTypeEnum.LENDER.getValue(), BusinessStatusEnum.platform_pass.getValue(), userInfo.getId());
        List<Integer> disposeIds = businessObjReMapper.listIdByTypeIdStatusUser(
                ObjectTypeEnum.LENDER.getValue(), BusinessStatusEnum.dispose.getValue(), userInfo.getId());
        List<Integer> businessIds = CommonUtil.pickList(passIds, disposeIds);
        List<Integer> managerPassIds = businessObjReMapper.listIdByTypeIdStatus(
                ObjectTypeEnum.LENDER.getValue(), BusinessStatusEnum.platform_pass.getValue()
        );
        List<Integer> managerDisposeIds = businessObjReMapper.listIdByTypeIdStatus(
                ObjectTypeEnum.LENDER.getValue(), BusinessStatusEnum.dispose.getValue()
        );
        List<Integer> managerBusinessIds = CommonUtil.pickList(managerDisposeIds, managerPassIds);

        LenderQuery lenderQuery = new LenderQuery();
        if (ObjectTabEnum.accept.getValue().equals(tab)) {
            // 待接收 -- 其他机构发起邀请未处理&协作器内没有接收的
            // 分配器中的待接收
            List<Integer> distributionIds = companyTeamReMapper.listObjectIdByTypeAndManager(
                    ObjectTypeEnum.LENDER.getValue(),
                    ObjectAcceptTypeEnum.init.getValue(),
                    UserSession.getCurrent().getUserId()
            );
            // 协作器中的待接收
            List<Integer> coordinatorIds = userTeamMapper.selectByOperatorAndStatus(UserSession.getCurrent().getUserId(),
                    TeammateReEnum.STATUS_INIT.getValue(), ObjectTypeEnum.LENDER.getValue());
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
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                lenderQuery.setIds(result);
            }
        } else if (ObjectTabEnum.apply.getValue().equals(tab)) {
            // 待申请 -- 暂未其他公司参与(只要对象的三个值都不存在1就是未参与)
            lenderQuery.setNoTakePart(true);
//            List<Integer> ids = companyTeamReMapper.listAssigned(ObjectTypeEnum.LENDER.getValue());
//            if (ids != null || ids.size() > 0) {
//                lenderQuery.setExceptIds(ids);
//            }
//            if (isUrgeOrLawyer) {
//                if(businessIds == null || businessIds.size() == 0){
//                    lenderQuery.setId(SysProperty.NULL_DATA_ID);
//                }else{
//                    lenderQuery.setIds(businessIds);
//                }
//            }
//            lenderQuery.setOperator(UserSession.getCurrent().getUserId());
        } else if (ObjectTabEnum.gongingOn.getValue().equals(tab)) {
            // 正在进行
//            List<Integer> ids = companyTeamReMapper.listObjectIdByTypeAndManager(
//                    ObjectTypeEnum.LENDER.getValue(),
//                    ObjectAcceptTypeEnum.accept.getValue(),
//                    UserSession.getCurrent().getUserId()
//            );
//            if (ids == null || ids.size() == 0) {
//                //  找不到数据,填充0数据限制
//                lenderQuery.setId(SysProperty.NULL_DATA_ID);
//            } else {
//                lenderQuery.setIds(ids);
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
//            List<Integer> result = CommonUtil.pickList(mineIds, companyIds);
            List<Integer> teamateIds = CommonUtil.pickList(adminIds, managerIds);
            List<Integer> result = CommonUtil.pickList(companyIds, CommonUtil.unionList(teamateIds, teamateIds));
            if (isUrgeOrLawyer) {
                lenderQuery.setIds(CommonUtil.unionList(result, businessIds));
            } else {
                lenderQuery.setIds(result);
            }
            if (CommonUtil.checkParam(lenderQuery.getIds()) || lenderQuery.getIds().size() == 0) {
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
            lenderQuery.setRepayStatus(SysProperty.BOOLEAN_FALSE);
        } else if (ObjectTabEnum.focus.getValue().equals(tab)) {
            // 聚焦
            lenderQuery.setId(SysProperty.NULL_DATA_ID); // 暂时不显示数据
        } else if (ObjectTabEnum.month.getValue().equals(tab)) {
            // 当月
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            objectUserRelationQuery.setStartAt(calendar.getTime());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (isUrgeOrLawyer) {
                lenderQuery.setIds(CommonUtil.unionList(ids, businessIds));
            } else {
                lenderQuery.setIds(ids);
            }
            if (CommonUtil.checkParam(lenderQuery.getIds()) || lenderQuery.getIds().size() == 0) {
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.stock.getValue().equals(tab)) {
            // 存量
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            objectUserRelationQuery.setEndAt(calendar.getTime());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (isUrgeOrLawyer) {
                lenderQuery.setIds(CommonUtil.unionList(ids, businessIds));
            } else {
                lenderQuery.setIds(ids);
            }
            if (CommonUtil.checkParam(lenderQuery.getIds()) || lenderQuery.getIds().size() == 0) {
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.over.getValue().equals(tab)) {
            // 完成
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            lenderQuery.setIds(ids);
            lenderQuery.setIsOver(true);
        } else if (ObjectTabEnum.outTime.getValue().equals(tab)) {
            // 超时
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (isUrgeOrLawyer || isPlatformOrEntrust) {
                lenderQuery.setIds(CommonUtil.unionList(ids, businessIds));
            } else {
                lenderQuery.setIds(ids);
            }
            lenderQuery.setIsOutTime(true);
        } else if (ObjectTabEnum.invalid.getValue().equals(tab)) {
            // 无效
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            lenderQuery.setIds(ids);
            lenderQuery.setStateflag(1);
        } else if (ObjectTabEnum.join.getValue().equals(tab)) {
            // 待参与
            UserTeamQuery query = new UserTeamQuery();
            if (!flag) {
                query.setCompanyId(userInfo.getCompanyId());
            }
            query.setObjectType(ObjectTypeEnum.LENDER.getValue());
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
                        if (re.getUserId().equals(userInfo.getId())
                                && re.getStatus().equals(TeammateReEnum.STATUS_ACCEPT.getValue())) {
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
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                lenderQuery.setIds(result);
            }
        } else if (ObjectTabEnum.joined.getValue().equals(tab)) {
            // 已参与
            List<Integer> ids = userTeamMapper.selectByOperatorAndStatus(UserSession.getCurrent().getUserId(),
                    TeammateReEnum.STATUS_ACCEPT.getValue(), ObjectTypeEnum.LENDER.getValue());
            if (ids != null && ids.size() > 0) {
                lenderQuery.setIds(ids);
            } else {
                // 找不到数据
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
        } else if (ObjectTabEnum.check.getValue().equals(tab)) {
            // 待审核
            if (flag) {
                List<Integer> ids = businessObjReMapper.listIdByTypeIdStatus(ObjectTypeEnum.LENDER.getValue(),
                        BusinessStatusEnum.init.getValue());
                lenderQuery.setIds(ids);
            } else {
                List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.LENDER.getValue(),
                        BusinessStatusEnum.init.getValue(), UserSession.getCurrent().getUserId());
                lenderQuery.setIds(ids);
            }
            if (CommonUtil.checkParam(lenderQuery.getIds()) || lenderQuery.getIds().size() == 0) {
                // 找不到数据
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
            lenderQuery.setIsNotAsset(true);
            if (!flag) {
                lenderQuery.setOperator(userInfo.getId());
            }
        } else if (ObjectTabEnum.refuse.getValue().equals(tab)) {
            // 已驳回
            List<Integer> ids = businessObjReMapper.listIdByTypeIdStatusUser(ObjectTypeEnum.LENDER.getValue(),
                    BusinessStatusEnum.platform_refuse.getValue(), UserSession.getCurrent().getUserId());
            if (!CommonUtil.checkParam(ids) && ids.size() > 0) {
                lenderQuery.setIds(ids);
            } else {
                // 找不到数据
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
            lenderQuery.setIsNotAsset(true);
            if (!flag) {
                lenderQuery.setOperator(userInfo.getId());
            }
        } else if (ObjectTabEnum.handle.getValue().equals(tab)) {
            // 待处置
            if (flag) {
                lenderQuery.setIds(managerPassIds); // 通过审核还未处置
            } else {
                lenderQuery.setIds(passIds); // 通过审核还未处置
                lenderQuery.setOperator(userInfo.getId());
            }
            if (CommonUtil.checkParam(lenderQuery.getIds()) || lenderQuery.getIds().size() == 0) {
                // 找不到数据
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
            lenderQuery.setIsNotAsset(true);
        } else if (ObjectTabEnum.assign.getValue().equals(tab)) {
            // 待分配
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            objectUserRelationQuery.setType(BusinessRelationEnum.team.getValue());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (!CommonUtil.checkParam(ids) && ids.size() > 0) {
                lenderQuery.setExceptIds(ids);
            }
            lenderQuery.setOperator(userInfo.getId());
            if (!flag) {
//                if (isPlatformOrEntrust) { // 修改于10.11
                    if (businessIds != null && businessIds.size() > 0) {
                        lenderQuery.setIds(businessIds);
                    } else {
                        lenderQuery.setId(SysProperty.NULL_DATA_ID);
                    }
//                }
            }
        } else if (ObjectTabEnum.new48h.getValue().equals(tab)) {
            // 48h 新
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (isUrgeOrLawyer) {
                lenderQuery.setIds(CommonUtil.unionList(ids, businessIds));
            } else {
                lenderQuery.setIds(ids);
            }
            if (CommonUtil.checkParam(lenderQuery.getIds()) || lenderQuery.getIds().size() == 0) {
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            lenderQuery.setStartAt(calendar.getTime());
        } else if (ObjectTabEnum.task.getValue().equals(tab)) {
            // 我的任务
//            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
//            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
//            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
//            objectUserRelationQuery.setType(BusinessRelationEnum.team.getValue());
//            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
//            List<Integer> relationIds = new ArrayList<>();
//            objectUserRelationList.forEach(objectUserRelation -> {
//                relationIds.add(objectUserRelation.getObjectId());
//            });
            List<Integer> teammateIds = teammateReMapper.listObjectIdByJoinType(ObjectTypeEnum.LENDER.getValue(),
                    UserSession.getCurrent().getUserId(), TeammateReEnum.TYPE_AUXILIARY.getValue());
//            List<Integer> ids = CommonUtil.unionList(relationIds, teammateIds);
            List<Integer> ids = teammateIds;
            if (CommonUtil.checkParam(ids) || ids.size() == 0) {
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                lenderQuery.setIds(ids);
            }
        } else if (ObjectTabEnum.handling_urge.getValue().equals(tab)
                || ObjectTabEnum.handling_entrust.getValue().equals(tab)) {
            // 委托的处置中
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            if (CommonUtil.checkParam(ids)) {
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                // 这里引入业务的处置中状态
                lenderQuery.setIds(CommonUtil.unionList(ids, managerDisposeIds));
            }
            lenderQuery.setIsTakePart(true);
        } else if (ObjectTabEnum.stop.getValue().equals(tab)) {
            // 暂停
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            lenderQuery.setIds(ids);
            lenderQuery.setIsStop(true);
        } else if (ObjectTabEnum.myUrge.getValue().equals(tab)) {
            // 我的催收
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery(); // 对象关系表
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (!flag) {
                objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            }
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            TeammateRe teammateRe = new TeammateRe(); // 协作器
            teammateRe.setType(TeammateReEnum.TYPE_AUXILIARY.getValue());
            teammateRe.setStatus(TeammateReEnum.STATUS_ACCEPT.getValue());
            teammateRe.setUserId(UserSession.getCurrent().getUserId());
            List<TeammateRe> list = teammateReMapper.selectSelective(teammateRe);
            List<Integer> teammateIds = new ArrayList<>();
            list.forEach(teammateRe1 -> {
                UserTeam userTeam = userTeamMapper.get(teammateRe1.getUserTeamId());
                if (userTeam != null) {
                    teammateIds.add(userTeam.getObjectId());
                }
            });
            List<Integer> result = CommonUtil.unionList(teammateIds, ids);
            if (result == null || result.size() == 0) {
                lenderQuery.setId(SysProperty.NULL_DATA_ID);
            } else {
                lenderQuery.setIds(result);
            }
        } else {
            return null;
        }
        return lenderQuery;
    }
}
