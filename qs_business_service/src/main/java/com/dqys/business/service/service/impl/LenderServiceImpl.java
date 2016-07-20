package com.dqys.business.service.service.impl;


import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.ContactInfoMapper;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.query.asset.IOUQuery;
import com.dqys.business.orm.query.asset.PawnQuery;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.constant.asset.AssetModelTypeEnum;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.constant.asset.LenderTypeEnum;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.LenderListQuery;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.core.base.BaseSelectonDTO;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private IOUInfoMapper iouInfoMapper;

    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessLogService businessLogService;


    @Override
    public JsonResponse queryList(LenderListQuery lenderListQuery) {


        return null;
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
        LenderInfo lenderInfo = AssetServiceUtils.toLenderInfo(lenderDTO);
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
            Integer result = contactInfoMapper.insert(AssetServiceUtils.toContactInfo(contactDTO));
            if (CommonUtil.checkResult(result)) {
                // todo 联系人增加失败,请处理

            }
        }
        // 添加业务
        if(lenderDTO.getAssetId() == null){
            businessService.addServiceObject(ObjectTypeEnum.LENDER.getValue(), lenderId, null, null);
        }else{
            businessService.addServiceObject(ObjectTypeEnum.LENDER.getValue(), lenderId,
                    ObjectTypeEnum.ASSETPACKAGE.getValue(), lenderDTO.getAssetId());
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
        Integer lender = lenderInfoMapper.update(AssetServiceUtils.toLenderInfo(lenderDTO));
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
                    Integer update = contactInfoMapper.update(AssetServiceUtils.toContactInfo(contactDTO));
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
                contactInfoMapper.insert(AssetServiceUtils.toContactInfo(contactDTO));
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
        LenderDTO lenderDTO = AssetServiceUtils.toLenderDTO(lenderInfo);
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
        resultMap.put("lenderDTO", AssetServiceUtils.toLenderDTO(lenderInfo));
        // 联系人
        List<ContactInfo> contactInfoList = contactInfoMapper.listByMode(AssetModelTypeEnum.LENDER, lenderInfo.getId());
        resultMap.put("contactDTOs", AssetServiceUtils.toContactDTO(contactInfoList));
        // 借据
        IOUQuery iouQuery = new IOUQuery();
        iouQuery.setLenderId(id);
        resultMap.put("iouDTOs", AssetServiceUtils.toIouDTO(iouInfoMapper.queryList(iouQuery)));
        // 抵押物
        PawnQuery pawnQuery = new PawnQuery();
        pawnQuery.setLenderId(id);
        resultMap.put("pawnDTOs", AssetServiceUtils.toPawnDTO(pawnInfoMapper.queryList(pawnQuery)));
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
}
