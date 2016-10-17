package com.dqys.business.orm.impl.company;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
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
        /*CompanyRelation companyRelation = new CompanyRelation();
        companyRelation.setCompanyAId(1);
        companyRelation.setCompanyBId(2);
        Integer result = companyRelationMapper.insert(companyRelation);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, Integer.valueOf(0));

        Integer id = companyRelation.getId();
        Assert.assertNotNull(id);

        CompanyRelation companyRelation1 = companyRelationMapper.get(id);
        Assert.assertNotNull(companyRelation1);

        companyRelation1.setCompanyBId(3);
        Integer result1 = companyRelationMapper.insert(companyRelation);
        Assert.assertNotNull(result1);
        Assert.assertNotEquals(result1, Integer.valueOf(0));
        List<CompanyRelation> list = companyRelationMapper.listByCompanyId(1);
        Assert.assertNotNull(list);*/

    }
}
