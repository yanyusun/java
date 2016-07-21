package com.dqys.business.service.service.impl;


import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.*;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.orm.query.asset.IOUQuery;
import com.dqys.business.orm.query.asset.LenderQuery;
import com.dqys.business.orm.query.asset.PawnQuery;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.constant.asset.AssetModelTypeEnum;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.constant.asset.LenderTabEnum;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.business.service.dto.asset.LenderListDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.LenderListQuery;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.utils.asset.IouServiceUtils;
import com.dqys.business.service.utils.asset.LenderServiceUtils;
import com.dqys.business.service.utils.asset.PawnServiceUtils;
import com.dqys.core.base.BaseSelectonDTO;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
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
    private CompanyTeamReMapper companyTeamReMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;
    @Autowired
    private UserTeamMapper userTeamMapper;
    @Autowired
    private TeammateReMapper teammateReMapper;

    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessLogService businessLogService;


    @Override
    public JsonResponse queryList(LenderListQuery lenderListQuery, Integer type) {
        LenderQuery lenderQuery = createQueryByTab(type);
        if (lenderQuery == null) {
            return null; // 类型不对,参数错误
        }
        Map<String, Object> map = new HashMap<>();
        // 不同tag类型有不同的搜索条件
        lenderQuery.setIsPaging(true);
        lenderQuery.setPageSize(lenderListQuery.getPageCount());
        if (lenderListQuery.getPage() > 0) {
            lenderQuery.setStartPageNum((lenderListQuery.getPage() - 1) * lenderListQuery.getPageCount());
        } else {
            lenderQuery.setStartPageNum(0);
        }
        lenderQuery = LenderServiceUtils.toLenderQuery(lenderQuery, lenderListQuery);

        TeammateRe teammateRe = new TeammateRe();
        boolean teammateFlag = false; // 控制是否查询协作器

        if (lenderQuery.getId().equals(0)) {
            // 搜索不到数据
            map.put("total", 0);
            map.put("data", null);
            return JsonResponseTool.success(map);
        }
        if (lenderListQuery.getAssetNo() != null) {
            AssetQuery assetQuery = new AssetQuery();
            assetQuery.setCode(lenderListQuery.getAssetNo());
            List<AssetInfo> assetInfoList = assetInfoMapper.pageList(assetQuery);
            if (assetInfoList == null) {
                // 搜索不到数据
                map.put("total", 0);
                map.put("data", null);
                return JsonResponseTool.success(map);
            } else {
                lenderQuery.setAssetId(assetInfoList.get(0).getId());
            }
        }
        if (lenderListQuery.getSearch() != null){
            // 搜索内容(借款人编号)

        }
        if (lenderListQuery.getBelong() != null) {
            // 所属人 -- 协作器
            teammateFlag = true;
            teammateRe.setUserId(lenderListQuery.getBelong());

        }
        if (lenderListQuery.getUrgeType() != null) {
            // 催收阶段 -- 协作器

        }
        if (lenderListQuery.getOutDays() != null) {
            // N天以上未催收借款人
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 0-lenderListQuery.getOutDays());


        }
        if (lenderListQuery.getIsAssigned().equals(1)) {
            // 已分配

        }
        if (lenderListQuery.getPassIn().equals(1)) {
            // 转入

        }
        if (lenderListQuery.getUrgeType().equals(1)) {
            // 转出

        }
        if (lenderListQuery.getWaitAssist().equals(1)) {
            // 待协助

        }
        if (lenderListQuery.getAssist().equals(1)) {
            // 正在协助的

        }

        if(teammateFlag){
            List<TeammateRe> teammateReList = teammateReMapper.selectSelective(teammateRe);

        }

        Integer count = lenderInfoMapper.queryCount(lenderQuery);
        map.put("total", count);
        List<LenderInfo> lenderInfoList = lenderInfoMapper.queryList(lenderQuery);
        List<LenderListDTO> lenderListDTOList = new ArrayList<>();
        lenderInfoList.forEach(lenderInfo -> {
            LenderListDTO lenderListDTO = new LenderListDTO();


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
            if (ContactTypeEnum.getContactTypeEnum(contactDTO.getType()).getValue().equals(ContactTypeEnum.LENDER)) {
                flag = true;
            }
        }
        if (!flag) {
            return JsonResponseTool.paramErr("缺少借款人信息");
        }
        // 添加借款人基础信息
        LenderInfo lenderInfo = LenderServiceUtils.toLenderInfo(lenderDTO);
        lenderInfo.setLenderNo(LenderServiceUtils.createLenderNo());
        Integer addResult = lenderInfoMapper.insert(lenderInfo);
        if (CommonUtil.checkResult(addResult)) {
            return JsonResponseTool.failure("添加失败");
        }
        Integer lenderId = lenderInfo.getId();
        // 添加历史记录
        businessLogService.add(lenderId, ObjectTypeEnum.LENDER.getValue(), LenderEnum.ADD.getValue(),
                "", "", 0, 0);
        // 增加借款人相关联系人的身份信息
        for (ContactDTO contactDTO : contactDTOList) {
            contactDTO.setMode(AssetModelTypeEnum.LENDER);
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
        // 增加对象与操作事物的联系
        ObjectUserRelation objectUserRelation = new ObjectUserRelation();
        objectUserRelation.setObjectType(ObjectTypeEnum.LENDER.getValue());
        objectUserRelation.setObjectId(lenderId);
        objectUserRelation.setUserId(UserSession.getCurrent().getUserId());
        objectUserRelation.setStatus(ObjectUserStatusEnum.checked.getValue());
        objectUserRelation.setType(BusinessRelationEnum.own.getValue());
        Integer result = objectUserRelationMapper.insert(objectUserRelation);
        if (CommonUtil.checkResult(result)) {
            // 增加失败,请记录


        }
        return JsonResponseTool.success(lenderId);
    }

    @Override
    public JsonResponse delete_tx(Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer lender = lenderInfoMapper.deleteByPrimaryKey(id);
        Integer contact = contactInfoMapper.deleteByMode(AssetModelTypeEnum.LENDER, id);

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
            if (ContactTypeEnum.getContactTypeEnum(contactDTO.getType()).getValue().equals(ContactTypeEnum.LENDER)) {
                flag = true;
            }
        }
        if (!flag) {
            return JsonResponseTool.paramErr("缺少借款人信息");
        }
        Integer lender = lenderInfoMapper.update(LenderServiceUtils.toLenderInfo(lenderDTO));
        if (!CommonUtil.checkResult(lender)) {
            flag = true;
        }
        // 添加历史记录
        businessLogService.add(lenderDTO.getId(), ObjectTypeEnum.LENDER.getValue(), LenderEnum.UPDATE_EDIT.getValue(),
                "", "", 0, 0);
        // 流程:比较先有的联系人信息与数据库中的差异性,余删缺增.
        List<ContactInfo> contactInfoList = contactInfoMapper.listByMode(AssetModelTypeEnum.LENDER, lender);
        for (ContactInfo contactInfo : contactInfoList) {
            boolean isExit = false; // 用于判断这条数据是否还在
            for (ContactDTO contactDTO : contactDTOList) {
                if (contactInfo.getId().equals(contactDTO.getId())) {
                    isExit = true;
                    Integer update = contactInfoMapper.update(LenderServiceUtils.toContactInfo(contactDTO));
                    break;
                }
            }
            if (!isExit) {
                contactInfoMapper.deleteByPrimaryKey(contactInfo.getId());
            }
        }
        // 补足新增联系人
        for (ContactDTO contactDTO : contactDTOList) {
            if (contactDTO.getId() == null) {
                contactInfoMapper.insert(LenderServiceUtils.toContactInfo(contactDTO));
            }
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
                AssetModelTypeEnum.LENDER, ContactTypeEnum.LENDER.getValue(), id);
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
        resultMap.put("lenderDTO", LenderServiceUtils.toLenderDTO(lenderInfo));
        // 联系人
        List<ContactInfo> contactInfoList = contactInfoMapper.listByMode(AssetModelTypeEnum.LENDER, lenderInfo.getId());
        resultMap.put("contactDTOs", LenderServiceUtils.toContactDTO(contactInfoList));
        // 借据
        IOUQuery iouQuery = new IOUQuery();
        iouQuery.setLenderId(id);
        resultMap.put("iouDTOs", IouServiceUtils.toIouDTO(iouInfoMapper.queryList(iouQuery)));
        // 抵押物
        PawnQuery pawnQuery = new PawnQuery();
        pawnQuery.setLenderId(id);
        resultMap.put("pawnDTOs", PawnServiceUtils.toPawnDTO(pawnInfoMapper.queryList(pawnQuery)));
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
                    AssetModelTypeEnum.LENDER, ContactTypeEnum.LENDER.getValue(), lenderInfo.getId()
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
     * 根据导航栏的标签,设置不同的搜索条件
     *
     * @param tab
     * @return
     */
    private LenderQuery createQueryByTab(Integer tab) {
        if (tab == null || LenderTabEnum.getLenderTabEnum(tab) == null) {
            return null;
        }

        LenderQuery lenderQuery = new LenderQuery();
        if (LenderTabEnum.accept.getValue().equals(tab)) {
            // 待接收 -- 其他机构发起邀请未处理
            List<Integer> ids = companyTeamReMapper.listObjectIdByTypeAndManager(
                    ObjectTypeEnum.LENDER.getValue(),
                    ObjectAcceptTypeEnum.init.getValue(),
                    UserSession.getCurrent().getUserId()
            );
            if (ids == null || ids.size() == 0) {
                // 找不到数据,填充0数据限制
                lenderQuery.setId(0);
            } else {
                lenderQuery.setIds(ids);
            }
        } else if (LenderTabEnum.apply.getValue().equals(tab)) {
            // 待申请 -- 暂未其他公司参与
            List<Integer> ids = companyTeamReMapper.listAssigned(ObjectTypeEnum.LENDER.getValue());
            if (ids == null || ids.size() == 0) {
                lenderQuery.setId(0);
            } else {
                lenderQuery.setExceptIds(ids);
            }
            lenderQuery.setOperator(UserSession.getCurrent().getUserId());
        } else if (LenderTabEnum.handling.getValue().equals(tab)) {
            // 正在处置
            List<Integer> ids = companyTeamReMapper.listObjectIdByTypeAndManager(
                    ObjectTypeEnum.LENDER.getValue(),
                    ObjectAcceptTypeEnum.accept.getValue(),
                    UserSession.getCurrent().getUserId()
            );
            if (ids == null || ids.size() == 0) {
                //  找不到数据,填充0数据限制
                lenderQuery.setId(0);
            } else {
                lenderQuery.setIds(ids);
            }
        } else if (LenderTabEnum.focus.getValue().equals(tab)) {
            // 聚焦

        } else if (LenderTabEnum.month.getValue().equals(tab)) {
            // 当月
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            objectUserRelationQuery.setStartAt(calendar.getTime());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            lenderQuery.setIds(ids);
        } else if (LenderTabEnum.stock.getValue().equals(tab)) {
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
            lenderQuery.setIds(ids);
        } else if (LenderTabEnum.over.getValue().equals(tab)) {
            // 完成
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            lenderQuery.setIds(ids);
            lenderQuery.setIsOver(true);
        } else if (LenderTabEnum.outTime.getValue().equals(tab)) {
            // 超时
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            lenderQuery.setIds(ids);
            lenderQuery.setIsOutTime(true);
        } else if (LenderTabEnum.invalid.getValue().equals(tab)) {
            // 无效
            ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
            objectUserRelationQuery.setObjectType(ObjectTypeEnum.LENDER.getValue());
            objectUserRelationQuery.setUserId(UserSession.getCurrent().getUserId());
            List<ObjectUserRelation> objectUserRelationList = objectUserRelationMapper.list(objectUserRelationQuery);
            List<Integer> ids = new ArrayList<>();
            objectUserRelationList.forEach(objectUserRelation -> {
                ids.add(objectUserRelation.getObjectId());
            });
            lenderQuery.setIds(ids);
            lenderQuery.setStateflag(1);
        } else if (LenderTabEnum.join.getValue().equals(tab)) {
            // 待参与
            List<Integer> ids = userTeamMapper.selectByOperatorAndStatus(UserSession.getCurrent().getUserId(),
                    TeammateReEnum.STATUS_INIT.getValue(), ObjectTypeEnum.LENDER.getValue());
            if (ids != null && ids.size() > 0) {
                lenderQuery.setIds(ids);
            } else {
                // 找不到数据
                lenderQuery.setId(0);
            }
        } else if (LenderTabEnum.joined.getValue().equals(tab)) {
            // 已参与
            List<Integer> ids = userTeamMapper.selectByOperatorAndStatus(UserSession.getCurrent().getUserId(),
                    TeammateReEnum.STATUS_ACCEPT.getValue(), ObjectTypeEnum.LENDER.getValue());
            if (ids != null && ids.size() > 0) {
                lenderQuery.setIds(ids);
            } else {
                // 找不到数据
                lenderQuery.setId(0);
            }
        } else if (LenderTabEnum.check.getValue().equals(tab)) {
            // 待审核

        } else if (LenderTabEnum.handle.getValue().equals(tab)) {
            // 待处置

        } else if (LenderTabEnum.assign.getValue().equals(tab)) {
            // 待分配
        } else {
            return null;
        }
        return lenderQuery;
    }
}
