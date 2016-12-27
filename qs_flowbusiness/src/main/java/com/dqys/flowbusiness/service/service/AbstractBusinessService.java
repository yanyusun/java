package com.dqys.flowbusiness.service.service;

import com.dqys.core.model.UserSession;
import com.dqys.flowbusiness.orm.mapper.BusinessLevelReMapper;
import com.dqys.flowbusiness.orm.mapper.BusinessMapper;
import com.dqys.flowbusiness.orm.mapper.BusinessObjReMapper;
import com.dqys.flowbusiness.orm.mapper.BusinesslevelUserReMapper;
import com.dqys.flowbusiness.orm.pojo.Business;
import com.dqys.flowbusiness.orm.pojo.BusinessLevelRe;
import com.dqys.flowbusiness.orm.pojo.BusinessObjRe;
import com.dqys.flowbusiness.orm.pojo.BusinesslevelUserRe;
import com.dqys.flowbusiness.orm.query.BusinessLevelReQuery;
import com.dqys.flowbusiness.orm.query.BusinesslevelUserReQuery;
import com.dqys.flowbusiness.service.dto.BusinessDto;
import com.dqys.flowbusiness.service.util.BusinessResultEnum;
import com.dqys.flowbusiness.service.util.Result;

import java.util.List;

/**
 * Created by pan on 16-12-22.
 */
public abstract class  AbstractBusinessService implements BusinessService{
    protected abstract BusinessMapper getBusinessMapper();
    protected abstract BusinessObjReMapper getBusinessObjReMapper();
    protected abstract BusinessLevelReMapper getBusinessLevelReMapper();
    protected abstract BusinesslevelUserReMapper getBusinesslevelUserReMapper();

    @Override
    public int createBusiness_tx(List<BusinessDto> businessDtoList, Integer userId, Integer businessType, Integer BusinessStatus) {
        Business business=insert(userId,businessType,BusinessStatus);
        for(BusinessDto businessDto:businessDtoList){
            createBusinessRe(businessDto,business.getId());
        }
        return business.getId();
    }

    @Override
    public int createBusiness_tx(BusinessDto businessDto,Integer userId,Integer businessType,Integer BusinessStatus) {
        Business business=insert(userId,businessType,BusinessStatus);
        createBusinessRe(businessDto,business.getId());
        return business.getId();
    }

    private Business insert(Integer userId,Integer businessType,Integer BusinessStatus){
        Business business = new Business();
        business.setType(businessType);
        business.setStatus(BusinessStatus);
        business.setCreateId(userId);
        getBusinessMapper().insert(business);
        return business;
    }
    private void createBusinessRe(BusinessDto businessDto,Integer businessId){
        BusinessObjRe businessObjRe = new BusinessObjRe();
        businessObjRe.setObjectId(businessDto.getObjectId());
        businessObjRe.setObjectType(businessDto.getObjcetType());
        businessObjRe.setBusinessId(businessId);
        getBusinessObjReMapper().insert(businessObjRe);
    }
    @Override
    public Business getBusiness(Integer id) {
        return getBusinessMapper().get(id);
    }

    @Override
    public Result flow(Integer businessId, Integer userId, Integer businessType, Integer businessLevel, Integer operType){
        if(businessId==null||userId==null||businessType==null||businessLevel==null||operType==null){
            return new Result(BusinessResultEnum.param_error);
        }
        //是否有该业务并且在当前阶段 TODO: 16-12-27 区分是否应该查历史
        Business business=getBusinessMapper().get(businessId);
        if(business==null){
            return new Result(BusinessResultEnum.not_find);
        }
        if(business.getStatus()!=businessLevel){
            return new Result(BusinessResultEnum.level_error);
        }
        //查询通向的阶段
        List<BusinessLevelRe> businessLevelReList=getBusinessLevelReMapper().list(getBusinessLevelReQuery(businessLevel,operType));
        if(businessLevelReList==null||businessLevelReList.size()!=1){
            return new Result(BusinessResultEnum.flow_re_error);
        }
        //查询是否有该权限
        BusinessLevelRe businessLevelRe = businessLevelReList.get(0);
        //// TODO: 16-12-27 修改成更具userid由表中获得
        int roleType= headerStringToInt(UserSession.getCurrent().getRoleId());
        List<BusinesslevelUserRe> BusinesslevelUserReList=getBusinesslevelUserReMapper().list(getBusinesslevelUserRe(businessLevelRe.getId(),roleType));
        if(BusinesslevelUserReList==null||BusinesslevelUserReList.size()!=1){
            return new Result(BusinessResultEnum.auth_error);
        }
        //改变业务状态
        business.setStatus(businessLevelRe.getGoBusinessType());
        getBusinessMapper().update(business);
        return new Result(BusinessResultEnum.sucesss);
    }
    private BusinessLevelReQuery getBusinessLevelReQuery(Integer businessLevel, Integer operType){
        BusinessLevelReQuery query = new BusinessLevelReQuery();
        query.setAtBusinessType(businessLevel);
        query.setOperType(operType);
        return query;
    }
    private BusinesslevelUserReQuery getBusinesslevelUserRe(Integer businesslevelReId, int roleType){
        BusinesslevelUserReQuery query = new BusinesslevelUserReQuery();
        query.setBusinesslevelReId(businesslevelReId);
        query.setRoleType(roleType);
        return query;
    }
    private static Integer headerStringToInt(String head) {
        String s = head.split(",")[0];
        return Integer.valueOf(s);
    }
}
