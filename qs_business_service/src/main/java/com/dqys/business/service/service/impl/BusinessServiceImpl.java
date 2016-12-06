package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.business.BusinessTypeEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.business.BusinessMapper;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.coordinator.UserDetail;
import com.dqys.business.orm.query.business.BusinessObjReQuery;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.base.BusinessFlowModel;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;

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
                    if (vfUserType(UserInfoEnum.USER_TYPE_ENTRUST.getValue())) {
                        business.setStatus(BusinessStatusEnum.not_publish.getValue());//只有当是委托方录入添加的业务状态为未发布
                    } else {
                        business.setStatus(BusinessStatusEnum.init.getValue());
                    }
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

    private boolean vfUserType(Integer userType) {
        UserDetail detail = coordinatorMapper.getUserDetail(UserSession.getCurrent().getUserId());
        if (detail != null && userType == detail.getUserType().intValue()) {
            return true;
        }
        return false;
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
        BusinessObjRe businessObjRe = businessObjReMapper.getByObject(ObjectType, ObjectId);
        if (businessObjRe == null) {
            return null;
        }
        Business business = businessMapper.get(businessObjRe.getBusinessId());
        return business;
    }

    @Override
    public List<BusinessObjRe> getlistByObjectTypeAndBusinessId(Integer ObjectType, Integer BusinessId) {
        BusinessObjReQuery businessObjReQuery = new BusinessObjReQuery();
        businessObjReQuery.setBusinessId(BusinessId);
        businessObjReQuery.setObjectType(ObjectType);
        return businessObjReMapper.list(businessObjReQuery);
    }

    @Override
    public List<BusinessObjRe> getListByObjecTypeAndObjectId(Integer objectType, Integer objectReType, Integer objectId) {
        BusinessObjRe businessObjRe = businessObjReMapper.getByObject(objectType, objectId);
        return getlistByObjectTypeAndBusinessId(objectReType, businessObjRe.getBusinessId());
    }

    @Override
    public void updateBusinessFlowObjOnType(Integer objectType, Integer id, int pawnStatus, int iouStatus) {
        UserSession userSession = UserSession.getCurrent();
        int userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        if (ObjectTypeEnum.ASSETSOURCE.getValue().intValue() == objectType) {//资产包
            //更新抵押物
            updatePawnOnStatusByBusinessObjReList(objectType, id, userType, pawnStatus);
            //更新借据
            updateIouOnStatusByBusinessObjReList(objectType, id, userType, iouStatus);
        } else if (ObjectTypeEnum.LENDER.getValue().intValue() == objectType) {//借款人
            //更新抵押物
            updatePawnOnStausByLenderId(id, userType, pawnStatus);
            //更新借据
            updateIouOnStausByLenderId(id, userType, iouStatus);
        }
    }

    /**
     * 根据接款人设置抵押物的状态
     *
     * @param id
     * @param userType
     */
    private void updatePawnOnStausByLenderId(Integer id, int userType, int status) {
        List<PawnInfo> pawnInfoList = pawnInfoMapper.listByLenderId(id);
        for (PawnInfo pawnInfo : pawnInfoList) {
            BusinessFlowModel businessFlowModel = initByOnStatus(new PawnInfo(), userType,
                    status, pawnInfo.getId());
            pawnInfoMapper.update((PawnInfo) businessFlowModel);
        }
    }

    private void updateIouOnStausByLenderId(Integer id, int userType, int status) {
        List<IOUInfo> iouInfoList = iouInfoMapper.listByLenderId(id);
        for (IOUInfo iouInfo : iouInfoList) {
            BusinessFlowModel businessFlowModel = initByOnStatus(new IOUInfo(), userType,
                    status, iouInfo.getId());
            iouInfoMapper.update((IOUInfo) businessFlowModel);
        }
    }


    /**
     * 设置抵押物的状态
     *
     * @param objectType
     * @param id
     * @param userType
     */
    private void updatePawnOnStatusByBusinessObjReList(Integer objectType, Integer id, int userType, int status) {
        List<BusinessObjRe> businessObjReList = getListByObjecTypeAndObjectId(objectType, ObjectTypeEnum.PAWN.getValue(), id);
        for (BusinessObjRe businessObjRe : businessObjReList) {
            BusinessFlowModel businessFlowModel = initByOnStatus(new PawnInfo(), userType,
                    status, businessObjRe.getObjectId());
            pawnInfoMapper.update((PawnInfo) businessFlowModel);
        }
    }

    /**
     * 设置借据的状态
     *
     * @param objectType
     * @param id
     * @param userType
     */
    private void updateIouOnStatusByBusinessObjReList(Integer objectType, Integer id, int userType, int status) {
        List<BusinessObjRe> businessObjReList = getListByObjecTypeAndObjectId(objectType, ObjectTypeEnum.IOU.getValue(), id);
        for (BusinessObjRe businessObjRe : businessObjReList) {
            BusinessFlowModel businessFlowModel = initByOnStatus(new IOUInfo(), userType,
                    status, businessObjRe.getObjectId());
            iouInfoMapper.update((IOUInfo) businessFlowModel);
        }
    }

    /**
     * 设置业务流转的状态
     *
     * @param businessFlowModel
     * @param userType
     * @param status
     * @param id
     * @return
     */
    private BusinessFlowModel initByOnStatus(BusinessFlowModel businessFlowModel, Integer userType, Integer status, Integer id) {
        if (UserInfoEnum.USER_TYPE_COLLECTION.getValue() == userType) {
            businessFlowModel.setOnCollection(status);
        } else if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue() == userType) {
            businessFlowModel.setOnLawyer(status);
        } else if (UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue() == userType) {
            businessFlowModel.setOnAgent(status);
        }
        businessFlowModel.setId(id);
        return businessFlowModel;
    }

}
