package com.dqys.business.orm.impl.coordinator;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.coordinator.CompanyRelationMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mkfeng on 2016/7/12.
 */
public class CompanyRelationMapperImplTest extends BaseTest {
    @Autowired
    private CompanyRelationMapper companyRelationMapper;

    @Test
    public void test() {
        CompanyRelation companyRelation = new CompanyRelation();
        companyRelation.setCompanyAId(1);
        companyRelation.setCompanyBId(120);
        Integer num = companyRelationMapper.insertSelective(companyRelation);
        Assert.assertNotNull(num);
        Assert.assertNotEquals("0", num);
        Integer n2 = companyRelationMapper.updateByPrimaryKeySelective(companyRelation);
        Assert.assertNotNull(n2);
        Assert.assertNotEquals("0", n2);


    }
}
