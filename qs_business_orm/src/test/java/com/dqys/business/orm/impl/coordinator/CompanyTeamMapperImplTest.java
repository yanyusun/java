package com.dqys.business.orm.impl.coordinator;

import com.dqys.business.orm.base.BaseTest;
import com.dqys.business.orm.mapper.coordinator.CompanyTeamMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by mkfeng on 2016/7/12.
 */
public class CompanyTeamMapperImplTest extends BaseTest {
    @Autowired
    private CompanyTeamMapper companyTeamMapper;

    @Test
    public void test() {
        CompanyTeam companyTeam = new CompanyTeam();
        companyTeam.setObjectType(110);
        companyTeam.setRemark("nihao");
        companyTeam.setCreateAt(new Date());
        companyTeam.setSenderId(11);
        companyTeam.setObjectId(22);
        Integer num = companyTeamMapper.insert(companyTeam);
        Assert.assertNotNull(num);
        Assert.assertNotEquals("0", num);
        companyTeam.setStateflag(22L);
        Integer n2 = companyTeamMapper.updateByPrimaryKey(companyTeam);
        Assert.assertNotNull(n2);
        Assert.assertNotEquals("0", n2);


    }
}
