package com.dqys.business.orm.impl.company;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by mkfeng on 2016/7/12.
 */
public class CompanyTeamReMapperImplTest extends BaseTest {

    @Autowired
    private CompanyTeamReMapper companyTeamReMapper;

    @Test
    public void test() {
        CompanyTeamRe companyTeamRe = new CompanyTeamRe();
        companyTeamRe.setCreateAt(new Date());
        companyTeamRe.setAccepterId(11);
        companyTeamRe.setCompanyTeamId(22);
        companyTeamRe.setAcceptCompanyId(66);
        Integer n = companyTeamReMapper.insert(companyTeamRe);
        Assert.assertNotNull(n);
        Assert.assertNotEquals("0", n);
        companyTeamRe.setStatus(1);
        Integer n2 = companyTeamReMapper.updateByPrimaryKey(companyTeamRe);
        Assert.assertNotNull(n2);
        Assert.assertNotEquals("0", n2);
    }


}
