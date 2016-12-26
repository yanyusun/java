package com.dqys.flowbusiness.service.service.impl;

import com.dqys.flowbusiness.orm.mapper.BusinessMapper;
import com.dqys.flowbusiness.orm.mapper.BusinessObjReMapper;
import com.dqys.flowbusiness.orm.pojo.Business;
import com.dqys.flowbusiness.service.service.AbstractBusinessService;
import com.dqys.flowbusiness.service.service.BusinessService;
import com.dqys.flowbusiness.service.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by yan on 16-12-22.
 */
@Service("saleBusinessService")
@Primary
public class SaleBusinessServiceImpl extends AbstractBusinessService implements BusinessService{
    @Autowired @Qualifier("saleBusinessMapper")
    private BusinessMapper businessMapper;

    @Autowired @Qualifier("saleBusinessObjReMapper")
    private BusinessObjReMapper businessObjReMapper;

    @Override
    protected BusinessMapper getBusinessMapper() {
        return businessMapper;
    }

    @Override
    protected BusinessObjReMapper getBusinessObjReMapper() {
        return businessObjReMapper;
    }

    @Override
    public Business getBusiness(Integer id) {
        return businessMapper.get(id);
    }

    @Override
    public Result flow(Integer businessId,Integer userId,Integer businessType,Integer businessLevel, Integer operType){
        return null;
    }
}
