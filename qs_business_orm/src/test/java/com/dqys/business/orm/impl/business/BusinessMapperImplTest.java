package com.dqys.business.orm.impl.business;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.business.BusinessTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.business.BusinessMapper;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yvan on 16/7/15.
 */
public class BusinessMapperImplTest extends BaseTest {

    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessObjReMapper businessObjReMapper;

    @Test
    public void test() {
        Business business = new Business();
        business.setStatus(BusinessStatusEnum.asset.getValue());
        business.setType(BusinessTypeEnum.end.getValue());
        business.setCreateId(11);

        Integer result = businessMapper.insert(business);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(Integer.valueOf(0), result);
        Integer businessId = business.getId();

        Business business1 = businessMapper.get(businessId);
        Assert.assertNotNull(business1);
        Assert.assertEquals(business.getType(), business1.getType());

        BusinessObjRe businessObjRe = new BusinessObjRe();
        businessObjRe.setBusinessId(businessId);
        businessObjRe.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
        businessObjRe.setObjectId(1);
        Integer result1 = businessObjReMapper.insert(businessObjRe);
        Assert.assertNotNull(result1);
        Assert.assertNotEquals(Integer.valueOf(0), result1);
        Integer businessObjReId = businessObjRe.getId();

        BusinessObjRe businessObjRe1 = businessObjReMapper.get(businessObjReId);
        Assert.assertNotNull(businessObjRe1);
        Assert.assertEquals(businessObjRe.getBusinessId(), businessObjRe1.getBusinessId());

    }
}
