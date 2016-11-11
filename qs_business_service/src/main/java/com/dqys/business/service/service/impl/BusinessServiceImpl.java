package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.business.BusinessTypeEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.business.BusinessMapper;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.service.service.BusinessService;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by Yvan on 16/7/15.
 */
@Repository
@Primary
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessObjReMapper businessObjReMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;


    @Override
    public Integer addServiceObject(Integer type, Integer id, Integer pType, Integer pId) {
        if (CommonUtil.checkParam(type, id) || ObjectTypeEnum.getObjectTypeEnum(type) == null) {
            return null;
        }
        BusinessObjRe businessObjRe = businessObjReMapper.getByObject(type, id);
        if (businessObjRe == null) {
            // 建立业务对象
            Business business = new Business();
            if (pType == null && pId == null) {
                // 如果是处置方，直接跳转到正在处置状态
                String typeStr = UserSession.getCurrent().getUserType();
                if (CommonUtil.isDispose(typeStr.substring(0, typeStr.indexOf(",")))) {
                    business.setStatus(BusinessStatusEnum.dispose.getValue());
                } else {
                    business.setStatus(BusinessStatusEnum.init.getValue());
                }
                if (type.equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                    business.setType(BusinessTypeEnum.asset.getValue());
                } else if (type.equals(ObjectTypeEnum.LENDER.getValue())) {
                    business.setType(BusinessTypeEnum.lender.getValue());
                } else if (type.equals(ObjectTypeEnum.CASE.getValue())) {
                    business.setType(BusinessTypeEnum.law.getValue());
                } else if (type.equals(ObjectTypeEnum.ASSETSOURCE.getValue())) {
                    business.setType(BusinessTypeEnum.source.getValue());
                } else {
                    // 创建业务失败,业务类型不对
                    return null;
                }
                business.setCreateId(UserSession.getCurrent().getUserId());
                Integer result = businessMapper.insert(business);
                if (CommonUtil.checkResult(result)) {
                    // todo 添加业务失败,请处理
                    return null;
                }
            } else {
                businessObjRe = businessObjReMapper.getByObject(pType, pId);
                if (businessObjRe == null) {
                    return null;
                }
                business.setId(businessObjRe.getBusinessId());
            }
            businessObjRe = new BusinessObjRe();
            businessObjRe.setObjectType(type);
            businessObjRe.setObjectId(id);
            businessObjRe.setBusinessId(business.getId());
            Integer result = businessObjReMapper.insert(businessObjRe);
            if (CommonUtil.checkResult(result)) {
                // todo 添加业务对象失败,请处理
                return null;
            }
            // 增加操作人员与被操作事物之间的关系
            ObjectUserRelation objectUserRelation = new ObjectUserRelation();
            objectUserRelation.setObjectType(type);
            objectUserRelation.setObjectId(id);
            objectUserRelation.setStatus(ObjectUserStatusEnum.accept.getValue());
            objectUserRelation.setUserId(UserSession.getCurrent().getUserId());
            objectUserRelation.setBusinessId(business.getId());
            objectUserRelation.setType(BusinessRelationEnum.own.getValue());
            result = objectUserRelationMapper.insert(objectUserRelation);
            if (CommonUtil.checkResult(result)) {
                // TODO 增加操作人员与被操作事物之间的关系失败,请记录

            }
            return businessObjRe.getId();
        }
        return null;
    }

    @Override
    public Integer updateService_tx(Business business) {
        if (CommonUtil.checkParam(business, business.getStatus(), business.getId())) {
            return null;
        }
        return businessMapper.update(business);
    }

    @Override
    public Integer updateObjectUser_tx(ObjectUserRelation objectUserRelation) {
        if (CommonUtil.checkParam(objectUserRelation, objectUserRelation.getId())) {
            return null;
        }
        return objectUserRelationMapper.update(objectUserRelation);
    }

    @Override
    public Business getBusiness(Integer ObjectType, Integer ObjectId) {
        return null;
    }
}
