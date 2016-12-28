package com.dqys.flowbusiness.service.service.impl;

import com.dqys.flowbusiness.orm.mapper.BusinessLevelReMapper;
import com.dqys.flowbusiness.orm.mapper.BusinessMapper;
import com.dqys.flowbusiness.orm.mapper.BusinessObjReMapper;
import com.dqys.flowbusiness.orm.mapper.BusinesslevelUserReMapper;
import com.dqys.flowbusiness.service.service.AbstractBusinessService;
import com.dqys.flowbusiness.service.service.BusinessService;
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

    @Autowired @Qualifier("saleBusinessLevelReMapper")
    private BusinessLevelReMapper businessLevelReMapper;

    @Autowired @Qualifier("saleBusinesslevelUserReMapper")
    private BusinesslevelUserReMapper businesslevelUserReMapper;

    @Override
    protected BusinessMapper getBusinessMapper() {
        return businessMapper;
    }

    @Override
    protected BusinessObjReMapper getBusinessObjReMapper() {
        return businessObjReMapper;
    }


    @Override
    protected BusinessLevelReMapper getBusinessLevelReMapper() {
        return businessLevelReMapper;
    }

    @Override
    protected BusinesslevelUserReMapper getBusinesslevelUserReMapper() {
        return businesslevelUserReMapper;
    }


}
