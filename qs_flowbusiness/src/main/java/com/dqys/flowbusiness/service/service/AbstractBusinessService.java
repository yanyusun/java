package com.dqys.flowbusiness.service.service;

import com.dqys.flowbusiness.orm.mapper.BusinessMapper;
import com.dqys.flowbusiness.orm.mapper.BusinessObjReMapper;
import com.dqys.flowbusiness.orm.pojo.Business;
import com.dqys.flowbusiness.orm.pojo.BusinessObjRe;
import com.dqys.flowbusiness.service.dto.BusinessDto;

import java.util.List;

/**
 * Created by pan on 16-12-22.
 */
public abstract class  AbstractBusinessService implements BusinessService{
    protected abstract BusinessMapper getBusinessMapper();
    protected abstract BusinessObjReMapper getBusinessObjReMapper();

    @Override
    public int createBusiness_tx(List<BusinessDto> businessDtoList, Integer userId, Integer businessType, Integer BusinessStatus) {
        int id=insert(userId,businessType,BusinessStatus);
        for(BusinessDto businessDto:businessDtoList){
            createBusinessRe(businessDto,id);
        }
        return id;
    }

    @Override
    public int createBusiness_tx(BusinessDto businessDto,Integer userId,Integer businessType,Integer BusinessStatus) {
        int id=insert(userId,businessType,BusinessStatus);
        createBusinessRe(businessDto,id);
        return id;
    }

    private int insert(Integer userId,Integer businessType,Integer BusinessStatus){
        Business business = new Business();
        business.setType(businessType);
        business.setStatus(BusinessStatus);
        business.setCreateId(userId);
        return getBusinessMapper().insert(business);
    }
    private void createBusinessRe(BusinessDto businessDto,Integer businessId){
        BusinessObjRe businessObjRe = new BusinessObjRe();
        businessObjRe.setObjectId(businessDto.getObjectId());
        businessObjRe.setObjectType(businessDto.getObjcetType());
        businessObjRe.setBusinessId(businessId);
        getBusinessObjReMapper().insert(businessObjRe);
    }
}
